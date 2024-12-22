package com.team5.fandom.common.utils;

import com.team5.fandom.entity.value.Level;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Converter;

@Converter
public class LevelAttributeConverter implements AttributeConverter<Level, String> {

    @Override
    public String convertToDatabaseColumn(Level attribute) {
        return attribute.getLevel();
    }

    @Override
    public Level convertToEntityAttribute(String dbData) {
        return Level.getInstance(dbData);
    }

}
