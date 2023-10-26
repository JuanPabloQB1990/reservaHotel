package com.reservaHoteles.hoteles.repositories;

import com.reservaHoteles.hoteles.models.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Integer> {
}
