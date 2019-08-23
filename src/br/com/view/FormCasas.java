package br.com.view;

import br.com.controller.CasaControle;
import br.com.controller.UpperCase;
import br.com.controller.WebServiceCep;
import br.com.model.Casas;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego Danniel
 */
public class FormCasas extends javax.swing.JInternalFrame {

    private static FormPesquisaCasas pesquisaCasas;
    private static FormPesquisarCidades cidades;
    private static FormPesquisarProprietarios proprietarios;
    private static FormCasas casaCadastro;
    private final CasaControle casas;
    private static Casas cs;
    JFileChooser file;
    String hall;
    String lareira;
    String salaDeJogos;
    String jardim;
    String cozinha;
    String lavabo;
    String copa;
    String salaDeVisitas;
    String bwcSocial;
    String ilumPublica;
    String living;
    String areaDeServico;
    String piscina;
    String salaIntima;
    String quintal;
    String depEmpregada;
    String aptoAndar;
    String piso;
    String dispensa;
    String salaDeJantar;
    String churrasqueira;
    String agua;
    String hidro;
    String closet;
    String energia;
    String gas;
    String sacada;
    String salaDeFesta;
    String observacao2;

    public FormCasas() {
        initComponents();
        txtInscricaoMunicipal.setVisible(false);
        jblInscricaoMunicipal.setVisible(false);
        jTabbedPane1.getSelectedComponent();
        casas = new CasaControle();

        txtBairro.setDocument(new UpperCase().getDocument());
        txtCaminhoFoto.setDocument(new UpperCase().getDocument());
        txtCidades.setDocument(new UpperCase().getDocument());
        txtEndereco.setDocument(new UpperCase().getDocument());
        txtObservacao.setDocument(new UpperCase().getDocument());
        txtCidades.setDocument(new UpperCase().getDocument());
        txtReferencia.setDocument(new UpperCase().getDocument());
        jlabelMensagemDeAviso.setVisible(false);
    }

    public static FormCasas getCasasCadastro() {
        if (casaCadastro == null) {
            casaCadastro = new FormCasas();
        }
        return casaCadastro;
    }

    public void selecionarPessoa() {
        if (rbtJuridico.isSelected()) {
            jblCpf.setText("CNPJ");
            jblRg.setText("INSCRIÇÃO ESTADUAL");
            txtInscricaoMunicipal.setVisible(true);
            jblInscricaoMunicipal.setVisible(true);
        }
        if (rbtFisico.isSelected()) {
            jblCpf.setText("CPF");
            jblRg.setText("RG");
            txtInscricaoMunicipal.setVisible(false);
            jblInscricaoMunicipal.setVisible(false);
        }
    }

    public void chamarTelaPesquisaCasas() {
        pesquisaCasas = new FormPesquisaCasas(this, true);
        pesquisaCasas.setVisible(true);
    }

    public void chamarTelaPesquisaCidades() {
        cidades = new FormPesquisarCidades(this, true);
        cidades.setVisible(true);
    }

    public void chamarTelaPesquisaProprietarios() {
        proprietarios = new FormPesquisarProprietarios(this, true);
        proprietarios.setVisible(true);
    }

    public void abrirFotos() {

        file = new JFileChooser("C:\\SysMac Imobiliario\\Fotos casas");
        file.setDialogTitle("Selecione a foto");
        file.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int opc = file.showOpenDialog(this.file);
        if (opc == JFileChooser.APPROVE_OPTION) {
            File files = new File("Caminho");
            files = file.getSelectedFile();
            String fileName = files.getAbsolutePath();
            txtCaminhoFoto.setText(fileName);
            ImageIcon imagem = new ImageIcon(file.getSelectedFile().getPath());
            imagem.getImageLoadStatus();
            jlbRecebeFotos.setIcon(new ImageIcon(imagem.getImage().getScaledInstance(jlbRecebeFotos.getWidth(),
                    jlbRecebeFotos.getHeight(), Image.SCALE_DEFAULT)));
        }

    }

    public void buscaCep() {
        //Faz a busca para o cep 58043-280
        WebServiceCep webServiceCep = WebServiceCep.searchCep(txtCep.getText());
        //A ferramenta de busca ignora qualquer caracter que n?o seja n?mero.

        //caso a busca ocorra bem, imprime os resultados.
        if (webServiceCep.wasSuccessful()) {
            txtEndereco.setText(webServiceCep.getLogradouroFull());
            txtCidades.setText(webServiceCep.getCidade());
            txtBairro.setText(webServiceCep.getBairro());
            jcboUf.setSelectedItem(webServiceCep.getUf());
            System.out.println("Cep: " + webServiceCep.getCep());
            System.out.println("Logradouro: " + webServiceCep.getLogradouroFull());
            System.out.println("Bairro: " + webServiceCep.getBairro());
            System.out.println("Cidade: "
                    + webServiceCep.getCidade() + "/" + webServiceCep.getUf());

            //caso haja problemas imprime as exce??es.
        } else {
            //JOptionPane.showMessageDialog(null, "Erro numero: " + webServiceCep.getResulCode());

            JOptionPane.showMessageDialog(null, "Descrição do erro: " + webServiceCep.getResultText());
        }
    }

    public void novoId(Casas cs) throws SQLException {
        this.casas.novoId();

        casas.getCasaCadastroModel().forEach((Casas casas1) -> {
            txtCodigoCasa.setText((String.valueOf(casas1.getCodigoCasa())));
            txtBairro.setEnabled(true);
            txtBairro.setEnabled(true);
            txtCaminhoFoto.setEnabled(true);
            txtCep.setEnabled(true);
            txtCidades.setEnabled(true);
            txtCodigoCasa.setEnabled(true);
            txtCodigoCidade.setEnabled(true);
            txtCpf.setEnabled(true);
            txtEndereco.setEnabled(true);
            txtInscricaoMunicipal.setEnabled(true);
            txtLote.setEnabled(true);
            txtMedidas.setEnabled(true);
            txtMetroQ.setEnabled(true);
            txtNEndereco.setEnabled(true);
            txtObservacao.setEnabled(true);
            txtQtdeGaragem.setEnabled(true);
            txtQuadra.setEnabled(true);
            txtReferencia.setEnabled(true);
            txtRg.setEnabled(true);
            txtqtdeBwc.setEnabled(true);
            txtqtdeQuartos.setEnabled(true);
            txtqtdeSalas.setEnabled(true);
            txtqtdeSuite.setEnabled(true);
            jcboClassificacao.setEnabled(true);
            jcboTipoImovel.setEnabled(true);

            txtBairro.setText(null);
            txtBairro.setText(null);
            txtCaminhoFoto.setText(null);
            txtCep.setText(null);
            txtCidades.setText(null);

            txtEndereco.requestFocus(true);

            txtCodigoCidade.setText(null);
            txtCpf.setText(null);
            txtEndereco.setText(null);
            txtInscricaoMunicipal.setText(null);
            txtLote.setText("");
            txtMedidas.setText("");
            txtMetroQ.setText("");
            txtNEndereco.setText(null);
            txtObservacao.setText(null);
            txtQtdeGaragem.setText(null);
            txtQuadra.setText("");
            txtReferencia.setText(null);
            txtRg.setText(null);
            txtqtdeBwc.setText("");
            txtqtdeQuartos.setText("");
            txtqtdeSalas.setText("");
            txtqtdeSuite.setText("");
            txtQtdeGaragem.setText("");
            jcboClassificacao.setSelectedItem(null);
            jcboTipoImovel.setSelectedItem(null);
            jcboUf.setSelectedItem(null);

            jcboUf.setEnabled(true);
            btnGravar.setEnabled(true);
            Calendar data = Calendar.getInstance();
            jdateContrucao.setDate(null);
            jdateReforma.setDate(null);
            jdateCadastro.setDate(data.getTime());

        });

    }

    public void cadastrarCasas(CasaControle casas) throws ParseException, SQLException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
        if (jcboClassificacao.getSelectedItem().equals(false)) {
            JOptionPane.showMessageDialog(null, "Campo de Classificação sem preenchimento!");
        } else {
            casas.getCasaCadastro().setClassificacao(jcboClassificacao.getSelectedItem().toString());
        }

