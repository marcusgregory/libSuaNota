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
public class ListaDF {

    private ArrayList<DocFiscal> DFs;

    public ListaDF() {
        DFs = new ArrayList<>();
    }

    public void addDF(DocFiscal df) {
        this.DFs.add(df);
    }

    public ArrayList<DocFiscal> getDFs() {
        return DFs;
    }

    public void setDFs(ArrayList<DocFiscal> DFs) {
        this.DFs = DFs;
    }
    public DocFiscal getFirstDF(){
        return this.DFs.get(0);
    }
    public DocFiscal getLastDF(){
        return this.DFs.get(this.DFs.size()-1);
    }

    @Override
    public String toString() {
        return "ListaDF{" + "DFs=" + DFs + '}';
    }

   

}
