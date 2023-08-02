package com.yiidein.yiidein.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
public class Service {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String adresse;
	private String telephone;
	private String description;
	@OneToMany(mappedBy = "service")
	private List<Prestataire> prestataires;
}
