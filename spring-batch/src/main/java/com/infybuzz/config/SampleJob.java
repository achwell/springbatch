package com.infybuzz.config;

import com.infybuzz.listener.FirstJobListener;
import com.infybuzz.listener.FirstStepListener;
import com.infybuzz.processor.FirstItemProcessor;
import com.infybuzz.reader.FirstItemReader;
import com.infybuzz.service.FirstTasklet;
import com.infybuzz.service.SecondTasklet;
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
    private final SecondTasklet secondTasklet;
    private final FirstTasklet firstTasklet;
    private final FirstJobListener firstJobListener;
    private final FirstStepListener firstStepListener;
    private final FirstItemReader firstItemReader;
    private final FirstItemProcessor firstItemProcessor;
    private final FirstItemWriter firstItemWriter;

    public SampleJob(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, SecondTasklet secondTasklet, FirstTasklet firstTasklet, FirstJobListener firstJobListener, FirstStepListener firstStepListener, FirstItemReader firstItemReader, FirstItemProcessor firstItemProcessor, FirstItemWriter firstItemWriter) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.secondTasklet = secondTasklet;
        this.firstTasklet = firstTasklet;
        this.firstJobListener = firstJobListener;
        this.firstStepListener = firstStepListener;
        this.firstItemReader = firstItemReader;
        this.firstItemProcessor = firstItemProcessor;
        this.firstItemWriter = firstItemWriter;
    }

    @Bean
    public Job taskletJob() {
        return jobBuilderFactory
                .get("First Job")
                .incrementer(new RunIdIncrementer())
                .start(firstTaskletStep())
                .next(secondTaskletStep())
                .listener(firstJobListener)
                .build();
    }

    @Bean
    public Job chunkJob() {
        return jobBuilderFactory
                .get("Chunk Job")
                .incrementer(new RunIdIncrementer())
                .start(firstChunkStep())
                .build();
    }

    @Bean
    public Job chunkJobWithoutProcessor() {
        return jobBuilderFactory
                .get("Chunk Job Without Processor")
                .incrementer(new RunIdIncrementer())
                .start(secondChunkStep())
                .build();
    }

    @Bean
    public Job combinedJob() {
        return jobBuilderFactory
                .get("Combined Job")
                .incrementer(new RunIdIncrementer())
                .start(firstChunkStep())
                .next(firstTaskletStep())
                .build();
    }

    private Step firstTaskletStep() {
        return stepBuilderFactory
                .get("First Step")
                .tasklet(firstTasklet)
                .listener(firstStepListener)
                .build();
    }

    private Step secondTaskletStep() {
        return stepBuilderFactory
                .get("Second Step")
                .tasklet(secondTasklet)
                .build();
    }

    private Step firstChunkStep() {
        return stepBuilderFactory
                .get("First Chunk Step")
                .<Integer, Long>chunk(3)
                .reader(firstItemReader)
                .processor(firstItemProcessor)
                .writer(firstItemWriter)
                .build();
    }
    private Step secondChunkStep() {
        return stepBuilderFactory
                .get("First Chunk Step")
                .<Integer, Long>chunk(3)
                .reader(firstItemReader)
                .writer(firstItemWriter)
                .build();
    }
}
