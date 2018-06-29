/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testes;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

/**
 *
 * @author Gregory
 */
public class TesteLoginImpl {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        System.out.println("1");
        Connection.Response execute = Jsoup.connect("https://www.sefaz.ce.gov.br/content/aplicacao/internet/suanota/consultas/consulta_ler_pf_ou_pj.asp?pagina=incluir_documento").userAgent("Mozilla/5.0").method(Connection.Method.GET).validateTLSCertificates(false)
                .execute();
        Map<String, String> cookies = execute.cookies();
        System.out.println("Cookies: " + cookies);

        System.out.println("7");
        Connection.Response execute6 = Jsoup.connect("https://www.sefaz.ce.gov.br/content/aplicacao/internet/suanota/digitacao_online/validar_usuario.asp").userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36")
                .method(Connection.Method.POST)
                .header("Referer", "https://www.sefaz.ce.gov.br/content/aplicacao/internet/suanota/consultas/ler_dados.asp")
                .cookies(cookies)
                .data("pagina", "incluir_documento")
                .data("pessoa", "fisica")
                .data("txtIdentificador", "1395051")
                .data("txtCpfCnpj", "06663852388")
                .data("btnOk", "Avançar").validateTLSCertificates(false).followRedirects(false).execute();
        System.err.println("StatusHTTP=" + execute.statusCode() + " | " + execute.statusMessage());
        if (execute.statusCode() == 200) {
            System.out.println("Login OK");

            System.out.println("|Identificador:|" + execute6.parse().select("form>table td:eq(1)").get(0).text());
            System.out.println("|Nome         :|" + execute6.parse().select("form>table td:eq(1)").get(1).text());
            System.out.println("|CPF          :|" + execute6.parse().select("form>table td:eq(1)").get(2).text());
            System.out.println("8");
            Connection.Response execute8 = Jsoup.connect("https://www.sefaz.ce.gov.br/content/aplicacao/internet/suanota/digitacao_online/ler_lote_digitacao.asp").userAgent("Mozilla/5.0").method(Connection.Method.POST).validateTLSCertificates(false).followRedirects(false)
                    .header("Referer", "https://www.sefaz.ce.gov.br/content/aplicacao/internet/suanota/digitacao_online/validar_usuario.asp")
                    .data("pessoa", "fisica")
                    .data("prosseguir", "Prosseguir")
                    .cookies(cookies)
                    .execute();

            System.out.println(execute8.parse().text().contains("Esse participante não possui nenhum lote de documentos aberto.") + " " + execute8.statusCode());
            Connection.Response executeF2 = Jsoup.connect("https://www.sefaz.ce.gov.br/content/aplicacao/internet/suanota/digitacao_online/incluir_cf.asp").userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36").method(Connection.Method.GET).validateTLSCertificates(false)
                    .header("Referer", "https://www.sefaz.ce.gov.br/content/aplicacao/internet/suanota/digitacao_online/tipo_documento.asp")
                    .cookies(cookies)
                    .execute();
            //System.out.println(executeF2.body());
        }
    }
}
