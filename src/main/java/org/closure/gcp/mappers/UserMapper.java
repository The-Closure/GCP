package org.closure.gcp.mappers;

import org.closure.gcp.entities.UserEntity;
import org.closure.gcp.models.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );
    

    UserModel UserEntityToUserModel(UserEntity user);
    
    // @Mapping(target = "gender", expression = "java(org.closure.gcp.models.Gender.valueOf(gender))")
    // @Mapping(target = "college", expression = "java(new org.closure.gcp.entities.CollegeEntity().withCollegeName(college))")
    // UserEntity UserModelToUserEntity(UserModel user);
}
