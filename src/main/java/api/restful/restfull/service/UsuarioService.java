package api.restful.restfull.service;

import api.restful.restfull.model.Usuario;

import java.util.List;

public interface UsuarioService {

    List<Usuario> listar();

    Usuario encontrar(Integer id);

    Usuario atualizar(Integer id, Usuario usuario);

    void criar(Usuario usuario);

    void deletar(Usuario usuario);
}
