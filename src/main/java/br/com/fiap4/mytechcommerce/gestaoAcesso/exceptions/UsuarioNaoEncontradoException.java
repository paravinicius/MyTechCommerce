package br.com.fiap4.mytechcommerce.gestaoAcesso.exceptions;

public class UsuarioNaoEncontradoException extends RuntimeException {
    public UsuarioNaoEncontradoException(Long id) {
        super("Usuario não encontrado com o ID " + id);
    }
}
