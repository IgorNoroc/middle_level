package ru.job4j.di;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConsoleInput {
    private List<String> list = new ArrayList<>();

    public void addToList(String value) {
        list.add(value);
    }

    public List<String> getList() {
        return list;
    }
}
