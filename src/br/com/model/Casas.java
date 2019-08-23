/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.model;

import java.text.ParseException;

/**
 *
 * @author Diego Danniel
 */
public class Casas {

    private int codigoCasa;
    private String classificacao;
    private String tipoImovel;
    private String dataCadastro;
    private String endereco;
    private String numero_end;
    private int cep;
    private String bairro;
    private String cidade;
    private String uf;
    private String referencia;
    private float lote;
    private float quadra;
    private float medida;
    private float metroQuadrado;
    private int quantidadeSala;
    private int quantidadeQuartos;
    private int quantidadeBanheiros;
    private int quantidadeSuite;
    private int quantidadeGaragem;
    private String dataDeContrucao;
    private String observacao1;
    private String dataReforma;

    /*Atributos da casas item*/
    private int codigoCasaCadastroItem;
    private String hallSocial;
    private String lavabo;
    private String living;
    private String salaDeVisitas;
    private String salaDeJantar;
    private String salaIntima;
    private String hidro;
    private String closet;
    private String lareira;
    private String copa;
    private String areaDeServico;
    private String depEmpregada;
    private String churrasqueira;
    private String sacada;
    private String dispensa;
    private String piso;
    private String salaDeJogos;
    private String salaDeFesta;
    private String piscina;
    private String aptoAndar;
    private String agua;
    private String energia;
    private String gas;
    private String quintal;
    private String jardim;
    private String bwcSocial;
    private String cozinha;
    private String ilumPublica;
    private String observacao2;
  

    public Casas() {

    }

    /**
     *
     * @param codigoCasa
     * @param classificacao
     * @param tipoImovel
     * @param dataCadastro
     * @param endereco
     * @param numero_end
     * @param cep
     * @param bairro
     * @param cidade
     * @param uf
     * @param referencia
     * @param lote
     * @param quadra
     * @param medida
     * @param metroQuadrado
     * @param quantidadeSala
     * @param quantidadeQuartos
     * @param quantidadeBanheiros
     * @param quantidadeGaragem
     * @param dataDeContrucao
     * @param dataReforma
     * @param observacao1
     *
     */
    

    

    

    public Casas(int codigoCasa,
            String classificacao, String tipoImovel, 
            String dataCadastro, String endereco, 
            String numero_end, int cep, String bairro,
            String cidade, String uf, String referencia,
            float lote, float quadra, float medida, 
            float metroQuadrado, int quantidadeSala, 
            int quantidadeQuartos, int quantidadeBanheiros,          
            int quantidadeGaragem, 
            String dataDeContrucao,String dataReforma, String observacao1) {
        this.codigoCasa = codigoCasa;
        this.classificacao = classificacao;
        this.tipoImovel = tipoImovel;
        this.dataCadastro = dataCadastro;
        this.endereco = endereco;
        this.numero_end = numero_end;
        this.cep = cep;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.referencia = referencia;
        this.lote = lote;
        this.quadra = quadra;
        this.medida = medida;
        this.metroQuadrado = metroQuadrado;
        this.quantidadeSala = quantidadeSala;
        this.quantidadeQuartos = quantidadeQuartos;
        this.quantidadeBanheiros = quantidadeBanheiros;
        
        this.quantidadeGaragem = quantidadeGaragem;
        this.dataDeContrucao = dataDeContrucao;
        this.observacao1 = observacao1;
        this.dataReforma = dataReforma;
    }

    public Casas(int codigoCasaCadastroItem, String hallSocial, String lavabo, String living, String salaDeVisitas, String salaDeJantar, String salaIntima, String hidro, String closet, String lareira, String copa, String areaDeServico, String depEmpregada, String churrasqueira, String sacada, String dispensa, String piso, String salaDeJogos, String salaDeFesta, String piscina, String aptoAndar, String agua, String energia, String gas, String quintal, String jardim, String bwcSocial, String cozinha, String ilumPublica, String observacao2) {
        this.codigoCasaCadastroItem = codigoCasaCadastroItem;
        this.hallSocial = hallSocial;
        this.lavabo = lavabo;
        this.living = living;
        this.salaDeVisitas = salaDeVisitas;
        this.salaDeJantar = salaDeJantar;
        this.salaIntima = salaIntima;
        this.hidro = hidro;
        this.closet = closet;
        this.lareira = lareira;
        this.copa = copa;
        this.areaDeServico = areaDeServico;
        this.depEmpregada = depEmpregada;
        this.churrasqueira = churrasqueira;
        this.sacada = sacada;
        this.dispensa = dispensa;
        this.piso = piso;
        this.salaDeJogos = salaDeJogos;
        this.salaDeFesta = salaDeFesta;
        this.piscina = piscina;
        this.aptoAndar = aptoAndar;
        this.agua = agua;
        this.energia = energia;
        this.gas = gas;
        this.quintal = quintal;
        this.jardim = jardim;
        this.bwcSocial = bwcSocial;
        this.cozinha = cozinha;
        this.ilumPublica = ilumPublica;
        this.observacao2 = observacao2;
    }
    

