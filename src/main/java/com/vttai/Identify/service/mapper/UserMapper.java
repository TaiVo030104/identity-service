package com.vttai.Identify.service.mapper;

import com.vttai.Identify.service.dto.request.UserCreateRequest;
import com.vttai.Identify.service.dto.request.UserUpdateRequest;
import com.vttai.Identify.service.dto.response.UserResponse;
import com.vttai.Identify.service.entity.User;
import com.vttai.Identify.service.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreateRequest request);

    UserResponse toUserResponse(User user);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}