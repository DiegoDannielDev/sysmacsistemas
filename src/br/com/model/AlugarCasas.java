/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.model;

/**
 *
 * @author Diego Danniel
 */
public class AlugarCasas {
    private int codigo;
    private int codigoCasa;
    private int codigoCliente;
    private String DataInicial;
    private String tipoDeAluguel;
    private String DataFinal;

    public AlugarCasas(int codigo) {
        this.codigo = codigo;
    }

    public AlugarCasas(){
        
    }
    
    public AlugarCasas(int codigo, int codigoCasa, int codigoCliente, String DataInicial, String tipoDeAluguel, String DataFinal) {
        this.codigo = codigo;
        this.codigoCasa = codigoCasa;
        this.codigoCliente = codigoCliente;
        this.DataInicial = DataInicial;
        this.tipoDeAluguel = tipoDeAluguel;
        this.DataFinal = DataFinal;
    }

    public AlugarCasas(int codigoCasa, int codigoCliente, String DataInicial, String tipoDeAluguel, String DataFinal) {
        this.codigoCasa = codigoCasa;
        this.codigoCliente = codigoCliente;
        this.DataInicial = DataInicial;
        this.tipoDeAluguel = tipoDeAluguel;
        this.DataFinal = DataFinal;
    }
    

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigoCasa() {
        return codigoCasa;
    }

    public void setCodigoCasa(int codigoCasa) {
        this.codigoCasa = codigoCasa;
    }

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public String getDataInicial() {
        return DataInicial;
    }

    public void setDataInicial(String DataInicial) {
        this.DataInicial = DataInicial;
    }

    public String getTipoDeAluguel() {
        return tipoDeAluguel;
    }

    public void setTipoDeAluguel(String tipoDeAluguel) {
        this.tipoDeAluguel = tipoDeAluguel;
    }

    public String getDataFinal() {
        return DataFinal;
    }

    public void setDataFinal(String DataFinal) {
        this.DataFinal = DataFinal;
    }
    
    
}

