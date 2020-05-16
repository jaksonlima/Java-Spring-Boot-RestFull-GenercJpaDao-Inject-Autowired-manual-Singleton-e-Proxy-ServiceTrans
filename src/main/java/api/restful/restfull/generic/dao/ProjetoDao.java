package api.restful.restfull.generic.dao;

import api.restful.restfull.generic.AbstractJpaDAO;
import api.restful.restfull.generic.IGenericDAO;
import api.restful.restfull.model.Projeto;
import api.restful.restfull.transactional.service.ServiceTransactional;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ServiceTransactional
public class ProjetoDao extends AbstractJpaDAO<Projeto>{

    IGenericDAO<Projeto> dao;

//    @Autowired
    public void setDao(IGenericDAO<Projeto> daoToSet) {
        dao = daoToSet;
        dao.setClass(Projeto.class);
    }

    public List<Projeto> listar() {
        StringBuilder sql = new StringBuilder();
        sql.append("select p from projects p");

        return createQuery(sql)
                .getResultList();
    }

    @ServiceTransactional(propagation = Propagation.REQUIRES_NEW)
    public void salvarProjeto() {
        Projeto projeto = new Projeto();
        projeto.setId(1);
        projeto.setTitolo("Teste merge");
        merge(projeto);
        System.out.println("ProjetoDao");
    }

    public void salvarProjetoV2() {
        Projeto projeto = new Projeto();
        projeto.setId(2);
        projeto.setTitolo("Teste merge V2");
        merge(projeto);
        System.out.println("ProjetoDao v2");
    }
}
