/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.utils;

import java.util.ResourceBundle;

/**
 *
 * @author joelkdb
 */
public class MarathonUtils {

    /**
     * Méthode renvoyant les codes des classes-entités générés sous un format
     *
     * @param prefixe
     * @param count
     * @return
     */
    public static String generateProjectCode(String prefixe, int count) {
        return prefixe + String.format("%02d", count);
    }

    /**
     * Permet de récupérer un message des fichiers properties à partir de la clé
     * et du nom du fichier
     * @param key
     * @param bundleName
     * @return la valeur correspondand au à la clé d'un bundle
     */
    public static String getValueFromBundle(String key, String bundleName) {
        //Locale[ ] locales = {Locale.FRENCH, Locale.ENGLISH};
        ResourceBundle bundle = ResourceBundle.getBundle(bundleName);
        return bundle.getString(key);
    }
}
