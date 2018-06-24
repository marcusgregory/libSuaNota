/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Exceptions.ErroLoginException;
import Sistema.Sistema;
import Sistema.Usuario;
import java.io.IOException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

/**
 *
 * @author Vitoria
 */
public class NewMain4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ErroLoginException {
        // TODO code application logic here
         Usuario s =new Sistema("06663852388").logar();
        System.out.println(s.getListaDocs().toString());
}
} 
