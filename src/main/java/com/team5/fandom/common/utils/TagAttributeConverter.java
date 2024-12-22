package com.team5.fandom.common.utils;

import com.team5.fandom.entity.value.Tag;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Converter;

@Converter
public class TagAttributeConverter implements AttributeConverter<Tag, String> {

    @Override
    public String convertToDatabaseColumn(Tag attribute) {
        return attribute.getHashTag();
    }

    @Override
    public Tag convertToEntityAttribute(String dbData) {
        return Tag.getInstance(dbData);
    }

}