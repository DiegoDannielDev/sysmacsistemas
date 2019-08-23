/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dao;

import br.com.model.Casas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego Danniel
 */
public class CasasDAO {

    ConexaoDAO conect = new ConexaoDAO();
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    private static Casas cs;

    public CasasDAO() {

    }

    /*
     * Metodo resposanvel pelo cadastro das casas
     */
    public List<Casas> novoId() {
        try {
            List<Casas> casa = new ArrayList<>();
            conect.conexao();
            String sql = "SELECT auto_increment  as  codigo  FROM information_schema.tables IT WHERE IT.table_name = 'casas_cadastro'";
            pst = conect.conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                casa.add(new Casas(rs.getInt("CODIGO")));

            }
            return casa;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return novoId();

    }

    public boolean insert(Casas casas) throws ParseException, SQLException {

        conect.conexao();
        String sql = "INSERT INTO `casas_cadastro` (`CLASSIFICACAO`,"
                + "`TIPO_IMOVEL`,"
                + "DATA_CADASTRO,"
                + "`ENDERECO`,"
                + "`N_ENDERECO`, "
                + "`CEP`,"
                + "`BAIRRO`,"
                + "`CIDADE`,"
                + "`UF`, "
                + "`REFERENCIA`,"
                + "`LOTE`, "
                + "`QUADRA`,"
                + "`MEDIDA`,"
                + "`METRO_QUADRADO`,"
                + "`QTDE_SALA`,"
                + "`QTDE_QUARTOS`,"
                + "`QTDE_GARAGEM`,"
                + "QTDE_BWC,"
                + "`DATA_CONSTRUCAO`,"
                + "`DATA_REFORMA`,"
                + "`OBSERVACAO_1`) "
                + "VALUES  (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        pst = conect.conn.prepareStatement(sql);
        pst.setString(1, casas.getClassificacao());
        pst.setString(2, casas.getTipoImovel());
        pst.setString(3, casas.getDataCadastro());
        pst.setString(4, casas.getEndereco());
        pst.setString(5, casas.getNumero_end());
        pst.setInt(6, casas.getCep());
        pst.setString(7, casas.getBairro());
        pst.setString(8, casas.getCidade());
        pst.setString(9, casas.getUf());
        pst.setString(10, casas.getReferencia());
        pst.setDouble(11, casas.getLote());
        pst.setFloat(12, casas.getQuadra());
        pst.setFloat(13, casas.getMedida());
        pst.setFloat(14, casas.getMetroQuadrado());
        pst.setInt(15, casas.getQuantidadeSala());
        pst.setInt(16, casas.getQuantidadeQuartos());
        pst.setInt(17, casas.getQuantidadeGaragem());
        pst.setInt(18, casas.getQuantidadeBanheiros());
        pst.setString(19, casas.getDataDeContrucao());
        pst.setString(20, casas.getDataReforma());
        pst.setString(21, casas.getObservacao1());

        int add = pst.executeUpdate();
        if (add > 0) {
            JOptionPane.showMessageDialog(null, "Dados gravados com sucesso");
        }
        return true;

    }

