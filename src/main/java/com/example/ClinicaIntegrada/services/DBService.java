package com.example.ClinicaIntegrada.services;

import com.example.ClinicaIntegrada.domain.Chamado;
import com.example.ClinicaIntegrada.domain.Paciente;
import com.example.ClinicaIntegrada.domain.Profissional;
import com.example.ClinicaIntegrada.domain.enums.Prioridade;
import com.example.ClinicaIntegrada.domain.enums.Status;
import com.example.ClinicaIntegrada.repositories.ChamadoRepository;
import com.example.ClinicaIntegrada.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private ChamadoRepository chamadoRepository;
    @Autowired
    private PessoaRepository pessoaRepository;

    public void instranciaDB() {

        Profissional dr1 = new Profissional(null, "Dr. Carlos", "903.347.070-56", "carlos@email.com", "123");
        Profissional dr2 = new Profissional(null, "Dr. Joao", "851.700.690-90", "joao@email.com", "123");
        Profissional dr3 = new Profissional(null, "Dr. Daniel", "809.123.370-10", "daniel@email.com", ("123"));
        Profissional dr4 = new Profissional(null, "Dr. Antonio", "031.778.600-84", "antonio@email.com", ("123"));
        Profissional dr5 = new Profissional(null, "Dra. Claudia", "137.749.160-98", "claudia@email.com", ("123"));
        Profissional dr6 = new Profissional(null, "Dra. Flavia", "768.493.240-39", "flavia@email.com", ("123"));

        Paciente pc1 = new Paciente(null, "Lara", "047.806.580-98", "lara1@email.com", ("123"));
        Paciente pc2 = new Paciente(null, "Paula", "761.117.970-50", "lara2@email.com", ("123"));
        Paciente pc3 = new Paciente(null, "Luis", "035.781.160-76", "lara3@email.com", ("123"));
        Paciente pc4 = new Paciente(null, "Caio", "189.247.040-36", "lara5@email.com", ("123"));
        Paciente pc5 = new Paciente(null, "Jose", "863.488.380-99", "lara6@email.com", ("123"));
        Paciente pc6 = new Paciente(null, "Gabriela", "424.425.450-12", "lara7@email.com", ("123"));

        Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 1", "Teste chamado 1", dr1, pc1);
        Chamado c2 = new Chamado(null, Prioridade.ALTA, Status.ABERTO, "Chamado 2", "Teste chamado 2", dr1, pc2);
        Chamado c3 = new Chamado(null, Prioridade.BAIXA, Status.ENCERRADO, "Chamado 3", "Teste chamado 3", dr2, pc3);
        Chamado c4 = new Chamado(null, Prioridade.ALTA, Status.ABERTO, "Chamado 4", "Teste chamado 4", dr3, pc3);
        Chamado c5 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 5", "Teste chamado 5", dr2, pc4);
        Chamado c6 = new Chamado(null, Prioridade.BAIXA, Status.ENCERRADO, "Chamado 6", "Teste chamado 6", dr1, pc5);

        pessoaRepository.saveAll(Arrays.asList(dr1, dr2, dr3, dr4, dr5, dr6, pc1, pc2, pc3, pc4, pc5, pc6));
        chamadoRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6));
    }

}
