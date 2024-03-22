package br.com.fiap4.mytechcommerce.gestaoAcesso.controllers;

import br.com.fiap4.mytechcommerce.gestaoAcesso.entities.Usuario;
import br.com.fiap4.mytechcommerce.gestaoAcesso.exceptions.UsuarioNaoEncontradoException;
import br.com.fiap4.mytechcommerce.gestaoAcesso.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping()
    public ResponseEntity<List<Usuario>> buscarTodosUsuarios() {
        List<Usuario> usuarios = usuarioService.buscarTodosUsuarios();

        if (usuarios == null || usuarios.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuarios);

    }

    @GetMapping("/{usuarioID}")
    public ResponseEntity<Usuario> buscarUsuarioByID(@PathVariable Long usuarioID) {
        var usuario = usuarioService.recuperarUsarioByID(usuarioID);

        if (usuario.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuario.get());
    }

    @PostMapping
    public ResponseEntity<?> criarUsuario(@RequestBody Usuario usuario) {
        Usuario novoUsuario = usuarioService.criarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    @PutMapping()
    public ResponseEntity<Usuario> atualizarUsuairo(@RequestBody Usuario usuario){
        Usuario novoUsuario;
        try {
            novoUsuario = usuarioService.atualizarUsuario(usuario);
        } catch (UsuarioNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(novoUsuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        try {
            usuarioService.deletarUsuario(id);
        } catch (UsuarioNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

}
