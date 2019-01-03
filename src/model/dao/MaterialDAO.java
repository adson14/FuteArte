/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import model.bean.Material;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;





public class MaterialDAO {
    
    public void create(Material m){
       
        //conexao com o banco
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO Material (nome,FUNCIONARIO_cod_Func)VALUES(?,?)");
            stmt.setString(1, m.getNome());
            stmt.setInt(2,m.getFUNCIONARIO_cod_Func());
           
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
            
        } catch (SQLException ex) {
            Logger.getLogger(MaterialDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao salvar! "+ex);
        }finally{
           ConnectionFactory.closeConnection(con,stmt);
        }
        
    }
    
    
    public List<Material> read(){
        //Conexão com o banco
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Material> materiais = new ArrayList();
        
        
        try {
            stmt = con.prepareStatement("SELECT * FROM Material");
           rs =  stmt.executeQuery();
           while(rs.next()){
               Material material = new Material();
               
               material.setCod_Material(rs.getInt("cod_Material"));
               material.setNome(rs.getString("nome"));
               material.setFUNCIONARIO_cod_Func(rs.getInt("FUNCIONARIO_cod_Func"));
               
               materiais.add(material);
           }
           
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Falha na busca dos dados" +ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        
        return materiais;
        
    }
    
    
    public void update(Material m){
       
        //Conexão com o banco
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE Material SET nome =?,FUNCIONARIO_cod_Func = ? WHERE cod_Material = ?");
            stmt.setString(1, m.getNome());
            stmt.setInt(2,m.getFUNCIONARIO_cod_Func());
           
            stmt.setInt(3, m.getCod_Material());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Editado com sucesso!");
            
        } catch (SQLException ex) {
            Logger.getLogger(MaterialDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro na Edição! "+ex);
        }finally{
           ConnectionFactory.closeConnection(con,stmt);
        }
        
    
}
    
    
    public void delete(Material m){
       
        //Conexão com o banco
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM Material WHERE cod_Material = ? ");
      
            stmt.setInt(1, m.getCod_Material());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
            
        } catch (SQLException ex) {
            Logger.getLogger(MaterialDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao Excluir! "+ex);
        }finally{
           ConnectionFactory.closeConnection(con,stmt);
        }
        
    
}
}
