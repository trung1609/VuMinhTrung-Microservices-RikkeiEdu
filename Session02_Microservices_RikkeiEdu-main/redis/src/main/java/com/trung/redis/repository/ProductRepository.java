package com.trung.redis.repository;

import com.trung.redis.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM product WHERE description = :desc LIMIT 1", nativeQuery = true)
    Optional<Product> findByDescriptionNative(@Param("desc") String desc);
}
