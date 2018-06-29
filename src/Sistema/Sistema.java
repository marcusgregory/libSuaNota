/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;

import Exceptions.ErroLoginException;
import Exceptions.UsuarioNaoLogadoException;
import java.io.IOException;

/**
 *
 * @author Gregory
 */
public class Sistema {

    private String CPF;
    private static Sistema sistema;

    public Sistema(String CPF) {
        this.CPF = CPF;
        sistema = this;
    }

    public static Sistema getSistemaAtual() throws UsuarioNaoLogadoException {
        if (sistema == null) {
            throw new UsuarioNaoLogadoException("Usuario não está logado no sistema!");
        } else {
            return sistema;
        }

    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public Usuario logar() throws IOException, ErroLoginException {
        return Requests.RequestLogin.request(this);
    }

    @Override
    public String toString() {
        return "Sistema{" + "CPF=" + CPF + '}';
    }

}
