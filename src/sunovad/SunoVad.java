/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sunovad;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

/**
 *
 * @author Gregory
 */
public class SunoVad {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Scanner entrada=new Scanner(System.in);
        System.out.println("Digite seu CPF:");
        String CPF=entrada.nextLine();
        System.out.println("Obtendo Cookies...");
       
        Connection.Response execute1 = Jsoup.connect("https://www.sefaz.ce.gov.br/content/aplicacao/internet/suanota/consultas/consulta_ID.asp").userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36").method(Connection.Method.POST).validateTLSCertificates(false)
                .data("txtCpfCnpj", CPF)
                .data("rdbCpfCnpj", "2")
                .data("btnOk", "Avançar")
                .execute();
        if(execute1.parse().select("#textoContainer > form > p:nth-child(1) > span").isEmpty()){
            System.out.println("VAZIO");
        }else{
            System.out.println("VALIDO");
    }
        String identificador=execute1.parse().select("#textoContainer > form > p:nth-child(1) > span").get(0).text();
        
        Map<String, String> cookies =execute1.cookies();
                System.out.println("Cookies:"+cookies);
        System.out.println("Cookies OK");
        System.out.println("Efetuando login...");
        Connection.Response execute = Jsoup.connect("https://www.sefaz.ce.gov.br/content/aplicacao/internet/suanota/digitacao_online/validar_usuario.asp").userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36")
                .method(Connection.Method.POST)
                .header("Connection","keep-alive")
                .header("Referer", "https://www.sefaz.ce.gov.br/content/aplicacao/internet/suanota/consultas/ler_dados.asp")
                .cookies(cookies)
                .data("pagina", "incluir_documento")
                .data("pessoa", "fisica")
                .data("txtIdentificador", identificador)
                .data("txtCpfCnpj", CPF)
                .data("btnOk", "Avançar").validateTLSCertificates(false).followRedirects(false).execute();
        System.err.println("StatusHTTP="+execute.statusCode()+" | "+execute.statusMessage());
        if(execute.statusCode()==200){
            System.out.println("Login OK");
        
        System.out.println("|Identificador:|"+execute.parse().select("form>table td:eq(1)").get(0).text());
        System.out.println("|Nome         :|"+execute.parse().select("form>table td:eq(1)").get(1).text());
        System.out.println("|CPF          :|"+execute.parse().select("form>table td:eq(1)").get(2).text());
        Connection.Response execute3 = Jsoup.connect("https://www.sefaz.ce.gov.br/content/aplicacao/internet/suanota/digitacao_online/incluir_cf.asp").userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36").method(Connection.Method.GET).validateTLSCertificates(false)
                .cookies(cookies)
                .execute();
            System.out.println(execute3.body());
       }else{
            System.err.println("Usuário não cadastrado, inativo ou número identificador errado!");
        }
    }
    
}
