package api.restful.restfull.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "projects")
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getDateCriacao() {
        return dateCriacao;
    }

    public void setDateCriacao(Date dateCriacao) {
        this.dateCriacao = dateCriacao;
    }

    public Date getDateAtualizacao() {
        return dateAtualizacao;
    }

    public void setDateAtualizacao(Date dateAtualizacao) {
        this.dateAtualizacao = dateAtualizacao;
    }
}
