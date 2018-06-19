/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;

import Exceptions.UsuarioNaoLogadoException;
import java.util.Map;

/**
 *
 * @author Gregory
 */
public class Usuario {

    private String nome;
    private String CPF;
    private String numID;
    private Map<String, String> cookies;
    private static Usuario usuario;

    public Usuario() {
        usuario = this;
    }

    public static Usuario getUsuarioAtual() throws UsuarioNaoLogadoException {
        if (usuario == null) {
            throw new UsuarioNaoLogadoException("Usuario não está logado no sistema!");
        } else {
            return usuario;
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getNumID() {
        return numID;
    }

    public void setNumID(String numID) {
        this.numID = numID;
    }

    public Map<String, String> getCookies() {
        return cookies;
    }

    public void setCookies(Map<String, String> cookies) {
        this.cookies = cookies;
    }

    @Override
    public String toString() {
        return "Usuario{" + "nome=" + nome + ", CPF=" + CPF + ", numID=" + numID + ", cookies=" + cookies + '}';
    }

    

}
