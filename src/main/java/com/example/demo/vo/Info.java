package com.example.demo.vo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@PropertySource("classpath:/info.properties")
public class Info implements Serializable {

    private String semail;
    private String sqm;
    private String host;
    private String remail;
    private String sender;
    private String subject;

}
