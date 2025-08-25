package com.vttai.Identify.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.vttai.Identify.service.dto.request.RoleRequest;
import com.vttai.Identify.service.dto.response.RoleResponse;
import com.vttai.Identify.service.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role Role);
}
