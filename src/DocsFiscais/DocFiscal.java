/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DocsFiscais;

import Exceptions.ErroDocFiscalException;
import Exceptions.UsuarioNaoLogadoException;
import Sistema.Usuario;
import java.io.IOException;

/**
 *
 * @author Vitoria
 */
public class DocFiscal {

    private TipoDoc tipo;
    private String numCGF;
    private String numDocCOO;
    private String numCaixaECF;
    private String numFab;
    private String dataEmissao;
    private String especie;
    private String serie;
    private String numAIDF;
    private String valor;
    private String hidLote;
    private String secDocFiscal="0";

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getNumAIDF() {
        return numAIDF;
    }

    public void setNumAIDF(String numAIDF) {
        this.numAIDF = numAIDF;
    }

    public TipoDoc getTipo() {
        return tipo;
    }

    public void setTipo(TipoDoc tipo) {
        this.tipo = tipo;
    }

    public String getSecDocFiscal() {
        return secDocFiscal;
    }

    public void setSecDocFiscal(String secDocFiscal) {
        this.secDocFiscal = secDocFiscal;
    }

    public String getHidLote() {
        return hidLote;
    }

    public void setHidLote(String hidLote) {
        this.hidLote = hidLote;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(String dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public String getNumFab() {
        return numFab;
    }

    public void setNumFab(String numFab) {
        this.numFab = numFab;
    }

    public String getNumCaixaECF() {
        return numCaixaECF;
    }

    public void setNumCaixaECF(String numCaixaECF) {
        this.numCaixaECF = numCaixaECF;
    }

    public String getNumDocCOO() {
        return numDocCOO;
    }

    public void setNumDocCOO(String numDocCOO) {
        this.numDocCOO = numDocCOO;
    }

    public String getNumECF() {
        return numCaixaECF;
    }

    public void setNumECF(String numECF) {
        this.numCaixaECF = numECF;
    }

    public String getNumCGF() {
        return numCGF;
    }

    public void setNumCGF(String numCGF) {
        this.numCGF = numCGF;
    }
      public void cadastrar() throws ErroDocFiscalException, UsuarioNaoLogadoException,IOException{
          Requests.RequestCadastroDF.request(this, Usuario.getUsuarioAtual());
      }
    @Override
    public String toString() {
        return "DocFiscal{" + "tipo=" + tipo + ", numCGF=" + numCGF + ", numDocCOO=" + numDocCOO + ", numCaixaECF=" + numCaixaECF + ", numFab=" + numFab + ", dataEmissao=" + dataEmissao + ", especie=" + especie + ", serie=" + serie + ", numAIDF=" + numAIDF + ", valor=" + valor + ", hidLote=" + hidLote + ", secDocFiscal=" + secDocFiscal + '}';
    }

}
