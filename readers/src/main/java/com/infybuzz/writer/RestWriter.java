package com.infybuzz.writer;

import com.infybuzz.model.Student;
import com.infybuzz.service.StudentService;
import org.springframework.batch.item.adapter.ItemWriterAdapter;
import org.springframework.stereotype.Component;

@Component
public class RestWriter extends ItemWriterAdapter<Student> {

    public RestWriter(StudentService studentService) {
        super();
        setTargetObject(studentService);
        setTargetMethod("restCallToCreateStudent");
    }
}
