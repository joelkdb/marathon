/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.core.rest.webservices;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author joelkdb
 */
public class WSJsonTools {

    public WSJsonTools() {
    }

    public Map<String, String> lecture(List<String> listeAttribut, final JsonReader reader) throws IOException {
        Map<String, String> resultat = new HashMap<>();
        reader.beginObject();
        while (reader.hasNext()) {
            final String name = reader.nextName();
            int iter = 0;
            for (String attribut : listeAttribut) {
                if (name.equals(attribut)) {
                    if (reader.peek() == JsonToken.NULL) {
                        reader.nextNull();
                        resultat.put(attribut, null);
                    } else {
                        resultat.put(attribut, reader.nextString());
                    }
                } else {
                    iter++;
                }
            }

            if (iter == listeAttribut.size()) {
                reader.skipValue();
            }
        }
        reader.endObject();
        return resultat;
    }

    public List<Map<String, String>> lectureJson(List<String> listeAttribut, final JsonReader reader)
            throws IOException {
        List<Map<String, String>> resultat2 = new ArrayList<>();
        reader.setLenient(true);
        reader.beginArray();
        while (reader.hasNext()) {
            resultat2.add(lecture(listeAttribut, reader));
        }
        reader.endArray();
        return resultat2;
    }

    /**
     * Cette methode permet de renvoyer une liste de map contenant pour chaque
     * map, les attributs(passé en parametre) et leurs valeurs concernant un
     * objet de la liste(liste d'objet resultant de l'url)
     *
     * @param urlwebservice url du webservice (faire reference à un renvoie
     * d'une liste d'objet)
     * @param listeAttribut une liste des attributs a chercher dans le Json
     * @return une liste de Map
     */
    public List<Map<String, String>> getListeLectureJsonFromUrl(String urlwebservice, List<String> listeAttribut) {
        try {
            if (!listeAttribut.isEmpty()) {
                String json = WSTools.get(urlwebservice);
                List<Map<String, String>> resultat3 = null;
                final JsonReader reader = new JsonReader(new StringReader(json));
                WSJsonTools classe = new WSJsonTools();
                resultat3 = classe.lectureJson(listeAttribut, reader);
                reader.close();
                return resultat3;
            } else {
                return null;
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Echec de l'opération");
            return null;
        }
    }

    /**
     * Cette methode permet de renvoyer une liste de map contenant pour chaque
     * map, les attributs(passé en parametre) et leurs valeurs concernant un
     * objet de la liste( liste d'objet dans le json passe en parametre)
     *
     * @param json les données en format json d'une liste d'objet)
     * @param listeAttribut une liste des attributs a chercher dans le Json
     * @return une liste de Map
     */
    public List<Map<String, String>> getListeLectureJson(String json, List<String> listeAttribut) {
        try {
            if (!listeAttribut.isEmpty()) {
                List<Map<String, String>> resultat3 = null;
                final JsonReader reader = new JsonReader(new StringReader(json));
                WSJsonTools classe = new WSJsonTools();
                resultat3 = classe.lectureJson(listeAttribut, reader);
                reader.close();
                return resultat3;
            } else {
                return null;
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Echec de l'opération");
            return null;
        }
    }

    /**
     * Cette methode permet de renvoyer un map contenant les attributs(passé en
     * parametre) et leurs valeurs
     *
     * @param urlwebservice url du webservice (faire reference à un renvoie d'un
     * seul objet)
     * @param listeAttribut une liste des attributs a chercher dans le Json
     * @return un Map
     */
    public Map<String, String> getObjetLectureJsonFromUrl(String urlwebservice, List<String> listeAttribut) {
        try {
            if (!listeAttribut.isEmpty()) {
                String json = WSTools.get(urlwebservice);
                final JsonReader reader = new JsonReader(new StringReader(json));
                Map<String, String> resultat = new HashMap<>();
                reader.beginObject();
                while (reader.hasNext()) {
                    final String name = reader.nextName();
                    int iter = 0;
                    for (String attribut : listeAttribut) {
                        if (name.equals(attribut)) {
                            if (reader.peek() == JsonToken.NULL) {
                                reader.nextNull();
                                resultat.put(attribut, null);
                            } else {
                                resultat.put(attribut, reader.nextString());
                            }
                        } else {
                            iter++;
                        }
                    }
                    if (iter == listeAttribut.size()) {
                        reader.skipValue();
                    }
                }
                reader.endObject();
                return resultat;
            } else {
                return null;
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Echec de l'opération");
            return null;
        }
    }

    /**
     * Cette methode permet de renvoyer un map contenant les attributs(passé en
     * parametre) et leurs valeurs
     *
     * @param json les données en format json
     * @param listeAttribut une liste des attributs a chercher dans le Json
     * @return un Map
     */
    public Map<String, String> getObjetLectureJson(String json, List<String> listeAttribut) {
        try {
            if (!listeAttribut.isEmpty()) {
                final JsonReader reader = new JsonReader(new StringReader(json));
                Map<String, String> resultat = new HashMap<>();
                reader.beginObject();
                while (reader.hasNext()) {
                    final String name = reader.nextName();
                    int iter = 0;
                    for (String attribut : listeAttribut) {
                        if (name.equals(attribut)) {
                            if (reader.peek() == JsonToken.NULL) {
                                reader.nextNull();
                                resultat.put(attribut, null);
                            } else {
                                resultat.put(attribut, reader.nextString());
                            }
                        } else {
                            iter++;
                        }
                    }
                    if (iter == listeAttribut.size()) {
                        reader.skipValue();
                    }
                }
                reader.endObject();
                return resultat;
            } else {
                return null;
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Echec de l'opération");
            return null;
        }
    }

    /**
     * Cette methode permet de renvoyer sous format Json l'objet passé en
     * paramètre
     *
     * @param value objet(String,entier,Long etc..) à transformer en Json
     * @return l'objet sous son format Json
     */
    public String toJsonSimpleObjet(Object value) {
        final Gson gson = new GsonBuilder().create();
        return gson.toJson(value);
    }

    /**
     * Cette methode permet de renvoyer sous format Json le contenu du
     * map(attributs et leurs valeurs) passé en paramètre
     *
     * @param valueMap un map contenant les attributs à transformer et leurs
     * valeurs
     * @return un string contenant la transformation du Map en format Json
     * @throws IOException
     */
    public String toJsonObjet(Map<?, ?> valueMap) throws IOException {
        try {
            if (!valueMap.isEmpty()) {
                final StringWriter stringWriter = new StringWriter();
                final JsonWriter jsonWriter = new JsonWriter(stringWriter);
                jsonWriter.beginObject();
                for (Map.Entry<?, ?> val : valueMap.entrySet()) {
                    if (val.getKey() != null) {
                        if (val.getValue() != null) {
                            jsonWriter.name(val.getKey().toString()).value(val.getValue().toString());
                        } else {
                            jsonWriter.name(val.getKey().toString()).nullValue();
                        }
                    }
                }
                jsonWriter.endObject();
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

    /**
     * Cette methode permet de renvoyer sous format d'un tableau Json le contenu
     * de la liste passé en paramètre
     *
     * @param valueDuTableau une liste d'objet(String,entier,long etc..) à
     * transformer en Json
     * @return
     * @throws IOException
     */
    public String toJsonTableau(List<?> valueDuTableau) throws IOException {
        try {
            if (!valueDuTableau.isEmpty()) {
                final StringWriter stringWriter = new StringWriter();
                final JsonWriter jsonWriter = new JsonWriter(stringWriter);
                jsonWriter.beginArray();
                for (Object value : valueDuTableau) {
                    if (value != null) {
                        jsonWriter.value(value.toString());
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

    /**
     * Cette methode permet de renvoyer sous format Json une liste de map passé
     * en paramètre, map contenant les attributs et leurs valeurs decrivant un
     * objet
     *
     * @param valueListeObjet une liste de Map d'objet, ce dernier contient les
     * attributs à transformer et leurs valeurs
     * @return la liste de Map d'objet sous format Json
     * @throws IOException
     */
    public String toJsonListeObjet(List<Map<?, ?>> valueListeObjet) throws IOException {
        try {
            if (!valueListeObjet.isEmpty()) {
                final StringWriter stringWriter = new StringWriter();
                final JsonWriter jsonWriter = new JsonWriter(stringWriter);
                jsonWriter.beginArray();
                for (Map<?, ?> value : valueListeObjet) {
                    if (!value.isEmpty()) {
                        jsonWriter.beginObject();
                        for (Map.Entry<?, ?> valueMap : value.entrySet()) {
                            if (valueMap.getKey() != null) {
                                if (valueMap.getValue() != null) {
                                    jsonWriter.name(valueMap.getKey().toString()).value(valueMap.getValue().toString());
                                } else {
                                    jsonWriter.name(valueMap.getKey().toString()).nullValue();
                                }
                            }
                        }
                        jsonWriter.endObject();
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
