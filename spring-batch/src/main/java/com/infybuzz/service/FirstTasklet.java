package com.infybuzz.service;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Service;

import static org.springframework.batch.repeat.RepeatStatus.FINISHED;

@Service
public class FirstTasklet implements Tasklet {

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        System.out.println("This is first tasklet step");
        System.out.println("Job Execution Context " + chunkContext.getStepContext().getJobExecutionContext());
        return FINISHED;

    }
}
