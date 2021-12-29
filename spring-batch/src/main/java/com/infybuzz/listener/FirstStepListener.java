package com.infybuzz.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.stereotype.Component;

import static org.springframework.batch.core.ExitStatus.COMPLETED;

@Component
public class FirstStepListener implements StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {
        System.out.println("Before step " + stepExecution.getStepName());
        System.out.println("Job params " + stepExecution.getJobParameters());
        System.out.println("Job Execution Context " + stepExecution.getJobExecution().getExecutionContext());
        final ExecutionContext executionContext = stepExecution.getExecutionContext();
        System.out.println("Step Execution Context " + executionContext);
        executionContext.put("sec", "sec value");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println("After step " + stepExecution.getStepName());
        System.out.println("Job params " + stepExecution.getJobParameters());
        System.out.println("Job Execution Context " + stepExecution.getJobExecution().getExecutionContext());
        System.out.println("Step Execution Context " + stepExecution.getExecutionContext());
        return COMPLETED;
    }
}
