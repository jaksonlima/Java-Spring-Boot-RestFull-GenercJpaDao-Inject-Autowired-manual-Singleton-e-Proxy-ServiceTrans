package api.restful.restfull.controller;

import api.restful.restfull.generic.repository.dao.IGenericRepository;
import api.restful.restfull.model.Usuario;
import api.restful.restfull.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = UsuarioController.PATH, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController {

    final static String PATH = "/usuario";

    @Autowired
    UsuarioService usuarioService;

    IGenericRepository<Usuario> iGenericDAO;

    @Autowired
    public void setDao(IGenericRepository<Usuario> genericDAO) {
        iGenericDAO = genericDAO;
        genericDAO.setClass(Usuario.class);
    }

    @GetMapping("/listar")
    ResponseEntity listar() {
        List<Usuario> usuarios = usuarioService.listar();

        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    ResponseEntity encontrar(@PathVariable("id") Integer idUsuario) {
        Usuario usuario = usuarioService.encontrar(idUsuario);

        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/{id}")
    ResponseEntity atualizar(@PathVariable("id") Integer idUsuario, @RequestBody Usuario usuario) {
        usuarioService.atualizar(idUsuario, usuario);

        return ResponseEntity.ok().build();
    }

    @PostMapping
    ResponseEntity criar(@RequestBody Usuario usuario) {
        UsuarioService usuarioService = null;

        usuarioService.criar(usuario);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity deletar(@PathVariable("id") Integer idUsuario) {
        Usuario usuario = iGenericDAO.find(idUsuario);

        UsuarioService usuarioService = null;

        usuarioService.deletar(usuario);

        return ResponseEntity.ok().build();
    }
}
