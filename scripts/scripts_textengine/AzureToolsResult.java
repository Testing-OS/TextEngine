package scripts_textengine;
import com.google.gson.JsonElement;
import okhttp3.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.Gson;
import scripts_techniques.Config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static scripts_textengine.AzureTools.getIdAzureByIdKalios;

public class AzureToolsResult {

    private static final String PAT = Config.PAT;
    private static final String ORGANIZATION = Config.ORGANIZATION;
    private static final String PROJECT = Config.PROJECT;
    private static final OkHttpClient client = new OkHttpClient();


    public static void main(String[] args) throws IOException {
        int testPlanId = 17;

        Map<Integer, String> testCases = new HashMap<>();

        testCases.put(1451, "Passed");
        testCases.put(1454, "Passed");
        //testCases.put(getTestPointId(testPlanId,12), "Passed");

        //createResult(testPlanId, testCases);


    }

    public static void createResult(int testPlanId,String testPlanName, ArrayList<TestCaseResult> testCases){
        try {
            int testRunId = createTestRun(testPlanId, testPlanName);

            for (TestCaseResult testCase : testCases) {
                int testPointIds = getTestPointId(testPlanId,getIdAzureByIdKalios(String.valueOf(testCase.getTestcase().getId())));
                String outcome = testCase.getOutcome();
                addOneTestResults(testRunId, testPointIds, outcome, testCase.getErrorMessage(), testCase.getComment());
            }
            completeTestRun(testRunId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void createResultIndepedant(int testPlanId,String testPlanName, ArrayList<TestCaseResult> testCases){
        try {
            int testRunId = createTestRun(testPlanId, testPlanName);

            for (TestCaseResult testCase : testCases) {
                int testPointIds = getTestPointId(testPlanId,testCase.getTestcase().getId());
                String outcome = testCase.getOutcome();
                addOneTestResults(testRunId, testPointIds, outcome, testCase.getErrorMessage(), "");
            }
            completeTestRun(testRunId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static int createTestRun(int testPlanId, String testPlanName) throws IOException {
        String url = "https://dev.azure.com/" + ORGANIZATION + "/" + PROJECT + "/_apis/test/runs?api-version=7.2-preview.3";

        // Corps de la requête JSON pour créer un test run
        String jsonBody = "{\"name\": \""+testPlanName+"\","
                + "\"plan\": {\"id\": \"" + testPlanId + "\"},"
                + "\"isAutomated\": false,"
                + "\"automated\": false,"
                + "\"state\": \"InProgress\"}";

        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonBody);

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .header("Authorization", Credentials.basic("", PAT))
                .header("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Erreur lors de la création du Test Run : " + response);
            String responseBody = response.body().string();
            System.out.println("Test Run créé");


            int testRunId = extractTestRunId(responseBody);
            return testRunId;
        }
    }

    // 2. Ajouter des résultats de test au Test Run
    public static void addOneTestResults(int testRunId, int testPointId, String outcome, String errorMessage, String comment) throws IOException {
        String url = "https://dev.azure.com/" + ORGANIZATION + "/" + PROJECT + "/_apis/test/runs/" + testRunId + "/results?api-version=7.2-preview.3";

        // Corps de la requête JSON pour ajouter un résultat de test
        String jsonBody = "[{\"testPoint\": {\"id\": " + testPointId + "},"
                + "\"outcome\": \"" + outcome + "\","
                + "\"errorMessage\": \"" + errorMessage + "\","
                + "\"comment\": \"" + comment + "\","
                + "\"state\": \"Completed\"}]";

        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonBody);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .header("Authorization", Credentials.basic("", PAT))
                .header("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful())
                throw new IOException("Erreur lors de l'ajout des résultats du Test Run : " + response);
           // System.out.println("Résultat de test ajouté : " + response.body().string());
        }
    }
    public static void addTwoTestResults(int testRunId, Map<Integer, String> testCases) throws IOException {
        String url = "https://dev.azure.com/" + ORGANIZATION + "/" + PROJECT + "/_apis/test/runs/" + testRunId + "/results?api-version=7.2-preview.3";

        // Construire le corps JSON pour les résultats de tests
        StringBuilder jsonBody = new StringBuilder("[");
        for (Map.Entry<Integer, String> testCase : testCases.entrySet()) {
            int testPointId = testCase.getKey();
            String outcome = testCase.getValue();

            // Ajouter chaque test case et son résultat
            jsonBody.append("{\"testPoint\": {\"id\": ")
                    .append(testPointId)
                    .append("}, \"outcome\": \"")
                    .append(outcome)
                    .append("\", \"state\": \"Completed\"},");
        }
        // Supprimer la dernière virgule et fermer le tableau JSON
        jsonBody.deleteCharAt(jsonBody.length() - 1).append("]");
        System.out.println(jsonBody);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonBody.toString());

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .header("Authorization", Credentials.basic("", PAT))
                .header("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Erreur lors de l'ajout des résultats du Test Run : " + response);
            System.out.println("Résultats des tests ajoutés : " + response.body().string());
        }
    }

    // Méthode pour extraire l'ID du Test Run de la réponse JSON (simplifiée)
    private static int extractTestRunId(String jsonResponse) {
        // Extraction simplifiée du Test Run ID à partir de la réponse JSON
        // Vous pouvez utiliser la bibliothèque Gson pour une extraction plus robuste
        String[] parts = jsonResponse.split("\"id\":");
        String idPart = parts[1].split(",")[0].trim();
        return Integer.parseInt(idPart);
    }
    public static void listTestCases(int testPlanId) throws IOException {
        OkHttpClient client = new OkHttpClient();

        String url = "https://dev.azure.com/" + ORGANIZATION + "/" + PROJECT +
                "/_apis/testplan/Plans/" + testPlanId + "/Suites/" + (testPlanId+1) + "/TestPoint?api-version=7.2-preview.2";

        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", Credentials.basic("", PAT))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Erreur lors de la récupération des cas de test : " + response);
            }

