package com.example.ClinicaIntegrada.repositories;

import com.example.ClinicaIntegrada.domain.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository <Paciente,Integer> {
}
