/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Aluno;

/**
 *
 * @author Adson , Gustaco, Matheus
 * @version 1.0
 */

/** Classe Controler do aluno*/
public class AlunoDAO {

   
    
      /** Método responsável pela inserção dos dados do aluno no Banco de dados, aqui fazemos a conexão
        * e preparamos o comando SQL. Também inserimos um tratamento TRY e CATCH.
        * @param a 
        */ 
    public void create(Aluno a) {
      
        Connection con = ConnectionFactory.getConnection(); //Abrindo conexão
        PreparedStatement stmt = null;
        String sql = "INSERT INTO Aluno (nome, idade, rg, cpf, endereco, statusMedico, FUNCIONARIO_cod_Func, TURMA_cod_Turma) VALUES ('" + a.getNome() + "', " + a.getIdade() + ", '" + a.getRg() + "', '" + a.getCpf() + "', '" + a.getEndereco() + "', '" + a.getStatusAvaliacaoMedica() + "', " + a.getFUNCIONARIO_cod_Func() + ", " + a.getTURMA_cod_Turma() + ")";

        try {
            stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao salvar: " + ex);
        } finally { //Independetemente se cair no try ou no catch, no final ele passa aqui
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    /** Método responsável pela atualização dos dados no banco. Como em cada método a conexão 
         * é aberta e executa-se o comando SQL , também com tratamento de erros
     * @param a
         */
    public void update(Aluno a) {
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE Aluno SET nome = '" + a.getNome() + "', idade = " + a.getIdade() + ", rg = '" + a.getRg() + "', cpf = '" + a.getCpf() + "', endereco = '" + a.getEndereco() + "', statusMedico = '" + a.getStatusAvaliacaoMedica() + "', FUNCIONARIO_cod_Func = " + a.getFUNCIONARIO_cod_Func() + ", TURMA_cod_Turma = " + a.getTURMA_cod_Turma() + " WHERE cod_Aluno = '" + a.getCod_Aluno() + "' ");

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

        /** metodo para deletar informações registradas no banco.
         * Nesse metodo é necessário apenas o ID do registro para realizar a remoção.
         * Nesse metodo estão duas execuções SQL diferentes sendo que uma remove a chave estrangeira
         * em outra tabela.
         *
     * @param num
         */
    public void delete(int num) {
   
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        String sql = "DELETE FROM  Aluno WHERE cod_Aluno = " + num + " ";
        String sqlM = "DELETE FROM Mensalidade WHERE ALUNO_cod_Aluno = " + num + " ";
        
        try {
            stmt = con.prepareStatement(sqlM);
            stmt.executeUpdate();
            stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Deletado com sucesso");
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao deletar: " + ex);
        } finally { //Independetemente se cair no try ou no catch, no final ele passa aqui
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

     /**Esse método do tipo LIST cria um objeto ao ser instanciado para retornar
         * uma lista de registros de acordo com a instrução SQL . Aqui a lista será exibida no jTable
     * @return 
         */
    public List<Aluno> listar() {
      

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Aluno> aluno = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM Aluno");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Aluno a = new Aluno();

                a.setCod_Aluno(rs.getInt("cod_Aluno"));
                a.setNome(rs.getString("nome"));
                a.setIdade(rs.getInt("idade"));
                a.setRg(rs.getString("rg"));
                a.setCpf(rs.getString("cpf"));
                a.setEndereco(rs.getString("endereco"));
                a.setStatusAvaliacaoMedica(rs.getString("statusMedico"));
                a.setFUNCIONARIO_cod_Func(rs.getInt("FUNCIONARIO_cod_Func"));
                a.setTURMA_cod_Turma(rs.getInt("TURMA_cod_Turma"));

                aluno.add(a);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return aluno;
    }


    /** Nessa lista será exibido os dados do aluno relacionando com a turma.Este método é especialmente criado
 * para utilizar no relátorio com o Ireport
     * @param codTurma
     * @return 
 */
    public List<Aluno> listarAlunoTurma(int codTurma) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Aluno> aluno = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT T.cod_Turma, T.nome AS 'TurmaNome', T.FUNCIONARIO_cod_Func, A.cod_Aluno, A.nome, A.idade, A.rg, A.cpf, A.endereco, A.statusMedico, A.FUNCIONARIO_cod_Func, A.TURMA_cod_Turma FROM Turma T INNER JOIN Aluno A ON A.TURMA_cod_Turma = T.cod_Turma where T.cod_Turma = "+codTurma+";");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Aluno a = new Aluno();

                a.setNome(rs.getString("A.nome"));
                a.setIdade(rs.getInt("A.idade"));
                a.setNomeTurma(rs.getString("TurmaNome"));

                aluno.add(a);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return aluno;
    }

}
