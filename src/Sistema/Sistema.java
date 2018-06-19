/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;

import java.io.IOException;
import java.util.logging.Logger;

/**
 *
 * @author Gregory
 */
public class Sistema {
    private String CPF;

    public Sistema(String CPF) {
        this.CPF = CPF;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public Usuario logar() throws IOException{
        return Requests.RequestUsuario.request(this);
    }
    @Override
    public String toString() {
        return "Sistema{" + "CPF=" + CPF + '}';
    }
    
}
