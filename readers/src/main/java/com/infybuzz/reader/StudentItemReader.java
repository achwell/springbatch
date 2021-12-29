package com.infybuzz.reader;

import com.infybuzz.model.Student;
import com.infybuzz.service.StudentService;
import org.springframework.batch.item.adapter.ItemReaderAdapter;
import org.springframework.stereotype.Component;

@Component
public class StudentItemReader extends ItemReaderAdapter<Student> {

    public StudentItemReader(StudentService studentService) {
        super();
        setTargetObject(studentService);
        setTargetMethod("getStudent");
    }
}
