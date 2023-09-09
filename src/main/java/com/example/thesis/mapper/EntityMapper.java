package com.example.thesis.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EntityMapper extends UserMapper,TeacherMapper,StudentMapper,
        RoleMapper,DocumentMapper,StudentTeacherRequestMapper {
}
