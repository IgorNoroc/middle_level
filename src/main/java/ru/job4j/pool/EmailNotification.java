package ru.job4j.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 2. ExecutorService рассылка почты. [#318299].
 */
public class EmailNotification {
    private final ExecutorService service;

    /**
     * When create an instance, ExecutorService start working.
     */
    public EmailNotification() {
        service = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );
    }

    /**
     * Create a task for ExecutorService.
     *
     * @param user user.
     */
    public void emailTo(User user) {
        String subject = String.format("Notification {%s} to email {%s}.", user.getUsername(), user.getEmail());
        String body = String.format("Add a new event to {%s}", user.getUsername());
        service.submit(
                () -> send(subject, body, user.getEmail())
        );
    }

    /**
     * Sending an email to User.
     *
     * @param subject subject.
     * @param body    text body.
     * @param email   email.
     */
    public void send(String subject, String body, String email) {

    }

    /**
     * Shutdown our ExecutorService.
     */
    public void close() {
        service.shutdown();
    }
}
