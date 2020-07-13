package ru.job4j.pool;

public class EmailSentEmulationTest extends EmailNotification {
    @Override
    public void send(String subject, String body, String email) {
        System.out.println(String.format(
                "%s : %s : %s",
                subject, body, email
        ));
    }
}
