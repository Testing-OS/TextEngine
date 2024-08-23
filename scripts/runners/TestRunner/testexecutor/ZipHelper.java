package runners.TestRunner.testexecutor;

import scripts_techniques.Config;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipHelper {

    private static final int BUFFER_SIZE = 4096;

    private List<String> fileList;

    public static void unzip(byte[] data, String dirName) throws IOException {
        File destDir = new File(dirName+"/scripts_metiers");

        try {

                // Exécuter l'action
                if (!destDir.exists()) {
                    System.out.println("Create folder:" + dirName);
                    destDir.mkdir();
                }
                File scriptsMetiersDir = new File(dirName + "/scripts_metiers");
                if (scriptsMetiersDir.exists()) {
                    System.out.println("Delete folder metiers:" + scriptsMetiersDir);
                    String[] entries = scriptsMetiersDir.list();
                    for (String s : entries) {
                        File currentFile = new File(scriptsMetiersDir.getPath(), s);
                        currentFile.delete();
                    }

                    //scriptsMetiersDir.delete();
                }
                TimeUnit.SECONDS.sleep(5);

                try (FileOutputStream fos = new FileOutputStream(dirName + "/export.zip")) {
                    fos.write(data); // Écrire les données du tableau d'octets dans le fichier
                } catch (IOException e) {
                    e.printStackTrace();
                }

                ZipInputStream zipIn = new ZipInputStream(new ByteArrayInputStream(data));
                ZipEntry entry;
                while ((entry = zipIn.getNextEntry()) != null) {
                    String entryName = entry.getName();
                    if(!entryName.equals("scripts_techniques/Teststep.java")) {
                    File entryFile = new File(dirName, entryName);
                    
                    if (entry.isDirectory()) {
                        entryFile.mkdirs();
                    } else {
                        if (entryFile.exists()) {
                            entryFile.delete();
                        }
                        try {
                        Files.copy(zipIn, entryFile.toPath());
                        }catch(Exception e) {
                        	e.printStackTrace();
                        }
                    }
                   }
                    zipIn.closeEntry();
                }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

    private static void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        byte[] bytesIn = new byte[BUFFER_SIZE];
        int read = 0;
        while ((read = zipIn.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
        }
        bos.close();
    }
    public static List<File> getXLastModifiedFile(String directoryFilePath, int X) {
        List<File> selectedFiles = new ArrayList<>();
        try {
            File directory = new File(directoryFilePath);
            File[] files = directory.listFiles(File::isFile);

            if (files != null) {
                Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());
                for (int i = 0; i < Math.min(X, files.length); i++) {
                    selectedFiles.add(files[i]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("fichier selectionné" + selectedFiles);
        return selectedFiles;
    }
    public static List<File> getXLastModifiedDirectory(String directoryFilePath, int X) {
        List<File> selectedFiles = new ArrayList<>();
        try {
            File directory = new File(directoryFilePath);
            File[] files = directory.listFiles(File::isDirectory);

            if (files != null) {
                Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());
                for (int i = 0; i < Math.min(X, files.length); i++) {
                    selectedFiles.add(files[i]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("fichier selectionné" + selectedFiles);
        return selectedFiles;
    }
    public static void archiveResultFolders(String projectFolder, int X){
        String applicationFolder = projectFolder.replace("scripts", "");

        try{
            List<File> FilesToZip = getXLastModifiedDirectory(Config.tunnel_path + "/results/planTests/", X);
            System.out.println("FILES TO ZIP : " + FilesToZip);
            for(File file : FilesToZip){

                try (FileOutputStream fos = new FileOutputStream(applicationFolder + "results/"+file.getName()+".zip");
                     ZipOutputStream zipOut = new ZipOutputStream(fos)) {
                    File fileToZip = new File(file.toString());
                    ZipHelper.zipFile(fileToZip, fileToZip.getName(), zipOut);
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }

   public static void archiveResultFolder(String projectFolder) {
        try{
            String applicationFolder = projectFolder.replace("scripts", "");
            String resultsApplicationFolder = applicationFolder + "results/planTests/";

            File folderToArchive = findLatestDirectory(resultsApplicationFolder);
           // try (FileOutputStream fos = new FileOutputStream(applicationFolder + "/"+folderToArchive.getName()+".zip");
            try (FileOutputStream fos = new FileOutputStream(applicationFolder + "results/"+folderToArchive.getName()+".zip");
                 ZipOutputStream zipOut = new ZipOutputStream(fos)) {
                 File fileToZip = new File(folderToArchive.toString());
                 ZipHelper.zipFile(fileToZip, fileToZip.getName(), zipOut);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to archive", e);
        }
    }
public static File findLatestDirectory(String path) {
    File dir = new File(path);
    File[] files = dir.listFiles();
    File lastModified = Arrays.stream(files).filter(File::isDirectory).max(Comparator.comparing(File::lastModified)).orElse(null);
    return lastModified;
}
    private static void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
        if (fileToZip.isHidden()) {
            return;
        }
        if (fileToZip.isDirectory()) {
           // LOG.info("IS directory:");
            if (fileName.endsWith("/")) {
               // LOG.info("END with /:");
                zipOut.putNextEntry(new ZipEntry(fileName));
                zipOut.closeEntry();
            } else {
               // LOG.info("ADD /:");
                zipOut.putNextEntry(new ZipEntry(fileName + "/"));
                zipOut.closeEntry();
            }
            File[] children = fileToZip.listFiles();
            if (children != null) {
                for (File childFile : children) {
                   // LOG.info("ADD FILE::" + childFile.getName());
                    zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
                }
            } else {
                System.out.println("CHILD NULL:");
            }
            return;
        }
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipOut.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }
        fis.close();
    }
}