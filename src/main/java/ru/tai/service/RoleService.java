package ru.tai.service;

import org.springframework.stereotype.Service;
import ru.tai.model.Role;

import java.util.List;

@Service("roleService")
public interface RoleService {

    Role findByRole(String role);

    List<Role> findAll();
}
