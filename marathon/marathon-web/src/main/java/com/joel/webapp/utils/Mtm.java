package com.joel.webapp.utils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.net.HttpURLConnection;
import java.net.URL;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author joelkdb
 */
public class Mtm {

    /**
     * 
     * @param joel le message à afficher en mode severity_warn
     */
    public static void joelMessageWarn(String joel) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, joel, "");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /**
     * 
     * @param joel le message à afficher en mode severity_info
     */
    public static void joelMessageInfoPerso(String joel) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, joel, "");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    /**
     * 
     * @param joel le message à afficher en severity_warn genre Veuillez saisir "+joel+" svp !
     */
    public static void joelMessageWarnSaisir(String joel) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Veuillez saisir "+joel+" s'il vous plait!", "");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    /**
     * 
     * @param joel le message à afficher en severity_warn genre Veuillez choisir "+joel+" svp !
     */
    public static void joelMessageWarnChoisir(String joel) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Veuillez choisir "+joel+" s'il vous plait !", "");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    /**
     * 
     * @param joel le message à afficher en severity_warn genre Veuillez sélectionner "+joel+" svp !
     */
    public static void joelMessageWarnSelectionner(String joel) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Veuillez sélectionner "+joel+" s'il vous plait !", "");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /**
     *  Message à afficher en severity_info genre Opération effectuée avec succès !
     */
    public static void joelMessageInfo() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Opération effectuée avec succès !", "");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /**
     * Message à afficher en severity_error genre Echec de l'opération
     */
    public static void joelMessageError() {
        FacesMessage message2 = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Echec de l'opération!", "");
        FacesContext.getCurrentInstance().addMessage(null, message2);
    }
    
    
    /**
     * 
     * @param joel le message à afficher en mode severity_error
     */
    public static void joelMessageErrorPerso(String joel) {
        FacesMessage message2 = new FacesMessage(FacesMessage.SEVERITY_ERROR, joel, "");
        FacesContext.getCurrentInstance().addMessage(null, message2);
    }

    /**
     * Message à afficher en severity_warn genre Opération interrompue : Absence de droit permettant d'effectuer cette opération, veuillez contacter l'administrateur Svp !
     */
    public static void joelLog4jMessageError() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Opération interrompue : Absence de droit permettant d'effectuer cette opération,"
                + "\nVeuillez contacter l'administrateur s'il vous plait!", "");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
   
    /**
     * Méthode permettant de tester si l'utilisateur dispose de la connexion sur son hôte
     * @return vai/faux
     */
    public static boolean testConnection(){
        HttpURLConnection connection = null;
        try {
            URL testgoogle = new URL("http://www.google.com");
            connection = (HttpURLConnection) testgoogle.openConnection();
            connection.setRequestMethod("GET");
             
            if(connection.getResponseCode() != HttpURLConnection.HTTP_OK){
                System.out.println(connection.getResponseMessage());
                System.out.println(" Reponse code :"+connection.getResponseCode());
                return false;
            }else{
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally{
            connection.disconnect();
        }
    }

//    public static void logMikiLog4j(String name, org.apache.log4j.Level INFO, String connexion_à_lapplication) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
  
}
