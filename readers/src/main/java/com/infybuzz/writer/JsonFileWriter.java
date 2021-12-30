package com.infybuzz.writer;

import com.infybuzz.model.Student;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.json.JacksonJsonObjectMarshaller;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class JsonFileWriter extends JsonFileItemWriter<Student> {

    public JsonFileWriter(@Value("#{jobParameters['outputJson']}") FileSystemResource resource) {
        super(resource, new JacksonJsonObjectMarshaller<>());
    }
}
