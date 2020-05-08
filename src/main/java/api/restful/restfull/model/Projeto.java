package api.restful.restfull.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity(name = "projects")
public class Projeto implements Serializable {

    @Id
    private Integer id;

    @Column(name = "title")
    private String titolo;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "created_at")
    private Date dateCriacao;

    @Column(name = "updated_at")
    private Date dateAtualizacao;

}
