/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsoft.marathon.web.beans;

import com.jsoft.marathon.compiler.CompilateurImpl;
import com.jsoft.marathon.entities.EquipeExercice;
import com.jsoft.marathon.entities.Exercice;
import com.jsoft.marathon.entities.MarathonEquipe;
import com.jsoft.marathon.entities.Test;
import com.jsoft.marathon.exception.InsufficientJokersNumberException;
import com.jsoft.marathon.service.EquipeExerciceServiceBeanRemote;
import com.jsoft.marathon.service.EquipeServiceBeanRemote;
import com.jsoft.marathon.service.ExerciceServiceBeanRemote;
import com.jsoft.marathon.service.TestServiceBeanRemote;
import com.jsoft.marathon.utils.MarathonConstants;
import com.jsoft.marathon.utils.MarathonUtils;
import com.webapp.commons.core.exception.BusinessException;
import com.webapp.commons.core.service.GenericServiceBeanRemote;
import com.webapp.commons.core.web.beans.GenericBean;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.omnifaces.util.Messages;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author joelkdb
 */
@ViewScoped
@ManagedBean(name = "exerciceBean")
public class ExerciceBean extends GenericBean<Exercice, String> {

    @EJB
    private ExerciceServiceBeanRemote service;
    @EJB
    private EquipeExerciceServiceBeanRemote equipeExerciceService;
    @EJB
    private EquipeServiceBeanRemote equipeService;
    @EJB
    private TestServiceBeanRemote testService;

    @ManagedProperty(value = "#{testBean}")
    private TestBean testBean;
    @ManagedProperty(value = "#{classementBean}")
    private ClassementBean classementBean;

    private EquipeExercice formObject;
    private MarathonEquipe marathonEquipe;
    private Test test;
    private List<EquipeExercice> dataList;
    CompilateurImpl ci = new CompilateurImpl();

    private Integer activeIndex;
    private Boolean renderPoll;
    private Date remaining;
    private boolean disableSave;
    private boolean disableEdit;
    private UploadedFile uploadedFile;
    public String result;

    @PostConstruct
    @Override
    public void init() {
        super.init();
        this.entity = new Exercice();
        this.renderPoll = true;
        this.activeIndex = 0;
        this.result = null;
        this.disableSave = false;
        this.setRenderPoll((Boolean) true);
        try {
            setMarathonEquipe(getTestBean().getMarathonEquipe());
            System.out.println("MARATHON-EQUIPE : " + getMarathonEquipe().getMarathon().getLibelle() + " -->" + 
                    getMarathonEquipe().getEquipe().getNom());
            setTest(getTestBean().getTest());
            System.out.println("TEST : " + getTestBean().getTest().getLibelle());
            // Si l'équipe connectée n'a pas encore de List<EquipeExercice> on instancie
            getMarathonEquipe().setEquipe(getEquipeExerciceService().createEquipeExercicesIfNot(
                    getMarathonEquipe().getEquipe(), getTest()));
            
            setDataList(getEquipeExerciceService().findByEquipeTest(
                    getMarathonEquipe().getEquipe(),
                    getTest()
            ));
            this.calculateRemainingTime(true); // Car certains controleur en ont besoin quand ils injectent celui-ci
            //setRemaining(getTestService().calculateRemainingTime(getTest()));
        } catch (Exception ex) {
            Logger.getLogger(ExerciceBean.class.getName()).log(Level.SEVERE, null, ex);
            MarathonUtils.getValueFromBundle("MSG_ERROR_ADD", MarathonConstants.BUNDLE_NAME);
        }
    }

    @Override
    public GenericServiceBeanRemote<Exercice, String> getService() {
        return service;
    }

    /**
     * Permet de sauvegarder l'exercice sur lequel l'équipe traite
     *
     * @param ee
     */
    public void changerExercice(EquipeExercice ee) {
        setFormObject(ee);
    }

    /**
     * Permet de vérifier et de prendre en compte une solution pour un exercice
     * et d'ajouter un joker à l'équipe s'il le faut
     *
     * @param ee
     */
    public void validateExercice(EquipeExercice ee) {
        changerExercice(ee);
        try {
            getTestBean().setMarathonEquipe(
                    getEquipeExerciceService().validateExercice(getFormObject(), getMarathonEquipe())
            );
        } catch (Exception ex) {
            Logger.getLogger(ExerciceBean.class.getName()).log(Level.SEVERE, null, ex);
            MarathonUtils.getValueFromBundle("MSG_ERROR_EXERCISE_VALIDATION",
                    MarathonConstants.BUNDLE_NAME);
        }
    }

