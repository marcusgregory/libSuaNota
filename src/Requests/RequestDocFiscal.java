/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Requests;

import DocsFiscais.CF;
import DocsFiscais.ListaCF;
import Sistema.Usuario;
import Verificadores.Regex;
import java.io.IOException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Gregory
 */
public class RequestDocFiscal {
    public static ListaCF request(Usuario usuario) throws IOException{
         Connection.Response execute = Jsoup.connect("https://www.sefaz.ce.gov.br/content/aplicacao/internet/suanota/digitacao_online/ler_lote_digitacao.asp").userAgent("Mozilla/5.0").method(Connection.Method.POST).validateTLSCertificates(false)
                    .header("Referer", "https://www.sefaz.ce.gov.br/content/aplicacao/internet/suanota/digitacao_online/validar_usuario.asp")
                    .data("pessoa", "fisica")
                    .data("prosseguir", "Prosseguir")
                    .cookies(usuario.getCookies())
                    .execute();

            Connection.Response execute2 = Jsoup.connect("https://www.sefaz.ce.gov.br/content/aplicacao/internet/suanota/digitacao_online/incluir_cf.asp").userAgent("Mozilla/5.0").method(Connection.Method.GET).validateTLSCertificates(false)
                    .header("Referer", "https://www.sefaz.ce.gov.br/content/aplicacao/internet/suanota/digitacao_online/tipo_documento.asp")             
                    .cookies(usuario.getCookies())
                    .execute();
            //System.out.println(execute2.body());
            Elements docsLote = execute2.parse().select("#textoContainer > form:nth-child(2) > table:nth-child(10)").select("tr").attr("height","20").next().next().not(".titulo");
            ListaCF lista = new ListaCF();
            for(Element docLote : docsLote){
                CF cf = new CF();
               // System.out.println("|Tipo:       |"+docLote.select("td:nth-child(1)").text());
                cf.setNumCGF(docLote.select("td:nth-child(2)").text());
                cf.setNumDocCOO(docLote.select("td:nth-child(3)").text());
                cf.setNumCaixaECF(docLote.select("td:nth-child(4)").text());
                cf.setNumFab(docLote.select("td:nth-child(5)").text());
                //System.out.println("|Espécie:    |"+docLote.select("td:nth-child(6)").text());
                //System.out.println("|Série:      |"+docLote.select("td:nth-child(7)").text());
                //System.out.println("|Nº AIDF:    |"+docLote.select("td:nth-child(8)").text());
                cf.setDataEmissao(docLote.select("td:nth-child(9)").text());
                cf.setValor(docLote.select("td:nth-child(10)").text());
                cf.setSecDocFiscal(Regex.numDocFiscal(docLote.select("td:nth-child(13)").html()));
                cf.setHidLote(execute2.parse().select("form:nth-child(2) > input:nth-child(5)").attr("value"));
                lista.addCF(cf);
                
            }
     return lista;   
    }
}
