package com.infybuzz.writer;

import com.infybuzz.model.Student;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class JdbcWriter extends JdbcBatchItemWriter<Student> {
    public JdbcWriter(@Qualifier("datasource") DataSource dataSource) {
        super();
        setDataSource(dataSource);
        setSql("insert into student(id, first_name, last_name, email) values (:id, :firstName, :lastName, :email)");
        setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
    }
}
