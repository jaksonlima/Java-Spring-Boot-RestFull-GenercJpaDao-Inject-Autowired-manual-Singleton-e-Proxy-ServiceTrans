package api.restful.restfull.generic.repository.dao;

import api.restful.restfull.generic.repository.AbstractJpaDAO;
import api.restful.restfull.model.Usuario;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UsuarioDao extends AbstractJpaDAO<Usuario> {

    public Usuario encontrarPorId(Integer id) {
        StringBuilder sql = new StringBuilder();
        sql.append("select u");
        sql.append(" from Usuario u ");
        sql.append("where u.id = :id ");

        TypedQuery<Usuario> tQuery = createTypedQuery(sql)
                .setParameter("id", id);

        return tQuery.getSingleResult();
    }

    public List<Usuario> listar() {
        StringBuilder sql = new StringBuilder();
        sql.append("select u");
        sql.append(" from Usuario u");

        return createTypedQuery(sql)
                .getResultList();
    }

}
