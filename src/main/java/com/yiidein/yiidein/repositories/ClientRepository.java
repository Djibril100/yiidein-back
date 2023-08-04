package com.yiidein.yiidein.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yiidein.yiidein.entities.Client;

public interface ClientRepository extends JpaRepository<Client, String> {

}
