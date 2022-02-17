package br.com.alura.spring.data.orm;

import java.math.BigDecimal;
// interface based Projection
public interface FuncionarioProjecao {
    Integer getId();
    String getNome();
    BigDecimal getSalario();
}
