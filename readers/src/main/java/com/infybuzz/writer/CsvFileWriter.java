package com.infybuzz.writer;

import com.infybuzz.model.Student;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@StepScope
public class CsvFileWriter extends FlatFileItemWriter<Student> {

    public CsvFileWriter(@Value("#{jobParameters['outputFile']}") FileSystemResource resource) {
        super();
        setResource(resource);
        setHeaderCallback(writer -> writer.write("ID, First Name, Last Name, Email"));
        final DelimitedLineAggregator<Student> lineAggregator = new DelimitedLineAggregator<>();
        final BeanWrapperFieldExtractor<Student> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(new String[] {"id", "firstName", "lastName", "email"});
        lineAggregator.setFieldExtractor(fieldExtractor);
        setLineAggregator(lineAggregator);
        setFooterCallback(writer -> writer.write("Created @ " + new Date()));
    }
}