    /**
     * Permet d'activer ou d'éviter l'activation d'un joker pour un exercice
     *
     * @param activer
     */
    public void activateJoker(Boolean activer) {
        getFormObject().setJockerActif(activer);
        if (!activer) { // On veut éviter une activation
            return;
        }
        try {
            getTestBean().setMarathonEquipe(getEquipeExerciceService()
                    .activateJoker(getFormObject(), getMarathonEquipe())
            );
        } catch (InsufficientJokersNumberException ex) {
            MarathonUtils.getValueFromBundle("MSG_INSUFFICIENT_JOKERS_NUMBER",
                    MarathonConstants.BUNDLE_NAME);
        } catch (Exception ex) {
            Logger.getLogger(ExerciceBean.class.getName()).log(Level.SEVERE, null, ex);
            MarathonUtils.getValueFromBundle("MSG_ERROR_JOKER_ACTIVATION",
                    MarathonConstants.BUNDLE_NAME);
        }
    }

    /**
     * Permet de savoir si la solution envoyée est correcte
     *
     * @param ee
     * @return
     */
    public Boolean isSolutionCorrect(EquipeExercice ee) {

        try {
            if (CompilateurImpl.result != null && !CompilateurImpl.result.isEmpty()) {
                return this.equipeExerciceService.isSolutionCorrect(ee);
            } else {
                return (Boolean) false;
            }
        } catch (Exception ex) {
            Logger.getLogger(ExerciceBean.class.getName()).log(Level.SEVERE, null, ex);
            MarathonUtils.getValueFromBundle("MSG_ERROR_DB_SEARCH",
                    MarathonConstants.BUNDLE_NAME);
        }
        //this.result = this.equipeExerciceService.compilerService();
        return (Boolean) false;
    }

    public Boolean isSolutionCorrect2(EquipeExercice ee) {
        return (ee.isResolu());
    }

    /**
     * Permet de désactiver un exercice si il est résolu ou si le temps est
     * écoulé
     *
     * @param ee
     * @return
     */
    public Boolean isExerciceDisabled(EquipeExercice ee) {
        return (this.isSolutionCorrect(ee) || (getRemaining().getTime() > getTest().getHeureFin().getTime()) || 
                this.isSolutionCorrect2(ee));
    }

    /**
     * Permet de connaître le nombre de jokers à gagner en résolvant l'exercice
     *
     * @param exercice
     * @return
     */
    public Integer findNbJokersToWin(Exercice exercice) {
        try {
            return this.service.findNbJokersToWin(exercice);

        } catch (Exception ex) {
            Logger.getLogger(TestBean.class
                    .getName()).log(Level.SEVERE, null, ex);
            MarathonUtils.getValueFromBundle("MSG_ERROR_DB_SEARCH",
                    MarathonConstants.BUNDLE_NAME);
        }
        return null;
    }

    /**
     * Permet de calculer le pourcentage du score de l'équipe pour le test en
     * cours
     *
     * @return
     */
    public Double calculateScorePercent() {
        try {
            return getEquipeService().calculateEquipeScorePercent(getMarathonEquipe().getEquipe(),
                    getTestBean().getTest());

        } catch (Exception ex) {
            Logger.getLogger(TestBean.class
                    .getName()).log(Level.SEVERE, null, ex);
            MarathonUtils.getValueFromBundle("MSG_ERROR_DB_SEARCH",
                    MarathonConstants.BUNDLE_NAME);
        }
        return null;
    }