        casas.getCasaCadastro().setTipoImovel(jcboTipoImovel.getSelectedItem().toString());
        casas.getCasaCadastro().setDataCadastro(jdateCadastro.getDateFormatString());
        casas.getCasaCadastro().setEndereco(txtEndereco.getText());
        casas.getCasaCadastro().setNumero_end(txtNEndereco.getText());
        casas.getCasaCadastro().setCep(Integer.parseInt(txtCep.getText()));
        casas.getCasaCadastro().setBairro(txtBairro.getText());
        casas.getCasaCadastro().setCidade(txtCidades.getText());
        casas.getCasaCadastro().setUf(jcboUf.getSelectedItem().toString());
        casas.getCasaCadastro().setReferencia(txtReferencia.getText());
        casas.getCasaCadastro().setLote(Float.parseFloat(txtLote.getText().replace(",", ".")));
        casas.getCasaCadastro().setQuadra(Float.parseFloat(txtQuadra.getText().replace(",", ".")));
        casas.getCasaCadastro().setMedida(Float.parseFloat(txtMedidas.getText().replace(",", ".")));
        casas.getCasaCadastro().setMetroQuadrado(Float.parseFloat(txtMetroQ.getText().replace(",", ".")));
        casas.getCasaCadastro().setQuantidadeQuartos(Integer.parseInt(txtqtdeQuartos.getText()));
        casas.getCasaCadastro().setQuantidadeSala(Integer.parseInt(txtqtdeSalas.getText()));
        casas.getCasaCadastro().setQuantidadeGaragem(Integer.parseInt(txtQtdeGaragem.getText()));
        casas.getCasaCadastro().setQuantidadeBanheiros(Integer.parseInt(txtqtdeBwc.getText()));
        casas.getCasaCadastro().setDataDeContrucao(sdf.format(jdateContrucao.getDate()));
        casas.getCasaCadastro().setDataReforma(sdf.format(jdateReforma.getDate()));
        casas.getCasaCadastro().setDataCadastro(sdf.format(jdateCadastro.getDate()));
        casas.getCasaCadastro().setObservacao1(txtObservacao.getText());
        boolean add = casas.inserirCasa();
        btnGravar.setEnabled(false);
        btnNovoI.setText("NOVO");
        btnPesquisar.setEnabled(true);
        desabilitarCampos();

    }

    public void alterarCasas(CasaControle casas) throws ParseException {
        int confirma = JOptionPane.showConfirmDialog(null, "Deseja processeguir com a alteração dos dados", "ATENÇÃO", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
            casas.getCasaCadastro().setCodigoCasa(Integer.parseInt(txtCodigoCasa.getText()));
            casas.getCasaCadastro().setClassificacao(jcboClassificacao.getSelectedItem().toString());
            casas.getCasaCadastro().setTipoImovel(jcboTipoImovel.getSelectedItem().toString());
            casas.getCasaCadastro().setDataCadastro(jdateCadastro.getDateFormatString());
            casas.getCasaCadastro().setEndereco(txtEndereco.getText());
            casas.getCasaCadastro().setNumero_end(txtNEndereco.getText());
            casas.getCasaCadastro().setCep(Integer.parseInt(txtCep.getText()));
            casas.getCasaCadastro().setCidade(txtCidades.getText());
            casas.getCasaCadastro().setUf(jcboUf.getSelectedItem().toString());
            casas.getCasaCadastro().setReferencia(txtReferencia.getText());
            casas.getCasaCadastro().setLote(Float.parseFloat(txtLote.getText().replace(",", ".")));
            casas.getCasaCadastro().setQuadra(Float.parseFloat(txtQuadra.getText().replace(",", ".")));
            casas.getCasaCadastro().setMedida(Float.parseFloat(txtMedidas.getText().replace(",", ".")));
            casas.getCasaCadastro().setMetroQuadrado(Float.parseFloat(txtMetroQ.getText().replace(",", ".")));
            casas.getCasaCadastro().setQuantidadeQuartos(Integer.parseInt(txtqtdeQuartos.getText()));
            casas.getCasaCadastro().setQuantidadeSala(Integer.parseInt(txtqtdeSalas.getText()));
            casas.getCasaCadastro().setQuantidadeGaragem(Integer.parseInt(txtQtdeGaragem.getText()));
            casas.getCasaCadastro().setQuantidadeBanheiros(Integer.parseInt(txtqtdeBwc.getText()));
            casas.getCasaCadastro().setDataDeContrucao(sdf.format(jdateContrucao.getDate()));
            casas.getCasaCadastro().setDataReforma(sdf.format(jdateReforma.getDate()));
            casas.getCasaCadastro().setDataCadastro(sdf.format(jdateCadastro.getDate()));
            casas.getCasaCadastro().setObservacao1(txtObservacao.getText());
            if (txtBairro.getText().isEmpty()) {
                this.casas.getCasaCadastro().setBairro(null);
            } else {
                casas.getCasaCadastro().setBairro(txtBairro.getText());
            }

            boolean atualizarCasa = casas.atualizarCasa();
        }

    }

    public void buscarCasaGeral(Casas cs) {
        casas.buscarCasaGeral(Integer.parseInt(txtCodigoCasa.getText()));
        if (casas.getCasaCadastroModel().isEmpty()) {
            jlabelMensagemDeAviso.setText("Essa casa não possui detalhes!");
            jlabelMensagemDeAviso.setVisible(true);
        } else {
            casas.getCasaCadastroModel().forEach((casas1) -> {

                try {
                    jlabelMensagemDeAviso.setVisible(false);

                    txtCodigoDetalhes.setText(String.valueOf(casas1.getCodigoCasaCadastroItem()));
                    jcboAgua.setSelectedItem(casas1.getAgua());
                    jcboAptoAndar.setSelectedItem(casas1.getAptoAndar());
                    jcboAreaDeServico.setSelectedItem(casas1.getAreaDeServico());
                    jcboBwcSocial.setSelectedItem(casas1.getBwcSocial());
                    jcboChurrasqueira.setSelectedItem(casas1.getChurrasqueira());
                    jcboClassificacao.setSelectedItem(casas1.getClassificacao());
                    jcboCloset.setSelectedItem(casas1.getCloset());
                    jcboCopa.setSelectedItem(casas1.getCopa());
                    jcboCozinha.setSelectedItem(casas1.getCozinha());
                    jcboDepEmpregada.setSelectedItem(casas1.getDepEmpregada());
                    jcboDispensa.setSelectedItem(casas1.getDispensa());
                    jcboEnergia.setSelectedItem(casas1.getEnergia());
                    jcboGas.setSelectedItem(casas1.getGas());
                    if (casas1.getHallSocial().endsWith("S")) {
                        jcboHall.setSelectedItem("SIM");
                    } else {
                        jcboHall.setSelectedItem("NÃO");
                    }

                    jcboHall.setSelectedItem(casas1.getHallSocial());
                    jcboHidro.setSelectedItem(casas1.getHidro());
                    jcboIlumPublica.setSelectedItem(casas1.getIlumPublica());
                    jcboJardim.setSelectedItem(casas1.getJardim());
                    jcboLareira.setSelectedItem(casas1.getLareira());
                    jcboLavabo.setSelectedItem(casas1.getLavabo());
                    jcboLiving.setSelectedItem(casas1.getLiving());
                    jcboPiscina.setSelectedItem(casas1.getPiscina());
                    jcboPiso.setSelectedItem(casas1.getPiso());
                    jcboQuintal.setSelectedItem(casas1.getQuintal());
                    jcboSacada.setSelectedItem(casas1.getSacada());
                    jcboSalaDeFesta.setSelectedItem(casas1.getSalaDeFesta());
                    jcboSalaDeJantar.setSelectedItem(casas1.getSalaDeJantar());
                    jcboSalaDeVisitas.setSelectedItem(casas1.getSalaDeVisitas());
                    jcboSalaIntima.setSelectedItem(casas1.getSalaIntima());
                    jcboTipoImovel.setSelectedItem(casas1.getTipoImovel());

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());

                }

            });
        }
    }

    public void buscarCasa(Casas cs) throws SQLException {

        casas.listarCasas(Integer.parseInt(txtCodigoCasa.getText()));
        if (casas.getCasaCadastroModel().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não foi encontrado nenhum resultado esperado");
        } else {
            casas.getCasaCadastroModel().forEach((casas1) -> {
                try {
                    buscarCasaGeral(cs);
                    SimpleDateFormat sdf = new SimpleDateFormat();
                    txtCodigoCasa.setText(String.valueOf(casas1.getCodigoCasa()));
                    jcboClassificacao.setSelectedItem(casas1.getClassificacao());
                    jcboTipoImovel.setSelectedItem(casas1.getTipoImovel());
                    txtEndereco.setText(casas1.getEndereco());
                    jcboUf.setSelectedItem(casas1.getUf());
                    txtNEndereco.setText(casas1.getNumero_end());
                    txtCep.setText(String.valueOf(casas1.getCep()));
                    txtReferencia.setText(casas1.getReferencia());
                    txtBairro.setText(casas1.getBairro());
                    txtCidades.setText(casas1.getCidade());
                    txtqtdeSalas.setText(String.valueOf(casas1.getQuantidadeSala()));
                    txtqtdeQuartos.setText(String.valueOf(casas1.getQuantidadeQuartos()));
                    txtqtdeBwc.setText(String.valueOf(casas1.getQuantidadeBanheiros()));
                    txtqtdeSuite.setText(String.valueOf(casas1.getQuantidadeSuite()));
                    txtQtdeGaragem.setText(String.valueOf(casas1.getQuantidadeGaragem()));
                    txtLote.setText(String.valueOf(casas1.getLote()).replace(".", ","));
                    txtQuadra.setText(String.valueOf(casas1.getQuadra()).replace(".", ","));
                    txtMedidas.setText(String.valueOf(casas1.getMedida()).replace(".", ","));
                    txtMetroQ.setText(String.valueOf(casas1.getMetroQuadrado()).replace(".", ","));
                    jdateCadastro.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(casas1.getDataCadastro()));
                    jdateContrucao.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(casas1.getDataDeContrucao()));
                    jdateReforma.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(casas1.getDataReforma()));
                    if (casas1.getObservacao1() == null) {
                        txtObservacao.setText("");
                    } else {
                        txtObservacao.setText(String.valueOf(casas1.getObservacao1()));
                    }

                    btnEcluir.setEnabled(true);
                    habilitarCampos();
                    btnGravar.setText("EDITAR");
                } catch (ParseException ex) {
                    Logger.getLogger(FormCasas.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

        }
    }

    public void buscarCasaProxima(Casas cs) throws SQLException {
        casas.listarCasasProximo();
        if (casas.getCasaCadastroModel().isEmpty()) {

        } else {
            casas.getCasaCadastroModel().forEach((casas1) -> {
                txtCodigoCasa.setText(String.valueOf(casas1.getCodigoCasa()));
                jcboClassificacao.setSelectedItem(casas1.getClassificacao());
                jcboTipoImovel.setSelectedItem(casas1.getTipoImovel());
                txtEndereco.setText(casas1.getEndereco());
                jcboUf.setSelectedItem(casas1.getUf());
                txtBairro.setText(casas1.getBairro());
                txtNEndereco.setText(casas1.getNumero_end());
                txtCep.setText(String.valueOf(casas1.getCep()));
                txtReferencia.setText(casas1.getReferencia());
                txtCidades.setText(casas1.getCidade());
                txtqtdeSalas.setText(String.valueOf(casas1.getQuantidadeSala()));
                txtqtdeQuartos.setText(String.valueOf(casas1.getQuantidadeQuartos()));
                txtqtdeBwc.setText(String.valueOf(casas1.getQuantidadeBanheiros()));
                txtqtdeSuite.setText(String.valueOf(casas1.getQuantidadeSuite()));
                txtQtdeGaragem.setText(String.valueOf(casas1.getQuantidadeGaragem()));
                txtLote.setText(String.valueOf(casas1.getLote()).replace(".", ","));
                txtQuadra.setText(String.valueOf(casas1.getQuadra()).replace(".", ","));
                txtMedidas.setText(String.valueOf(casas1.getMedida()).replace(".", ","));
                txtMetroQ.setText(String.valueOf(casas1.getMetroQuadrado()).replace(".", ","));
                if (casas1.getObservacao1() == null) {
                    txtObservacao.setText("");
                } else {
                    txtObservacao.setText(String.valueOf(casas1.getObservacao1()));
                }

            });

        }
    }

    public void buscarCasaAnterior(Casas cs) throws SQLException {
        casas.listarCasasAnterior();
        if (casas.getCasaCadastroModel().isEmpty()) {

        } else {
            casas.getCasaCadastroModel().forEach((casas1) -> {
                txtCodigoCasa.setText(String.valueOf(casas1.getCodigoCasa()));
                jcboClassificacao.setSelectedItem(casas1.getClassificacao());
                jcboUf.setSelectedItem(casas1.getUf());
                jcboTipoImovel.setSelectedItem(casas1.getTipoImovel());
                txtEndereco.setText(casas1.getEndereco());
                txtNEndereco.setText(casas1.getNumero_end());
                txtBairro.setText(casas1.getBairro());
                txtCep.setText(String.valueOf(casas1.getCep()));
                txtReferencia.setText(casas1.getReferencia());
                txtCidades.setText(casas1.getCidade());
                txtqtdeSalas.setText(String.valueOf(casas1.getQuantidadeSala()));
                txtqtdeQuartos.setText(String.valueOf(casas1.getQuantidadeQuartos()));
                txtqtdeBwc.setText(String.valueOf(casas1.getQuantidadeBanheiros()));
                txtqtdeSuite.setText(String.valueOf(casas1.getQuantidadeSuite()));
                txtQtdeGaragem.setText(String.valueOf(casas1.getQuantidadeGaragem()));
                txtLote.setText(String.valueOf(casas1.getLote()).replace(".", ","));
                txtQuadra.setText(String.valueOf(casas1.getQuadra()).replace(".", ","));
                txtMedidas.setText(String.valueOf(casas1.getMedida()).replace(".", ","));
                txtMetroQ.setText(String.valueOf(casas1.getMetroQuadrado()).replace(".", ","));
                if (casas1.getObservacao1() == null) {
                    txtObservacao.setText("");
                } else {
                    txtObservacao.setText(String.valueOf(casas1.getObservacao1()));
                }
            });

        }
    }

    public void deletarCasas(CasaControle casas) throws SQLException {
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir esse registro?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            casas.deletaCasas(Integer.parseInt(txtCodigoCasa.getText()));
            txtBairro.setText(null);
            txtBairro.setText(null);
            txtCaminhoFoto.setText(null);
            txtCep.setText(null);
            txtCidades.setText(null);
            txtCodigoCasa.setText(null);
            txtCodigoCidade.setText(null);
            txtCpf.setText(null);
            txtEndereco.setText(null);
            txtInscricaoMunicipal.setText(null);
            txtLote.setText("0,0");
            txtMedidas.setText("0,0");
            txtMetroQ.setText("0,0");
            txtNEndereco.setText(null);
            txtObservacao.setText(null);
            txtQtdeGaragem.setText(null);
            txtQuadra.setText("0,0");
            txtReferencia.setText(null);
            txtRg.setText(null);
            txtqtdeBwc.setText("0");
            txtqtdeQuartos.setText("0");
            txtqtdeSalas.setText("0");
            txtqtdeSuite.setText("0");
            txtQtdeGaragem.setText("0");
            jcboClassificacao.setSelectedItem(null);
            jcboTipoImovel.setSelectedItem(null);
            jcboUf.setSelectedItem(null);

        }

    }

    public void inserirCasasDetalhes(CasaControle casas) {
        try {
            casas.getCasaCadastro().setCodigoCasa(Integer.parseInt(txtCodigoCasa.getText()));
            casas.getCasaCadastro().setHallSocial(hall);
            casas.getCasaCadastro().setLavabo(lavabo);
            casas.getCasaCadastro().setLiving(living);
            casas.getCasaCadastro().setSalaDeVisitas(salaDeVisitas);
            casas.getCasaCadastro().setSalaDeJantar(salaDeJantar);
            casas.getCasaCadastro().setSalaIntima(salaIntima);
            casas.getCasaCadastro().setHidro(hidro);
            casas.getCasaCadastro().setCloset(closet);
            casas.getCasaCadastro().setLareira(lareira);
            casas.getCasaCadastro().setCopa(copa);
            casas.getCasaCadastro().setAreaDeServico(areaDeServico);
            casas.getCasaCadastro().setDepEmpregada(depEmpregada);
            casas.getCasaCadastro().setChurrasqueira(churrasqueira);
            casas.getCasaCadastro().setSacada(sacada);
            casas.getCasaCadastro().setDispensa(dispensa);
            casas.getCasaCadastro().setPiso(piso );
            casas.getCasaCadastro().setSalaDeJogos(salaDeJogos);
            casas.getCasaCadastro().setSalaDeFesta(salaDeFesta);
            casas.getCasaCadastro().setPiscina(piscina);
            casas.getCasaCadastro().setAptoAndar(aptoAndar);
            casas.getCasaCadastro().setAgua(agua);
            casas.getCasaCadastro().setGas(gas);
            casas.getCasaCadastro().setQuintal(quintal);
            casas.getCasaCadastro().setBwcSocial(bwcSocial);
            casas.getCasaCadastro().setIlumPublica(ilumPublica);
            casas.getCasaCadastro().setJardim(jardim);
            casas.getCasaCadastro().setCozinha(cozinha);
            casas.getCasaCadastro().setObservacao2(txtObservacao2.getText());
            casas.getCasaCadastro().setEnergia(energia);
            boolean add = casas.inserirCasaItem();
            if (add) {
                btnInserirCasasDetalhes.setEnabled(false);
            } else if (!add) {
                btnInserirCasasDetalhes.setEnabled(true);
            }

        } catch (NumberFormatException e) {
        }

    }

    public void habilitarCampos() {
        txtBairro.setEnabled(true);
        txtBairro.setEnabled(true);
        txtCaminhoFoto.setEnabled(true);
        txtCep.setEnabled(true);
        txtCidades.setEnabled(true);
        txtCodigoCasa.setEnabled(true);
        txtCodigoCidade.setEnabled(true);
        txtCpf.setEnabled(true);
        txtEndereco.setEnabled(true);
        txtInscricaoMunicipal.setEnabled(true);
        txtLote.setEnabled(true);
        txtMedidas.setEnabled(true);
        txtMetroQ.setEnabled(true);
        txtNEndereco.setEnabled(true);
        txtObservacao.setEnabled(true);
        txtQtdeGaragem.setEnabled(true);
        txtQuadra.setEnabled(true);
        txtReferencia.setEnabled(true);
        txtRg.setEnabled(true);
        txtqtdeBwc.setEnabled(true);
        txtqtdeQuartos.setEnabled(true);
        txtqtdeSalas.setEnabled(true);
        txtqtdeSuite.setEnabled(true);
        jcboClassificacao.setEnabled(true);
        jcboTipoImovel.setEnabled(true);

        btnGravar.setEnabled(true);
        btnBuscarCep.setEnabled(true);
        jcboUf.setEnabled(true);
    }

    public void desabilitarCampos() {
        txtBairro.setEnabled(false);
        txtBairro.setEnabled(false);
        txtCaminhoFoto.setEnabled(false);
        txtCep.setEnabled(false);
        txtCidades.setEnabled(false);
        // txtCodigoCasa.setEnabled(false);
        txtCodigoCidade.setEnabled(false);
        txtCpf.setEnabled(false);
        txtEndereco.setEnabled(false);
        txtInscricaoMunicipal.setEnabled(false);
        txtLote.setEnabled(false);
        txtMedidas.setEnabled(false);
        txtMetroQ.setEnabled(false);
        txtNEndereco.setEnabled(false);
        txtObservacao.setEnabled(false);
        txtQtdeGaragem.setEnabled(false);
        txtQuadra.setEnabled(false);
        txtReferencia.setEnabled(false);
        txtRg.setEnabled(false);
        txtqtdeBwc.setEnabled(false);
        txtqtdeQuartos.setEnabled(false);
        txtqtdeSalas.setEnabled(false);
        txtqtdeSuite.setEnabled(false);
        jcboClassificacao.setEnabled(false);
        jcboTipoImovel.setEnabled(false);
        jcboUf.setEnabled(false);
        jdateCadastro.setEnabled(false);
        jdateContrucao.setEnabled(false);
        jdateReforma.setEnabled(false);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel64 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        PanelPrincipal = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jcboClassificacao = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jcboTipoImovel = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jdateCadastro = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        txtEndereco = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtBairro = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtCodigoCidade = new javax.swing.JTextField();
        txtCidades = new javax.swing.JTextField();
        jcboUf = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtReferencia = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txtqtdeSalas = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtqtdeQuartos = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtqtdeBwc = new javax.swing.JTextField();
        txtqtdeSuite = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtQtdeGaragem = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtObservacao = new javax.swing.JEditorPane();
        jLabel71 = new javax.swing.JLabel();
        txtCep = new javax.swing.JTextField();
        jLabel73 = new javax.swing.JLabel();
        txtNEndereco = new javax.swing.JTextField();
        btnBuscarCep = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jdateReforma = new com.toedter.calendar.JDateChooser();
        jdateContrucao = new com.toedter.calendar.JDateChooser();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtLote = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtQuadra = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtMedidas = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtMetroQ = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jcboHall = new javax.swing.JComboBox<>();
        jLabel30 = new javax.swing.JLabel();
        jcboLavabo = new javax.swing.JComboBox<>();
        jcboLiving = new javax.swing.JComboBox<>();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jcboSalaDeVisitas = new javax.swing.JComboBox<>();
        jLabel33 = new javax.swing.JLabel();
        jcboSalaDeJantar = new javax.swing.JComboBox<>();
        jLabel34 = new javax.swing.JLabel();
        jcboBwcSocial = new javax.swing.JComboBox<>();
        jLabel35 = new javax.swing.JLabel();
        jcboHidro = new javax.swing.JComboBox<>();
        jLabel36 = new javax.swing.JLabel();
        jcboCloset = new javax.swing.JComboBox<>();
        jcboLareira = new javax.swing.JComboBox<>();
        jLabel37 = new javax.swing.JLabel();
        jcboCopa = new javax.swing.JComboBox<>();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jcboAreaDeServico = new javax.swing.JComboBox<>();
        jLabel40 = new javax.swing.JLabel();
        jcboDepEmpregada = new javax.swing.JComboBox<>();
        jLabel41 = new javax.swing.JLabel();
        jcboChurrasqueira = new javax.swing.JComboBox<>();
        jLabel42 = new javax.swing.JLabel();
        jcboSacada = new javax.swing.JComboBox<>();
        jLabel43 = new javax.swing.JLabel();
        jcboDispensa = new javax.swing.JComboBox<>();
        jLabel44 = new javax.swing.JLabel();
        jcboPiso = new javax.swing.JComboBox<>();
        jLabel45 = new javax.swing.JLabel();
        jcboSalaDeJogos = new javax.swing.JComboBox<>();
        jLabel46 = new javax.swing.JLabel();
        jcboSalaDeFesta = new javax.swing.JComboBox<>();
        jLabel47 = new javax.swing.JLabel();
        jcboPiscina = new javax.swing.JComboBox<>();
        jLabel48 = new javax.swing.JLabel();
        jcboAptoAndar = new javax.swing.JComboBox<>();
        jLabel49 = new javax.swing.JLabel();
        jcboAgua = new javax.swing.JComboBox<>();
        jLabel50 = new javax.swing.JLabel();
        jcboEnergia = new javax.swing.JComboBox<>();
        jLabel51 = new javax.swing.JLabel();
        jcboGas = new javax.swing.JComboBox<>();
        jLabel52 = new javax.swing.JLabel();
        jcboQuintal = new javax.swing.JComboBox<>();
        jLabel53 = new javax.swing.JLabel();
        jcboSalaIntima = new javax.swing.JComboBox<>();
        jLabel54 = new javax.swing.JLabel();
        jcboJardim = new javax.swing.JComboBox<>();
        jLabel55 = new javax.swing.JLabel();
        jcboCozinha = new javax.swing.JComboBox<>();
        jLabel56 = new javax.swing.JLabel();
        jcboIlumPublica = new javax.swing.JComboBox<>();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtObservacao2 = new javax.swing.JTextPane();
        btnInserirCasasDetalhes = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        txtCodigoDetalhes = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        jTextField23 = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        jTextField24 = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        jTextField25 = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        jTextField26 = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        jTextField27 = new javax.swing.JTextField();
        jTextField28 = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        jTextField29 = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        jComboBox34 = new javax.swing.JComboBox<>();
        jblCpf = new javax.swing.JLabel();
        txtCpf = new javax.swing.JTextField();
        jblRg = new javax.swing.JLabel();
        txtRg = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        jTextField32 = new javax.swing.JTextField();
        rbtJuridico = new javax.swing.JRadioButton();
        rbtFisico = new javax.swing.JRadioButton();
        jblInscricaoMunicipal = new javax.swing.JLabel();
        txtInscricaoMunicipal = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel65 = new javax.swing.JLabel();
        jComboBox35 = new javax.swing.JComboBox<>();
        jLabel66 = new javax.swing.JLabel();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jPanel11 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel12 = new javax.swing.JPanel();
        jLabel68 = new javax.swing.JLabel();
        jTextField30 = new javax.swing.JTextField();
        jLabel69 = new javax.swing.JLabel();
        jTextField31 = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jLabel70 = new javax.swing.JLabel();
        jComboBox36 = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jlbRecebeFotos = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel72 = new javax.swing.JLabel();
        txtCaminhoFoto = new javax.swing.JTextField();
        jButton16 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableNomeFotos = new javax.swing.JTable();
        jProgressBar1 = new javax.swing.JProgressBar();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jTextField18 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jTextField19 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jTextField20 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jTextField21 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jTextField22 = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel27 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        btnGravar = new javax.swing.JButton();
        btnPesquisar = new javax.swing.JButton();
        btnEcluir = new javax.swing.JButton();
        btnNovoI = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtCodigoCasa = new javax.swing.JTextField();
        jlabelMensagemDeAviso = new javax.swing.JLabel();

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel64.setText("jLabel64");

        setClosable(true);
        setIconifiable(true);
        setTitle("Cadastro de casas");
        setToolTipText("Cadastro de casas");
        setName("Cadastro de casas"); // NOI18N

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        PanelPrincipal.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Informações Gerais"));
        PanelPrincipal.setForeground(new java.awt.Color(255, 255, 255));

        jLabel2.setText("CLASSIFICAÇÃO");

        jcboClassificacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "\t", "COMERCIAL", "RESIDENCIAL" }));
        jcboClassificacao.setEnabled(false);

        jLabel3.setText("TIPO DE IMÓVEL");

        jcboTipoImovel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "\t", "APARTAMENTO", "CASA COMERCIAL", "CASA RESIDENCIAL", "LOTE", "MISTO", "SOBRADO" }));
        jcboTipoImovel.setEnabled(false);

        jLabel4.setText("DATA DE CADASTRO");

        jLabel5.setText("ENDEREÇO");

        txtEndereco.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtEndereco.setEnabled(false);

        jLabel6.setText("BAIRRO");

        txtBairro.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtBairro.setEnabled(false);

        jLabel7.setText("CIDADE:");

        txtCodigoCidade.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtCodigoCidade.setToolTipText("Press F2 para abrir a tela de Cidades");
        txtCodigoCidade.setEnabled(false);
        txtCodigoCidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoCidadeKeyPressed(evt);
            }
        });

        txtCidades.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtCidades.setEnabled(false);

        jcboUf.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jcboUf.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "\t", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO", " " }));
        jcboUf.setEnabled(false);

        jLabel8.setText("UF");

        jLabel9.setText("REFERÊNCIA");

        txtReferencia.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtReferencia.setEnabled(false);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "DETALHES DE APRESENTAÇÃO"));

        jLabel15.setText("SALA");

        txtqtdeSalas.setEnabled(false);
        txtqtdeSalas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtqtdeSalasKeyPressed(evt);
            }
        });

        jLabel16.setText("QUARTOS");

        txtqtdeQuartos.setEnabled(false);
        txtqtdeQuartos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtqtdeQuartosKeyPressed(evt);
            }
        });

        jLabel17.setText("BWC");

        txtqtdeBwc.setEnabled(false);
        txtqtdeBwc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtqtdeBwcKeyPressed(evt);
            }
        });

        txtqtdeSuite.setEnabled(false);
        txtqtdeSuite.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtqtdeSuiteKeyPressed(evt);
            }
        });

        jLabel18.setText("SUITE");

        txtQtdeGaragem.setEnabled(false);
        txtQtdeGaragem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtQtdeGaragemKeyPressed(evt);
            }
        });

        jLabel19.setText("GARAGEM");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtQtdeGaragem, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel17)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtqtdeSalas, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                                .addComponent(txtqtdeQuartos))
                            .addComponent(txtqtdeBwc, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtqtdeSuite, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtqtdeSalas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtqtdeQuartos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtqtdeBwc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtqtdeSuite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtQtdeGaragem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "OBSERVAÇÕES"));

        txtObservacao.setEnabled(false);
        jScrollPane3.setViewportView(txtObservacao);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );

        jLabel71.setText("CEP°");

        txtCep.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtCep.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtCep.setEnabled(false);

        jLabel73.setText("N°");

        txtNEndereco.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        txtNEndereco.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtNEndereco.setEnabled(false);

        btnBuscarCep.setText("Buscar CEP");
        btnBuscarCep.setEnabled(false);
        btnBuscarCep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarCepActionPerformed(evt);
            }
        });

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Construção e Reforma"));

        jLabel74.setText("Construida em:");

        jLabel75.setText("Ultima reforma:");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel75)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdateReforma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel74)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdateContrucao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel74, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jdateContrucao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jdateReforma, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel75, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(84, Short.MAX_VALUE))
        );

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Medidas"));
        jPanel17.setToolTipText("");

        jLabel10.setText("LOTE");

        txtLote.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtLote.setForeground(new java.awt.Color(255, 0, 0));
        txtLote.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtLote.setEnabled(false);
        txtLote.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLoteKeyPressed(evt);
            }
        });

        jLabel11.setText("QUADRA");

        txtQuadra.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtQuadra.setForeground(new java.awt.Color(255, 0, 0));
        txtQuadra.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtQuadra.setEnabled(false);
        txtQuadra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtQuadraKeyPressed(evt);
            }
        });

        jLabel14.setText("MEDIDAS");

        txtMedidas.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtMedidas.setForeground(new java.awt.Color(255, 0, 0));
        txtMedidas.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtMedidas.setEnabled(false);
        txtMedidas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMedidasKeyPressed(evt);
            }
        });

        jLabel12.setText("M²");

        txtMetroQ.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtMetroQ.setForeground(new java.awt.Color(255, 0, 0));
        txtMetroQ.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtMetroQ.setEnabled(false);
        txtMetroQ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMetroQKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel11))
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel10))
                            .addComponent(jLabel14))
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(txtMedidas))
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtQuadra))
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtLote))))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMetroQ, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtLote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtQuadra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtMedidas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtMetroQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PanelPrincipalLayout = new javax.swing.GroupLayout(PanelPrincipal);
        PanelPrincipal.setLayout(PanelPrincipalLayout);
        PanelPrincipalLayout.setHorizontalGroup(
            PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPrincipalLayout.createSequentialGroup()
                .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelPrincipalLayout.createSequentialGroup()
                        .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(txtReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelPrincipalLayout.createSequentialGroup()
                                .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel73)
                                    .addComponent(txtNEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtBairro)
                                    .addGroup(PanelPrincipalLayout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel71)
                                    .addGroup(PanelPrincipalLayout.createSequentialGroup()
                                        .addComponent(txtCep, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnBuscarCep))))
                            .addGroup(PanelPrincipalLayout.createSequentialGroup()
                                .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PanelPrincipalLayout.createSequentialGroup()
                                        .addComponent(txtCodigoCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCidades, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jcboUf, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addGap(0, 228, Short.MAX_VALUE))))
                    .addGroup(PanelPrincipalLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcboTipoImovel, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcboClassificacao, 0, 236, Short.MAX_VALUE)
                        .addGap(131, 131, 131)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdateCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelPrincipalLayout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        PanelPrincipalLayout.setVerticalGroup(
            PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPrincipalLayout.createSequentialGroup()
                .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelPrincipalLayout.createSequentialGroup()
                        .addComponent(jdateCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelPrincipalLayout.createSequentialGroup()
                        .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jcboTipoImovel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jcboClassificacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jLabel71)
                    .addComponent(jLabel73))
                .addGap(4, 4, 4)
                .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarCep, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PanelPrincipalLayout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addGap(0, 0, 0)
                            .addComponent(txtReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(PanelPrincipalLayout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addGap(0, 0, 0)
                            .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtCodigoCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtCidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(PanelPrincipalLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jcboUf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        jTabbedPane1.addTab("DADOS DO IMÓVEL", PanelPrincipal);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "DETALHES GERAL"));

        jLabel29.setText("HALL SOCIAL");

        jcboHall.setForeground(new java.awt.Color(56, 66, 33));
        jcboHall.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "SIM", "NÃO" }));
        jcboHall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcboHallActionPerformed(evt);
            }
        });

        jLabel30.setText("LAVABO");

        jcboLavabo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "SIM", "NÃO" }));
        jcboLavabo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcboLavaboActionPerformed(evt);
            }
        });

        jcboLiving.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "SIM", "NÃO" }));
        jcboLiving.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcboLivingActionPerformed(evt);
            }
        });

        jLabel31.setText("LIVING");

        jLabel32.setText("SALA DE VISITAS");

        jcboSalaDeVisitas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "SIM", "NÃO" }));
        jcboSalaDeVisitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcboSalaDeVisitasActionPerformed(evt);
            }
        });

        jLabel33.setText("SALA DE JANTAR");

        jcboSalaDeJantar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "SIM", "NÃO" }));
        jcboSalaDeJantar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcboSalaDeJantarActionPerformed(evt);
            }
        });

        jLabel34.setText("SALA INTIMA");

        jcboBwcSocial.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "SIM", "NÃO" }));
        jcboBwcSocial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcboBwcSocialActionPerformed(evt);
            }
        });

        jLabel35.setText("HIDRO");

        jcboHidro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "SIM", "NÃO" }));
        jcboHidro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcboHidroActionPerformed(evt);
            }
        });

        jLabel36.setText("CLOSET");

        jcboCloset.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "SIM", "NÃO" }));
        jcboCloset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcboClosetActionPerformed(evt);
            }
        });

        jcboLareira.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "SIM", "NÃO" }));
        jcboLareira.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcboLareiraActionPerformed(evt);
            }
        });

        jLabel37.setText("LAREIRA");

        jcboCopa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "SIM", "NÃO" }));
        jcboCopa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcboCopaActionPerformed(evt);
            }
        });

        jLabel38.setText("COPA");

        jLabel39.setText("ÁREA SERVIÇO");

        jcboAreaDeServico.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "SIM", "NÃO" }));
        jcboAreaDeServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcboAreaDeServicoActionPerformed(evt);
            }
        });

        jLabel40.setText("DEP.EMPREGADA");

        jcboDepEmpregada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "SIM", "NÃO" }));
        jcboDepEmpregada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcboDepEmpregadaActionPerformed(evt);
            }
        });

        jLabel41.setText("CHURRASQUEIRA");

        jcboChurrasqueira.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "SIM", "NÃO" }));
        jcboChurrasqueira.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcboChurrasqueiraActionPerformed(evt);
            }
        });

        jLabel42.setText("SACADA");

        jcboSacada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "SIM", "NÃO" }));
        jcboSacada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcboSacadaActionPerformed(evt);
            }
        });

        jLabel43.setText("DISPENSA");

        jcboDispensa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "SIM", "NÃO" }));
        jcboDispensa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcboDispensaActionPerformed(evt);
            }
        });

        jLabel44.setText("PISO");

        jcboPiso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "SIM", "NÃO" }));
        jcboPiso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcboPisoActionPerformed(evt);
            }
        });

        jLabel45.setText("SALA DE JOGOS");

        jcboSalaDeJogos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "SIM", "NÃO" }));
        jcboSalaDeJogos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcboSalaDeJogosActionPerformed(evt);
            }
        });

        jLabel46.setText("SALA DE FESTA");

        jcboSalaDeFesta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "SIM", "NÃO" }));
        jcboSalaDeFesta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcboSalaDeFestaActionPerformed(evt);
            }
        });

        jLabel47.setText("PISCINA");

        jcboPiscina.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "SIM", "NÃO" }));
        jcboPiscina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcboPiscinaActionPerformed(evt);
            }
        });

        jLabel48.setText("APTO P/ ANDAR");

        jcboAptoAndar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "SIM", "NÃO" }));
        jcboAptoAndar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcboAptoAndarActionPerformed(evt);
            }
        });

        jLabel49.setText("ÁGUA");

        jcboAgua.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "SIM", "NÃO" }));
        jcboAgua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcboAguaActionPerformed(evt);
            }
        });

        jLabel50.setText("ENERGIA");

        jcboEnergia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "SIM", "NÃO" }));
        jcboEnergia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcboEnergiaActionPerformed(evt);
            }
        });

        jLabel51.setText("GÁS");

        jcboGas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "SIM", "NÃO" }));
        jcboGas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcboGasActionPerformed(evt);
            }
        });

        jLabel52.setText("QUINTAL");

        jcboQuintal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "SIM", "NÃO" }));
        jcboQuintal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcboQuintalActionPerformed(evt);
            }
        });

        jLabel53.setText("JARDIM");

        jcboSalaIntima.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "SIM", "NÃO" }));
        jcboSalaIntima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcboSalaIntimaActionPerformed(evt);
            }
        });

        jLabel54.setText("BWC SOCIAL");

        jcboJardim.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "SIM", "NÃO" }));
        jcboJardim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcboJardimActionPerformed(evt);
            }
        });

        jLabel55.setText("COZINHA");

        jcboCozinha.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "SIM", "NÃO" }));
        jcboCozinha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcboCozinhaActionPerformed(evt);
            }
        });

        jLabel56.setText("ILUM. PUBLICA");

        jcboIlumPublica.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "SIM", "NÃO" }));
        jcboIlumPublica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcboIlumPublicaActionPerformed(evt);
            }
        });

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Observações"));

        jScrollPane2.setViewportView(txtObservacao2);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnInserirCasasDetalhes.setText("INSERIR");
        btnInserirCasasDetalhes.setEnabled(false);
        btnInserirCasasDetalhes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInserirCasasDetalhesActionPerformed(evt);
            }
        });

        jButton6.setText("EXCLUIR");

        txtCodigoDetalhes.setEditable(false);

        jLabel13.setText("Código de detalhes");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel8Layout.createSequentialGroup()
                                            .addGap(54, 54, 54)
                                            .addComponent(jLabel30)
                                            .addGap(4, 4, 4)
                                            .addComponent(jcboLavabo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                            .addGap(21, 21, 21)
                                            .addComponent(jLabel31)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jcboLiving, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel8Layout.createSequentialGroup()
                                            .addContainerGap()
                                            .addComponent(jLabel32)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jcboSalaDeVisitas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel8Layout.createSequentialGroup()
                                                .addComponent(jLabel33)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jcboSalaDeJantar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel8Layout.createSequentialGroup()
                                                .addComponent(jLabel50)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jcboEnergia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel38, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel37, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jcboLareira, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jcboCopa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel41)
                                            .addComponent(jLabel51)
                                            .addComponent(jLabel40))
                                        .addGap(4, 4, 4)
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jcboDepEmpregada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jcboGas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jcboChurrasqueira, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                        .addGap(24, 24, 24)
                                        .addComponent(jLabel39)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jcboAreaDeServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(9, 9, 9)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel47)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jcboPiscina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel46)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jcboSalaDeFesta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel45)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jcboSalaDeJogos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel49)
                                            .addComponent(jLabel48)
                                            .addComponent(jLabel42))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jcboAptoAndar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jcboAgua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jcboSacada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel8Layout.createSequentialGroup()
                                            .addComponent(jLabel34)
                                            .addGap(4, 4, 4)
                                            .addComponent(jcboSalaIntima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel8Layout.createSequentialGroup()
                                                .addComponent(jLabel53)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jcboJardim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel8Layout.createSequentialGroup()
                                                .addComponent(jLabel54)
                                                .addGap(4, 4, 4)
                                                .addComponent(jcboBwcSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel35)
                                            .addComponent(jLabel44))
                                        .addGap(4, 4, 4)
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jcboPiso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jcboHidro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel52)
                                        .addGap(4, 4, 4)
                                        .addComponent(jcboQuintal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel43)
                                        .addGap(4, 4, 4)
                                        .addComponent(jcboDispensa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel36)
                                        .addGap(4, 4, 4)
                                        .addComponent(jcboCloset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel55)
                                            .addComponent(jLabel56))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jcboIlumPublica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jcboCozinha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jLabel29)
                                .addGap(4, 4, 4)
                                .addComponent(jcboHall, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodigoDetalhes, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnInserirCasasDetalhes)
                        .addGap(6, 6, 6)
                        .addComponent(jButton6)))
                .addContainerGap())
        );

        jPanel8Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jcboAreaDeServico, jcboChurrasqueira, jcboCopa, jcboDepEmpregada, jcboGas, jcboLareira});

        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel29)
                                .addComponent(jcboHall, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel45)
                                .addComponent(jcboSalaDeJogos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jcboLareira, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel37))
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel55)
                                .addComponent(jcboCozinha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel53)
                                .addComponent(jcboJardim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jcboIlumPublica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel56))
                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel54)
                                        .addComponent(jcboSalaDeFesta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel46)
                                        .addComponent(jcboBwcSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jcboSalaIntima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jcboQuintal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel34)
                                        .addComponent(jLabel47)
                                        .addComponent(jcboPiscina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel52))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jcboPiso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jcboDispensa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel44)
                                        .addComponent(jcboAptoAndar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel48))
                                    .addComponent(jLabel43))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jcboCloset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel36)
                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jcboAgua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel49)
                                        .addComponent(jLabel35)
                                        .addComponent(jcboHidro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel42)
                                    .addComponent(jcboSacada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jcboCopa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel38))
                                        .addGap(10, 10, 10)
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel31)
                                                .addComponent(jcboLiving, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jcboAreaDeServico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel39)))
                                        .addGap(10, 10, 10)
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jcboSalaDeVisitas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel32)
                                            .addComponent(jcboDepEmpregada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel40)))
                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jcboLavabo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel30)))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jcboSalaDeJantar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel33)
                                    .addComponent(jLabel41)
                                    .addComponent(jcboChurrasqueira, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jcboEnergia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel50))
                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jcboGas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel51)))))
                        .addGap(19, 19, 19)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodigoDetalhes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnInserirCasasDetalhes)
                            .addComponent(jButton6))))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel8Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jcboAreaDeServico, jcboChurrasqueira, jcboCopa, jcboDepEmpregada, jcboGas, jcboLareira});

        jPanel8Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jcboEnergia, jcboHall, jcboLavabo, jcboLiving, jcboSalaDeJantar, jcboSalaDeVisitas});

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("DETALHES", jPanel3);

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "DADOS DO PROPRIETARIO"));

        jLabel57.setText("CÓDIGO");

        jTextField23.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField23KeyPressed(evt);
            }
        });

        jLabel58.setText("NOME");

        jLabel59.setText("ENDEREÇO");

        jLabel60.setText("BAIRRO");

        jLabel61.setText("CIDADE");

        jLabel62.setText("N°");

        jLabel63.setText("UF");

        jComboBox34.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jblCpf.setText("CPF");

        jblRg.setText("RG");

        jLabel67.setText("REFERÊNCIA");

        rbtJuridico.setText("JURIDICA");
        rbtJuridico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtJuridicoActionPerformed(evt);
            }
        });

        rbtFisico.setText("FISICA");
        rbtFisico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtFisicoActionPerformed(evt);
            }
        });

        jblInscricaoMunicipal.setText("INSCRIÇÃO MUNICIPAL");

        jButton1.setText("GRAVAR");

        jButton2.setText("INSERIR");

        jButton7.setText("PESQUISAR");

        jButton8.setText("EXCLUIR");

        jButton9.setText("CANCELAR");

        jLabel65.setText("NACIONALIDADE");

        jComboBox35.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel66.setText("DATA DE NASCIMENTO");

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "DADOS COMPLEMENTARES"));

        jLabel68.setText("VALOR DE RECEBIMENTO EM PORCENTAGEM NA VENDA");

        jLabel69.setText("VALOR DE RECEBIMENTO BRUTO NA VENDA");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel68)
                    .addComponent(jLabel69))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTextField31)
                    .addComponent(jTextField30, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel68)
                    .addComponent(jTextField30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel69)
                    .addComponent(jTextField31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane2.addTab("ACORDO DE VALORES", jPanel12);

        jLabel70.setText("NOME");

        jComboBox36.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "NOME", "MATRICULA"
            }
        ));
        jScrollPane4.setViewportView(jTable1);

        jButton13.setText("+>>");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setText("<<-");

        jButton15.setText("+");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jButton14)
                        .addGap(18, 18, 18)
                        .addComponent(jButton13)
                        .addContainerGap(753, Short.MAX_VALUE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel70)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox36, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4))))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel70)
                            .addComponent(jComboBox36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton15)))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton14)
                    .addComponent(jButton13))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("CORRETORES", jPanel13);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel57)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(191, 191, 191))
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel10Layout.createSequentialGroup()
                                    .addComponent(rbtFisico)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(rbtJuridico))
                                .addGroup(jPanel10Layout.createSequentialGroup()
                                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel60, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel59, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel61, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel58, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextField26)
                                        .addGroup(jPanel10Layout.createSequentialGroup()
                                            .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jTextField28))
                                        .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField24)))))
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(jLabel62))
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(jblCpf)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtCpf, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                                    .addComponent(jTextField29))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(jblRg)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtRg, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jblInscricaoMunicipal)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtInscricaoMunicipal, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(jLabel65)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBox35, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel63)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox34, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel67)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField32, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel66)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 3, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtJuridico)
                    .addComponent(rbtFisico))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel57)
                            .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel58)
                            .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jblCpf)
                            .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jblRg)
                            .addComponent(txtRg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jblInscricaoMunicipal)
                            .addComponent(txtInscricaoMunicipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel59)
                            .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel62)
                            .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel65)
                            .addComponent(jComboBox35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel60)
                            .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel67)
                            .addComponent(jTextField32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel66)))
                    .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61)
                    .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel63)
                    .addComponent(jComboBox34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton7)
                    .addComponent(jButton8)
                    .addComponent(jButton9)))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("DADOS DO PROPRIETÁRIO(S)", jPanel4);

        jlbRecebeFotos.setBackground(new java.awt.Color(204, 204, 255));
        jlbRecebeFotos.setForeground(new java.awt.Color(204, 204, 255));
        jlbRecebeFotos.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Fotos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "FOTOS DO IMÓVEL"));

        jLabel72.setText("CAMINHO DO ARQUIVO");

        jButton16.setText("BUSCAR");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jTableNomeFotos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "FOTO"
            }
        ));
        jScrollPane5.setViewportView(jTableNomeFotos);

        jButton23.setText("INSERIR");

        jButton24.setText("GRAVAR");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCaminhoFoto)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel72)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton24))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton16))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel72)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCaminhoFoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton24)
                    .addComponent(jButton23))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlbRecebeFotos, javax.swing.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jlbRecebeFotos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("FOTOS DO IMÓVEL", jPanel5);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "VALORES DE VENDAS E ALUGUEL"));

        jLabel21.setText("VALOR DE VENDA");

        jLabel22.setText("VALOR DE RENDA");

        jLabel23.setText("VALOR DE ENTRADA");

        jLabel24.setText("VALOR PRESTAÇÃO");

        jLabel25.setText("VALOR DO ALUGUEL");

        jLabel26.setText("VALOR IPTU");

        jLabel27.setText("SITUAÇÃO");

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel28.setText("ATÉ");

        jButton21.setText("INSERIR");

        jButton22.setText("GRAVAR");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel28)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel24)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel21))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel26)
                                    .addComponent(jLabel25))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(63, 63, 63)
                                    .addComponent(jLabel27))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jTextField22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                                .addComponent(jTextField21, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField20, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField19, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField18, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField17, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGap(0, 645, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton22)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton22)
                    .addComponent(jButton21))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("VALORES", jPanel15);

        btnGravar.setText("GRAVAR");
        btnGravar.setEnabled(false);
        btnGravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGravarActionPerformed(evt);
            }
        });

        btnPesquisar.setText("PESQUISAR");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        btnEcluir.setText("EXCLUIR");
        btnEcluir.setEnabled(false);
        btnEcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEcluirActionPerformed(evt);
            }
        });

        btnNovoI.setText("NOVO");
        btnNovoI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoIActionPerformed(evt);
            }
        });

        jButton18.setText(">");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton19.setText("<");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jButton20.setText("<<");

        jButton25.setText(">>");

        jLabel1.setText("CÓDIGO:");

        txtCodigoCasa.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtCodigoCasa.setToolTipText("Press F2 para abrir a Tela de Casas");
        txtCodigoCasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoCasaActionPerformed(evt);
            }
        });
        txtCodigoCasa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoCasaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoCasaKeyReleased(evt);
            }
        });

        jlabelMensagemDeAviso.setForeground(new java.awt.Color(204, 0, 51));
        jlabelMensagemDeAviso.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlabelMensagemDeAviso.setText("mensagem de aviso");
        jlabelMensagemDeAviso.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 954, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnNovoI, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPesquisar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGravar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton25))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodigoCasa, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlabelMensagemDeAviso, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCodigoCasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlabelMensagemDeAviso))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGravar)
                    .addComponent(btnPesquisar)
                    .addComponent(btnEcluir)
                    .addComponent(btnNovoI)
                    .addComponent(jButton18)
                    .addComponent(jButton19)
                    .addComponent(jButton20)
                    .addComponent(jButton25)))
        );

        setBounds(180, 50, 978, 504);
    }// </editor-fold>//GEN-END:initComponents

    private void rbtFisicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtFisicoActionPerformed
        // TODO add your handling code here:
        selecionarPessoa();
    }//GEN-LAST:event_rbtFisicoActionPerformed

    private void rbtJuridicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtJuridicoActionPerformed
        // TODO add your handling code here:
        selecionarPessoa();
    }//GEN-LAST:event_rbtJuridicoActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
        abrirFotos();
    }//GEN-LAST:event_jButton16ActionPerformed

    private void btnGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGravarActionPerformed
        try {
            if (btnGravar.getText().equals("GRAVAR")) {
                cadastrarCasas(casas);
                btnInserirCasasDetalhes.setEnabled(true);

            } else if (btnGravar.getText().equals("EDITAR")) {
                alterarCasas(casas);
                btnInserirCasasDetalhes.setEnabled(false);
                //btnGravar.setEnabled(false);

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_btnGravarActionPerformed

    private void txtCodigoCasaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoCasaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            chamarTelaPesquisaCasas();
        }
    }//GEN-LAST:event_txtCodigoCasaKeyPressed

    private void txtCodigoCidadeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoCidadeKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            chamarTelaPesquisaCidades();
        }
    }//GEN-LAST:event_txtCodigoCidadeKeyPressed

    private void jTextField23KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField23KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            chamarTelaPesquisaProprietarios();
        }
    }//GEN-LAST:event_jTextField23KeyPressed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed

        try {
            // TODO add your handling code here:
            buscarCasa(cs);
            buscarCasaGeral(cs);
        } catch (SQLException ex) {
            Logger.getLogger(FormCasas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        try {
            // TODO add your handling code here:
            buscarCasaProxima(cs);
        } catch (SQLException ex) {
            Logger.getLogger(FormCasas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        try {
            // TODO add your handling code here:
            buscarCasaAnterior(cs);
        } catch (SQLException ex) {
            Logger.getLogger(FormCasas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton19ActionPerformed

    private void btnEcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEcluirActionPerformed
        try {
            // TODO add your handling code here:
            deletarCasas(casas);
        } catch (SQLException ex) {
            Logger.getLogger(FormCasas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEcluirActionPerformed

    private void txtCodigoCasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoCasaActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            buscarCasa(cs);

        } catch (SQLException ex) {

        }
    }//GEN-LAST:event_txtCodigoCasaActionPerformed

    private void btnNovoIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoIActionPerformed

        try {
            if (btnNovoI.getText().equals("NOVO")) {
                novoId(cs);
                habilitarCampos();
                btnNovoI.setText("CANCELAR");
                btnGravar.setText("GRAVAR");
                btnPesquisar.setEnabled(false);
                btnEcluir.setEnabled(false);
            } else if (btnNovoI.getText().equals("CANCELAR")) {
                desabilitarCampos();
                btnGravar.setText("GRAVAR");
                btnNovoI.setText("NOVO");
                txtCodigoCasa.setText(null);
                txtBairro.setText(null);
                txtBairro.setText(null);
                txtCaminhoFoto.setText(null);
                txtCep.setText(null);
                txtCidades.setText(null);
                txtCodigoCasa.setText(null);
                txtCodigoCidade.setText(null);
                txtCpf.setText(null);
                txtEndereco.setText(null);
                txtInscricaoMunicipal.setText(null);
                txtLote.setText("0,0");
                txtMedidas.setText("0,0");
                txtMetroQ.setText("0,0");
                txtNEndereco.setText(null);
                txtObservacao.setText(null);
                txtQtdeGaragem.setText(null);
                txtQuadra.setText("0,0");
                txtReferencia.setText(null);
                txtRg.setText(null);
                txtqtdeBwc.setText("0");
                txtqtdeQuartos.setText("0");
                txtqtdeSalas.setText("0");
                txtqtdeSuite.setText("0");
                txtQtdeGaragem.setText("0");
                jcboClassificacao.setSelectedItem(null);
                jcboTipoImovel.setSelectedItem(null);
                jcboUf.setSelectedItem(null);
                btnGravar.setEnabled(false);
                btnEcluir.setEnabled(false);
                btnPesquisar.setEnabled(true);

            }

        } catch (SQLException ex) {
            Logger.getLogger(FormCasas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnNovoIActionPerformed

    private void btnBuscarCepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCepActionPerformed
        // TODO add your handling code here:
        buscaCep();
    }//GEN-LAST:event_btnBuscarCepActionPerformed

    private void txtCodigoCasaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoCasaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoCasaKeyReleased

    private void btnInserirCasasDetalhesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInserirCasasDetalhesActionPerformed
        // TODO add your handling code here:
        inserirCasasDetalhes(casas);

    }//GEN-LAST:event_btnInserirCasasDetalhesActionPerformed

    private void jcboHallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcboHallActionPerformed
        // TODO add your handling code here:
        if (jcboHall.getSelectedItem().equals("SIM")) {
            hall = "S";
        } else if (jcboHall.getSelectedItem().equals("NÃO")) {
            hall = "N";
        }
    }//GEN-LAST:event_jcboHallActionPerformed

    private void jcboLareiraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcboLareiraActionPerformed
        // TODO add your handling code here:
        if (jcboLareira.getSelectedItem().equals("SIM")) {
            lareira = "S";
        } else if (jcboLareira.getSelectedItem().equals("NÃO")) {
            lareira = "N";
        }
    }//GEN-LAST:event_jcboLareiraActionPerformed

    private void jcboSalaDeJogosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcboSalaDeJogosActionPerformed
        // TODO add your handling code here:
        if (jcboSalaDeJogos.getSelectedItem().equals("SIM")) {
            salaDeJogos = "S";
        } else if (jcboSalaDeJogos.getSelectedItem().equals("NÃO")) {
            salaDeJogos = "N";
        }
    }//GEN-LAST:event_jcboSalaDeJogosActionPerformed

    private void jcboJardimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcboJardimActionPerformed
        // TODO add your handling code here:
        if (jcboJardim.getSelectedItem().equals("SIM")) {
            jardim = "S";
        } else if (jcboJardim.getSelectedItem().equals("NÃO")) {
            jardim = "N";
        }
    }//GEN-LAST:event_jcboJardimActionPerformed

    private void jcboCozinhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcboCozinhaActionPerformed
        // TODO add your handling code here:
        if (jcboCozinha.getSelectedItem().equals("SIM")) {
            cozinha = "S";
        } else if (jcboCozinha.getSelectedItem().equals("NÃO")) {
            cozinha = "N";
        }
    }//GEN-LAST:event_jcboCozinhaActionPerformed

    private void txtqtdeSalasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtqtdeSalasKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_ENTER) {
            txtqtdeQuartos.requestFocus();
            txtqtdeQuartos.selectAll();
        }
    }//GEN-LAST:event_txtqtdeSalasKeyPressed

    private void txtqtdeQuartosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtqtdeQuartosKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_ENTER) {
            txtqtdeBwc.requestFocus();
            txtqtdeBwc.selectAll();
        }
    }//GEN-LAST:event_txtqtdeQuartosKeyPressed

    private void txtqtdeBwcKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtqtdeBwcKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_ENTER) {
            txtqtdeSuite.requestFocus();
            txtqtdeSuite.selectAll();
        }

    }//GEN-LAST:event_txtqtdeBwcKeyPressed

    private void txtqtdeSuiteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtqtdeSuiteKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_ENTER) {
            txtQtdeGaragem.requestFocus();
            txtQtdeGaragem.selectAll();
        }
    }//GEN-LAST:event_txtqtdeSuiteKeyPressed

    private void txtQtdeGaragemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQtdeGaragemKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_ENTER) {
            txtLote.requestFocus();
            txtLote.selectAll();
        }
    }//GEN-LAST:event_txtQtdeGaragemKeyPressed

    private void txtQuadraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuadraKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_ENTER) {
            txtMedidas.requestFocus();
            txtMedidas.selectAll();
        }

    }//GEN-LAST:event_txtQuadraKeyPressed

    private void txtMedidasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMedidasKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_ENTER) {
            txtMetroQ.requestFocus();
            txtMetroQ.selectAll();
        }
    }//GEN-LAST:event_txtMedidasKeyPressed

    private void txtMetroQKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMetroQKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_ENTER) {
            btnGravar.requestFocus();

        }
    }//GEN-LAST:event_txtMetroQKeyPressed

    private void txtLoteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLoteKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_ENTER) {
            txtQuadra.requestFocus();
            txtQuadra.selectAll();
        }
    }//GEN-LAST:event_txtLoteKeyPressed

    private void jcboLavaboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcboLavaboActionPerformed
        if (jcboLavabo.getSelectedItem().equals("SIM")) {
            lavabo = "S";
        } else if (jcboLavabo.getSelectedItem().equals("NÃO")) {
            lavabo = "N";
        }
    }//GEN-LAST:event_jcboLavaboActionPerformed

    private void jcboCopaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcboCopaActionPerformed
        // TODO add your handling code here:
        if (jcboCopa.getSelectedItem().equals("SIM")) {
            copa = "S";
        } else if (jcboCopa.getSelectedItem().equals("NÃO")) {
            copa = "N";
        }
    }//GEN-LAST:event_jcboCopaActionPerformed

    private void jcboSalaDeFestaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcboSalaDeFestaActionPerformed
        // TODO add your handling code here:
        if (jcboSalaDeFesta.getSelectedItem().equals("SIM")) {
            salaDeFesta = "S";
        } else if (jcboSalaDeFesta.getSelectedItem().equals("NÃO")) {
            salaDeFesta = "N";
        }
    }//GEN-LAST:event_jcboSalaDeFestaActionPerformed

    private void jcboBwcSocialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcboBwcSocialActionPerformed
        // TODO add your handling code here:
        if (jcboBwcSocial.getSelectedItem().equals("SIM")) {
            bwcSocial = "S";
        } else if (jcboBwcSocial.getSelectedItem().equals("NÃO")) {
            bwcSocial = "N";
        }
    }//GEN-LAST:event_jcboBwcSocialActionPerformed

    private void jcboIlumPublicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcboIlumPublicaActionPerformed
        // TODO add your handling code here:
        if (jcboIlumPublica.getSelectedItem().equals("SIM")) {
            ilumPublica = "S";
        } else if (jcboIlumPublica.getSelectedItem().equals("NÃO")) {
            ilumPublica = "N";
        }
    }//GEN-LAST:event_jcboIlumPublicaActionPerformed

    private void jcboLivingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcboLivingActionPerformed
        // TODO add your handling code here:
        if (jcboLiving.getSelectedItem().equals("SIM")) {
            living = "S";
        } else if (jcboLiving.getSelectedItem().equals("NÃO")) {
            living = "N";
        }
    }//GEN-LAST:event_jcboLivingActionPerformed

    private void jcboAreaDeServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcboAreaDeServicoActionPerformed
        // TODO add your handling code here:
        if (jcboAreaDeServico.getSelectedItem().equals("SIM")) {
            areaDeServico = "S";
        } else if (jcboAreaDeServico.getSelectedItem().equals("NÃO")) {
            areaDeServico = "N";
        }
    }//GEN-LAST:event_jcboAreaDeServicoActionPerformed

    private void jcboPiscinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcboPiscinaActionPerformed
        // TODO add your handling code here:
        if (jcboPiscina.getSelectedItem().equals("SIM")) {
            piscina = "S";
        } else if (jcboPiscina.getSelectedItem().equals("NÃO")) {
            piscina = "N";
        }
    }//GEN-LAST:event_jcboPiscinaActionPerformed

    private void jcboSalaIntimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcboSalaIntimaActionPerformed
        // TODO add your handling code here:
        if (jcboSalaIntima.getSelectedItem().equals("SIM")) {
            salaIntima = "S";
        } else if (jcboSalaIntima.getSelectedItem().equals("NÃO")) {
            salaIntima = "N";
        }
    }//GEN-LAST:event_jcboSalaIntimaActionPerformed

    private void jcboQuintalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcboQuintalActionPerformed
        // TODO add your handling code here:
        if (jcboQuintal.getSelectedItem().equals("SIM")) {
            quintal = "S";
        } else if (jcboQuintal.getSelectedItem().equals("NÃO")) {
            quintal = "N";
        }
    }//GEN-LAST:event_jcboQuintalActionPerformed

    private void jcboSalaDeVisitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcboSalaDeVisitasActionPerformed
        // TODO add your handling code here:
        if (jcboSalaDeVisitas.getSelectedItem().equals("SIM")) {
            salaDeVisitas = "S";
        } else if (jcboSalaDeVisitas.getSelectedItem().equals("NÃO")) {
            salaDeVisitas = "N";
        }
    }//GEN-LAST:event_jcboSalaDeVisitasActionPerformed

    private void jcboDepEmpregadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcboDepEmpregadaActionPerformed
        // TODO add your handling code here:
        if (jcboDepEmpregada.getSelectedItem().equals("SIM")) {
            depEmpregada = "S";
        } else if (jcboDepEmpregada.getSelectedItem().equals("NÃO")) {
            depEmpregada = "N";
        }
    }//GEN-LAST:event_jcboDepEmpregadaActionPerformed

    private void jcboAptoAndarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcboAptoAndarActionPerformed
        // TODO add your handling code here:
        if (jcboAptoAndar.getSelectedItem().equals("SIM")) {
            aptoAndar = "S";
        } else if (jcboAptoAndar.getSelectedItem().equals("NÃO")) {
            aptoAndar = "N";
        }
    }//GEN-LAST:event_jcboAptoAndarActionPerformed

    private void jcboPisoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcboPisoActionPerformed
        // TODO add your handling code here:
        if (jcboPiso.getSelectedItem().equals("SIM")) {
            piso = "S";
        } else if (jcboDepEmpregada.getSelectedItem().equals("NÃO")) {
            piso = "N";
        }
    }//GEN-LAST:event_jcboPisoActionPerformed

    private void jcboDispensaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcboDispensaActionPerformed
        // TODO add your handling code here:
        if (jcboDispensa.getSelectedItem().equals("SIM")) {
            dispensa = "S";
        } else if (jcboDispensa.getSelectedItem().equals("NÃO")) {
            dispensa = "N";
        }
    }//GEN-LAST:event_jcboDispensaActionPerformed

    private void jcboSalaDeJantarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcboSalaDeJantarActionPerformed
        // TODO add your handling code here:
        if (jcboSalaDeJantar.getSelectedItem().equals("SIM")) {
            salaDeJantar = "S";
        } else if (jcboSalaDeJantar.getSelectedItem().equals("NÃO")) {
            salaDeJantar = "N";
        }
    }//GEN-LAST:event_jcboSalaDeJantarActionPerformed

    private void jcboChurrasqueiraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcboChurrasqueiraActionPerformed
        // TODO add your handling code here:
        if (jcboChurrasqueira.getSelectedItem().equals("SIM")) {
            churrasqueira = "S";
        } else if (jcboChurrasqueira.getSelectedItem().equals("NÃO")) {
            churrasqueira = "N";
        }
    }//GEN-LAST:event_jcboChurrasqueiraActionPerformed

    private void jcboAguaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcboAguaActionPerformed
        // TODO add your handling code here:
        if (jcboAgua.getSelectedItem().equals("SIM")) {
            agua = "S";
        } else if (jcboAgua.getSelectedItem().equals("NÃO")) {
            agua = "N";
        }
    }//GEN-LAST:event_jcboAguaActionPerformed

    private void jcboHidroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcboHidroActionPerformed
        // TODO add your handling code here:
        if (jcboHidro.getSelectedItem().equals("SIM")) {
            hidro = "S";
        } else if (jcboHidro.getSelectedItem().equals("NÃO")) {
            hidro = "N";
        }
    }//GEN-LAST:event_jcboHidroActionPerformed

    private void jcboClosetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcboClosetActionPerformed
        // TODO add your handling code here:
             if (jcboCloset.getSelectedItem().equals("SIM")) {
            closet = "S";
        } else if (jcboCloset.getSelectedItem().equals("NÃO")) {
            closet = "N";
        }
    }//GEN-LAST:event_jcboClosetActionPerformed

    private void jcboEnergiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcboEnergiaActionPerformed
        // TODO add your handling code here:
             if (jcboEnergia.getSelectedItem().equals("SIM")) {
           energia = "S";
        } else if (jcboEnergia.getSelectedItem().equals("NÃO")) {
            energia = "N";
        }
    }//GEN-LAST:event_jcboEnergiaActionPerformed

    private void jcboGasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcboGasActionPerformed
        // TODO add your handling code here:
             if (jcboGas.getSelectedItem().equals("SIM")) {
            gas = "S";
        } else if (jcboGas.getSelectedItem().equals("NÃO")) {
            gas = "N";
        }
    }//GEN-LAST:event_jcboGasActionPerformed

    private void jcboSacadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcboSacadaActionPerformed
        // TODO add your handling code here:
             if (jcboSacada.getSelectedItem().equals("SIM")) {
            sacada = "S";
        } else if (jcboSacada.getSelectedItem().equals("NÃO")) {
            sacada = "N";
        }
    }//GEN-LAST:event_jcboSacadaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelPrincipal;
    private javax.swing.JButton btnBuscarCep;
    private javax.swing.JButton btnEcluir;
    private javax.swing.JButton btnGravar;
    private javax.swing.JButton btnInserirCasasDetalhes;
    private javax.swing.JButton btnNovoI;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox34;
    private javax.swing.JComboBox<String> jComboBox35;
    private javax.swing.JComboBox<String> jComboBox36;
    private javax.swing.JComboBox<String> jComboBox5;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTableNomeFotos;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JTextField jTextField29;
    private javax.swing.JTextField jTextField30;
    private javax.swing.JTextField jTextField31;
    private javax.swing.JTextField jTextField32;
    private javax.swing.JLabel jblCpf;
    private javax.swing.JLabel jblInscricaoMunicipal;
    private javax.swing.JLabel jblRg;
    private javax.swing.JComboBox<String> jcboAgua;
    private javax.swing.JComboBox<String> jcboAptoAndar;
    private javax.swing.JComboBox<String> jcboAreaDeServico;
    private javax.swing.JComboBox<String> jcboBwcSocial;
    private javax.swing.JComboBox<String> jcboChurrasqueira;
    private javax.swing.JComboBox<String> jcboClassificacao;
    private javax.swing.JComboBox<String> jcboCloset;
    private javax.swing.JComboBox<String> jcboCopa;
    private javax.swing.JComboBox<String> jcboCozinha;
    private javax.swing.JComboBox<String> jcboDepEmpregada;
    private javax.swing.JComboBox<String> jcboDispensa;
    private javax.swing.JComboBox<String> jcboEnergia;
    private javax.swing.JComboBox<String> jcboGas;
    private javax.swing.JComboBox<String> jcboHall;
    private javax.swing.JComboBox<String> jcboHidro;
    private javax.swing.JComboBox<String> jcboIlumPublica;
    private javax.swing.JComboBox<String> jcboJardim;
    private javax.swing.JComboBox<String> jcboLareira;
    private javax.swing.JComboBox<String> jcboLavabo;
    private javax.swing.JComboBox<String> jcboLiving;
    private javax.swing.JComboBox<String> jcboPiscina;
    private javax.swing.JComboBox<String> jcboPiso;
    private javax.swing.JComboBox<String> jcboQuintal;
    private javax.swing.JComboBox<String> jcboSacada;
    private javax.swing.JComboBox<String> jcboSalaDeFesta;
    private javax.swing.JComboBox<String> jcboSalaDeJantar;
    private javax.swing.JComboBox<String> jcboSalaDeJogos;
    private javax.swing.JComboBox<String> jcboSalaDeVisitas;
    private javax.swing.JComboBox<String> jcboSalaIntima;
    private javax.swing.JComboBox<String> jcboTipoImovel;
    private javax.swing.JComboBox<String> jcboUf;
    private com.toedter.calendar.JDateChooser jdateCadastro;
    private com.toedter.calendar.JDateChooser jdateContrucao;
    private com.toedter.calendar.JDateChooser jdateReforma;
    private javax.swing.JLabel jlabelMensagemDeAviso;
    private javax.swing.JLabel jlbRecebeFotos;
    private javax.swing.JRadioButton rbtFisico;
    private javax.swing.JRadioButton rbtJuridico;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JTextField txtCaminhoFoto;
    private javax.swing.JTextField txtCep;
    private javax.swing.JTextField txtCidades;
    private javax.swing.JTextField txtCodigoCasa;
    private javax.swing.JTextField txtCodigoCidade;
    private javax.swing.JTextField txtCodigoDetalhes;
    private javax.swing.JTextField txtCpf;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JTextField txtInscricaoMunicipal;
    private javax.swing.JTextField txtLote;
    private javax.swing.JTextField txtMedidas;
    private javax.swing.JTextField txtMetroQ;
    private javax.swing.JTextField txtNEndereco;
    private javax.swing.JEditorPane txtObservacao;
    private javax.swing.JTextPane txtObservacao2;
    private javax.swing.JTextField txtQtdeGaragem;
    private javax.swing.JTextField txtQuadra;
    private javax.swing.JTextField txtReferencia;
    private javax.swing.JTextField txtRg;
    private javax.swing.JTextField txtqtdeBwc;
    private javax.swing.JTextField txtqtdeQuartos;
    private javax.swing.JTextField txtqtdeSalas;
    private javax.swing.JTextField txtqtdeSuite;
    // End of variables declaration//GEN-END:variables
}
