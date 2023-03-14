package com.rakesh.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rakesh.blog.entities.Role;

public interface RoleRepo  extends JpaRepository<Role, Integer>{

}
