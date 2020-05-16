package api.restful.restfull.controller;

import api.restful.restfull.generic.dao.ProjetoDao;
import api.restful.restfull.service.ProjetoService;
import api.restful.restfull.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerPathDefault {

    @Autowired
    private UsuarioService projetoDao;

    @GetMapping(path = "/")
    public ResponseEntity get() {
        projetoDao.PropagationREQUIRES_NEW();
        return ResponseEntity.ok("user");
    }
}
