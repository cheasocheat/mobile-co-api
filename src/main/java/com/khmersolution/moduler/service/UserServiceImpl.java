package com.khmersolution.moduler.service;

import com.khmersolution.moduler.domain.Role;
import com.khmersolution.moduler.domain.User;
import com.khmersolution.moduler.repository.RoleRepository;
import com.khmersolution.moduler.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Vannaravuth Yo
 * Date : 8/24/2017, 2:23 PM
 * Email : ravuthz@gmail.com
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<User> getAll() {
        return (List<User>) repository.findAll();
    }

    @Override
    public Page<User> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public User getById(Serializable id) {
        return repository.findOne((Long) id);
    }

    @Override
    public User save(User entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(Serializable id) {
        repository.delete((Long) id);
    }

    public Page<User> getUsersByRole(Role role, Pageable pageable) {
        return repository.findAllByRolesEquals(Arrays.asList(role), pageable);
    }

    public Page<User> getUsersByRoleContains(Role role, Pageable pageable) {
        return repository.findAllByRolesContains(Arrays.asList(role), pageable);
    }

    public Page<User> getUsersByRoleName(String name, Pageable pageable) {
        Role role = roleRepository.findByName(name);
        if (role != null) {
            return repository.findAllByRolesEquals(Arrays.asList(role), pageable);
        }
        return null;
    }

    public Page<User> getUsersByRoleNameContains(String name, Pageable pageable) {
        Role role = roleRepository.findByName(name);
        if (role != null) {
            return getUsersByRoleContains(role, pageable);
        }
        return null;
    }
}
