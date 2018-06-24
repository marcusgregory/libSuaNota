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
import org.jsoup.select.Elements;

/**
 *
 * @author Gregory
 */
public class RequestLoginUsuario {
//Método "request" recebe um objeto "Sistema" e faz o Login do usuário usando o CPF e retorna um objeto "Usuário"

    public static Usuario request(Sistema sistema) throws IOException, ErroLoginException {
        //Obtém os cookies da página da sefaz e o numero identificador.
        System.out.println("Obtendo Cookies...");
        Connection.Response pagConsultaID = Jsoup.connect("https://www.sefaz.ce.gov.br/content/aplicacao/internet/suanota/consultas/consulta_ID.asp")
                .userAgent("Mozilla/5.0")
                .method(Connection.Method.POST)
                .validateTLSCertificates(false)//ignora o uso de certificados TLS 
                .data("txtCpfCnpj", sistema.getCPF())//CPF do Usuário.
                .data("rdbCpfCnpj", "2")//tipo de usuário 1=CNPJ 2=CPF.
                .data("btnOk", "Avançar")//Click no botão de avançar na página.
                .execute();
        //Verifica se existe um "alert" de erro na página de resposta, se houver então o CPF/CNPJ foi digitado incorretamente 
        //ou não existe no Sistema da Sefaz.
        if (pagConsultaID.parse().select("#textoContainer > form > p:nth-child(1) > span").isEmpty()) {
            throw new ErroLoginException("CPF/CNPJ Inválido ou não cadastrado!");//Lança uma exceção.
            //se não houver um "alert" prossegue com as solicitações.
        } else {
            //Obte o número identificador necessário para efetuar o Login na próxima solicitação.
            String identificador = pagConsultaID.parse().select("#textoContainer > form > p:nth-child(1) > span").get(0).text();
            //Salva os cookies da sessão
            Map<String, String> cookies = pagConsultaID.cookies();
            System.out.println("Cookies:" + cookies);
            System.out.println("Cookies OK");
            System.out.println("Efetuando login...");
            //Efetua o Login usando o identificador e o CPF do usuario.
            Connection.Response pagLogin = Jsoup.connect("https://www.sefaz.ce.gov.br/content/aplicacao/internet/suanota/digitacao_online/validar_usuario.asp")
                    .method(Connection.Method.POST)
                    .header("Connection", "keep-alive")
                    .header("Referer", "https://www.sefaz.ce.gov.br/content/aplicacao/internet/suanota/consultas/ler_dados.asp")
                    .cookies(cookies)
                    .data("pagina", "incluir_documento")
                    .data("pessoa", "fisica")//tipo de usuario "fisica"/"juridica"
                    .userAgent("Mozilla/5.0")
                    .data("txtIdentificador", identificador)//numero identificador
                    .data("txtCpfCnpj", sistema.getCPF())//CPF
                    .data("btnOk", "Avançar")
                    .validateTLSCertificates(false)
                    .followRedirects(false)//permite que a página não seja redirecionada com "HTTP/302"
                    .execute();
            System.err.println("StatusHTTP=" + pagLogin.statusCode() + " | " + pagLogin.statusMessage());
            /*Verifica se a resposta é HTTP/200, se for o usuário efetuou o Login
            se a resposta for 302 o usuário está inativo no sistema ou o numero identificador está errado.
            */
            if (pagLogin.statusCode() == 200) {
                System.out.println("Login OK");
                Elements tabUsuario = pagLogin.parse().select("form>table td:eq(1)");//Tabela onde se encontra o nome,cpf e o id.
                Usuario usuario = new Usuario();
                usuario.setNumID(tabUsuario.get(0).text());
                usuario.setNome(tabUsuario.get(1).text());
                usuario.setCPF(tabUsuario.get(2).text());
                usuario.setCookies(cookies);
                return usuario;
            } else {
                throw new ErroLoginException("Usuário inativo ou número identificador errado!");
            }
        }

    }
}
