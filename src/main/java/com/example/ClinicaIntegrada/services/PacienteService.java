package com.example.ClinicaIntegrada.services;

import com.example.ClinicaIntegrada.domain.Paciente;
import com.example.ClinicaIntegrada.domain.Pessoa;
import com.example.ClinicaIntegrada.domain.dtos.PacienteDTO;
import com.example.ClinicaIntegrada.repositories.PacienteRepository;
import com.example.ClinicaIntegrada.repositories.PessoaRepository;
import com.example.ClinicaIntegrada.services.excptions.DataIntegrityViolationException;
import com.example.ClinicaIntegrada.services.excptions.ObjectnotFoundExceotion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;
    @Autowired
    private PessoaRepository pessoaRepository;

    public Paciente findById(Integer id) {
        Optional<Paciente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectnotFoundExceotion("Objeto não encontrado! Id: " + id));
    }

    public List<Paciente> findAll() {
        return repository.findAll();
    }

    public Paciente create(PacienteDTO objDTO) {
        objDTO.setId(null);
        objDTO.setSenha(objDTO.getSenha());
        validaPorCpfEEmail(objDTO);
        Paciente newObj = new Paciente(objDTO);
        return repository.save(newObj);

    }

    public Paciente update(Integer id, @Valid PacienteDTO objDTO) {
        objDTO.setId(id);
        Paciente oldObj = findById(id);

        if (!objDTO.getSenha().equals(oldObj.getSenha()))
            objDTO.setSenha(objDTO.getSenha());

        validaPorCpfEEmail(objDTO);
        oldObj = new Paciente(objDTO);
        return repository.save(oldObj);
    }

    public void delete(Integer id) {
        Paciente obj = findById(id);
        if (obj.getChamados().size() > 0) {
            throw new DataIntegrityViolationException("Paciente possui ordens de serviço, nao pode ser deletado. ");
        }
        repository.deleteById(id);
    }

    private void validaPorCpfEEmail(PacienteDTO objDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");

        }
        obj = pessoaRepository.findByEmail(objDTO.getEmail());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("Email já cadastrado no sistema!");
        }
    }
}