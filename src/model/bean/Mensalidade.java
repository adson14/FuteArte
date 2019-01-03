/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

/**
 *
 * @author Aluno
 */
public class Mensalidade {
    private int cod_Mensalidade;
    private double valor;
    private String dataEmisao;
    private String dataVencimento;
    private String descricao;
    private String codigoBarras;
    private int ALUNO_cod_Aluno;
    private int FUNCIONARIO_cod_Func;
    private String nomeAluno;
    private double total;

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public int getCod_Mensalidade() {
        return cod_Mensalidade;
    }

    public void setCod_Mensalidade(int cod_Mensalidade) {
        this.cod_Mensalidade = cod_Mensalidade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDataEmisao() {
        return dataEmisao;
    }

    public void setDataEmisao(String dataEmisao) {
        this.dataEmisao = dataEmisao;
    }

    public String getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public int getALUNO_cod_Aluno() {
        return ALUNO_cod_Aluno;
    }

    public void setALUNO_cod_Aluno(int ALUNO_cod_Aluno) {
        this.ALUNO_cod_Aluno = ALUNO_cod_Aluno;
    }

    public int getFUNCIONARIO_cod_Func() {
        return FUNCIONARIO_cod_Func;
    }

    public void setFUNCIONARIO_cod_Func(int FUNCIONARIO_cod_Func) {
        this.FUNCIONARIO_cod_Func = FUNCIONARIO_cod_Func;
    }
    
    
}
