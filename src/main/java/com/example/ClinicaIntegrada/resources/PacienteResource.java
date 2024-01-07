package com.example.ClinicaIntegrada.resources;

import com.example.ClinicaIntegrada.domain.Paciente;
import com.example.ClinicaIntegrada.domain.dtos.PacienteDTO;
import com.example.ClinicaIntegrada.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/pacientes")
public class PacienteResource {
    @Autowired
    private PacienteService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<PacienteDTO> findById(@PathVariable Integer id) {
        Paciente obj = service.findById(id);
        return ResponseEntity.ok().body(new PacienteDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<PacienteDTO>> findAll() {
        List<Paciente> list = service.findAll();
        List<PacienteDTO> listDTO = list.stream().map(obj -> new PacienteDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<PacienteDTO> create(@Valid @RequestBody PacienteDTO objDTO) {
        Paciente newObj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PacienteDTO> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
