/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.dao;

import com.jsoft.marathon.entities.Equipe;
import com.jsoft.marathon.entities.EquipeExercice;
import com.jsoft.marathon.entities.EquipeExercicePK;
import com.jsoft.marathon.entities.Test;
import com.webapp.commons.core.dao.GenericDAOBeanLocal;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author joelkdb
 */
@Local
public interface EquipeExerciceDAOBeanLocal extends
        GenericDAOBeanLocal<EquipeExercice, EquipeExercicePK> {

    /**
     * Permet de trouver les exercices auxquels doit être confrontée l'équipe
     *
     * @param equipe
     * @param test
     * @return
     */
    public List<EquipeExercice> findByEquipeTest(Equipe equipe, Test test);
}
