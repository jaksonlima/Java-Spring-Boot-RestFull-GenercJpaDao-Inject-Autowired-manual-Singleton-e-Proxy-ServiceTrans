package api.restful.restfull.service.impl;

import api.restful.restfull.generic.repository.dao.UsuarioDao;
import api.restful.restfull.model.Usuario;
import api.restful.restfull.service.UsuarioService;
import api.restful.restfull.transactional.service.ServiceTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;

import java.time.LocalDateTime;
import java.util.List;

@ServiceTransactional
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Override
    public List<Usuario> listar() {
        return usuarioDao.listar();
    }

    @Override
    public Usuario encontrar(Integer id) {
        if (id == null) {
            throw new RuntimeException("ID não pode ser vazio.");
        }
        return usuarioDao.encontrarPorId(id);
    }

    @Override
    @ServiceTransactional(propagation = Propagation.REQUIRES_NEW)
    public Usuario atualizar(Integer id, Usuario usuario) {
        Usuario usuarioCadastro = usuarioDao.encontrarPorId(id);

        validaUsuario(usuarioCadastro);

        usuarioCadastro.setNome(usuario.getNome());
        usuarioCadastro.setLogin(usuario.getLogin());
        usuarioCadastro.setSenha(usuario.getSenha());
        usuarioCadastro.setDhAlteracao(LocalDateTime.now());

        return usuarioDao.merge(usuarioCadastro);
    }

    @Override
    public void criar(Usuario usuario) {
        validaUsuario(usuario);

        usuario.setDhCriacao(LocalDateTime.now());

        usuarioDao.merge(usuario);
    }

    @Override
    public void deletar(Usuario usuario) {
        validaUsuario(usuario);

        usuarioDao.remove(usuario);
    }

    void validaUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new RuntimeException("Usuario não encontrado.");
        }
    }

}
