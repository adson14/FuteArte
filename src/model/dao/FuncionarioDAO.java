/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;


import connection.ConnectionFactory;
import model.bean.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class FuncionarioDAO {
    
    
    
     public void create(Funcionario f){
       //conexao com o banco
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO Funcionario (nome,idade,rg,cpf,endereco,cargo)VALUES(?,?,?,?,?,?)");
            stmt.setString(1, f.getNome());
            stmt.setString(2, f.getIdade());
            stmt.setString(3, f.getRg());
            stmt.setString(4, f.getCpf());
            stmt.setString(5, f.getEndereco());
            stmt.setString(6, f.getCargo());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
            
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao salvar! "+ex);
        }finally{
           ConnectionFactory.closeConnection(con,stmt);
        }
        
    }
     
     
     
     
     public List<Funcionario> read(){
        //conexao com o banco
         Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Funcionario> funcionarios = new ArrayList();
        
        
        try {
            stmt = con.prepareStatement("SELECT * FROM Funcionario");
           rs =  stmt.executeQuery();
           while(rs.next()){
               Funcionario funcionario = new Funcionario();
               
               funcionario.setCod_Func(rs.getInt("cod_Func"));
               funcionario.setNome(rs.getString("nome"));
               funcionario.setIdade(rs.getString("idade"));
              funcionario.setRg(rs.getString("rg"));
               funcionario.setCpf(rs.getString("cpf"));
               funcionario.setEndereco(rs.getString("endereco"));
               funcionario.setCargo(rs.getString("cargo"));
               funcionarios.add(funcionario);
           }
           
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Falha na busca dos dados" +ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        
        return funcionarios;
        
    }
     
     
     
      public void update(Funcionario f){
       //Conexão com o banco
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE Funcionario SET nome =?,idade = ?,rg =?,cpf = ?,endereco = ?,cargo = ? WHERE cod_Func = ?");
            stmt.setString(1, f.getNome());
            stmt.setString(2,f.getIdade());
            stmt.setString(3, f.getRg());
            stmt.setString(4, f.getCpf());
            stmt.setString(5, f.getEndereco());
            stmt.setString(6, f.getCargo());
            stmt.setInt(7, f.getCod_Func());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Editado com sucesso!");
            
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro na Edição! "+ex);
        }finally{
           ConnectionFactory.closeConnection(con,stmt);
        }
    
        
        
    
}
      
      
      public void delete(Funcionario f){
       
        //Conexão com o banco  
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM Funcionario WHERE cod_Func = ? ");
      
            stmt.setInt(1, f.getCod_Func());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
            
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao Excluir! "+ex);
        }finally{
           ConnectionFactory.closeConnection(con,stmt);
        }
        
    
}
}