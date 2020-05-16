package api.restful.restfull.service;

import api.restful.restfull.transactional.service.ServiceTransactional;
import org.springframework.beans.factory.annotation.Autowired;

@ServiceTransactional
public class UsuarioService {

    @Autowired
    private ProjetoService projetoService;

    public void PropagationREQUIRES_NEW(){
        projetoService.PropagationREQUIRES_NEW();
        System.out.println("UsuarioService");
    }
}
