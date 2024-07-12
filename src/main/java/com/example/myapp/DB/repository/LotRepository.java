package com.example.myapp.DB.repository;

import com.example.myapp.DB.model.Lot;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LotRepository extends JpaRepository<Lot, Long> {
}

