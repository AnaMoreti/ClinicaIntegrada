package com.example.ClinicaIntegrada.services;

import com.example.ClinicaIntegrada.domain.Chamado;
import com.example.ClinicaIntegrada.domain.Paciente;
import com.example.ClinicaIntegrada.domain.Profissional;
import com.example.ClinicaIntegrada.domain.dtos.ChamadoDTO;
import com.example.ClinicaIntegrada.domain.enums.Prioridade;
import com.example.ClinicaIntegrada.domain.enums.Status;
import com.example.ClinicaIntegrada.repositories.ChamadoRepository;
import com.example.ClinicaIntegrada.services.excptions.ObjectnotFoundExceotion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {
    @Autowired
    private ChamadoRepository repository;
    @Autowired
    private ProfissionalService profissionalService;
    @Autowired
    private PacienteService pacienteService;

    public Chamado findById(Integer id) {
        Optional<Chamado> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectnotFoundExceotion("Objeto nao encontrado! ID: " + id));
    }

    public List<Chamado> findAll() {
        return repository.findAll();
    }

    public Chamado create(ChamadoDTO obj) {
        return repository.save(newChamado(obj));
    }

    public Chamado update(Integer id, @Valid ChamadoDTO objDTO) {
        objDTO.setId(id);
        Chamado oldObj = findById(id);
        oldObj = newChamado(objDTO);
        return repository.save(oldObj);
    }

    private Chamado newChamado(ChamadoDTO obj) {
        Profissional profissional = profissionalService.findById(obj.getProfissional());
        Paciente paciente = pacienteService.findById(obj.getPaciente());

        Chamado chamado = new Chamado();
        if (obj.getId() != null) {
            chamado.setId(obj.getId());
        }
        if (obj.getStatus().equals(2)) {
            chamado.setDataFechamento(LocalDate.now());
        }
        chamado.setProfissional(profissional);
        chamado.setPaciente(paciente);
        chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
        chamado.setStatus(Status.toEnum(obj.getStatus()));
        chamado.setTitulo(obj.getTitulo());
        chamado.setObservacoes(obj.getObservacoes());
        return chamado;
    }
}
