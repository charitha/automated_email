package com.intuit.autoemail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.ecwid.mailchimp.MailChimpClient;
import com.ecwid.mailchimp.MailChimpObject;
import com.ecwid.mailchimp.method.v2_0.lists.Email;
import com.ecwid.mailchimp.method.v2_0.lists.MemberInfoData;
import com.ecwid.mailchimp.method.v2_0.lists.MemberInfoMethod;
import com.ecwid.mailchimp.method.v2_0.lists.MemberInfoResult;
import com.ecwid.mailchimp.method.v2_0.lists.SubscribeMethod;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;


@SpringBootApplication
public class AutoemailApplication {

//    public static void main(String[] args) {
//        SpringApplication.run(AutoemailApplication.class, args);
//    }
//


        public static class MergeVars extends MailChimpObject {

            @Field
            public String EMAIL, FNAME, LNAME;

            public MergeVars() {
            }

            public MergeVars(String email, String fname, String lname) {
                this.EMAIL = email;
                this.FNAME = fname;
                this.LNAME = lname;
            }
        }

        public static void main(String[] args) throws Exception {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Enter api key: ");
            String apikey = in.readLine().trim();

            System.out.print("Enter list id: ");
            String listId = in.readLine().trim();

            System.out.print("Enter email: ");
            String email = in.readLine().trim();

            // reuse the same MailChimpClient object whenever possible
            MailChimpClient mailChimpClient = new MailChimpClient();

            // Subscribe a person
            SubscribeMethod subscribeMethod = new SubscribeMethod();
            subscribeMethod.apikey = apikey;
            subscribeMethod.id = listId;
            subscribeMethod.email = new Email();
            subscribeMethod.email.email = email;
            subscribeMethod.double_optin = false;
            subscribeMethod.update_existing = true;
            subscribeMethod.merge_vars = new MergeVars(email, "fox", "studio");
            mailChimpClient.execute(subscribeMethod);

            System.out.println(email+" has been successfully subscribed to the list. Now will check it...");

            MemberInfoMethod memberInfoMethod = new MemberInfoMethod();
            memberInfoMethod.apikey = apikey;
            memberInfoMethod.id = listId;
            memberInfoMethod.emails = Arrays.asList(subscribeMethod.email);

            MemberInfoResult memberInfoResult = mailChimpClient.execute(memberInfoMethod);
            MemberInfoData data = memberInfoResult.data.get(0);
            System.out.println(data.email+"'s status is "+data.status);

            mailChimpClient.close();
        }





}
