package com.epam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.entity.Batch;

public interface BatchRepository extends JpaRepository<Batch, Integer>{

}
