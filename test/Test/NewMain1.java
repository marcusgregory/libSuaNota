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
 * @author Gregory
 */
public class NewMain1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
       Connection.Response execute3 = Jsoup.connect("https://www.sefaz.ce.gov.br/content/aplicacao/internet/suanota/digitacao_online/incluir_cf.asp").userAgent("Mozilla/5.0").method(Connection.Method.GET).validateTLSCertificates(false)
                .execute();
            System.out.println(execute3.body());
    }
    
}
