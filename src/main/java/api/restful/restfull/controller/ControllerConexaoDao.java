package api.restful.restfull.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = "*/*")
public class ControllerConexaoDao {

    @GetMapping(path = "/dao")
    public ResponseEntity getDao() {
        return ResponseEntity.ok("user");
    }
}
