/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DocsFiscais;

import java.util.ArrayList;

/**
 *
 * @author Gregory
 */
public class ListaDocFiscal {

    private ArrayList<DocFiscal> DocsFiscais;

    public ListaDocFiscal() {
        DocsFiscais = new ArrayList<>();
    }

    public void addDocFiscal(DocFiscal df) {
        this.DocsFiscais.add(df);
    }

    public ArrayList<DocFiscal> getDocsFiscais() {
        return DocsFiscais;
    }

    public void setDocsFiscais(ArrayList<DocFiscal> DFs) {
        this.DocsFiscais = DFs;
    }

    public DocFiscal getFirstDF() {
        return this.DocsFiscais.get(0);
    }

    public DocFiscal getLastDF() {
        return this.DocsFiscais.get(this.DocsFiscais.size() - 1);
    }

    @Override
    public String toString() {
        return "ListaDocFiscal{" + "DocsFiscais=" + DocsFiscais + '}';
    }

}
