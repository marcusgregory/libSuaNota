/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testes;

import DocsFiscais.DocFiscal;
import DocsFiscais.TipoDoc;
import Exceptions.ErroDocFiscalException;
import Exceptions.ErroLoginException;
import Exceptions.UsuarioNaoLogadoException;
import Sistema.Sistema;
import Sistema.Usuario;
import java.io.IOException;

/**
 *
 * @author Gregory
 */
public class TesteCadastroDocs {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ErroLoginException, UsuarioNaoLogadoException, ErroDocFiscalException {
        Usuario u=new Sistema("38200297373").logar();
        System.out.println("Olá " + u.getNome() + ", seu Identificador é:" + u.getNumID());
        DocFiscal df = new DocFiscal();
        df.setTipo(TipoDoc.CUPOM_FISCAL);
        df.setNumCGF("5802470");
        df.setNumDocCOO("1234514");
        df.setNumCaixaECF("5");
        df.setNumFab("DR0609BR000000190898");
        df.setDataEmissao("12/06/2018");
        df.setValor("55,07");
        df.cadastrar();
        
    }
    
}
