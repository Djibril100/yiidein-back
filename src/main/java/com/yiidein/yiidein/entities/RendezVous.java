package com.yiidein.yiidein.entities;

import java.util.Date;

import com.yiidein.yiidein.enums.StatusDesRendezVous;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
public class RendezVous {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String motif;
	private String note;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateDeDebut;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateDeFin;
	@Enumerated(EnumType.STRING)
	private StatusDesRendezVous status;
	@ManyToOne
	private Client client;
	@ManyToOne
	private Prestataire prestataire;
}
