package com.example.ClinicaIntegrada.resources;

import com.example.ClinicaIntegrada.domain.Profissional;
import com.example.ClinicaIntegrada.domain.dtos.ProfissionalDTO;
import com.example.ClinicaIntegrada.services.ProfissionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/profissional")
public class ProfissionalResource {
    @Autowired
    private ProfissionalService service;
    @GetMapping(value ="/{id}")
    public ResponseEntity<ProfissionalDTO> findById(@PathVariable Integer id) {
        Profissional obj = service.findById(id);
        return ResponseEntity.ok().body(new ProfissionalDTO(obj));
    }
    @GetMapping
    public ResponseEntity<List<ProfissionalDTO>> findAll() {
        List<Profissional> list = service.findAll();
        List<ProfissionalDTO> listDTO = list.stream().map(obj -> new ProfissionalDTO(obj)).collect(Collectors.toList());
        ;
        return ResponseEntity.ok().body(listDTO);
    }
    @PostMapping
    public ResponseEntity<ProfissionalDTO> create(@Valid @RequestBody ProfissionalDTO objDTO){
        Profissional newObj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<ProfissionalDTO> update(@PathVariable Integer id,@Valid @RequestBody ProfissionalDTO objDTO){
        Profissional obj = service.update(id, objDTO);
        return ResponseEntity.ok().body(new ProfissionalDTO(obj));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ProfissionalDTO> delete (@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
