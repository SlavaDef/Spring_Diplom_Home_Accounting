package com.project.chinazess.config;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.LocalTime;

@Converter(autoApply = true)
public class LocalTimeConverte implements AttributeConverter<LocalTime, String> {

    @Override
    public String convertToDatabaseColumn(LocalTime time) {
        return time.toString();
    }

    @Override
    public LocalTime convertToEntityAttribute(String time) {
        return LocalTime.parse(time);
    }
}
