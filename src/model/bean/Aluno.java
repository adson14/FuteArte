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
public class Aluno {
    private int cod_Aluno;
    private String nome;
    private int idade;
    private String rg;
    private String cpf;
    private String endereco;
    private String statusAvaliacaoMedica;
    private int FUNCIONARIO_cod_Func;
    private int TURMA_cod_Turma;

    public String getNomeTurma() {
        return nomeTurma;
    }

    public void setNomeTurma(String nomeTurma) {
        this.nomeTurma = nomeTurma;
    }
    private String nomeTurma;

    public int getCod_Aluno() {
        return cod_Aluno;
    }

    public void setCod_Aluno(int cod_Aluno) {
        this.cod_Aluno = cod_Aluno;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getStatusAvaliacaoMedica() {
        return statusAvaliacaoMedica;
    }

    public void setStatusAvaliacaoMedica(String statusAvaliacaoMedica) {
        this.statusAvaliacaoMedica = statusAvaliacaoMedica;
    }

    public int getFUNCIONARIO_cod_Func() {
        return FUNCIONARIO_cod_Func;
    }

    public void setFUNCIONARIO_cod_Func(int FUNCIONARIO_cod_Func) {
        this.FUNCIONARIO_cod_Func = FUNCIONARIO_cod_Func;
    }

    public int getTURMA_cod_Turma() {
        return TURMA_cod_Turma;
    }

    public void setTURMA_cod_Turma(int TURMA_cod_Turma) {
        this.TURMA_cod_Turma = TURMA_cod_Turma;
    }

    @Override
    public String toString(){
        return getNome();
    }
    
}
