package com.vttai.Identify.service.mapper;
import com.vttai.Identify.service.dto.request.PermissionRequest;
import com.vttai.Identify.service.dto.request.UserCreateRequest;
import com.vttai.Identify.service.dto.request.UserUpdateRequest;
import com.vttai.Identify.service.dto.response.PermissionResponse;
import com.vttai.Identify.service.dto.response.UserResponse;
import com.vttai.Identify.service.entity.Permission;
import com.vttai.Identify.service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}