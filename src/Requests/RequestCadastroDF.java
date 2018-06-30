/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Requests;

import DocsFiscais.DocFiscal;
import DocsFiscais.TipoDoc;
import Exceptions.ErroDocFiscalException;
import Sistema.Usuario;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

/**
 *
 * @author Gregory
 */
public class RequestCadastroDF {

    public static void request(DocFiscal df,Usuario usuario) throws IOException, ErroDocFiscalException {
       if(df.getTipo()==TipoDoc.CUPOM_FISCAL){
           CadCupomFiscal(df,usuario);
       }else{
           CadNotaFiscal(df,usuario);
       }
    }
    private static void CadCupomFiscal(DocFiscal df,Usuario usuario) throws IOException, ErroDocFiscalException{
        if(Requests.RequestVerificarLote.verificar(usuario)){
            Connection.Response execute2 = Jsoup.connect("https://www.sefaz.ce.gov.br/content/aplicacao/internet/suanota/digitacao_online/incluir_cf.asp").userAgent("Mozilla/5.0")
                .method(Connection.Method.GET)
                .validateTLSCertificates(false)
                .header("Referer", "https://www.sefaz.ce.gov.br/content/aplicacao/internet/suanota/digitacao_online/incluir_cf.asp")
                .cookies(usuario.getCookies())
                .execute();
            String numLote=execute2.parse().select("form:nth-child(2) > input:nth-child(5)").attr("value");
         Connection.Response execute3 = Jsoup.connect("https://www.sefaz.ce.gov.br/content/aplicacao/internet/suanota/digitacao_online/incluir_cf.asp").userAgent("Mozilla/5.0").method(Connection.Method.POST).validateTLSCertificates(false)
                .header("Referer", "https://www.sefaz.ce.gov.br/content/aplicacao/internet/suanota/digitacao_online/incluir_cf.asp")
                .cookies(usuario.getCookies())
                 .followRedirects(false)
                .data("cgf", df.getNumCGF())
                .data("numero_cf", df.getNumDocCOO())
                .data("numero_caixa", df.getNumCaixaECF())
                .data("num_fab_ecf", df.getNumFab())
                .data("data_emissao", df.getDataEmissao())
                .data("valor", df.getValor())
                .data("hidLote", numLote)
                .data("hidParticipante", usuario.getNumID())
                .data("seqDocFiscal", df.getSecDocFiscal())
                .data("tipoParticipanteSessao", "fisica")
                .data("salvar", "Salvar")
                .execute();

        System.out.println(execute3.body());
        
        if(execute3.statusCode()==302){
   
        Pattern pattern = Pattern.compile("\\.\\.\\/erro\\.asp\\?msg=(?<msg>.+)\\&");
        Matcher comparator = pattern.matcher( URLDecoder.decode(execute3.header("Location"),"UTF-8"));
        if (comparator.find(0)) {
          throw new ErroDocFiscalException(comparator.group("msg"));
        }
        }    
            
        }else{
            System.err.println("O usuário não possui lote aberto");
        }
    }
    private static void CadNotaFiscal(DocFiscal df,Usuario usuario){
        throw new UnsupportedOperationException("O cadastro de nota fiscal ainda não foi implementado!");
    }
}
