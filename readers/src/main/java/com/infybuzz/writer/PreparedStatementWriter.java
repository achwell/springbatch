package com.infybuzz.writer;

import com.infybuzz.model.Student;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class PreparedStatementWriter extends JdbcBatchItemWriter<Student> {
    public PreparedStatementWriter(@Qualifier("datasource") DataSource dataSource) {
        super();
        setDataSource(dataSource);
        setSql("insert into student(id, first_name, last_name, email) values (?, ?, ?, ?)");
        setItemPreparedStatementSetter((item, ps) -> {
            ps.setLong(1, item.getId());
            ps.setString(2, item.getFirstName());
            ps.setString(3, item.getLastName());
            ps.setString(4, item.getEmail());
        });
    }
}
