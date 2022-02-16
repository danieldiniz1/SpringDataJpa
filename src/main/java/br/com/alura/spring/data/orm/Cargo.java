package br.com.alura.spring.data.orm;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cargos")
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descricao;
    @OneToMany(mappedBy = "cargo")
    private List<Funcionario> funcionarios;

    public Cargo() {
    }

    public Cargo(String descricao) {
        this.descricao = descricao;
    }

    public Cargo(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return "O cargo : " + this.descricao + " tem id: " + this.id;
    }
}
