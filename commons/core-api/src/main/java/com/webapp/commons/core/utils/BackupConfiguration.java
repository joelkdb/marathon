/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.utils;

import java.io.Serializable;

/**
 *
 * @author gedCommit
 */
public class BackupConfiguration implements Serializable {
    
    private String backupName;
    private String backupFolder;
    private String format;
    private String filePath;
    private String compressionType;
    private String encryption;
    private String encoding;
    private String persistenceName;

    public BackupConfiguration() {
    //this.backupFolder="I://";
    }

    public BackupConfiguration(String backupFolder, String backupName,String encoding, String filePath, String format) {
        this.backupFolder =backupFolder ;
        this.format = format;
       
    }

    public String getBackupFolder() {
        return backupFolder;
    }

    public void setBackupFolder(String backupFolder) {
        this.backupFolder = backupFolder;
    }

    public String getBackupName() {
        return backupName;
    }

    public void setBackupName(String backupName) {
        this.backupName = backupName;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getCompressionType() {
        return compressionType;
    }

    public void setCompressionType(String compressionType) {
        this.compressionType = compressionType;
    }

    public String getEncryption() {
        return encryption;
    }

    public void setEncryption(String encryption) {
        this.encryption = encryption;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getPersistenceName() {
        return persistenceName;
    }

    public void setPersistenceName(String persistenceName) {
        this.persistenceName = persistenceName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    
    

}
