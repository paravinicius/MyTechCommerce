package br.com.fiap4.mytechcommerce.gestaoAcesso.repositories;

import br.com.fiap4.mytechcommerce.gestaoAcesso.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
