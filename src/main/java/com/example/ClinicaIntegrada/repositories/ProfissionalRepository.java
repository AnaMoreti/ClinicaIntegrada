package com.example.ClinicaIntegrada.repositories;

import com.example.ClinicaIntegrada.domain.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfissionalRepository extends JpaRepository <Profissional, Integer> {
}
