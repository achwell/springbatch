package com.infybuzz.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.List.of;

@Component
public class FirstItemReader implements ItemReader<Integer> {

    List<Integer> list = of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    int i = 0;

    @Override
    public Integer read() throws Exception {
        System.out.println("Inside Item Reader");
        Integer item;
        if(i < list.size()) {
            item = list.get(i);
            i++;
            return item;
        }
        i = 0;
        return null;
    }
}
