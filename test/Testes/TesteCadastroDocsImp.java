/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testes;

import Exceptions.ErroLoginException;
import Sistema.Sistema;
import Sistema.Usuario;
import java.io.IOException;
import java.net.URLDecoder;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

/**
 *
 * @author Gregory
 */
public class TesteCadastroDocsImp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ErroLoginException {
        // TODO code application logic here
        Usuario u=new Sistema("38200297373").logar();
        System.out.println("Olá " + u.getNome() + ", seu Identificador é:" + u.getNumID());
        if(Requests.RequestVerificarLote.verificar(u)){
            Connection.Response execute2 = Jsoup.connect("https://www.sefaz.ce.gov.br/content/aplicacao/internet/suanota/digitacao_online/incluir_cf.asp").userAgent("Mozilla/5.0")
                .method(Connection.Method.GET)
                .validateTLSCertificates(false)
                .header("Referer", "https://www.sefaz.ce.gov.br/content/aplicacao/internet/suanota/digitacao_online/incluir_cf.asp")
                .cookies(u.getCookies())
                .execute();
            String numLote=execute2.parse().select("form:nth-child(2) > input:nth-child(5)").attr("value");
         Connection.Response execute3 = Jsoup.connect("https://www.sefaz.ce.gov.br/content/aplicacao/internet/suanota/digitacao_online/incluir_cf.asp").userAgent("Mozilla/5.0").method(Connection.Method.POST).validateTLSCertificates(false)
                .header("Referer", "https://www.sefaz.ce.gov.br/content/aplicacao/internet/suanota/digitacao_online/incluir_cf.asp")
                .cookies(u.getCookies())
                 .followRedirects(false)
                .data("cgf", "5802470")
                .data("numero_cf", "12342557")
                .data("numero_caixa", "5")
                .data("num_fab_ecf", "DR0609BR000000190898")
                .data("data_emissao", "12/06/2018")
                .data("valor", "55")
                .data("hidLote", numLote)
                .data("hidParticipante", u.getNumID())
                .data("seqDocFiscal", "0")
                .data("tipoParticipanteSessao", "fisica")
                .data("salvar", "Salvar")
                .execute();

       // System.out.println(execute3.body());
        
        System.out.println(execute3.statusCode());
            System.out.println(URLDecoder.decode(execute3.header("Location"),"UTF-8"));
            
        }else{
            System.err.println("O usuário não possui lote aberto");
        }
    }
    
}
