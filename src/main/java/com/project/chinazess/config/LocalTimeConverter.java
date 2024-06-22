package com.project.chinazess.config;

import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.stereotype.Component;

import java.time.LocalTime;


/*@Component
    @ConfigurationPropertiesBinding
    public class LocalTimeConverter implements Converter<String, LocalTime> {
        @Override
        public LocalTime convert(String source) {
            if(source==null){
                return null;
            }
            return LocalTime.parse(source, DateTimeFormatter.ofPattern("HH:mm:ss"));
        }
    }

*/