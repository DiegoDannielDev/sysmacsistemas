/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dao;

import ConexaoBd.ConexaoDAO;
import br.com.controller.UsuarioControler;
import br.com.exceptions.ApiExceptions;
import br.com.model.Usuario;
import br.com.view.FormPrincipal;
import br.com.view.FormUsuarios;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Diego Danniel
 */
public class UsuarioDAO extends ConexaoDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(Usuario.class);
    UsuarioControler user;
    FormUsuarios codigoUsuario;
    Usuario cad;

    private static FormPrincipal principal;

    public UsuarioDAO() {
        principal = new FormPrincipal();
    }

    /**
     *
     */
    public boolean insert(Usuario usuario) {
        int adicionado;
        conexao();
        String sql = "INSERT INTO `usuarios` (`NOME`,\n"
                + "  `LOGIN`,\n"
                + "  `SENHA`,\n"
                + "  `EMAIL`) VALUES (?,?,?,?) ";

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, usuario.getNomeUser());
            pst.setString(2, usuario.getLoginUser());
            pst.setString(3, usuario.getSenhaUser());
            pst.setString(4, usuario.getEmailUser());
            adicionado = pst.executeUpdate();

            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
            }

            return true;

        } catch (SQLException e) {
            LOGGER.error("ERRO "+e);
            JOptionPane.showMessageDialog(principal,e.getMessage());
           throw new ApiExceptions(e);
          
        } finally {
            desconecta();
        }
    }

    public boolean update(Usuario usuario) {
        return false;

    }

    public boolean delete(int codigo) {
        return false;

    }

    public boolean validaUsuarioDao(Usuario usuario) throws SQLException {
        conexao();

        String sql = "SELECT * FROM usuarios  WHERE login=? AND senha=?";
        try {

            pst = conn.prepareStatement(sql);
            pst.setString(1, usuario.getLoginUser());
            pst.setString(2, usuario.getSenhaUser());

            rs = pst.executeQuery();

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Erro" + ex.getMessage());
            return false;

        } finally {
            desconecta();
            pst.close();
            rs.close();
        }
        return true;

    }

    public List<Usuario> findAll() throws SQLException {
        List<Usuario> usuario = new ArrayList<>();
        conexao();
        user = new UsuarioControler();
        codigoUsuario = new FormUsuarios();
        try {
            String sql = "SELECT * FROM USUARIOS";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                usuario.add(new Usuario(
                        rs.getInt("codigo"),
                        rs.getString("nome"),
                        rs.getString("login"),
                        rs.getString("email")));
            }
            return usuario;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return usuario;
        }
    }

    public void listarPrimeiro() {
        cad = new Usuario();

        try {
            executasql("select * from usuarios");
            rs.next();
            cad.setCodigoUser(rs.getInt("CODIGO"));
            cad.setNomeUser(rs.getString("NOME"));
            cad.setLoginUser(rs.getString("LOGIN"));
            cad.setEmailUser(rs.getString("EMAIL"));

            pst.executeQuery();

            //model.setSenhaUser(rs.getString());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }

    public List<Usuario> listarProximo() {
        List<Usuario> users = new ArrayList<>();

        try {

            rs.last();

            users.add(new Usuario(
                    rs.getInt("CODIGO"),
                    rs.getString("nome"),
                    rs.getString("LOGIN"),
                    rs.getString("EMAIL")));

            //model.setSenhaUser(rs.getString());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return users;
    }
}
