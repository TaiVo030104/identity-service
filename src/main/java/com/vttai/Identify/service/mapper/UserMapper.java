package com.vttai.Identify.service.mapper;
import com.vttai.Identify.service.dto.request.UserCreateRequest;
import com.vttai.Identify.service.dto.request.UserUpdateRequest;
import com.vttai.Identify.service.dto.response.UserResponse;
import com.vttai.Identify.service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface  UserMapper {
    User toUser(UserCreateRequest request);
    UserResponse toUserResponse(User user);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}