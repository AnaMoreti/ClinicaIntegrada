package com.example.ClinicaIntegrada.services;

import com.example.ClinicaIntegrada.domain.Pessoa;
import com.example.ClinicaIntegrada.domain.Profissional;
import com.example.ClinicaIntegrada.domain.dtos.ProfissionalDTO;
import com.example.ClinicaIntegrada.repositories.PessoaRepository;
import com.example.ClinicaIntegrada.repositories.ProfissionalRepository;
import com.example.ClinicaIntegrada.services.excptions.DataIntegrityViolationException;
import com.example.ClinicaIntegrada.services.excptions.ObjectnotFoundExceotion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class ProfissionalService {
    @Autowired
    private ProfissionalRepository repository;
    @Autowired
    private PessoaRepository pessoaRepository;

    public Profissional findById(Integer id) {
        Optional<Profissional> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectnotFoundExceotion("Objeto não encontrado! Id: " + id));
    }

    public List<Profissional> findAll() {
        return repository.findAll();
    }

    public Profissional create(ProfissionalDTO objDTO) {
        objDTO.setId(null);
        objDTO.setSenha(objDTO.getSenha());
        validaPorCpfEEmail(objDTO);
        Profissional newObj = new Profissional(objDTO);
        return repository.save(newObj);
    }

    public Profissional update(Integer id, @Valid ProfissionalDTO objDTO) {
        objDTO.setId(id);
        Profissional oldObj = findById(id);

        if(!objDTO.getSenha().equals(oldObj.getSenha()))
            objDTO.setSenha(objDTO.getSenha());

        validaPorCpfEEmail(objDTO);
        oldObj = new Profissional(objDTO);
        return repository.save(oldObj);
    }

    public void delete(Integer id) {
        Profissional obj = findById(id);

        if (obj.getChamados().size() > 0) {
            throw new DataIntegrityViolationException("Técnico possui ordens de serviço e não pode ser deletado!");
        }

        repository.deleteById(id);
    }

    private void validaPorCpfEEmail(ProfissionalDTO objDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
        }

        obj = pessoaRepository.findByEmail(objDTO.getEmail());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
        }
    }

}
