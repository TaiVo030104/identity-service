package com.vttai.Identify.service.mapper;
import com.vttai.Identify.service.dto.request.RoleRequest;
import com.vttai.Identify.service.dto.request.UserCreateRequest;
import com.vttai.Identify.service.dto.request.UserUpdateRequest;
import com.vttai.Identify.service.dto.response.RoleResponse;
import com.vttai.Identify.service.dto.response.UserResponse;
import com.vttai.Identify.service.entity.Role;
import com.vttai.Identify.service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface  RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);
    RoleResponse toRoleResponse(Role Role);
}