/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.compiler;

import com.github.egbakou.mrtcompilers.complex.CSharpCompiler;
import com.github.egbakou.mrtcompilers.complex.GccCompiler;
import com.github.egbakou.mrtcompilers.complex.JavaCompiler;
import com.github.egbakou.mrtcompilers.complex.PascalCompiler;
import com.github.egbakou.mrtcompilers.simple.JsCompiler;
import com.github.egbakou.mrtcompilers.simple.PhpCompiler;
import com.github.egbakou.mrtcompilers.simple.PythonCompiler;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 *
 * @author joelkdb
 */
public class Compilateur {

    private JavaCompiler javaCompiler;
    private PythonCompiler pythonCompiler;
    private PhpCompiler phpCompiler;
    private JsCompiler jsCompiler;
    private GccCompiler gccCompiler;
    private PascalCompiler pascalCompiler;
    private CSharpCompiler cSharpCompiler;

    public Compilateur() {
        
    }

    public String getCompilerResult(String chemin, String fileName, String langage) {
        String result = null;
        if (langage.equalsIgnoreCase("JAVA")) {
            try {
                javaCompiler = new JavaCompiler();
                result = javaCompiler.directory(new File(chemin)).compileAndRunInTiming(fileName, TimeUnit.MINUTES, 1L);
            } catch (InterruptedException ex) {
                System.out.println("Compilation interrompue !");
            } catch (TimeoutException ex) {
                System.out.println("Temps d'exécution écoulée !");
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } else if (langage.equalsIgnoreCase("PYTHON")) {
            try {
                result = pythonCompiler.directory(new File(chemin)).runInTiming(fileName, TimeUnit.MINUTES, 1L);
            } catch (InterruptedException ex) {
                System.out.println("Compilation interrompue !");
            } catch (TimeoutException ex) {
                System.out.println("Temps d'exécution écoulée !");
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } else if (langage.equalsIgnoreCase("PHP")) {
            try {
                phpCompiler = new PhpCompiler();
                result = phpCompiler.directory(new File(chemin)).runInTiming(fileName, TimeUnit.MINUTES, 1L);
            } catch (InterruptedException ex) {
                System.out.println("Compilation interrompue !");
            } catch (TimeoutException ex) {
                System.out.println("Temps d'exécution écoulée !");
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } else if (langage.equalsIgnoreCase("JAVASCRIPT")) {
            try {
                jsCompiler = new JsCompiler();
                result = jsCompiler.directory(new File(chemin)).runInTiming(fileName, TimeUnit.MINUTES, 1L);
            } catch (InterruptedException ex) {
                System.out.println("Compilation interrompue !");
            } catch (TimeoutException ex) {
                System.out.println("Temps d'exécution écoulée !");
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } else if (langage.equalsIgnoreCase("PASCAL")) {
            try {
                pascalCompiler = new PascalCompiler();
                result = pascalCompiler.directory(new File(chemin)).compileAndRunInTiming(fileName, TimeUnit.MINUTES, 1L);
            } catch (InterruptedException ex) {
                System.out.println("Compilation interrompue !");
            } catch (TimeoutException ex) {
                System.out.println("Temps d'exécution écoulée !");
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } else if (langage.equalsIgnoreCase("C/C++")) {
            try {
                gccCompiler = new GccCompiler();
                result = gccCompiler.directory(new File(chemin)).compileAndRunInTiming(fileName, TimeUnit.MINUTES, 1L);
            } catch (InterruptedException ex) {
                System.out.println("Compilation interrompue !");
            } catch (TimeoutException ex) {
                System.out.println("Temps d'exécution écoulée !");
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } else if (langage.equalsIgnoreCase("C#")) {
            try {
                cSharpCompiler = new CSharpCompiler();
                result = cSharpCompiler.directory(new File(chemin)).compileAndRunInTiming(fileName, TimeUnit.MINUTES, 1L);
            } catch (InterruptedException ex) {
                System.out.println("Compilation interrompue !");
            } catch (TimeoutException ex) {
                System.out.println("Temps d'exécution écoulée !");
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

        return result;
    }

    public JavaCompiler getJavaCompiler() {
        return javaCompiler;
    }

    public void setJavaCompiler(JavaCompiler javaCompiler) {
        this.javaCompiler = javaCompiler;
    }

    public PythonCompiler getPythonCompiler() {
        return pythonCompiler;
    }

    public void setPythonCompiler(PythonCompiler pythonCompiler) {
        this.pythonCompiler = pythonCompiler;
    }

    public PhpCompiler getPhpCompiler() {
        return phpCompiler;
    }

    public void setPhpCompiler(PhpCompiler phpCompiler) {
        this.phpCompiler = phpCompiler;
    }

    public JsCompiler getJsCompiler() {
        return jsCompiler;
    }

    public void setJsCompiler(JsCompiler jsCompiler) {
        this.jsCompiler = jsCompiler;
    }

    public GccCompiler getGccCompiler() {
        return gccCompiler;
    }

    public void setGccCompiler(GccCompiler gccCompiler) {
        this.gccCompiler = gccCompiler;
    }

    public PascalCompiler getPascalCompiler() {
        return pascalCompiler;
    }

    public void setPascalCompiler(PascalCompiler pascalCompiler) {
        this.pascalCompiler = pascalCompiler;
    }

    public CSharpCompiler getcSharpCompiler() {
        return cSharpCompiler;
    }

    public void setcSharpCompiler(CSharpCompiler cSharpCompiler) {
        this.cSharpCompiler = cSharpCompiler;
    }

}
