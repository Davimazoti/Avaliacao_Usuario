package com.example.Avaliacao_Usuario.controller;


import com.example.Avaliacao_Usuario.DTO.UsuarioDTO;
import com.example.Avaliacao_Usuario.entity.Usuario;
import com.example.Avaliacao_Usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @GetMapping
    public List<Usuario> getAll(@RequestParam(required = false) String nome, @RequestParam(required = false) String cpf){
        if (nome != null && !nome.isEmpty()){
            return usuarioService.getAllByName(nome);
        }

        if (cpf != null && !cpf.isEmpty()){
            return usuarioService.getAllByCpf(cpf);
        }

        return usuarioService.getAllUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getById(@PathVariable Long id){
        Optional<UsuarioDTO> usuarioDTOOptional = usuarioService.getById(id);
        if (usuarioDTOOptional.isPresent()) {
            return ResponseEntity.ok(usuarioDTOOptional.get());
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> create(@RequestBody UsuarioDTO usuarioDTO){
        UsuarioDTO usuarioDTOSave = usuarioService.createUsuario(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDTOSave);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO){
        Optional<UsuarioDTO> usuarioDTOOptional = usuarioService.updateUsuario(id, usuarioDTO);
        if (usuarioDTOOptional.isPresent()){
            return ResponseEntity.ok(usuarioDTOOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/atualizarSenha/{id}")
    public ResponseEntity<UsuarioDTO> updateUserSenha(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO){
        Optional<UsuarioDTO> usuarioDTOOptional = usuarioService.updateUsuarioUserSenha(id, usuarioDTO);
        if (usuarioDTOOptional.isPresent()){
            return ResponseEntity.ok(usuarioDTOOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if (usuarioService.delete(id)){
            return ResponseEntity.noContent().build();
        } else{
            return ResponseEntity.notFound().build();
        }
    }
}
