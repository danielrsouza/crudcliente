package com.devsuperior.crudcliente.repositories;

import com.devsuperior.crudcliente.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {}
