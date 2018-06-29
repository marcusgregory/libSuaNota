/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testes;

import Exceptions.ErroLoginException;
import Sistema.Sistema;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gregory
 */
public class TesteLogin {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ErroLoginException {
        try {
            // TODO code application logic here
            System.out.println(new Sistema("06663852388").logar().toString());
        } catch (IOException ex) {
            Logger.getLogger(TesteLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