    public Casas(int codigoCasa, String classificacao, String tipoImovel, String endereco, String numero_end, int cep, String bairro, String cidade, String uf, String referencia, float lote, float quadra, float medida, float metroQuadrado, int quantidadeSala, int quantidadeQuartos, int quantidadeBanheiros, int quantidadeGaragem, String observacao1) {
        this.codigoCasa = codigoCasa;
        this.classificacao = classificacao;
        this.tipoImovel = tipoImovel;
        this.endereco = endereco;
        this.numero_end = numero_end;
        this.cep = cep;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.referencia = referencia;
        this.lote = lote;
        this.quadra = quadra;
        this.medida = medida;
        this.metroQuadrado = metroQuadrado;
        this.quantidadeSala = quantidadeSala;
        this.quantidadeQuartos = quantidadeQuartos;
        this.quantidadeBanheiros = quantidadeBanheiros;
        
        this.quantidadeGaragem = quantidadeGaragem;
        this.observacao1 = observacao1;
    }

    

   

    public Casas(int codigoCasa) {
        this.codigoCasa = codigoCasa;
    }

    public int getCodigoCasa() {
        return codigoCasa;
    }

    public void setCodigoCasa(int codigoCasa) {
        this.codigoCasa = codigoCasa;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public String getTipoImovel() {
        return tipoImovel;
    }

    public void setTipoImovel(String tipoImovel) {
        this.tipoImovel = tipoImovel;
    }

    public String getDataCadastro() throws ParseException {

        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;

    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero_end() {
        return numero_end;
    }

    public void setNumero_end(String numero_end) {
        this.numero_end = numero_end;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public float getLote() {

        return lote;
    }

    public void setLote(float lote) {
        this.lote = lote;
    }

    public float getQuadra() {
        return quadra;
    }

    public void setQuadra(float quadra) {
        this.quadra = quadra;
    }

    public float getMedida() {
        return medida;
    }

    public void setMedida(float medida) {
        this.medida = medida;
    }

    public float getMetroQuadrado() {
        return metroQuadrado;
    }

    public void setMetroQuadrado(float metroQuadrado) {
        this.metroQuadrado = metroQuadrado;
    }

    public int getQuantidadeSala() {
        return quantidadeSala;
    }

    public void setQuantidadeSala(int quantidadeSala) {
        this.quantidadeSala = quantidadeSala;
    }

    public int getQuantidadeQuartos() {
        return quantidadeQuartos;
    }

    public void setQuantidadeQuartos(int quantidadeQuartos) {
        this.quantidadeQuartos = quantidadeQuartos;
    }

    public int getQuantidadeBanheiros() {
        return quantidadeBanheiros;
    }

    public void setQuantidadeBanheiros(int quantidadeBanheiros) {
        this.quantidadeBanheiros = quantidadeBanheiros;
    }

    public int getQuantidadeGaragem() {
        return quantidadeGaragem;
    }

    public void setQuantidadeGaragem(int quantidadeGaragem) {
        this.quantidadeGaragem = quantidadeGaragem;
    }

    public String getDataDeContrucao() {
        return dataDeContrucao;
    }

    public void setDataDeContrucao(String dataDeContrucao) {
        this.dataDeContrucao = dataDeContrucao;
    }

    public String getObservacao1() {
        return observacao1;
    }

    public void setObservacao1(String observacao1) {
        this.observacao1 = observacao1;
    }

    public int getQuantidadeSuite() {
        return quantidadeSuite;
    }

    public void setQuantidadeSuite(int quantidadeSuite) {
        this.quantidadeSuite = quantidadeSuite;
    }

    public String getDataReforma() {
        return dataReforma;
    }

    public void setDataReforma(String dataReforma) {
        this.dataReforma = dataReforma;
    }

    public int getCep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }
    
    /*Fim*/
    
    /*Get e Set casa cadastro item*/

    public int getCodigoCasaCadastroItem() {
        return codigoCasaCadastroItem;
    }

    public void setCodigoCasaCadastroItem(int codigoCasaCadastroItem) {
        this.codigoCasaCadastroItem = codigoCasaCadastroItem;
    }

    public String getHallSocial() {
        return hallSocial;
    }

    public void setHallSocial(String hallSocial) {
        this.hallSocial = hallSocial;
    }

    public String getLareira() {
      
        return lareira;
    }

    public void setLareira(String lareira) {
        this.lareira = lareira;
    }

    public String getSalaDeJogos() {
        return salaDeJogos;
    }

    public void setSalaDeJogos(String salaDeJogos) {
        this.salaDeJogos = salaDeJogos;
    }

    public String getJardim() {
        return jardim;
    }

    public void setJardim(String jardim) {
        this.jardim = jardim;
    }

    public String getCozinha() {
        return cozinha;
    }

    public void setCozinha(String cozinha) {
        this.cozinha = cozinha;
    }

    public String getLavabo() {
        return lavabo;
    }

    public void setLavabo(String lavabo) {
        this.lavabo = lavabo;
    }

    public String getCopa() {
        return copa;
    }

    public void setCopa(String copa) {
        this.copa = copa;
    }

    public String getSalaDeVisitas() {
        return salaDeVisitas;
    }

    public void setSalaDeVisitas(String salaDeVisitas) {
        this.salaDeVisitas = salaDeVisitas;
    }

    public String getBwcSocial() {
        return bwcSocial;
    }

    public void setBwcSocial(String bwcSocial) {
        this.bwcSocial = bwcSocial;
    }

    public String getIlumPublica() {
        return ilumPublica;
    }

    public void setIlumPublica(String ilumPublica) {
        this.ilumPublica = ilumPublica;
    }

    public String getLiving() {
        return living;
    }

    public void setLiving(String living) {
        this.living = living;
    }

    public String getAreaDeServico() {
        return areaDeServico;
    }

    public void setAreaDeServico(String areaDeServico) {
        this.areaDeServico = areaDeServico;
    }

    public String getPiscina() {
        return piscina;
    }

    public void setPiscina(String piscina) {
        this.piscina = piscina;
    }

    public String getSalaIntima() {
        return salaIntima;
    }

    public void setSalaIntima(String salaIntima) {
        this.salaIntima = salaIntima;
    }

    public String getQuintal() {
        return quintal;
    }

    public void setQuintal(String quintal) {
        this.quintal = quintal;
    }

    public String getDepEmpregada() {
        return depEmpregada;
    }

    public void setDepEmpregada(String depEmpregada) {
        this.depEmpregada = depEmpregada;
    }

    public String getAptoAndar() {
        return aptoAndar;
    }

    public void setAptoAndar(String aptoAndar) {
        this.aptoAndar = aptoAndar;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getDispensa() {
        return dispensa;
    }

    public void setDispensa(String dispensa) {
        this.dispensa = dispensa;
    }

    public String getSalaDeJantar() {
        return salaDeJantar;
    }

    public void setSalaDeJantar(String salaDeJantar) {
        this.salaDeJantar = salaDeJantar;
    }

    public String getChurrasqueira() {
        return churrasqueira;
    }

    public void setChurrasqueira(String churrasqueira) {
        this.churrasqueira = churrasqueira;
    }

    public String getAgua() {
        return agua;
    }

    public void setAgua(String agua) {
        this.agua = agua;
    }

    public String getHidro() {
        return hidro;
    }

    public void setHidro(String hidro) {
        this.hidro = hidro;
    }

    public String getCloset() {
        return closet;
    }

    public void setCloset(String closet) {
        this.closet = closet;
    }

    public String getEnergia() {
        return energia;
    }

    public void setEnergia(String energia) {
        this.energia = energia;
    }

    public String getGas() {
        return gas;
    }

    public void setGas(String gas) {
        this.gas = gas;
    }

    public String getSacada() {
        return sacada;
    }

    public void setSacada(String sacada) {
        this.sacada = sacada;
    }

    public String getSalaDeFesta() {
        return salaDeFesta;
    }

    public void setSalaDeFesta(String salaDeFesta) {
        this.salaDeFesta = salaDeFesta;
    }
    

    public String getObservacao2() {
        return observacao2;
    }

    public void setObservacao2(String observacao2) {
        this.observacao2 = observacao2;
    }

    /*fim*/    
    
    
    public void CadastrarCasa() {

    }

}
