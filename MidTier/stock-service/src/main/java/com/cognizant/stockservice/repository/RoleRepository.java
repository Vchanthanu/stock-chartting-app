package com.cognizant.stockservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.stockservice.bean.Role;
/**
 * @author 805831
 *
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
