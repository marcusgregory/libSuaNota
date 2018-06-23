/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Requests;

import Exceptions.ErroLoginException;
import Sistema.Sistema;
import Sistema.Usuario;
import java.io.IOException;
import java.util.Map;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

/**
 *
 * @author Gregory
 */
public class RequestUsuario {

    public static Usuario request(Sistema sistema) throws IOException, ErroLoginException {
        System.out.println("Obtendo Cookies...");
        Connection.Response execute1 = Jsoup.connect("https://www.sefaz.ce.gov.br/content/aplicacao/internet/suanota/consultas/consulta_ID.asp").userAgent("Mozilla/5.0").method(Connection.Method.POST).validateTLSCertificates(false)
                .data("txtCpfCnpj", sistema.getCPF())
                .data("rdbCpfCnpj", "2")
                .data("btnOk", "Avançar")
                .execute();
         if(execute1.parse().select("#textoContainer > form > p:nth-child(1) > span").isEmpty()){
            throw new IOException("CPF/CNPJ Inválido ou não cadastrado!");
        }else{
        String identificador = execute1.parse().select("#textoContainer > form > p:nth-child(1) > span").get(0).text();

        Map<String, String> cookies = execute1.cookies();

        System.out.println("Cookies:" + cookies);
        System.out.println("Cookies OK");
        System.out.println("Efetuando login...");
        Connection.Response execute = Jsoup.connect("https://www.sefaz.ce.gov.br/content/aplicacao/internet/suanota/digitacao_online/validar_usuario.asp")
                .method(Connection.Method.POST)
                .header("Connection", "keep-alive")
                .header("Referer", "https://www.sefaz.ce.gov.br/content/aplicacao/internet/suanota/consultas/ler_dados.asp")
                .cookies(cookies)
                .data("pagina", "incluir_documento")
                .data("pessoa", "fisica").userAgent("Mozilla/5.0")
                .data("txtIdentificador", identificador)
                .data("txtCpfCnpj", sistema.getCPF())
                .data("btnOk", "Avançar").validateTLSCertificates(false).followRedirects(false).execute();
        System.err.println("StatusHTTP=" + execute.statusCode() + " | " + execute.statusMessage());
        if (execute.statusCode() == 200) {
            System.out.println("Login OK");
            Usuario usuario = new Usuario();
            usuario.setNumID(execute.parse().select("form>table td:eq(1)").get(0).text());
            usuario.setNome(execute.parse().select("form>table td:eq(1)").get(1).text());
            usuario.setCPF(execute.parse().select("form>table td:eq(1)").get(2).text());
            usuario.setCookies(cookies);
            return usuario;
        } else {
            throw new ErroLoginException("Usuário inativo ou número identificador errado!");
        }
         }
        
    }
}
