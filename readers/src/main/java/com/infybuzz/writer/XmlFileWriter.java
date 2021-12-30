package com.infybuzz.writer;

import com.infybuzz.model.Student;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class XmlFileWriter extends StaxEventItemWriter<Student> {
    public XmlFileWriter(@Value("#{jobParameters['outputXml']}") FileSystemResource resource) {
        super();
        setResource(resource);
        setRootTagName("students");
        final Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(Student.class);
        setMarshaller(marshaller);
    }
}
