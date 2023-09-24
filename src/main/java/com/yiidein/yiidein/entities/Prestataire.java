package com.yiidein.yiidein.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
public class Prestataire extends Utilisateur {

	@Id
	private String id;
	private String nom;
	@OneToMany(mappedBy = "prestataire")
	private List<RendezVous> rendezVous;
	@ManyToOne
	private Service_ service;
}
