package com.infybuzz.reader;

import com.infybuzz.model.Student;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class StudentJsonReader extends JsonItemReader<Student> {

    public StudentJsonReader(@Value("#{jobParameters['inputJson']}") ClassPathResource resource) {
        super(resource, new JacksonJsonObjectReader<>(Student.class));
        setMaxItemCount(8);
    }
}
