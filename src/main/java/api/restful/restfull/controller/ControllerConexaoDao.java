package api.restful.restfull.controller;

import api.restful.restfull.context.Context;
import api.restful.restfull.generic.ProjetoRepository;
import api.restful.restfull.generic.dao.ProjetoDao;
import api.restful.restfull.model.Projeto;
import api.restful.restfull.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = "*/*")
public class ControllerConexaoDao {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ProjetoDao bean;

    @GetMapping(path = "/dao")
    public ResponseEntity getDao() {
//        ProjetoDao bean = Context.getBean(ProjetoDao.class);

        List<Projeto> listar = bean.listar();

        List<Projeto> allProjects = projetoRepository.findAll();

        return ResponseEntity.ok("user");
    }
}
