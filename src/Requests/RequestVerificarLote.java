/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Requests;

import Exceptions.NenhumLoteAbertoException;
import Sistema.Usuario;
import java.io.IOException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

/**
 *
 * @author Gregory
 */
public class RequestVerificarLote {
    public static boolean verificar(Usuario usuario) throws IOException{
        Connection.Response execute = Jsoup.connect("https://www.sefaz.ce.gov.br/content/aplicacao/internet/suanota/digitacao_online/ler_lote_digitacao.asp").userAgent("Mozilla/5.0")
                .method(Connection.Method.POST)
                .validateTLSCertificates(false)
                .header("Referer", "https://www.sefaz.ce.gov.br/content/aplicacao/internet/suanota/digitacao_online/validar_usuario.asp")
                .data("pessoa", "fisica")
                .data("prosseguir", "Prosseguir")
                .followRedirects(false)
                .cookies(usuario.getCookies())
                .execute();
        if (execute.statusCode() == 200) {
            return false;
        } else {
        return true;
        }
        
    }
}
