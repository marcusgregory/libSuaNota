/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

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
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
         Connection.Response execute2 = Jsoup.connect("").userAgent("Mozilla/5.0").method(Connection.Method.GET).validateTLSCertificates(false)
                    
                    .execute();
        System.out.println(execute2.body());
}} 
