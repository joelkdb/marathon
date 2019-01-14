/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.commons.core.service;

import com.webapp.commons.core.exception.BusinessException;
import com.webapp.commons.core.utils.BackupConfiguration;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.persistence.PersistenceException;


/**
 *
 * @author gedCommit
 */
@Stateless
public class BackupServiceBean implements BackupServiceBeanRemote {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void backup(BackupConfiguration configuration) {

        SimpleDateFormat formDate = new SimpleDateFormat("dd-MM-yyyy");
        String strDate = formDate.format(new Date());
        SimpleDateFormat sdfTime = new SimpleDateFormat("HH-mm-ss");
        String strTime = sdfTime.format(new Date());
        String path = configuration.getBackupFolder() + configuration.getBackupName() + "_" + strDate + "_" + strTime + "." + configuration.getFormat();        
        if (("xml".equals(configuration.getFormat()) && ("aucune".equals(configuration.getCompressionType())))) {
            temptoFile(objecttoXml(configuration), path);
        }
        else if (("xml".equals(configuration.getFormat()) && (!"aucune".equals(configuration.getCompressionType())))) {
            temptoFile(objecttoXml(configuration), path);
            try {
                String zipfile = path + ".zip";
                compress(zipfile, path);
                Path filePath = Paths.get(path);
                if (!filePath.toFile().delete()) {
                    filePath.toFile().delete();
                }

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        } 
    }


    private void temptoFile(File temp, String filePath) {
        OutputStream out;
        try (InputStream inputStream = new FileInputStream(temp)) {
            out = new FileOutputStream(new File(filePath));
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getCause());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            

        }
         if (temp.delete()) {
                        temp.delete();
                    }

    }

   
  
  
    @Override
    public void restore(BackupConfiguration configuration) {
        //System.out.println("ici");
        if (configuration.getFilePath().indexOf(".xml") != -1) {
             System.out.println(configuration.getFilePath()+"iciiii");
            xmltoObject(configuration);
        }
        else {
        throw new BusinessException("Veuillez choisir un fichier d'extension xml");
        }
        
    }


    
 private File decompress(String archiveName) throws IOException {
        Path tempFile = Files.createTempFile("tempFile", ".tmp");
        File tempRealFile = tempFile.toFile();
        ZipFile zipFile = new ZipFile(archiveName);
        Enumeration entries = zipFile.entries();
        ZipEntry entry = null;

        while (entries.hasMoreElements()) {
            entry = (ZipEntry) entries.nextElement();
            if (!entry.isDirectory()) {
                System.out.println("Extraction du fichier " + entry.getName());

            }
        }
        int i = 0;
        byte[] bytes = new byte[1024];
        BufferedOutputStream out = new BufferedOutputStream(
                new FileOutputStream(tempRealFile));
        BufferedInputStream in = new BufferedInputStream(zipFile
                .getInputStream(entry));
        while ((i = in.read(bytes)) != -1) {
            out.write(
                    bytes,
                    0,
                    i);
        }
        in.close();
        out.flush();
        out.close();

        return tempRealFile;
    }
    

    private void xmltoObject(BackupConfiguration configuration) {

        Properties properties = new Properties();
        properties.setProperty("javax.persistence.schema-generation.database.action", "create");//       
        Persistence.generateSchema(configuration.getPersistenceName(), properties);

        try {
        } catch (PersistenceException ex) {
            throw new BusinessException("Veuillez saisir un nom d'unité de persistence valide, conforme à celui du projet", ex);
        }
        System.out.println(configuration.getFilePath());
        try {
            XMLStreamReader xmlsrs = creerFabriqueReader(configuration.getFilePath());
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getCause());
            throw new BusinessException("Fichier de sauvegarder non trouvé");
        } catch (XMLStreamException ex) {
            System.out.println(ex.getCause());
        }
        try {
            System.out.println(allClass().toString()+"info  les classes");
            for (Class entityClass : allClass()) {
                List<Object> objectList = new ArrayList<>();
                JAXBContext context = JAXBContext.newInstance(entityClass);
                objectList = entityListfromxml(context, entityClass,
                        configuration.getFilePath());

                for (Object listElement : objectList) {
                    try {
                        this.em.persist(listElement);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }

        } catch (JAXBException ex) {
            System.out.println(ex.getCause());
            throw new BusinessException("Une erreur est survenue dans la création du fichier de sauvegarde", ex);
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getCause());
            throw new BusinessException("Une erreur est survenue dans la création du fichier de sauvegarde", ex);
        } catch (XMLStreamException ex) {
            throw new BusinessException("Une erreur est survenue dans la lecture du fichier");
        }

    }

  
   private List<Class> allClass() {
        List<Class> liste = new ArrayList<>();
        try {
            Metamodel model = em.getEntityManagerFactory().getMetamodel();

            for (EntityType entityType : model.getEntities()) {
                liste.add(entityType.getBindableJavaType());
            }
        } catch (Exception e) {
            throw new  BusinessException("La base de donnée n'est pas demarré ou les tables sont inexistantes dans la base de données");
        }
        return liste;
        
    }

