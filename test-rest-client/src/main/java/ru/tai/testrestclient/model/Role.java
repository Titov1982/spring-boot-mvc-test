package ru.tai.testrestclient.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@JsonIgnoreProperties
public class Role implements GrantedAuthority {

    private Long id;

    private String role;

    private List<User> users = new ArrayList<>();

    public Role() {
    }

    public Role(String role){
        this.role = role;
    }

    public Role(String role, User user) {
        this.role = role;
        addUser(user);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addUser(User user){
        users.add(user);
    }

    @Override
    public String getAuthority() {
        return role;
    }

    // Делаем только для поля role, так как наименования ролей уникальны
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role1 = (Role) o;
        return role.equals(role1.role);
    }
    // Делаем только для поля role, так как наименования ролей уникальны
    @Override
    public int hashCode() {
        return Objects.hash(role);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }
}
