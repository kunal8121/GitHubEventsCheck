package com.Practise.practiseCode.runner;

import com.Practise.practiseCode.service.GitHubEventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class GitHubEventsRunner implements CommandLineRunner {

    @Autowired
    private GitHubEventsService gitHubEventsService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("=== CommandLineRunner started ===");
        System.out.println("Arguments received: " + args.length);

        for (int i = 0; i < args.length; i++) {
            System.out.println("Arg[" + i + "]: " + args[i]);
        }

        if(args.length == 0){
            System.out.println("Usage : java -jar app.jar <github-username>");
            return;
        }

        String username = args[0];
        System.out.println("Processing username: " + username);
        System.out.println("Calling gitHubEventsService.fetchAndDisplay()...");

        try {
            gitHubEventsService.fetchAndDisplay(username);
        } catch (Exception e) {
            System.out.println("Exception in fetchAndDisplay: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("=== CommandLineRunner completed ===");
    }
}