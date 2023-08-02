package com.yiidein.yiidein.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor @AllArgsConstructor
public class Utilisateur {

	@Id
	private String id;
	private String login;
	private String password;
}
