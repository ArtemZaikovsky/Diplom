package com.degree.GraduateWork.service.interfaces;

import com.degree.GraduateWork.models.Role;

import java.util.Set;

public interface RoleService {
    void createRoles(Set<Role> roles);
    Set<Role> getAllRoles();
}
