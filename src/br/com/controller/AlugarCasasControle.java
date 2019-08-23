/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controller;

import br.com.dao.AlugarCasasDAO;
import br.com.model.AlugarCasas;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diego Danniel
 */
public class AlugarCasasControle {

    private AlugarCasas alugarCasa;
    private List<AlugarCasas> alugarCasas;
    private AlugarCasasDAO dao;

    public AlugarCasasControle() {
        dao = new AlugarCasasDAO();

    }

    private void novo() {
        alugarCasa = new AlugarCasas();
        alugarCasas = new ArrayList<>();
    }

    public boolean inserirRegistro() {
 
        return true;
    }

    public AlugarCasas getAlugarCasa() {
        return alugarCasa;
    }

    public void setAlugarCasa(AlugarCasas alugarCasa) {
        this.alugarCasa = alugarCasa;
    }

    public List<AlugarCasas> getAlugarCasas() {
        return alugarCasas;
    }

    public void setAlugarCasas(List<AlugarCasas> alugarCasas) {
        this.alugarCasas = alugarCasas;
    }

    public AlugarCasasDAO getDao() {
        return dao;
    }

    public void setDao(AlugarCasasDAO dao) {
        this.dao = dao;
    }
}
