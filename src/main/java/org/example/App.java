package org.example;

import org.example.actor.Moderator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App implements CommandLineRunner
{
    @Autowired
    private Moderator moderator;

    public static void main(String[] args) {
        new SpringApplicationBuilder(App.class)
                .headless(false)
                .build()
                .run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        moderator.start();
    }
}
