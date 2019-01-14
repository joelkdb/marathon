/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import com.webapp.commons.core.utils.FilterParams;
import com.webapp.commons.core.utils.IListFilter;
import com.webapp.commons.core.utils.SortParams;

/**
 *
 * @author Ramses
 * @param <E> represente entité
 * @param <ID> Type de l'identifiant
 */
public interface GenericServiceBeanRemote<E extends Serializable, ID> {

    /**
     * Persiste l'objet passé en paramètre.
     *
     * @param e L'objet à persister.
     */
    void addOne(E e);

    /**
     * Persiste les différents objets passés en paramètre.
     *
     * @param es la liste d'objets à persister.
     */
    void addAll(Collection<E> es);

    /**
     * Modifie l'objet passé en paramètre.
     *
     * @param e la nouvelle valeur de l'objet.
     *
     * @return l'objet après sa modification.
     */
    E updateOne(E e);

    /**
     * Modifie les differents objets passés en paramètre.
     *
     * @param es la liste des nouvelles valeurs des differents objets
     *
     * @return la liste des nouvelles valeurs des objets passés en paramètres
     */
    Collection<E> updateAll(Collection<E> es);

    /**
     * Supprime l'objet passé en paramètre.
     *
     * @param e l'objet à supprimer.
     */
    void deleteOne(E e);

    /**
     * Modifie les differents objets passés en paramètre.
     *
     * @param es la liste des objets à supprimer.
     */
    void deleteAll(Collection<E> es);

    /**
     * Supprime l'objet passé en paramètre.
     *
     * @param id l'identifiant de l'objet à supprimer.
     */
    void deleteOne(ID id);

    /**
     * Modifie les differents objets passés en paramètre.
     *
     * @param ids la liste des identifiants des objets à supprimer.
     */
    void deleteOne(Collection<ID> ids);

    /**
     * Supprime toute instance de l'objet.
     */
    void deleteAll();

    /**
     * Renvoie l'entité correspondant à l'identifiant passé en paramètre.
     *
     * @param id l'identifiant de l'objet.
     * @return l'objet ayant l'identifiant passé en paramètre.
     */
    E getOne(ID id);

    /**
     * Renvoie les entités correspondant aux identifiants passés en paramètre.
     *
     * @param ids Les identifiants.
     * @return Les entités correspondantes.
     */
    List<E> getAll(Collection<ID> ids);

    /**
     *
     * @return la liste de tout les objets de la classe.
     */
    List<E> getAll();

    /**
     * Liste tout les objets selon une propriété donnée dans l'ordre ascendant
     * ou descendant
     *
     * @param sortProperty propriété de tri.
     * @param sortAsc tri Ascendant si <pre>true</pre>
     *
     * @return la liste de tout les objets triés de la classe.
     */
    List<E> getAll(String sortProperty, boolean sortAsc);

    List<E> getAll(SortParams sortParams);

    List<E> getAll(SortParams sortParams, FilterParams filterParams);

    /**
     * Liste limitée des differents objets selon une propriété donnée dans
     * l'ordre ascendant ou descendant
     *
     * @param first index du premier objet
     * @param size nombre de lignz à afficher
     * @param sortProperty propriété de tri
     * @param sortAsc tri Ascendant si <pre>true</pre>
     *
     * @return la liste limitée des objets triés de la classe selon la
     * propriété.
     */
    List<E> getAll(int first, int size, String sortProperty, boolean sortAsc);

    /**
     * Liste limitée des differents objets triée selon <c>SortParams</c>.
     *
     * @param first index du premier objet
     * @param size nombre de lignz à afficher
     * @param sortParams Paramètre de tri
     *
     * @return la liste limitée des objets triés de la classe selon la
     * propriété.
     */
    List<E> getAll(int first, int size, SortParams sortParams);

    List<E> getAll(int first, int size, SortParams sortParams, FilterParams filterParams);

    List<E> getAll(IListFilter listFilter);

    List<E> getAll(IListFilter listFilter, Integer max);

    List<E> getAll(IListFilter listFilter, SortParams sortParams);

    List<E> getAll(int first, int size, IListFilter listFilter);

    List<E> getAll(int first, int size, IListFilter listFilter, SortParams sortParams);

    List<E> getAll(FilterParams filterParams);

    /**
     * Renvoie le nombre d'enregistrement.
     *
     * @return Le nombre d'enregistrement.
     */
    Long count();

    Long count(IListFilter listFilter);

    ID getId(E e);

    /**
     * Vérifie si l'objet entité passé en paramètre existe dans l'unité de
     * persistence.
     *
     * @param e L'objet entité à vérifier.
     * @return <code>true</code> si l'objet existe.
     */
    boolean exists(E e);

    /**
     * Vérifie si l'objet entité dont l'identifiant est passé en paramètre
     * existe dans l'unité de persistence.
     *
     * @param id L'identifiant de l'objet entité à vérifier.
     * @return <code>true</code> si l'objet existe.
     */
    boolean exists(ID id);
}
