package api.restful.restfull;

import api.restful.restfull.generic.repository.repository.UsuarioRepository;
import api.restful.restfull.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.Arrays;

@SpringBootApplication()
public class RestfullApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestfullApplication.class, args);
    }

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Bean
    public CommandLineRunner run() {
        return (CommandLineRunner) -> {
            Usuario usuario = new Usuario();
            usuario.setNome("Alisom");
            usuario.setLogin("teste");
            usuario.setSenha("12345");
            usuario.setDhCriacao(LocalDateTime.now());

            Usuario usuario1 = new Usuario();
            usuario1.setNome("Tigas");
            usuario1.setLogin("hello");
            usuario1.setSenha("12345");
            usuario1.setDhCriacao(LocalDateTime.now());

            Usuario usuario2 = new Usuario();
            usuario2.setNome("Talis");
            usuario2.setLogin("word");
            usuario2.setSenha("12345");
            usuario2.setDhCriacao(LocalDateTime.now());

            usuarioRepository.saveAll(Arrays.asList(usuario, usuario1, usuario2));
        };
    }
}
