/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.core.rest.webservices;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.ParameterizedType;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joelkdb
 * @param <T> Type de l'objet
 */
public class WSObjectTools<T extends Object> {

    private final Class<T> type;

    public WSObjectTools() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().
                getGenericSuperclass();
        this.type = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }

    public WSObjectTools(Class<T> type) {
        this.type = type;
    }
    
    /**
     * Cette methode permet de retourner une liste d'objet (type d'objet passé
     * en parametre à la classe) resultants de l'url passé en parametre
     * @param urlWebservice url du web service (pas besoin de paramètre path)
     * @return liste de l'objet passé en parametre au constructeur de la classe
     * MikiWsTools ou null s'il ya erreur
     */
    public List<T> objectListFromUrl(String urlWebservice) {
        final Gson gson = new GsonBuilder().create();
        final List<T> values = new ArrayList<T>();
        try {

            URL url = new URL(urlWebservice);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Erreur -> HTTP code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            String output2 = "";
            while ((output = br.readLine()) != null) {
                output2 += output;
            }

            final JsonReader jsonReader = new JsonReader(new StringReader(output2));
            final JsonParser jsonParser = new JsonParser();
            final JsonArray jsonArray = jsonParser.parse(jsonReader).getAsJsonArray();

            for (final JsonElement element : jsonArray) {
                final T value = gson.fromJson(element, type);
                values.add(value);
            }

            jsonReader.close();

            conn.disconnect();

            return values;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;

        } catch (RuntimeException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Cette methode permet de retourner une liste d'objet (type d'objet passé
     * en parametre à la classe) resultants du json passé en paramètre
     * @param json String contenant le json passé en paramètre(le json en
     * question doit renfermé une liste de l'objet)
     * @return liste de l'objet passé en parametre au constructeur de la classe
     * MikiWsTools ou null s'il ya erreur
     */
    public List<T> objectList(String json) {
        final Gson gson = new GsonBuilder().create();
        final List<T> values = new ArrayList<T>();
        try {

            final JsonReader jsonReader = new JsonReader(new StringReader(json));
            final JsonParser jsonParser = new JsonParser();
            final JsonArray jsonArray = jsonParser.parse(jsonReader).getAsJsonArray();

            for (final JsonElement element : jsonArray) {
                final T value = gson.fromJson(element, type);
                values.add(value);
            }

            jsonReader.close();

            return values;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Cette methode permet de retourner un objet (type d'objet passé en
     * parametre à la classe) resultant de l'url passé en parametre
     * @param urlWebservice url du webservice (paramètre path obligatoire)
     * @return un objet (type d'objet passé en parametre à la classe) ou null
     * s'il ya erreur
     */
    public T getObjectFromUrl(String urlWebservice) {
        final Gson gson = new GsonBuilder().create();
        try {

            URL url = new URL(urlWebservice);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Erreur -> HTTP code :"
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            String output2 = "";
            while ((output = br.readLine()) != null) {
                output2 += output;
            }

            conn.disconnect();

            final T value = gson.fromJson(output2, type);
            return value;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;

        } catch (RuntimeException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Cette methode permet de retourner un objet (type d'objet passé en
     * parametre à la classe) resultant du json passé en paramètre
     *
     * @param json String contenant le json passé en paramètre(le json en
     * question doit renfermé un seul objet)
     * @return un objet (type d'objet passé en parametre à la classe) ou null
     * s'il ya erreur
     */
    public T getObject(String json) {
        final Gson gson = new GsonBuilder().create();
        try {
            final T value = gson.fromJson(json, type);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Cette methode permet d'ajouter un objet(type d'objet passé en parametre à
     * la classe) à l'url passé en parametre
     *
     * @param urlWebservice url du webservice (pas besoin de paramètre path)
     * @param value objet à ajouter
     * @return true si l'opération est un succès ou false sinon
     */
    public boolean ajouterObjet(String urlWebservice, T value) {
        final Gson gson = new GsonBuilder().create();
        try {

            URL url = new URL(urlWebservice);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            String input = gson.toJson(value);

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_NO_CONTENT) {
                throw new RuntimeException("Erreur -> HTTP code :"
                        + conn.getResponseCode());
            } else {
                System.out.println("Ajout de l'objet éffectué avec succès !");
            }

            conn.disconnect();
            return true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;

        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * Cette methode permet de modifier un objet(type d'objet passé en parametre
     * à la classe) à l'url passé en parametre
     *
     * @param urlWebservice url du webservice (paramètre path obligatoire)
     * @param value objet à modifier
     * @return true si l'opération est un succès ou false sinon
     */
    public boolean modifierObjet(String urlWebservice, T value) {
        final Gson gson = new GsonBuilder().create();
        try {

            URL url = new URL(urlWebservice);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");

            String input = gson.toJson(value);

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_NO_CONTENT) {
                throw new RuntimeException("Erreur -> HTTP code :"
                        + conn.getResponseCode());
            } else {
                System.out.println("Modification de l'objet éffectuée avec succès !");
            }

            conn.disconnect();
            return true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;

        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;

        }

    }

    /**
     * Cette methode permet de supprimer un objet(type d'objet passé en
     * parametre à la classe) à l'url passé en parametre
     *
     * @param urlWebservice url du webservice (paramètre path obligatoire)
     * @return true si l'opération est un succès ou false sinon
     */
    public boolean supprimerObjet(String urlWebservice) {
        try {

            URL url = new URL(urlWebservice);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");

            if (conn.getResponseCode() != HttpURLConnection.HTTP_NO_CONTENT) {
                throw new RuntimeException("Erreur -> HTTP code :"
                        + conn.getResponseCode());
            } else {
                System.out.println("Suppression de l'objet éffectuée avec succès !");
            }

            conn.disconnect();
            return true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;

        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * Cette methode permet de transformer en format Json l'objet passé en
     * parametre
     *
     * @param value objet à transformer en Json
     * @return l'objet sous son format Json
     */
    public String toJson(T value) {
        final Gson gson = new GsonBuilder().create();
        return gson.toJson(value);
    }

    /**
     * Cette methode permet de transformer en format Json une liste d'objet
     * passé en paramètre
     * @param valueListe liste d'objet à transformer en Json
     * @return la liste sous format Json
     */
    public String toJsonListObject(List<T> valueListe) {
        final Gson gson = new GsonBuilder().create();
        try {
            if (!valueListe.isEmpty()) {
                final StringWriter stringWriter = new StringWriter();
                final JsonWriter jsonWriter = new JsonWriter(stringWriter);
                jsonWriter.beginArray();
                for (T val : valueListe) {
                    if (val != null) {
                        gson.toJson(val, type, jsonWriter);
                    } else {
                        jsonWriter.nullValue();
                    }
                }
                jsonWriter.endArray();
                jsonWriter.close();
                return stringWriter.toString();
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
