package com.infybuzz.processor;

import com.infybuzz.model.Student;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class StudentProcessor implements ItemProcessor<Student, Student> {
    @Override
    public Student process(Student student) throws Exception {
        final Student response = new Student();
        response.setId(student.getId());
        response.setFirstName(student.getFirstName().toUpperCase());
        response.setLastName(student.getLastName().toUpperCase());
        response.setEmail(student.getEmail().toLowerCase());
        return response;
    }
}
