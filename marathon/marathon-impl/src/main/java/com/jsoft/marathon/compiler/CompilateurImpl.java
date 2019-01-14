/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.compiler;

/**
 *
 * @author joelkdb
 */
public class CompilateurImpl {
    
    public static String result;
    private Compilateur compilateur;

    public CompilateurImpl() {
        this.compilateur = new Compilateur();
    }
    
    public String getCompilerResultService(String chemin, String fileName, String langage){
        result = compilateur.getCompilerResult(chemin, fileName, langage);
        return result;
    }

    public static String getResult() {
        return result;
    }

    public static void setResult(String result) {
        CompilateurImpl.result = result;
    }
    
    
}
