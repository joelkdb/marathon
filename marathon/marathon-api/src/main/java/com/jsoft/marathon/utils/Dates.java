/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.utils;

import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author joelkdb
 */
public class Dates {

    /**
     * Méthode qui renvoie la date d'aujourd'ui
     *
     * @return
     * @throws ParseException
     */
    public static Date today() throws ParseException {
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDate localDate = localDateTime.toLocalDate();

        Date date = java.sql.Date.valueOf(localDate);
        return date;
    }

    /**
     * Méthiode qui renvoi l'heure courante
     *
     * @return
     * @throws ParseException
     */
    public static Date todayTime() throws ParseException {
        LocalDateTime localDateTime = LocalDateTime.now();

        Date date = java.sql.Timestamp.valueOf(localDateTime);
        return date;
    }

    //Méthode pour convertir une Date en LocalDateTime
    private static LocalDateTime convertToLocalDateTime(Date dateConverter) {
        return new java.sql.Timestamp(dateConverter.getTime()).toLocalDateTime();
    }

    /**
     * Méthode qui compare deux dates
     *
     * @param date1
     * @param date2
     * @return un type <strong>int</strong>
     */
    public static int comparteTwoDates(Date date1, Date date2) {
        return date1.compareTo(date2);
    }

    /**
     * Méthode pour comparer les heures de deux dates si les dates(jour, mois,
     * année) sont les mêmes
     *
     * @param date1
     * @param date2
     * @return
     */
    public static long comparteTimeOfSameDate(Date date1, Date date2) {
        return date1.getTime() - date2.getTime();
    }

    /**
     * Méthode pour faire la différence d'heure entre deux dates et retourne le
     * temps restant (heure, minute, seconde, miliseconde)
     *
     * @param date1
     * @param date2
     * @return un type <strong>Date</strong>
     */
    public static Date getTimeDiffOfDate(Date date1, Date date2) {
        Duration duration = Duration.between(convertToLocalDateTime(date1), convertToLocalDateTime(date2));
        return new Date(duration.toMillis());
    }
}
