/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Requests;

import DocsFiscais.DocFiscal;
import DocsFiscais.ListaDocFiscal;
import DocsFiscais.TipoDoc;
import Exceptions.NenhumLoteAbertoException;
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

    public static ListaDocFiscal request(Usuario usuario) throws IOException, NenhumLoteAbertoException {
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
            throw new NenhumLoteAbertoException("O usuário não possui lote de documentos fiscais aberto.");
        } else {
            Connection.Response execute2 = Jsoup.connect("https://www.sefaz.ce.gov.br/content/aplicacao/internet/suanota/digitacao_online/incluir_cf.asp").userAgent("Mozilla/5.0").method(Connection.Method.GET).validateTLSCertificates(false)
                    .header("Referer", "https://www.sefaz.ce.gov.br/content/aplicacao/internet/suanota/digitacao_online/tipo_documento.asp")
                    .cookies(usuario.getCookies())
                    .execute();
           
            Elements docsLote = execute2.parse().select("#textoContainer > form:nth-child(2) > table:nth-child(10)").select("tr").attr("height", "20").next().next().not(".titulo");
            ListaDocFiscal lista = new ListaDocFiscal();
            String numLote=execute2.parse().select("form:nth-child(2) > input:nth-child(5)").attr("value");
            for (Element docLote : docsLote) {
                DocFiscal df = new DocFiscal();
                String tipo = docLote.select("td:nth-child(1)").text();
                switch (tipo) {
                    case "CF":
                        df.setTipo(TipoDoc.CUPOM_FISCAL);
                        break;
                    case "NF":
                        df.setTipo(TipoDoc.NOTA_FISCAL);
                        break;
                    default:
                        return lista;

                }
                df.setNumCGF(docLote.select("td:nth-child(2)").text());
                df.setNumDocCOO(docLote.select("td:nth-child(3)").text());
                df.setNumCaixaECF(docLote.select("td:nth-child(4)").text());
                df.setNumFab(docLote.select("td:nth-child(5)").text());
                df.setEspecie(docLote.select("td:nth-child(6)").text());
                df.setSerie(docLote.select("td:nth-child(7)").text());
                df.setNumAIDF(docLote.select("td:nth-child(8)").text());
                df.setDataEmissao(docLote.select("td:nth-child(9)").text());
                df.setValor(docLote.select("td:nth-child(10)").text());
                df.setSecDocFiscal(Regex.numDocFiscal(docLote.select("td:nth-child(13)").html()));
                df.setHidLote(numLote);
                lista.addDocFiscal(df);

            }
            return lista;
        }
    }
}
