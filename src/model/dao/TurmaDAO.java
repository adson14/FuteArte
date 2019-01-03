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
import model.bean.Turma;

public class TurmaDAO {
    public void create(Turma t){
        Connection con = ConnectionFactory.getConnection(); //Abrindo conexão
        PreparedStatement stmt = null;
        String sql = "INSERT INTO Turma (nome, FUNCIONARIO_cod_Func) VALUES ('"+t.getNome()+"', "+t.getFUNCIONARIO_cod_Func()+" )";
    
        try {
            stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null,"Salvo com sucesso");
        } catch (SQLException ex) {
            Logger.getLogger(TurmaDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao salvar: "+ ex);
        }finally{ //Independetemente se cair no try ou no catch, no final ele passa aqui
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void update(Turma t){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE Turma SET nome = '"+t.getNome()+"', FUNCIONARIO_cod_Func = "+t.getFUNCIONARIO_cod_Func()+" WHERE cod_Turma = "+t.getCod_Turma()+" ");

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void delete(int num){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        String sql = "DELETE FROM Turma WHERE cod_Turma = "+num+" ";
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null,"Deletado com sucesso");
        } catch (SQLException ex) {
            Logger.getLogger(TurmaDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao deletar: "+ ex);
        }finally{ //Independetemente se cair no try ou no catch, no final ele passa aqui
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public List<Turma> listar(){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Turma> turma = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM Turma");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Turma t = new Turma();
                
                t.setCod_Turma(rs.getInt("cod_Turma"));
                t.setNome(rs.getString("nome"));
                t.setFUNCIONARIO_cod_Func(rs.getInt("FUNCIONARIO_cod_Func"));
                
                turma.add(t);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TurmaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return turma;   
    } 
}
