package com.vttai.Identify.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vttai.Identify.service.entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {}