    public boolean update(Casas casas) throws ParseException {
        conect.conexao();
        String sql = "UPDATE `casas_cadastro` SET \n"
                + "  `CLASSIFICACAO` = ?,\n"
                + "  `TIPO_IMOVEL` = ?,\n"
                + "   DATA_CADASTRO =?,"
                + "  `ENDERECO` = ?,\n"
                + "  `N_ENDERECO` = ?,\n"
                + "  `CEP` = ?,\n"
                + "  `BAIRRO` = ?,\n"
                + "  `CIDADE` = ?,\n"
                + "  `UF` = ?,\n"
                + "  `REFERENCIA` = ?,\n"
                + "  `LOTE` = ?,\n"
                + "  `QUADRA` = ?,\n"
                + "  `MEDIDA` = ?,\n"
                + "  `METRO_QUADRADO` = ?,\n"
                + "  `QTDE_SALA` = ?,\n"
                + "  `QTDE_QUARTOS` = ?,\n"
                + "  `QTDE_GARAGEM` = ?,\n"
                + "  `QTDE_BWC` = ?,\n"
                + "  `DATA_CONSTRUCAO` = ?,\n"
                + "  `DATA_REFORMA` = ?,\n"
                + "  `OBSERVACAO_1` = ? \n"
                + "WHERE `CODIGO` = ?;";
        try {
            pst = conect.conn.prepareStatement(sql);
            pst.setString(1, casas.getClassificacao());
            pst.setString(2, casas.getTipoImovel());
            pst.setString(3, casas.getDataCadastro());
            pst.setString(4, casas.getEndereco());
            pst.setString(5, casas.getNumero_end());
            pst.setInt(6, casas.getCep());
            pst.setString(7, casas.getBairro());
            pst.setString(8, casas.getCidade());
            pst.setString(9, casas.getUf());
            pst.setString(10, casas.getReferencia());
            pst.setDouble(11, casas.getLote());
            pst.setFloat(12, casas.getQuadra());
            pst.setFloat(13, casas.getMedida());
            pst.setFloat(14, casas.getMetroQuadrado());
            pst.setInt(15, casas.getQuantidadeSala());
            pst.setInt(16, casas.getQuantidadeQuartos());
            pst.setInt(17, casas.getQuantidadeGaragem());
            pst.setInt(18, casas.getQuantidadeBanheiros());
            pst.setString(19, casas.getDataDeContrucao());
            pst.setString(20, casas.getDataReforma());
            pst.setString(21, casas.getObservacao1());
            pst.setInt(22, casas.getCodigoCasa());
            int add = pst.executeUpdate();
            while (add > 0) {
                JOptionPane.showMessageDialog(null, "Dados alterados com sucesso");
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Atenção\nNão e possivel alterar os dados\n" + ex.getMessage());
            return false;
        }

        return true;
    }

    public List<Casas> findAll(int codigo) throws SQLException {

        List<Casas> casa = new ArrayList<>();
        conect.conexao();
        String sql = "SELECT "
                + "`CODIGO`,"
                + " `CLASSIFICACAO`, "
                + "`TIPO_IMOVEL`,"
                + " DATE_FORMAT(`DATA_CADASTRO`, '%d/%m/%Y') AS DATA_CADASTRO,"
                + " `ENDERECO`,\n"
                + "`N_ENDERECO`,"
                + " `CEP`, "
                + "`BAIRRO`, "
                + "`CIDADE`, "
                + "`UF`, "
                + "`REFERENCIA`, "
                + "`LOTE`, "
                + "`QUADRA`, "
                + "`MEDIDA`,\n"
                + "`METRO_QUADRADO`,"
                + " `QTDE_SALA`, "
                + "`QTDE_GARAGEM`, "
                + "`QTDE_QUARTOS`, "
                + "`QTDE_BWC`, "
                + "DATE_FORMAT(`DATA_CONSTRUCAO`, '%d/%m/%Y') AS DATA_CONSTRUCAO,"
                + "DATE_FORMAT(`DATA_REFORMA`, '%d/%m/%Y') AS DATA_REFORMA, "
                + "`OBSERVACAO_1` FROM CASAS_CADASTRO WHERE CODIGO = '" + codigo + "'";
        try {
            pst = conect.conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                casa.add(new Casas(
                        rs.getInt("CODIGO"),
                        rs.getString("CLASSIFICACAO"),
                        rs.getString("TIPO_IMOVEL"),
                        rs.getString("DATA_CADASTRO"),
                        rs.getString("ENDERECO"),
                        rs.getString("N_ENDERECO"),
                        rs.getInt("CEP"),
                        rs.getString("BAIRRO"),
                        rs.getString("CIDADE"),
                        rs.getString("UF"),
                        rs.getString("REFERENCIA"),
                        rs.getFloat("LOTE"),
                        rs.getFloat("QUADRA"),
                        rs.getFloat("MEDIDA"),
                        rs.getFloat("METRO_QUADRADO"),
                        rs.getInt("QTDE_SALA"),
                        rs.getInt("QTDE_QUARTOS"),
                        rs.getInt("QTDE_GARAGEM"),
                        rs.getInt("QTDE_BWC"),
                        rs.getString("DATA_CONSTRUCAO"),
                        rs.getString("DATA_REFORMA"),
                        rs.getString("OBSERVACAO_1")));
            }
            return casa;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return casa;
        }

    }

