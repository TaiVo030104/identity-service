package com.vttai.Identify.service.mapper;

import org.mapstruct.Mapper;

import com.vttai.Identify.service.dto.request.PermissionRequest;
import com.vttai.Identify.service.dto.response.PermissionResponse;
import com.vttai.Identify.service.entity.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
