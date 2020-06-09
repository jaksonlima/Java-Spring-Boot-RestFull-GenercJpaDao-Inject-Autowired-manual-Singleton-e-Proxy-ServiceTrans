package api.restful.restfull.generic.repository.repository;

import api.restful.restfull.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
