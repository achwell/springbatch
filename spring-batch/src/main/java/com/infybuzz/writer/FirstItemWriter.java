package com.infybuzz.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FirstItemWriter implements ItemWriter<Number> {

    @Override
    public void write(List<? extends Number> items) throws Exception {
        System.out.println("Inside Item Writer");
        items.forEach(System.out::println);
    }
}
