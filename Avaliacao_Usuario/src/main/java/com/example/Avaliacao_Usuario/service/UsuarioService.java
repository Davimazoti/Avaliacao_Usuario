package com.example.Avaliacao_Usuario.service;

import com.example.Avaliacao_Usuario.DTO.UsuarioDTO;
import com.example.Avaliacao_Usuario.entity.Usuario;
import com.example.Avaliacao_Usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.Copies;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getAllUsuarios(){
        return usuarioRepository.findAll();
    }

    public Optional<UsuarioDTO> getById(Long id){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()){
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            return Optional.of(usuarioDTO.fromUsuario(usuarioOptional.get()));
        } else{
            return Optional.empty();
        }
    }

    public UsuarioDTO createUsuario(UsuarioDTO usuarioDTO){
        Usuario usuario = usuarioDTO.toUsuario();
        usuario = usuarioRepository.save(usuario);
        return usuarioDTO.fromUsuario(usuario);
    }


    public Optional<UsuarioDTO> updateUsuario(Long id, UsuarioDTO usuarioDTO){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()){
            Usuario usuario = usuarioOptional.get();
            usuario.setNome(usuarioDTO.getNome());
            usuario.setSobrenome(usuarioDTO.getSobrenome());
            usuario.setCpf(usuarioDTO.getCpf());
            usuario.setEmail(usuarioDTO.getEmail());
            usuario.setDataNascimento(usuarioDTO.getDataNascimento());

            usuario = usuarioRepository.save(usuario);

            return Optional.of(usuarioDTO.fromUsuario(usuario));
        } else{
            return Optional.empty();
        }
    }

    public boolean delete(Long id){
        if (usuarioRepository.existsById(id)){
            usuarioRepository.deleteById(id);
            return true;
        } else{
            return false;
        }
    }
}
