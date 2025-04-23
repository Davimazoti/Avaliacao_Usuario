package com.example.Avaliacao_Usuario.repository;

import com.example.Avaliacao_Usuario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
