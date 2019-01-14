/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.web.beans;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author joelkdb
 */
@ManagedBean
public class LangageBean {

    private List<String> langages;

    @PostConstruct
    public void init() {
        langages = new ArrayList<>();
        langages.add("C/C++");
        langages.add("C#");
        langages.add("JAVA");
        langages.add("JAVASCRIPT");
        langages.add("PASCAL");
        langages.add("PHP");
        langages.add("PYTHON");
    }

    public List<String> getLangages() {
        return langages;
    }

}
