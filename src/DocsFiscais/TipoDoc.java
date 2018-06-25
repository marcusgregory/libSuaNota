/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DocsFiscais;

/**
 *
 * @author Gregory
 */
public enum TipoDoc {
    CUPOM_FISCAL("CF"), NOTA_FISCAL("NF");
    public final String valor;
    
    TipoDoc(String valorOpcao){
        valor=valorOpcao;
    }
    
   public String getValor(){
       return valor;
   }
}
