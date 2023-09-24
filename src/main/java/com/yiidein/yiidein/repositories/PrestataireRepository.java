package com.yiidein.yiidein.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yiidein.yiidein.entities.Prestataire;

public interface PrestataireRepository extends JpaRepository<Prestataire, String> {

	List<Prestataire> findByServiceId(Long idService);
	
}
