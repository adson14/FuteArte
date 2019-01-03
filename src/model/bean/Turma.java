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
public class Turma {
    private int cod_Turma;
    private String nome;
    private int FUNCIONARIO_cod_Func;
    

    public int getCod_Turma() {
        return cod_Turma;
    }

    public void setCod_Turma(int cod_Turma) {
        this.cod_Turma = cod_Turma;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getFUNCIONARIO_cod_Func() {
        return FUNCIONARIO_cod_Func;
    }

    public void setFUNCIONARIO_cod_Func(int FUNCIONARIO_cod_Func) {
        this.FUNCIONARIO_cod_Func = FUNCIONARIO_cod_Func;
    }
    
    @Override
    public String toString(){
        
        return getNome();
    }
    
}
