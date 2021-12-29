package com.infybuzz.reader;

import com.infybuzz.model.Student;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class StudentJdbcReader extends JdbcCursorItemReader<Student> {
    public StudentJdbcReader(@Qualifier("universitydatasource") DataSource dataSource) {
        super();
        setDataSource(dataSource);
        setSql("select id, first_name as firstName, last_name as lastName, email from student");
        final BeanPropertyRowMapper<Student> rowMapper = new BeanPropertyRowMapper<>();
        rowMapper.setMappedClass(Student.class);
        setRowMapper(rowMapper);
    }
}