            String responseBody = response.body().string();
            System.out.println("Liste des cas de test : " + responseBody);
        }
    }
    public static void listTestPoints(int testPlanId, int testSuiteId) throws IOException {
        String url = "https://dev.azure.com/" + ORGANIZATION + "/" + PROJECT + "/_apis/test/plans/" + testPlanId + "/suites/" + testSuiteId + "/points?api-version=7.2-preview.2";

        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", Credentials.basic("", PAT))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Erreur lors de la récupération des points de test : " + response);
            }
            String responseBody = response.body().string();
            System.out.println("Liste des points de test : " + responseBody);
        }
    }
    public static void completeTestRun(int testRunId) throws IOException {
        String url = "https://dev.azure.com/" + ORGANIZATION + "/" + PROJECT + "/_apis/test/runs/" + testRunId + "?api-version=7.2-preview.3";

        // Corps de la requête JSON pour compléter le Test Run
        String jsonBody = "{\"state\": \"Completed\"}";

        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonBody);

        Request request = new Request.Builder()
                .url(url)
                .patch(body)
                .header("Authorization", Credentials.basic("", PAT))
                .header("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Erreur lors de la complétion du Test Run : " + response);
            System.out.println("Test Run complété");
        }
    }
    public static int getTestPointId(int testPlanId,int testCaseId) throws IOException {


        // Récupérer les suites dans le Test Plan
        int testSuiteId = getFirstTestSuiteId(testPlanId);

        // Récupérer le TestPointId pour le Test Case spécifique
        int testPointId = getTestPointId(testPlanId, testSuiteId, testCaseId);
        return testPointId;
    }
    public static int getFirstTestSuiteId(int testPlanId) throws IOException {
        String url = "https://dev.azure.com/" + ORGANIZATION + "/" + PROJECT + "/_apis/testplan/Plans/" + testPlanId + "/suites?api-version=7.2-preview.1";

        Request request = new Request.Builder()
                .url(url)
                .get()
                .header("Authorization", Credentials.basic("", PAT))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Erreur lors de la récupération des suites : " + response);
            String responseBody = response.body().string();

            // Extraire la première Test Suite ID (simplifié pour cet exemple)
            int testSuiteId = extractTestSuiteId(responseBody);
            return testSuiteId;
        }
    }

    // Récupérer le Test Point ID pour un Test Case spécifique
    public static int getTestPointId(int testPlanId, int testSuiteId, int testCaseId) throws IOException {
        String url = "https://dev.azure.com/" + ORGANIZATION + "/" + PROJECT + "/_apis/testplan/Plans/" + testPlanId + "/Suites/" + testSuiteId + "/TestPoint?api-version=7.2-preview.2";

        Request request = new Request.Builder()
                .url(url)
                .get()
                .header("Authorization", Credentials.basic("", PAT))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw     new IOException("Erreur lors de la récupération des points de test : " + response);
            String responseBody = response.body().string();

            // Extraire le Test Point ID correspondant au Test Case ID
            int testPointId = extractTestPointId(responseBody, testCaseId);
            return testPointId;
        }
    }

    // Méthode pour extraire le Test Suite ID (simplifié)
    private static int extractTestSuiteId(String jsonResponse) {
        // Logique pour extraire le premier suiteId du JSON (à adapter selon le format JSON)
        // Par exemple : Chercher le champ "id" dans la réponse
        return Integer.parseInt(jsonResponse.split("\"id\":")[1].split(",")[0].trim());
    }

    // Méthode pour extraire le Test Point ID correspondant au Test Case ID
    public static int extractTestPointId(String jsonResponse, int testCaseId) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(jsonResponse, JsonObject.class);
        JsonArray points = jsonObject.getAsJsonArray("value");
        for (JsonElement pointElement : points) {
            JsonObject point = pointElement.getAsJsonObject();
            JsonObject testCaseReference = point.getAsJsonObject("testCaseReference");

            if (testCaseReference != null && testCaseReference.get("id").getAsInt() == testCaseId) {
                return point.get("id").getAsInt(); // Retourne l'ID du point de test
            }
        }

        return -1; // Si aucun testPointId correspondant n'a été trouvé
    }
}