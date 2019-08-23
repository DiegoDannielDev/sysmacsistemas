
package br.com.model;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;


public class GerenciamentoTelaModel {
    private static JDesktopPane jDesktopPane;
    
    public GerenciamentoTelaModel(JDesktopPane jDesktopPane){
        br.com.model.GerenciamentoTelaModel.jDesktopPane = jDesktopPane;
    }
    
    public void abrirJanelas(JInternalFrame jInternalFrame){
        if(jInternalFrame.isVisible()){
            jInternalFrame.toFront();
            jInternalFrame.requestFocus();
            jInternalFrame.isVisible();
        }else if (jInternalFrame.isClosed()) {
            jInternalFrame.isClosed();
        }
        
        
        else{
            jDesktopPane.add(jInternalFrame);
            jInternalFrame.setVisible(true);
            jInternalFrame.setMaximizable(true);
        }
    }
}
