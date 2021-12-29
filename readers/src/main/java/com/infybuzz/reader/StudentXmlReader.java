package com.infybuzz.reader;

import com.infybuzz.model.Student;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Component
@StepScope
public class StudentXmlReader extends StaxEventItemReader<Student> {
    public StudentXmlReader(@Value("#{jobParameters['inputXml']}") ClassPathResource resource) {
        super();
        setResource(resource);
        setFragmentRootElementName("student");
        final Jaxb2Marshaller unmarshaller = new Jaxb2Marshaller();
        unmarshaller.setClassesToBeBound(Student.class);
        setUnmarshaller(unmarshaller);
    }
}
