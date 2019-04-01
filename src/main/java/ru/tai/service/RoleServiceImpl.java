package ru.tai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tai.model.Role;
import ru.tai.repository.RoleRepository;

import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findByRole(String role) {
        return roleRepository.findByRole(role);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
