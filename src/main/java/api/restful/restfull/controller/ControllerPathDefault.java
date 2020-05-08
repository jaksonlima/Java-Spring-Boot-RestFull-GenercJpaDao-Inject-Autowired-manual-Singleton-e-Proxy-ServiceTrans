package api.restful.restfull.controller;

import api.restful.restfull.context.Context;
import api.restful.restfull.generic.ProjetoRepository;
import api.restful.restfull.generic.dao.ProjetoDao;
import api.restful.restfull.model.Projeto;
import api.restful.restfull.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ControllerPathDefault {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ProjetoDao bean;

    @GetMapping(path = "/")
    public ResponseEntity get() {
//        ProjetoDao bean = Context.getBean(ProjetoDao.class);

        List<Projeto> listar = bean.listar();

        List<Projeto> allProjects = projetoRepository.findAll();

        return ResponseEntity.ok("user");
    }
}
