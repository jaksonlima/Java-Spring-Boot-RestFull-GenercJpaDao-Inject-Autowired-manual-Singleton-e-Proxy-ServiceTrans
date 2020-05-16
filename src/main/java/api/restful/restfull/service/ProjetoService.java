package api.restful.restfull.service;

import api.restful.restfull.generic.dao.ProjetoDao;
import api.restful.restfull.transactional.service.ServiceTransactional;
import org.springframework.beans.factory.annotation.Autowired;

@ServiceTransactional
public class ProjetoService {

    @Autowired
    private ProjetoDao projetoDao;

    public void PropagationREQUIRES_NEW(){
        projetoDao.salvarProjeto();
        projetoDao.salvarProjetoV2();
        System.out.println("ProjetoService");
    }
}
