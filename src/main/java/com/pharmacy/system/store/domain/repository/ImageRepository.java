package com.pharmacy.system.store.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pharmacy.system.store.domain.model.Image;

/**
 * ImageRepository
 */
@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

}
