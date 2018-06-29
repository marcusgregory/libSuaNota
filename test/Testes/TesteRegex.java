/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testes;

import Verificadores.Regex;

/**
 *
 * @author Vitoria
 */
public class TesteRegex {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String numDocFiscal = Regex.numDocFiscal("<td><a href=\"javascript: staOnlineEd(1,239110945, 'DR0609BR000000190391'); \"><img src=\"../../imagens/suanota/alterar.gif\" border=\"0\" /></a></td>\n"
                + "											<td><a href=\"javascript: staOnlineEx(1,239110945); \"><img src=\"../../imagens/suanota/exclusao.gif\" border=\"0\" /></a></td>\n");
        System.out.println(numDocFiscal);

    }

}
