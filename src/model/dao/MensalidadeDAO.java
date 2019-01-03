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
import model.bean.Mensalidade;

public class MensalidadeDAO {
    
    
    
    
    public void create(Mensalidade m){
        Connection con = ConnectionFactory.getConnection(); //Abrindo conexão
        PreparedStatement stmt = null;
        String sql = "INSERT INTO Mensalidade (valor, dataEmisao, dataVencimento, descricao, codigoBarras, ALUNO_cod_Aluno, FUNCIONARIO_cod_Func) VALUES ("+m.getValor()+", '"+m.getDataEmisao()+"', '"+m.getDataVencimento()+"', '"+m.getDescricao()+"', '"+m.getCodigoBarras()+"',  "+m.getALUNO_cod_Aluno()+", "+m.getFUNCIONARIO_cod_Func()+") ";
    
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
    
    public void update(Mensalidade m){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE Mensalidade SET valor = "+m.getValor()+", dataEmisao = '"+m.getDataEmisao()+"', dataVencimento = '"+m.getDataVencimento()+"',  descricao = '"+m.getDescricao()+"', codigoBarras = '"+m.getCodigoBarras()+"', ALUNO_cod_Aluno = "+m.getALUNO_cod_Aluno()+", FUNCIONARIO_cod_Func = "+m.getFUNCIONARIO_cod_Func()+" WHERE cod_Mensalidade = "+m.getCod_Mensalidade()+" ");

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
        String sql = "DELETE FROM Mensalidade WHERE cod_Mensalidade = "+num+" ";
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null,"Deletado com sucesso");
        } catch (SQLException ex) {
            Logger.getLogger(MensalidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao deletar: "+ ex);
        }finally{ //Independetemente se cair no try ou no catch, no final ele passa aqui
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public List<Mensalidade> listar(){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Mensalidade> mensalidade = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM Mensalidade");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Mensalidade m = new Mensalidade();
                
                m.setCod_Mensalidade(rs.getInt("cod_Mensalidade"));
                m.setValor(rs.getDouble("valor"));
                m.setDataEmisao(rs.getString("dataEmisao"));
                m.setDataVencimento(rs.getString("dataVencimento"));
                m.setDescricao(rs.getString("descricao"));
                m.setCodigoBarras(rs.getString("codigoBarras"));
                m.setALUNO_cod_Aluno(rs.getInt("ALUNO_cod_Aluno"));
                m.setFUNCIONARIO_cod_Func(rs.getInt("FUNCIONARIO_cod_Func"));
                
                
                mensalidade.add(m);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MensalidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return mensalidade;
        
    }
    
    
    
    
    
    public List<Mensalidade> listarMensalidadeTurma(int codTurma){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Mensalidade> mensalidade = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement(" SELECT  A.nome,M.dataVencimento,M.valor  FROM MENSALIDADE M INNER JOIN ALUNO A ON(M.ALUNO_cod_Aluno=A.cod_Aluno) WHERE TURMA_cod_Turma ='"+codTurma+"';");
            rs = stmt.executeQuery();
            
            
            while (rs.next()) {

                Mensalidade m = new Mensalidade();
                
                m.setNomeAluno(rs.getString("A.nome"));
                m.setDataVencimento(rs.getString("M.dataVencimento"));
                m.setValor(rs.getDouble("M.valor"));
               
                mensalidade.add(m);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MensalidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return mensalidade;
        
    }
    
    
    public double sumMensalidade(int codTurma){
        
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        double Soma = 0;
        try {
            stmt = con.prepareStatement(" SELECT  sum(valor) as TOTAL  FROM MENSALIDADE M INNER JOIN ALUNO A ON(M.ALUNO_cod_Aluno=A.cod_Aluno) WHERE TURMA_cod_Turma ='"+codTurma+"';");
            rs = stmt.executeQuery();
            
                       
            
            
            Soma = rs.getDouble("sum(valor)");
  
        } catch (Exception e) {
                      
        }finally{
           ConnectionFactory.closeConnection(con);
            System.out.println(Soma);
           return Soma; 
        } 
        
    }
    
    
    
    
    //////////////////////////////
    
    
    
    
     public List<Mensalidade> listarTotalMensalidade(int codTurma){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Mensalidade> mensalidade = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement(" SELECT  sum(valor) as TOTAL  FROM MENSALIDADE M INNER JOIN ALUNO A ON(M.ALUNO_cod_Aluno=A.cod_Aluno) WHERE TURMA_cod_Turma ='"+codTurma+"';");
            rs = stmt.executeQuery();
            
            
            while (rs.next()) {

                Mensalidade m = new Mensalidade();
                
               m.setTotal(rs.getDouble("TOTAL"));
               
               
                mensalidade.add(m);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MensalidadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return mensalidade;
        
    }
     
    public String gerarCod(){
        String x = "";
        int y;
        for(int i = 0; i < 15; i++){
            y = randInt(0,9);
            x = x + Integer.toString(y);
        }
        
        return x;
        
    }
    
    public static int randInt(int min, int max) {
        java.util.Random rand = new java.util.Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}
