package com.bdqn.conlletion;

import com.bdqn.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoleDao {
    List<Role> getAll();
}
