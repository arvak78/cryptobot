package com.core;
import com.commons.exceptions.ExchangeException;
import com.commons.exceptions.MarshallException;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerBean implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(CommandLineRunnerBean.class);

    @Autowired
    private Starter markets;

    public void run(String... args) throws ExchangeException {
    	String strArgs = Arrays.stream(args).collect(Collectors.joining("|"));
        try {
            markets.initConfig();
        } catch (MarshallException e) {
            e.printStackTrace();
        }
        logger.info("Application started with arguments:" + strArgs);
    }

}