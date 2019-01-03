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
import model.bean.LoginSenha;

/**
 *
 * @author Aluno
 */
public class LoginSenhaDAO {
    
    public boolean logando(String nome, String senha) {
        int linha = 0;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<LoginSenha> loginSenha = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM Login WHERE login LIKE '"+nome+"' AND senha LIKE '"+senha+"' ");
            rs = stmt.executeQuery();

            while (rs.next()) {
                linha++;
                LoginSenha ls = new LoginSenha();

                ls.setUsuario(rs.getString("login"));
                ls.setSenha(rs.getString("senha"));
                loginSenha.add(ls);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(LoginSenhaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        if(linha > 0){
            return true;
        }else{
            return false;
        }
    }
}
