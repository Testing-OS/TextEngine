package runners.TestRunner.testexecutor;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.Comparator;

public class TestExecutorService {


    public void Download(String url, String projectFolder, String fileName, String executionType, String extension, boolean isLoadTesting) throws IOException, InterruptedException {

            byte[] archive = generate(url);
            try {
                ZipHelper.unzip(archive, projectFolder);
            } catch (IOException e) {
                throw new RuntimeException("Failed to unzip", e);
            }

    }
    public byte[] generate(String url) {
        HttpClient httpClient = HttpClients.createDefault();
        int essai = 0;
        try {

            HttpGet request = new HttpGet(url);
            HttpResponse response = httpClient.execute(request);


                while(!(response.getStatusLine().getStatusCode() == 200) && essai <3){
                System.out.println("Erreur lors de la récupération des données. Code de statut : " + response.getStatusLine().getStatusCode());
                response = httpClient.execute(request);
                essai++;
                }

            if (response.getStatusLine().getStatusCode() == 200)
                return EntityUtils.toByteArray(response.getEntity());
            else
                System.out.println("Recuperation des données KO");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void upload(String url, String pathToFile) {
        pathToFile = pathToFile.replace(" ", "_");
        uploadResult(url, pathToFile);
    }



    private static File getFile(String dirName, String errorMessage) {
        File executionDir = new File(dirName);
        if (!executionDir.exists()) {
            throw new RuntimeException(errorMessage);
        }
        return executionDir;
    }
    public void uploadResult(String url, String pathToFile) {
        HttpClient httpClient = HttpClients.createDefault();
        int essai = 0;
        try {
            HttpPost request = new HttpPost(url);

            // Construire l'entité multipart
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addPart("file", new FileBody(new File(pathToFile)));


            // Définir l'entité multipart dans la requête
            request.setEntity(builder.build());

            // Exécuter la requête
            HttpResponse response = httpClient.execute(request);

            while(!(response.getStatusLine().getStatusCode() == 200) && essai <3){
                System.out.println("Try "+(essai+1)+" Erreur lors de l'upload des données. Code de statut : " + response.getStatusLine().getStatusCode() + response.getStatusLine());
                response = httpClient.execute(request);
                essai++;
                if (essai >= 3) {
                    break; // Sortir de la boucle
                }
            }


            // Vérifier si la réponse est réussie (statut 200 OK)
            if (response.getStatusLine().getStatusCode() == 200) {
                System.out.println("Upload réussi");
            } else {
                // Gérer les erreurs ici, par exemple, afficher le code de statut
                System.out.println("Erreur lors de l'upload. Code de statut : " +
                        response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Gérer les exceptions ici
        }
    }

    public static void createTxtFileWithImageNames(String projectFolder) {

        String applicationFolder = projectFolder.replace("scripts", "");
        String resultsApplicationFolder = applicationFolder + "/results/planTests/";
        File folder = ZipHelper.findLatestDirectory(resultsApplicationFolder);
        if (!folder.isDirectory()) {
            System.err.println("Provided path is not a directory.");
            return;
        }

        // Get a list of all directories in the given folder
        File[] subfolders = folder.listFiles(File::isDirectory);

        // Process each subfolder
        for (File subfolder : subfolders) {
            try {
                // List all .jpg files in the subfolder
                File[] imageFiles = subfolder.listFiles((dir, name) -> name.toLowerCase().endsWith(".jpg"));
                Arrays.sort(imageFiles, (f1, f2) -> Long.compare(getCreationTimeInMillis(f1), getCreationTimeInMillis(f2)));

                // Create a text file to store the image names
                File txtFile = new File(subfolder.getPath() + File.separator + "image_names.txt");
                FileWriter writer = new FileWriter(txtFile);

                // Write image names to the text file
                for (File imageFile : imageFiles) {
                    writer.write("file '{replacePath}/"+imageFile.getName()+"'" + System.lineSeparator());
                }

                // Close the writer
                writer.close();
                //System.out.println("Text file created for folder: " + subfolder.getName());
            } catch (IOException e) {
                System.err.println("Error occurred while creating text file for folder: " + subfolder.getName());
                e.printStackTrace();
            }
        }
    }

    private static long getCreationTimeInMillis(File file) {
        try {
            BasicFileAttributes attrs = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
            return attrs.creationTime().toMillis();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

}
