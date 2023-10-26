package com.reservaHoteles.hoteles.repositories;

import com.reservaHoteles.hoteles.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}