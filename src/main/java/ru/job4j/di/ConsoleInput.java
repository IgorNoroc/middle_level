package ru.job4j.di;

import java.util.ArrayList;
import java.util.List;

public class ConsoleInput {
    private List<String> list = new ArrayList<>();

    public void addToList(String value) {
        list.add(value);
    }

    public List<String> getList() {
        return list;
    }
}
