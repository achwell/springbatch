package com.infybuzz.reader;

import com.infybuzz.model.Student;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class StudentCsvReader extends FlatFileItemReader<Student> {

    public StudentCsvReader(@Value("#{jobParameters['inputFile']}") ClassPathResource resource) {
        super();

        setResource(resource);

        final DefaultLineMapper<Student> lineMapper = new DefaultLineMapper<>();

        final DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames("ID", "First Name", "Last Name", "Email");
        tokenizer.setDelimiter("|");

        final BeanWrapperFieldSetMapper<Student> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Student.class);

        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        setLinesToSkip(1);

        setLineMapper(lineMapper);
    }
}
