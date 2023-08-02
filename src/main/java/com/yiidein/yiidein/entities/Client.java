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
public class Client extends Utilisateur {
	
	private String prenom;
	private String nom;
	private String genre;
	private String adresse;
	private String telephone;
	@OneToMany(mappedBy = "client")
	private List<RendezVous> rendezVous;

}
