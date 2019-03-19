package ru.tai.service;

import org.springframework.stereotype.Service;
import ru.tai.model.Role;

@Service("roleService")
public interface RoleService {

    Role findByRole(String role);
}
