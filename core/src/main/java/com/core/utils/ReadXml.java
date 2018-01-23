package com.core.utils;

import org.slf4j.Logger;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
 
@Configuration
public class ReadXml {

    @Autowired
    private static Logger log; // = LoggerFactory.getLogger(CurrencyService.class);

    public static OperationCurrencies readOperatingCurrencies() {
        OperationCurrencies operationCurrencies = null;
        ReadXml readXml = new ReadXml();
        StaxEventItemReader<OperationCurrencies> reader = readXml.xmlFileItemReader();
        ExecutionContext executionContext = new ExecutionContext();
        reader.open(executionContext);
        try {
            operationCurrencies = reader.read();
        } catch (Exception e) {
            log.error("Error al recuperar operating currencies");
            operationCurrencies = new OperationCurrencies();
        }
        return operationCurrencies;
    }

    @Bean
    private static StaxEventItemReader<OperationCurrencies> xmlFileItemReader() {
        StaxEventItemReader<OperationCurrencies> xmlFileReader = new StaxEventItemReader<>();
        xmlFileReader.setResource(new ClassPathResource("currencies.xml"));
        xmlFileReader.setFragmentRootElementName("currencies");

        Jaxb2Marshaller studentMarshaller = new Jaxb2Marshaller();
        studentMarshaller.setClassesToBeBound(OperationCurrencies.class);
        xmlFileReader.setUnmarshaller(studentMarshaller);

        return xmlFileReader;
    }

}