    public List<Object> allObjectFromClass(Class entityName) {
        try {
            return em.createQuery("select p from " + entityName.getName() + " p").getResultList();
        } catch (Exception ex) {
            throw new BusinessException(" Tables sont inexistantes dans la base de données");
        }

    }

    private XMLStreamReader creerFabriqueReader(String fileName) throws
            FileNotFoundException, XMLStreamException {
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
        XMLStreamReader xmlsr = xmlif.createXMLStreamReader(new FileReader(fileName));

        return xmlsr;
    }
    private List<Object> entityListfromxml(JAXBContext context, Class entityClass, String filename) throws FileNotFoundException, XMLStreamException, JAXBException {
        try {
            List<Object> data = new ArrayList<>();
            XMLStreamReader xmlsrs = creerFabriqueReader(filename);
            Object item;
            while (xmlsrs.hasNext()) {
                int eventType = xmlsrs.next();
                switch (eventType) {
                    case XMLStreamConstants.START_ELEMENT:

                        if (xmlsrs.getLocalName().equals(entityClass.getSimpleName().toLowerCase())) {
                            JAXBElement<?> unmarshalledObj = context.createUnmarshaller().unmarshal(xmlsrs, entityClass);
                            item = unmarshalledObj.getValue();
                            System.out.println(item);
                            data.add(item);
                        }
                        break;
                    case XMLStreamConstants.END_DOCUMENT:
                        xmlsrs.close();
                        break;
                }
            }
            return data;
        } catch (Exception ex) {
            throw new BusinessException("Le document ne contient aucune information concernant une base de donnée", ex);
        }
    }


    private void compress(String archiveName, String fileName) throws IOException {
        try {
            ZipOutputStream zip = new ZipOutputStream(
                    new FileOutputStream(archiveName));
            zip.setMethod(ZipOutputStream.DEFLATED);
            zip.setLevel(Deflater.BEST_COMPRESSION);
            // lecture du fichier
            File fichier = new File(fileName);
            FileInputStream fis = new FileInputStream(fichier);
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);
            // ajout d'une nouvelle entrée dans l'archive contenant le fichier
            System.out.println(fileName);
            String newEntry = fileName.substring(fileName.lastIndexOf(File.separator),fileName.length());
            
            String lastEntry = newEntry.substring(1, newEntry.length());
            ZipEntry entry = new ZipEntry(lastEntry);
            entry.setTime(fichier.lastModified());

            zip.putNextEntry(entry);
            zip.write(bytes);
            // fermeture des flux
            zip.closeEntry();
            fis.close();
            zip.close();
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    

   


    private File objecttoXml(BackupConfiguration configuration) {
        File tempRealFile = null;
        try {
            Path tempFile = Files.createTempFile("tempXmlFile", ".tmp");
            tempRealFile = tempFile.toFile();

            OutputStream os = null;
            try {
                os = new FileOutputStream(tempRealFile);
            } catch (FileNotFoundException ex) {

                System.out.println(ex.getCause());
            }

            Writer writ = new OutputStreamWriter(os);
            XMLStreamWriter writer = null;
            try {
                writer = XMLOutputFactory.newInstance().createXMLStreamWriter(writ);
            } catch (XMLStreamException ex) {
                //throw new LibraryException(ex.getCause());
                System.out.println(ex.getCause());
            }

            try {
                System.out.println(configuration.getEncoding());
                writer.writeStartDocument(configuration.getEncoding(), "1.0");
            } catch (XMLStreamException ex) {
                System.out.println(ex.getStackTrace());
            }
            writer.writeComment("ms-backup version 1.0 \n -Generate at " + new Date().toGMTString() + "\n - Mediasoft library for backup and restore database \n");
           
            writer.writeStartElement("database");
            writer.writeCharacters("\n");

            try {
                for (Class entityClass : allClass()) {
                    JAXBContext context = JAXBContext.newInstance(entityClass);
                    Marshaller marshal = context.createMarshaller();
                    marshal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                    System.out.println(entityClass);
                    List<Object> listeproduits = new ArrayList<>();
                    try {
                        
                        listeproduits.addAll(allObjectFromClass(entityClass));

                        System.out.println(allObjectFromClass(entityClass));
                        writer.writeComment("Table " + entityClass.getSimpleName());
                    } catch (Exception ex) {

                        System.out.println(ex.getCause());
                    }

                    for (Object object : listeproduits) {

                        marshal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                        marshal.setProperty(Marshaller.JAXB_FRAGMENT, true);
                        marshal.marshal(object, writer);
                    }
                }

                writer.writeEndElement();
                writer.writeEndDocument();
                writer.flush();
                writer.close();

            } catch (JAXBException ex) {
                try {
                    writer.close();
                } catch (XMLStreamException ex1) {
                    Logger.getLogger(BackupServiceBean.class.getName()).log(Level.SEVERE, null, ex1);
                }
                System.out.println(ex.getStackTrace());
            } catch (XMLStreamException ex) {
                System.out.println(ex.getStackTrace());
            }
        } catch (IOException ex) {
            Logger.getLogger(BackupServiceBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XMLStreamException ex) {
            System.out.println(ex.getCause());

        } catch (Exception ex) {
            throw new  BusinessException("une erreur est survenue dans la création du fichier de sauvegarde ");
        }

        return tempRealFile;
    }
}
