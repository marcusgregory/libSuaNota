/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Exceptions.ErroLoginException;
import Exceptions.NenhumLoteAbertoException;
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
         Usuario s = null;
        try{
         s =new Sistema("38200297373").logar();
            System.out.println("Olá "+s.getNome()+" seu Identificador é:"+s.getNumID());
          System.out.println(s.getListaDocs().toString());
        }catch(IOException ex){
            System.err.println(ex);
            
        }catch(NenhumLoteAbertoException ex){
            System.err.println(ex);
        }
       
}
} 
