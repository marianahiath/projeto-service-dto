package br.org.serratec.servicedto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.servicedto.dto.EnderecoDTO;
import br.org.serratec.servicedto.service.EnderecoService;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
    
    @Autowired
    private EnderecoService enderecoService;

    @GetMapping("/{cep}")
    public ResponseEntity<EnderecoDTO> buscar(@PathVariable String cep) {
        EnderecoDTO enderecoDTO = enderecoService.buscar(cep);
        if(enderecoDTO == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(enderecoDTO);
    }
}
