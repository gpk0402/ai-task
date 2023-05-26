package com.epam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.entity.Associate;

public interface AssociateRepository extends JpaRepository<Associate, Integer>{
	List<Associate> findAllByGender(String gender);

}
