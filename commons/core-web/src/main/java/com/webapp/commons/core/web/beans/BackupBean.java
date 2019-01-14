/*
 * * Ms-Backup est une libraire de sauvegarde et de restauration de base de données.
 réaliser par AGOTSI Komi Gédéonm Etudiant en deuxième année a l'IAI-TOGO.
 */
package com.webapp.commons.core.web.beans;

import com.webapp.commons.core.exception.BusinessException;
import com.webapp.commons.core.service.BackupServiceBeanRemote;
import com.webapp.commons.core.utils.BackupConfiguration;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author gedCommit
 */
@ManagedBean
@SessionScoped
public class BackupBean implements Serializable {

    @EJB
    private BackupServiceBeanRemote backupService;

    private BackupConfiguration configuration;
    private UploadedFile file;
    private long end;
    private String extension;
    private boolean test;
    private String backupfoldertemp;
    private boolean disablecancelbtn;
    private boolean renderedrestorepanel;
    private List<String> listFile;
    private String encoding;

    public List<String> getListFile() {
        return listFile;
    }

    public void setListFile(List<String> listFile) {
        this.listFile = listFile;
    }

    public boolean isRenderedrestorepanel() {
        return renderedrestorepanel; 
    }

    public void setRenderedrestorepanel(boolean renderedrestorepanel) {
        this.renderedrestorepanel = renderedrestorepanel;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
        configuration.setFilePath(file.getFileName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isTest() {
        return test;
    }

    public void setTest(boolean test) {
        this.test = test;
    }

    public BackupServiceBeanRemote getBackupService() {
        return backupService;
    }

    public void setBackupService(BackupServiceBeanRemote backupService) {
        this.backupService = backupService;
    }

    public boolean isDisablecancelbtn() {
        return disablecancelbtn;
    }

    public void setDisablecancelbtn(boolean disablecancelbtn) {
        this.disablecancelbtn = disablecancelbtn;
    }

    private String name;

    /**
     * Creates a new instance of BackupBean
     */
    public BackupBean() {
        this.configuration = new BackupConfiguration();
        init();
        this.disablecancelbtn = true;
        this.extension = "Aucun fichier selectionné";
        this.name = "Aucun fichier selectionné";

    }

    public void listenerRender() {
        this.renderedrestorepanel = true;

    }

    public BackupConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(BackupConfiguration configuration) {
        this.configuration = configuration;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getBackupfoldertemp() {
        return backupfoldertemp;
    }

    public void setBackupfoldertemp(String backupfoldertemp) {
        this.backupfoldertemp = backupfoldertemp;
    }

    public void init() {

        Path monRepertoire = Paths.get("C:" + File.separator + "msBackup");
        try {

            Path file = Files.createDirectory(monRepertoire);
        } catch (FileAlreadyExistsException ex) {
            System.out.println("Le fichier de sauvegarde renseigné existe déja Veuillez renseigner un autre");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        configuration.setBackupFolder("C:" + File.separator + "msBackup" + File.separator);

    }

    public void executeBackup() {

        try {
            long start = System.currentTimeMillis();
            System.out.println(start);
            this.backupService.backup(configuration);
            end = System.currentTimeMillis() - start;
            System.out.println(end);

            System.out.println(end);

            System.out.println(configuration.getFormat());
            System.out.println(configuration.getEncoding());

            String messages = "Le fichier de sauvegarde: " + configuration.getBackupName() + "  a été crée avec succès au format : " + this.configuration.getFormat() + "; durée d'execution:  " + end + " ms";

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", messages));
            configuration.setBackupName("");
            reset();
        } catch (BusinessException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Une erreur est survenue dans la creation du fichier de sauvegarde; cause :" + ex.getMessage()));
            reset();

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Une erreur est survenue dans la creation du fichier de sauvegarde; cause : la base de donnée n'est pas démarrée"));
            reset();
        }

    }

    public void executeRestore() {
        try {
            long start = System.currentTimeMillis();
            this.backupService.restore(configuration);

            end = System.currentTimeMillis() - start;
            String messages = "La base de donnée a éte restauré avec succès; durée d'execution :  " + end + " ms";
            configuration.setPersistenceName(null);
            this.name = null;
            this.extension = null;

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Fichier : " + messages));

        } catch (BusinessException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Une erreur est survenue aucours de la restauration de la base de donnée; cause :", ex.getMessage()));
            configuration.setPersistenceName(null);
            this.name = null;
            this.extension = null;
        }
    }

    public void modifyBackupFolder() throws IOException {
        this.configuration.setBackupFolder(this.backupfoldertemp + File.separator);//ici//
        Path monRepertoire = Paths.get(this.backupfoldertemp);
        try {
            System.out.println(this.backupfoldertemp);
            Path file = Files.createDirectory(monRepertoire);
            this.backupfoldertemp = "";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Le dossier " + this.backupfoldertemp + " a été crée avec succes"));
        } catch (FileAlreadyExistsException ex) {
            this.backupfoldertemp = "";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Le dossier  existe déja; veuillez créer un autre"));

        }
    }

    public void handleFileUpload(FileUploadEvent event) throws IOException {

         // the name of the character encoding returned
        String name = event.getFile().getFileName();
        this.name = name.substring(0, name.indexOf("."));

        Path tempFile = Files.createTempFile(name, ".tmp");
        File temprealFile = tempFile.toFile();
        this.setTest(true);

        try {
            //Path file = Files.createFile(monFichier);

            OutputStream out;
            try (InputStream inputStream = event.getFile().getInputstream()) {
                out = new FileOutputStream(temprealFile);
                int read = 0;
                byte[] bytes = new byte[1024];
                while ((read = inputStream.read(bytes)) != -1) {
                    out.write(bytes, 0, read);

                }
            }
            out.flush();
            out.close();
            System.out.println(temprealFile.toPath());
            configuration.setFilePath(temprealFile.getPath());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        FileInputStream fis = null;

        System.out.println(name);
//        if (".test".equals(name.substring(name.lastIndexOf('.'), name.length()))) {
//this.test=true;
//            System.out.println("vrai");
//            
        if (".xml".equals(name.substring(name.indexOf('.'), name.length()))) {
            extension = "XML";

        } else if (".json".equals(name.substring(name.indexOf('.'), name.length()))) {
            extension = "JSON";

        }

        System.out.println(extension);
    }

   // pubic void activeBtn(keyup)
    public void cancel() {

        reset();

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La sauvegarde a été annulé"));

    }

    public void reset() {
        configuration.setBackupName(null);
        configuration.setPersistenceName(null);
        configuration.setEncoding("aucune");
        configuration.setCompressionType("aucune");
        configuration.setEncryption("aucune");

    }

}
