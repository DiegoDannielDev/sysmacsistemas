/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dao;

import br.com.model.AlugarCasas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego Danniel
 */
public class AlugarCasasDAO {
    ConexaoDAO conect = new ConexaoDAO();
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public AlugarCasasDAO(){
        
    }
    public boolean insert(AlugarCasas alugar){
        conect.conexao();
        String sql = "";
        
        try {
            pst = conect.conn.prepareStatement(sql);
            pst.setInt(1, alugar.getCodigoCasa());
            pst.setInt(2, alugar.getCodigoCliente());
            pst.setString(3, alugar.getDataInicial());
            pst.setString(4, alugar.getDataFinal());
            pst.setString(5, alugar.getTipoDeAluguel());
            
            int add = pst.executeUpdate();
            if (add > 0) {
                JOptionPane.showMessageDialog(null, "Registro efetuado");
            }
            else{
                JOptionPane.showMessageDialog(null, "Erro ao gravar dados!");
                return false;
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AlugarCasasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ;
        return true;
    }
    
}
