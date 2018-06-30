/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Verificadores;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Vitoria
 */
public class Regex {

    public static String numDocFiscal(String html) {
        Pattern pattern = Pattern.compile("staOnlineEx\\(1,(.+\\d)\\);");
        Matcher comparator = pattern.matcher(html);
        if (comparator.find(0)) {
            return comparator.group(1);
        } else {
            return null;
        }
    }
}
