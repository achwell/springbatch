package com.infybuzz.service;

import com.infybuzz.model.Student;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class StudentService {

    private final WebClient webClient;

    public StudentService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8081/api/v1/").build();
    }

    private List<Student> list;

    public List<Student> restCallToGetStudents() {
        list = webClient.get().uri("students").retrieve().bodyToFlux(List.class).blockFirst();
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

    public Student restCallToCreateStudent(Student student) {
        return webClient.post().uri("createStudent").body(Mono.just(student), Student.class).retrieve().bodyToMono(Student.class).block();
    }
}
