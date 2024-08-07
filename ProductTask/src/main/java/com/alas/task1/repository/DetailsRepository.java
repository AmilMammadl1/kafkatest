package com.alas.task1.repository;

import com.alas.task1.model.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailsRepository extends JpaRepository<ProductDetails,Integer> {
}
