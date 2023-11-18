package com.pharmacy.system.store.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.pharmacy.system.store.domain.model.Role;

/**
 * RoleRepository
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

  @Query("SELECT r FROM roles r WHERE r.id IN :ids")
  public List<Role> findByIdIn(List<Long> ids);
}
