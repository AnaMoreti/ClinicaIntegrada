package com.example.ClinicaIntegrada.repositories;

import com.example.ClinicaIntegrada.domain.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {
    @Override
    Optional<Chamado> findById(Integer id);



}
