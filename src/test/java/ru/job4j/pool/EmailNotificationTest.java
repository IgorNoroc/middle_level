package ru.job4j.pool;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class EmailNotificationTest {
    private EmailSentEmulationTest emulationTest = new EmailSentEmulationTest();
    private ByteArrayOutputStream out = new ByteArrayOutputStream();
    private PrintStream std = System.out;

    @Before
    public void setOut() {
        System.setOut(new PrintStream(out));
    }

    @After
    public void setOldOut() {
        System.setOut(std);
    }

    @Test
    public void whenHave3Users() throws InterruptedException {
        User first = new User("user1", "user1.com");
        User second = new User("user2", "user2.com");
        User third = new User("user3", "user3.com");
        emulationTest.emailTo(first);
        Thread.sleep(50);
        emulationTest.emailTo(second);
        Thread.sleep(50);
        emulationTest.emailTo(third);
        Thread.sleep(50);
        emulationTest.close();
        assertThat(out.toString(), is(new StringJoiner(System.lineSeparator())
                .add("Notification {user1} to email {user1.com}. : Add a new event to {user1} : user1.com")
                .add("Notification {user2} to email {user2.com}. : Add a new event to {user2} : user2.com")
                .add("Notification {user3} to email {user3.com}. : Add a new event to {user3} : user3.com")
                .add("")
                .toString())
        );
    }
}