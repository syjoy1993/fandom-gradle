package com.team5.fandom.common.utils;

import com.team5.fandom.entity.value.Role;

import jakarta.persistence.AttributeConverter;

public class RoleAttributeConverter implements AttributeConverter<Role, String> {

	@Override
	public String convertToDatabaseColumn(Role attribute) {
		// TODO Auto-generated method stub
		return attribute.getRoleName();
	}

	@Override
	public Role convertToEntityAttribute(String dbData) {
		// TODO Auto-generated method stub
		return Role.getInstance(dbData);
	}


}