    public List<Casas> findAllProximo(Casas c) throws SQLException {

        List<Casas> casa = new ArrayList<>();
        conect.conexao();
        //String sql = "SELECT * FROM CASAS_CADASTRO";
        try {
            //  pst = conect.conn.prepareStatement(sql);
            //  rs = pst.executeQuery();
            if (rs.next()) {
                casa.add(new Casas(rs.getInt("CODIGO"),
                        rs.getString("CLASSIFICACAO"),
                        rs.getString("TIPO_IMOVEL"),
                        rs.getString("ENDERECO"),
                        rs.getString("N_ENDERECO"),
                        rs.getInt("CEP"),
                        rs.getString("BAIRRO"),
                        rs.getString("CIDADE"),
                        rs.getString("UF"),
                        rs.getString("REFERENCIA"),
                        rs.getFloat("LOTE"),
                        rs.getFloat("QUADRA"),
                        rs.getFloat("MEDIDA"),
                        rs.getFloat("METRO_QUADRADO"),
                        rs.getInt("QTDE_SALA"),
                        rs.getInt("QTDE_QUARTOS"),
                        //rs.getInt("QTDE_SUITE") ,
                        rs.getInt("QTDE_GARAGEM"),
                        rs.getInt("QTDE_BWC"),
                        rs.getString("OBSERVACAO_1")));
            }
            return casa;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return casa;
        }

    }

    public List<Casas> findAllAnterior(Casas c) throws SQLException {

        List<Casas> casa = new ArrayList<>();
        conect.conexao();
        // String sql = "SELECT * FROM CASAS_CADASTRO";
        try {
            //     pst = conect.conn.prepareStatement(sql);
            //    rs = pst.executeQuery();
            if (rs.previous()) {
                casa.add(new Casas(rs.getInt("CODIGO"),
                        rs.getString("CLASSIFICACAO"),
                        rs.getString("TIPO_IMOVEL"),
                        rs.getString("ENDERECO"),
                        rs.getString("N_ENDERECO"),
                        rs.getInt("CEP"),
                        rs.getString("BAIRRO"),
                        rs.getString("CIDADE"),
                        rs.getString("UF"),
                        rs.getString("REFERENCIA"),
                        rs.getFloat("LOTE"),
                        rs.getFloat("QUADRA"),
                        rs.getFloat("MEDIDA"),
                        rs.getFloat("METRO_QUADRADO"),
                        rs.getInt("QTDE_SALA"),
                        rs.getInt("QTDE_QUARTOS"),
                        //rs.getInt("QTDE_SUITE") ,
                        rs.getInt("QTDE_GARAGEM"),
                        rs.getInt("QTDE_BWC"),
                        rs.getString("OBSERVACAO_1")));
            }
            return casa;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return casa;
        }

    }