    /**
     * Permet de calculer le temps restant pour le test en cours
     *
     * @param callFromInit
     */
    public void calculateRemainingTime(boolean callFromInit) {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        try {
            setRemaining(getTestService().calculateRemainingTime(getTest()));
            if ((getRemaining().getTime() > getTest().getHeureFin().getTime()) && getRenderPoll()) { // Le temps est écoulé
                setRenderPoll((Boolean) false);
                if (!callFromInit) {
                    //Rediriger vers la page de classement si le temps est écoulé
                    externalContext.redirect(externalContext.getApplicationContextPath() + "/marathon/pages/test/"
                            + "classement.xhtml");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(TestBean.class
                    .getName()).log(Level.SEVERE, null, ex);
            MarathonUtils.getValueFromBundle("MSG_ERROR_DB_SEARCH",
                    MarathonConstants.BUNDLE_NAME);
        }
    }

    @Override
    public String delete(Exercice e) {
        try {
            this.service.deleteOne(e);
            Messages.addFlashGlobalInfo("Suppression effectué avec succés !!!");
        } catch (BusinessException be) {
            Messages.addFlashGlobalError("Erreur lors du processus !!!");
            System.out.println(be.getMessage());
        }
        return null;
    }

    @Override
    public String update() {
        try {
            this.service.update(this.entity);
            Messages.addFlashGlobalInfo("Modification effectué avec succés !!!");
            this.init();
        } catch (Exception be) {
            Messages.addFlashGlobalError("Erreur lors du processus !!!");
            System.out.println(be.getMessage());
        }
        return null;
    }

    @Override
    public String add() {
//        FacesContext context = FacesContext.getCurrentInstance();
        try {
            this.service.addOne(this.entity);
            this.init();
            Messages.addFlashGlobalInfo("Ajout effectué avec succés !!!");
        } catch (Exception be) {
            Messages.addFlashGlobalError("Erreur lors du processus !!!");
            System.out.println(be.getMessage());
            this.init();
        }
        return null;
    }

    public void handleUploadFile(EquipeExercice ee) {
        System.out.println("Le chemin de l'équipe : " + ee.getEquipe().getChemin());
        System.out.println("Nom fichier upload : " + uploadedFile.getFileName());
        try {
            String newfileName = ee.getEquipe().getChemin();
            File dir = new File(newfileName);
            boolean isCreated = dir.mkdirs();
            //if (isCreated) {
            InputStream inputStream;
            try (FileOutputStream fileOutputStream = new FileOutputStream(dir + File.separator + uploadedFile.getFileName())) {
                byte[] buffer = new byte[8192];
                int bulk;
                inputStream = getUploadedFile().getInputstream();
                while (true) {
                    bulk = inputStream.read(buffer);
                    if (bulk < 0) {
                        break;
                    }
                    fileOutputStream.write(buffer, 0, bulk);
                    fileOutputStream.flush();
                }
                //}
                inputStream.close();
            }
            CompilateurImpl.result = this.ci.getCompilerResultService(ee.getEquipe().getChemin(), uploadedFile.getFileName(),
                    ee.getNomLangage());
            System.out.println("RESULTAT: " + CompilateurImpl.result);
            this.validateExercice(ee);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de l'écriture du fichier.");
        }
    }

    public void testLogUpload() {
        System.out.println("INSIDE TEST LOG");
    }

    @Override
    public boolean canAdd() {
        return true;
    }

    @Override
    public boolean canUpdate() {
        return true;
    }

    @Override
    public boolean canDelete() {
        return true;
    }

    @Override
    public void initAdd() {
        this.entity = new Exercice();
    }

    @Override
    public void setEntity(Exercice entity) {
        super.setEntity(entity);
        this.disableEdit = false;
        this.disableSave = true;
    }

    @Override
    public LazyDataModel<Exercice> getModel() {
        return super.getModel();
    }

    public boolean isDisableSave() {
        return disableSave;
    }

    public void setDisableSave(boolean disableSave) {
        this.disableSave = disableSave;
    }

    public boolean isDisableEdit() {
        return disableEdit;
    }

    public void setDisableEdit(boolean disableEdit) {
        this.disableEdit = disableEdit;
    }

    public EquipeExerciceServiceBeanRemote getEquipeExerciceService() {
        return equipeExerciceService;
    }

    public EquipeServiceBeanRemote getEquipeService() {
        return equipeService;
    }

    public TestServiceBeanRemote getTestService() {
        return testService;
    }

    public TestBean getTestBean() {
        return testBean;
    }

    public void setTestBean(TestBean testBean) {
        this.testBean = testBean;
    }

    public ClassementBean getClassementBean() {
        return classementBean;
    }

    public void setClassementBean(ClassementBean classementBean) {
        this.classementBean = classementBean;
    }

    public EquipeExercice getFormObject() {
        return formObject;
    }

    public void setFormObject(EquipeExercice formObject) {
        this.formObject = formObject;
    }

    public MarathonEquipe getMarathonEquipe() {
        return marathonEquipe;
    }

    public void setMarathonEquipe(MarathonEquipe marathonEquipe) {
        this.marathonEquipe = marathonEquipe;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public List<EquipeExercice> getDataList() {
        return dataList;
    }

    public void setDataList(List<EquipeExercice> dataList) {
        this.dataList = dataList;
    }

    public Integer getActiveIndex() {
        return activeIndex;
    }

    public void setActiveIndex(Integer activeIndex) {
        this.activeIndex = activeIndex;
    }

    public Boolean getRenderPoll() {
        return renderPoll;
    }

    public void setRenderPoll(Boolean renderPoll) {
        this.renderPoll = renderPoll;
    }

    public Date getRemaining() {
        return remaining;
    }

    public void setRemaining(Date remaining) {
        this.remaining = remaining;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public CompilateurImpl getCi() {
        return ci;
    }

    public void setCi(CompilateurImpl ci) {
        this.ci = ci;
    }

}
