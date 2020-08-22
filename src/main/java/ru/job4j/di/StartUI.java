package ru.job4j.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component
public class StartUI {
    @Autowired
    private Store store;
    @Autowired
    private ConsoleInput consoleInput;


    public void add(String value) {
        store.add(value);
    }

    public void addToConsole(String value) {
        consoleInput.addToList(value);
    }

    public void print() {
        for (String value : store.getAll()) {
            System.out.println(value);
        }
        for (String value : consoleInput.getList()) {
            System.out.println(value);
        }
    }

}