    public boolean deleteCasa(int codigo) {
        try {
            String sql = "DELETE FROM CASAS_CADASTRO WHERE CODIGO = '" + codigo + "'";
            pst = conect.conn.prepareStatement(sql);
            pst.execute();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Registro deletado com sucesso");
                conect.conn.close();

            }
            return true;
        } catch (SQLException ex) {

            if (ex.getErrorCode() == 1451) {
                JOptionPane.showMessageDialog(null, "A casa esta vinculada a mas de um registro\n" + ex.getMessage());
            } else {
                JOptionPane.showMessageDialog(null, ex.getNextException());
            }
        }
        return true;
    }

    public boolean insertCasasItens(Casas casas) {
        String sql = "INSERT INTO casa_detalhes (CASAS_CADASTRO_CODIGO,HALL_SOCIAL,LAVABO,LIVING,SALA_VISITAS,SALA_JANTAR,SALA_INTIMA,\n"
                + "  HIDRO, CLOSET,LAREIRA,COPA,AREA_SERVICO, DEP_EMPREGADA,CHURRASQUEIRA, SACADA, DISPENSA, PISO,SALA_JOGOS,SALA_FESTAS,\n"
                + "  PISCINA, APTO_ANDAR, AGUA, ENERGIA, GAS, QUINTAL,  JARDIM, BWC_SOCIAL, COZINHA, ILUMINACAO_PUBLICA,OBSERVACAO) \n"
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? );";

        try {
            pst = conect.conn.prepareStatement(sql);
            pst.setInt(1, casas.getCodigoCasa());
            pst.setString(2, casas.getHallSocial());
            pst.setString(3, casas.getLavabo());
            pst.setString(4, casas.getLiving());
            pst.setString(5, casas.getSalaDeVisitas());
            pst.setString(6, casas.getSalaDeJantar());
            pst.setString(7, casas.getSalaIntima());
            pst.setString(8, casas.getHidro());
            pst.setString(9, casas.getCloset());
            pst.setString(10, casas.getLareira());
            pst.setString(11, casas.getCopa());
            pst.setString(12, casas.getAreaDeServico());
            pst.setString(13, casas.getDepEmpregada());
            pst.setString(14, casas.getChurrasqueira());
            pst.setString(15, casas.getSacada());
            pst.setString(16, casas.getDispensa());
            pst.setString(17, casas.getPiso());
            pst.setString(18, casas.getSalaDeJogos());
            pst.setString(19, casas.getSalaDeFesta());
            pst.setString(20, casas.getPiscina());
            pst.setString(21, casas.getAptoAndar());
            pst.setString(22, casas.getAgua());
            pst.setString(23, casas.getEnergia());
            pst.setString(24, casas.getGas());
            pst.setString(25, casas.getQuintal());
            pst.setString(26, casas.getJardim());
            pst.setString(27, casas.getBwcSocial());
            pst.setString(28, casas.getCozinha());
            pst.setString(29, casas.getIlumPublica());
            pst.setString(30, casas.getObservacao2());

            int add = pst.executeUpdate();
            while (add > 0) {
                JOptionPane.showMessageDialog(null, "Detalhes inseridos com sucesso");
                return true;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Detalhes do erro" + ex.getMessage());
            return false;
        }

        return true;
    }

    public List<Casas> buscarCasasDetalhes(int codigo) {
        List<Casas> casa = new ArrayList<>();
        conect.conexao();
        String sql = "SELECT * FROM casa_detalhes WHERE CASAS_CADASTRO_CODIGO = '" + codigo + "'";
        try {
            pst = conect.conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                casa.add(new Casas(
                        rs.getInt("CODIGO"),
                        rs.getString("HALL_SOCIAL"),
                        rs.getString("LAVABO"),
                        rs.getString("LIVING"),
                        rs.getString("SALA_VISITAS"),
                        rs.getString("SALA_JANTAR"),
                        rs.getString("SALA_INTIMA"),
                        rs.getString("HIDRO"),
                        rs.getString("CLOSET"),
                        rs.getString("LAREIRA"),
                        rs.getString("COPA"),
                        rs.getString("AREA_SERVICO"),
                        rs.getString("DEP_EMPREGADA"),
                        rs.getString("CHURRASQUEIRA"),
                        rs.getString("SACADA"),
                        rs.getString("DISPENSA"),
                        rs.getString("PISO"),
                        rs.getString("SALA_JOGOS"),
                        rs.getString("SALA_FESTAS"),
                        rs.getString("PISCINA"),
                        rs.getString("APTO_ANDAR"),
                        rs.getString("AGUA"),
                        rs.getString("ENERGIA"),
                        rs.getString("GAS"),
                        rs.getString("QUINTAL"),
                        rs.getString("JARDIM"),
                        rs.getString("BWC_SOCIAL"),
                        rs.getString("COZINHA"),
                        rs.getString("ILUMINACAO_PUBLICA"),
                        rs.getString("OBSERVACAO")));
            }
            return casa;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return casa;

    }

}
