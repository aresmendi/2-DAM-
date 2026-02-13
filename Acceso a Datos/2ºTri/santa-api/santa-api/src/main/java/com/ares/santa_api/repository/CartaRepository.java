package com.ares.santa_api.repository;

import com.ares.santa_api.entity.Carta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartaRepository extends JpaRepository<Carta, Long> {
    List<Carta> findByAsistente_Id(String asistenteId);
}
