package api.restful.restfull.generic.dao;

import api.restful.restfull.context.IProxy;
import api.restful.restfull.generic.AbstractJpaDAO;
import api.restful.restfull.generic.IGenericDAO;
import api.restful.restfull.model.Projeto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProjetoDao extends AbstractJpaDAO<Projeto> implements IProxy {

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
}
