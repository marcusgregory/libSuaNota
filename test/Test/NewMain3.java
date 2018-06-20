/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Sistema.Sistema;
import Sistema.Usuario;
import java.io.IOException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Gregory
 */
public class NewMain3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
       Usuario u = new Sistema("06663852388").logar();
        System.out.println(u.toString());     
        Connection.Response execute = Jsoup.connect("https://www.sefaz.ce.gov.br/content/aplicacao/internet/suanota/digitacao_online/ler_lote_digitacao.asp").userAgent("Mozilla/5.0").method(Connection.Method.POST).validateTLSCertificates(false)
                    .header("Referer", "https://www.sefaz.ce.gov.br/content/aplicacao/internet/suanota/digitacao_online/validar_usuario.asp")
                    .data("pessoa", "fisica")
                    .data("prosseguir", "Prosseguir")
                    .cookies(u.getCookies())
                    .execute();

            Connection.Response execute2 = Jsoup.connect("https://www.sefaz.ce.gov.br/content/aplicacao/internet/suanota/digitacao_online/incluir_nf.asp").userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36").method(Connection.Method.GET).validateTLSCertificates(false)
                    .header("Referer", "https://www.sefaz.ce.gov.br/content/aplicacao/internet/suanota/digitacao_online/tipo_documento.asp")             
                    .cookies(u.getCookies())
                    .execute();
            //System.out.println(execute2.body());
            Elements docsLote = execute2.parse().select("#textoContainer > form:nth-child(2) > table:nth-child(10)").select("tr").attr("height","20").next().next().not(".titulo");
     System.out.println("-------------------------------------------------------------");

            for(Element docLote : docsLote){
               
                System.out.println("|Tipo:       |"+docLote.select("td:nth-child(1)").text());
                System.out.println("|CGF:        |"+docLote.select("td:nth-child(2)").text());
                System.out.println("|Nº Doc.:    |"+docLote.select("td:nth-child(3)").text());
                System.out.println("|Nº Cx.:     |"+docLote.select("td:nth-child(4)").text());
                System.out.println("|Nº Fab. ECF:|"+docLote.select("td:nth-child(5)").text());
                System.out.println("|Espécie:    |"+docLote.select("td:nth-child(6)").text());
                System.out.println("|Série:      |"+docLote.select("td:nth-child(7)").text());
                System.out.println("|Nº AIDF:    |"+docLote.select("td:nth-child(8)").text());
                System.out.println("|Data:       |"+docLote.select("td:nth-child(9)").text());
                System.out.println("|Valor (R$): |"+docLote.select("td:nth-child(10)").text());
                System.out.println("-------------------------------------------------------------");
                
            }
            //System.out.println(docsLote.eq(1));
    }
    
}
