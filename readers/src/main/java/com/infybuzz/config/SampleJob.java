package com.infybuzz.config;

import com.infybuzz.model.Student;
import com.infybuzz.processor.FirstItemProcessor;
import com.infybuzz.reader.*;
import com.infybuzz.writer.FirstItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SampleJob {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final StudentCsvReader studentCsvReader;
    private final StudentJsonReader studentJsonReader;
    private final StudentXmlReader studentXmlReader;
    private final StudentJdbcReader studentJdbcReader;
    private final StudentItemReader studentItemReader;
    private final FirstItemProcessor firstItemProcessor;
    private final FirstItemWriter firstItemWriter;

    public SampleJob(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, StudentCsvReader studentCsvReader, StudentJsonReader studentJsonReader, StudentXmlReader studentXmlReader, StudentJdbcReader studentJdbcReader, StudentItemReader studentItemReader, FirstItemProcessor firstItemProcessor, FirstItemWriter firstItemWriter) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.studentCsvReader = studentCsvReader;
        this.studentJsonReader = studentJsonReader;
        this.studentXmlReader = studentXmlReader;
        this.studentJdbcReader = studentJdbcReader;
        this.studentItemReader = studentItemReader;
        this.firstItemProcessor = firstItemProcessor;
        this.firstItemWriter = firstItemWriter;
    }


    @Bean
    public Job chunkJob() {
        return jobBuilderFactory
                .get("Chunk Job")
                .incrementer(new RunIdIncrementer())
                .start(firstChunkStep())
                .build();
    }

    private Step firstChunkStep() {
        return stepBuilderFactory
                .get("First Chunk Step")
                .<Student, Student>chunk(3)
                .reader(studentItemReader)
                //.processor(firstItemProcessor)
                .writer(firstItemWriter)
                .build();
    }
}