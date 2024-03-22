package br.com.fiap4.mytechcommerce.gestaoAcesso.services;

import br.com.fiap4.mytechcommerce.gestaoAcesso.entities.Usuario;
import br.com.fiap4.mytechcommerce.gestaoAcesso.exceptions.UsuarioNaoEncontradoException;
import br.com.fiap4.mytechcommerce.gestaoAcesso.repositories.UsuarioRepository;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Getter
public class UsuarioService {
    @Autowired
    private final UsuarioRepository usuarioRepository;
    @Autowired
    private final EntityManager entityManager;

    public UsuarioService(UsuarioRepository usuarioRepository, EntityManager entityManager) {
        this.usuarioRepository = usuarioRepository;
        this.entityManager = entityManager;
    }

    public List<Usuario> buscarTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario criarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> recuperarUsarioByID(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario atualizarUsuario(Usuario usuario) throws UsuarioNaoEncontradoException {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(usuario.getId());

        if (usuarioExistente.isEmpty()) {
            throw new UsuarioNaoEncontradoException(usuario.getId());
        }
       return usuarioRepository.save(usuario);
    }

    public void deletarUsuario(Long id) throws UsuarioNaoEncontradoException {
        Optional<Usuario> usuarioExistente = recuperarUsarioByID(id);

        if (usuarioExistente.isEmpty()) {
            throw new UsuarioNaoEncontradoException(id);
        }
        usuarioRepository.deleteById(id);
    }

}
