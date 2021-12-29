package com.infybuzz.service;

import com.infybuzz.model.Student;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class StudentService {

    private List<Student> list;

    public List<Student> restCallToGetStudents() {
        RestTemplate restTemplate = new RestTemplate();
        Student[] studentResponseArray = restTemplate.getForObject("http://localhost:8081/api/v1/students", Student[].class);

        list = new ArrayList<>();

        if(studentResponseArray != null) {
            Collections.addAll(list, studentResponseArray);
        }

        return list;
    }

    public Student getStudent() {
        if (list == null) {
            restCallToGetStudents();
        }
        if (list != null && !list.isEmpty()) {
            return list.remove(0);
        }
        return null;
    }
}
