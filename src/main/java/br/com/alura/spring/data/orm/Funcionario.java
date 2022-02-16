package br.com.alura.spring.data.orm;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "funcionarios")
public class Funcionario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String cpf;
    private BigDecimal salario;
    private final LocalDate dataContratacao = LocalDate.now();

    @ManyToOne
    private Cargo cargo;

    @Fetch(FetchMode.SELECT)
    @ManyToMany(fetch = FetchType.EAGER)
    private List<UnidadeTrabalho> unidadeTrabalho;

    public Funcionario() {
    }

    public Funcionario(String nome, String cpf, BigDecimal salario) {
        this.nome = nome;
        this.cpf = cpf;
        this.salario = salario;
    }

    public Funcionario(String nome, String cpf, BigDecimal salario, Cargo cargo) {
        this.nome = nome;
        this.cpf = cpf;
        this.salario = salario;
        this.cargo = cargo;
    }

    public Funcionario(Integer id, String nome) {
        this.id = id;
        this.nome =  nome;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public LocalDate getDataContratacao() {
        return dataContratacao;
    }

    public Cargo getCargo() {
        return cargo;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", salário=" + salario +
                ", data de contratacao=" + dataContratacao +
                "} e seu cargo é: " + getCargo().getDescricao();
    }
}
