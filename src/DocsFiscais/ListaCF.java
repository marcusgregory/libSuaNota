/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DocsFiscais;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gregory
 */
public class ListaCF {

    private ArrayList<CF> CFs;

    public ListaCF() {
        CFs = new ArrayList<>();
    }

    public void addCF(CF cf) {
        this.CFs.add(cf);
    }

    public ArrayList<CF> getCFs() {
        return CFs;
    }

    public void setCFs(ArrayList<CF> CFs) {
        this.CFs = CFs;
    }

    @Override
    public String toString() {
        return "ListaCF{" + "CFs=" + CFs + '}';
    }

}
