package com.khmersolution.moduler.service;

import com.khmersolution.moduler.domain.Role;
import com.khmersolution.moduler.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Vannaravuth Yo
 * Date : 8/24/2017, 2:27 PM
 * Email : ravuthz@gmail.com
 */

public interface UserService extends CRUDService<User> {
    Page<User> getUsersByRole(Role role, Pageable pageable);
    Page<User> getUsersByRoleName(String name, Pageable pageable);
    Page<User> getUsersByRoleContains(Role role, Pageable pageable);
    Page<User> getUsersByRoleNameContains(String name, Pageable pageable);
}
