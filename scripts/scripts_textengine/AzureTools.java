package scripts_textengine;
import browserstack.shaded.com.google.gson.JsonParser;
import browserstack.shaded.org.json.JSONArray;
import browserstack.shaded.org.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import okhttp3.*;
import scripts_techniques.Config;

import java.io.File;
import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class AzureTools {


    public static void main(String[] args) throws Exception {
        //List<TestCaseResult> testResults = createTestResults();
        //createTestPlan("kalios");
        String stepsXml = "<steps id=\\\"0\\\" last=\\\"3\\\">" +
                "<step id=\\\"1\\\" type=\\\"ActionStep\\\">" +
                "<action>testEdit1</action>" +
                "<expectedresult>OK</expectedresult>" +
                "</step>" +
                "<step id=\\\"2\\\" type=\\\"ActionStep\\\">" +
                "<action>testEdit2</action>" +
                "<expectedresult>OK</expectedresult>" +
                "</step>" +
                "<step id=\\\"3\\\" type=\\\"ActionStep\\\">" +
                "<action>testEdit3</action>" +
                "<expectedresult>OK</expectedresult>" +
                "</step>" +
                "</steps>";

//        NewTestCase("recherche barre de recherche", "30", "");
//        NewTestCase("recherche par menu", "31", "");
//        List<Integer> list = new ArrayList<>();
//        list.add(30);
//        list.add(31);
//        NewTestPlan("test par recherche",list);
        //System.out.println(getIdAzureByIdKalios("1454"));
        //System.out.println(getIdAzureByIdKalios("1451"));
        System.out.println(getTestCaseIdByName("parcours1"));
        System.out.println(getTestCaseIdByName("parcours2"));
        System.out.println(getTestCaseIdByName("parcours3"));
        System.out.println(getTestCaseIdByName("parcours4"));
        //System.out.println(getAllTestCase());        //addTestCasesToSuite(9,1);
        //addTestCasesToSuite(9,2);

        //removeTestCaseFromTestPlan(9,list);
        //listTestCases(13);
        //executerequest("https://dev.azure.com/"+Config.ORGANIZATION+"/"+Config.PROJECT+"/_apis/test/plans/3/testcases?api-version=7.2-preview.1");
//        int idtestsuite = createTestSuite(1,"testsuite " + d, 2);
        //int idtestcase = createTestCase("19-09", "15",stepsXml, "ready", "Completed");
//        getTC(idtestcase);
//        addTestCasesToSuite(1,idtestsuite,idtestcase);

        //UpdateTestCase("testEdit",60,stepsXml);
        //createTestCase("testauto", " ");
        //getTestPlans();
        //getTestSuite(5);
        //getAllTestPlan();

//        if (testResults != null && !testResults.isEmpty()) {
//            try {
//                //TextEngine.createTestRun("test");
//                //TextEngine.uploadTestResults(testResults);
//                //TextEngine.updateTestRunState("Completed");
//                //TextEngine.getLinkResult("http://instance130624.test.kalios-saas.com/web-instance/web/api/public/sharable-testplan-results-create?token=dbc98b1178bd1737fa54e837a9a6e84e&testplan=11&dir_testplan=planTests/TNR___11___1718371867/exemple_parcours_achat_juno_jacket___par_icon/&dataset=1");
//                //String link = TextEngine.getLinkResult("test20231023","f910a1a8eff9c2dcfa1b20cc8bc1cc4e" , "4", Config.dir_export+"/"+"exemple_parcours"+"/");
//                //Xray();
//                //attachTestResultFile(188, 100000);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } else {
//            System.out.println("Aucun résultat de test à ajouter.");
//        }
    }

    public static int NewTestCase(String Title, String IdKalios, String Step){
        try {
            HashMap<String, Integer> AllTestCase = getAllTestCase();
            ArrayList<String> AllTestCaseName = getAllTestCaseNames(Title);

            if(IdKalios.equals("-1")){
                if(!AllTestCaseName.isEmpty()) {
                    int id = UpdateTestCase(Title,getTestCaseIdByName(Title) , Step);
                    return id;
                }
                else{
                    int id = createTestCase(Title, IdKalios, Step, "Active", "OK");
                    return id;
                }
            }
            if(AllTestCase.containsKey(IdKalios)){
                int id = UpdateTestCase(Title,AllTestCase.get(IdKalios),Step);
                System.out.println("Test Case Updated");
                return id;
            }
            else{
                int id = createTestCase(Title, IdKalios, Step, "Active", "OK");
                System.out.println("TestCase Created");
                return id;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static int getIdAzureByIdKalios(String IdKalios){
        try {
            HashMap<String, Integer> AllTestcase = getAllTestCase();
            return AllTestcase.getOrDefault(IdKalios, -1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private static List<TestCaseResult> createTestResults() {
        // Exemple de création de résultats de test (à adapter selon votre logique)
        List<TestCaseResult> results = new ArrayList<>(); ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);

        // Définir le format souhaité
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

        // Formater la date et l'heure actuelles
        String formattedNow = now.format(formatter);

        TestCaseResult result1 = new TestCaseResult("Sample Test Case", "7", "Failed", "windows", "","");
        //TestCaseResult result2 = new TestCaseResult("VerifyWebsiteLinks", "1471", "Warning", "windows", "","");
        //TestCaseResult result3 = new TestCaseResult("VerifyWebsiteHomePage", "1454", "Failed", "windows", "","");
        //result2.setErrorMessage("error");
        results.add(result1);
        //results.add(result2);
        //results.add(result3);

        return results;
    }
    public static void removeTestCaseFromTestPlan(int testPlanId, List<Integer> testCaseIds) throws Exception {
        StringBuilder S_testCaseIds = new StringBuilder(testCaseIds.get(0).toString());
        testCaseIds.remove(0);
        for (int id:testCaseIds) {
            S_testCaseIds.append(",").append(id);
        }
        String url = String.format("https://dev.azure.com/%s/%s/_apis/testplan/Plans/%d/Suites/%d/TestCase?testIds=%s&api-version=7.1",
                Config.ORGANIZATION, Config.PROJECT, testPlanId, getFirstTestSuiteId(testPlanId), S_testCaseIds);

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .delete() // Utiliser DELETE pour retirer le Test Case
                .header("Authorization", Credentials.basic("", Config.PAT))
                .header("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                System.out.println("Test Case retiré avec succès du Plan de Test.");
            } else {
                throw new IOException("Erreur lors du retrait du Test Case : " + response.body().string());
            }
        }
    }
    public static int getFirstTestSuiteId(int testPlanId) throws IOException {
        String url = "https://dev.azure.com/" + Config.ORGANIZATION + "/" + Config.PROJECT + "/_apis/testplan/Plans/" + testPlanId + "/suites?api-version=7.2-preview.1";

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .header("Authorization", Credentials.basic("", Config.PAT))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Erreur lors de la récupération des suites : " + response);
            String responseBody = response.body().string();

            // Extraire la première Test Suite ID (simplifié pour cet exemple)
            int testSuiteId = extractTestSuiteId(responseBody);
            return testSuiteId;
        }
    }
    private static int extractTestSuiteId(String jsonResponse) {
        // Logique pour extraire le premier suiteId du JSON (à adapter selon le format JSON)
        // Par exemple : Chercher le champ "id" dans la réponse
        return Integer.parseInt(jsonResponse.split("\"id\":")[1].split(",")[0].trim());
    }
    public static HashMap<String,Integer> getAllTestCase() throws IOException{
        // Configurations
        String organization = Config.ORGANIZATION;
        String project = Config.PROJECT;
        String patToken = Config.PAT; // Jeton d'accès personnel

        // URL de l'API
        String url = String.format(
                "https://dev.azure.com/%s/%s/_apis/wit/wiql?api-version=7.1-preview.2",
                organization, project
        );

        // Corps de la requête WIQL pour obtenir tous les Test Cases
        String wiqlQuery = "{ \"query\": \"Select [System.Id], [System.Title] From WorkItems Where [System.WorkItemType] = 'Test Case'\" }";

        // Requête HTTP
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), wiqlQuery);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .header("Authorization", Credentials.basic("", patToken))
                .header("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Erreur lors de la récupération des cas de test : " + response);
            }
            // Parse la réponse JSON avec Gson
            String responseBody = response.body().string();
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);
            JsonArray workItems = jsonObject.getAsJsonArray("workItems");
            HashMap<String,Integer> AllTestCase = new HashMap();
            // Afficher les détails des test cases
            for (int i = 0; i < workItems.size(); i++) {
                JsonObject workItem = workItems.get(i).getAsJsonObject();
                String link = workItem.get("url").getAsString();
                int id = workItem.get("id").getAsInt();
                //System.out.println("Test Case url: " + link);
                browserstack.shaded.com.google.gson.JsonObject jo = JsonParser.parseString(executerequest(link)).getAsJsonObject();
                //System.out.println(jo);
                String title = jo.getAsJsonObject("fields").get("System.Title").getAsString();
                String IdKalios;
                try {
                    IdKalios = String.valueOf(jo.getAsJsonObject("fields").get("System.Description").getAsInt());
                }catch (Exception e){
                    IdKalios = "Id non défini";
                }
                AllTestCase.put(IdKalios,id);
                //System.out.println("Title: " + title + " Id : " + IdKalios);
            }
            return AllTestCase;
        }
    }
    public static ArrayList<String> getAllTestCaseNames(String Title) throws IOException {
        // Configurations
        String organization = Config.ORGANIZATION;
        String project = Config.PROJECT;
        String patToken = Config.PAT; // Jeton d'accès personnel

        // URL de l'API
        String url = String.format(
                "https://dev.azure.com/%s/%s/_apis/wit/wiql?api-version=7.1-preview.2",
                organization, project
        );

        // Corps de la requête WIQL pour obtenir tous les Test Cases
        String wiqlQuery = "{ \"query\": \"Select [System.Id], [System.Title] From WorkItems Where [System.WorkItemType] = 'Test Case' AND [System.Title] = '"+Title+"'\"  }";

        // Requête HTTP
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), wiqlQuery);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .header("Authorization", Credentials.basic("", patToken))
                .header("Content-Type", "application/json")
                .build();

        ArrayList<String> testCaseNames = new ArrayList<>();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Erreur lors de la récupération des cas de test : " + response);
            }

            // Parse la réponse JSON
            String responseBody = response.body().string();
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);
            JsonArray workItems = jsonObject.getAsJsonArray("workItems");

            // Récupérer le titre de chaque Test Case
            for (int i = 0; i < workItems.size(); i++) {
                JsonObject workItem = workItems.get(i).getAsJsonObject();
                String link = workItem.get("url").getAsString();

                // Récupérer les informations supplémentaires en utilisant le lien
                browserstack.shaded.com.google.gson.JsonObject jo = JsonParser.parseString(executerequest(link)).getAsJsonObject();
                String title = jo.getAsJsonObject("fields").get("System.Title").getAsString();

                // Ajouter le nom du test case à la liste
                testCaseNames.add(title);
            }
        }

        return testCaseNames;
    }
    public static String executerequest(String url) {

        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", Credentials.basic("", Config.PAT))
                .build();
        OkHttpClient client = new OkHttpClient();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Erreur lors de l'éxecution de la requete' : " + response);
            }
            String responseBody = response.body().string();
            //System.out.println("request response :  " + responseBody);
            return responseBody;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static void createTestRunPlan(String name) {

        String createRunUrl = String.format("https://dev.azure.com/%s/%s/_apis/test/plans?api-version=6.0", Config.ORGANIZATION, Config.PROJECT);

        browserstack.shaded.com.google.gson.JsonObject createRunPayload = new browserstack.shaded.com.google.gson.JsonObject();
        createRunPayload.addProperty("name", name);
        ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

        String formattedNow = now.format(formatter);
        createRunPayload.addProperty("startDate", formattedNow);

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), String.valueOf(createRunPayload));

        String credentials = Credentials.basic("", Config.PAT);

        Request request = new Request.Builder()
                .url(createRunUrl)
                .header("Authorization", credentials)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                browserstack.shaded.com.google.gson.JsonObject jo = JsonParser.parseString(response.body().string()).getAsJsonObject();

                int TP = Integer.parseInt(jo.get("id").getAsString());
                System.out.println(TP);

            } else {
                System.out.println("Failed to create test run. Status code: " + response.code()+response.message());
                System.out.println(response.body().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private static void getTestPlans() throws Exception {
        String url = String.format("https://dev.azure.com/%s/%s/_apis/testplan/Plans?api-version=7.1-preview.1", Config.ORGANIZATION, Config.PROJECT);

        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", Credentials.basic("", Config.PAT))
                .build();
        OkHttpClient client = new OkHttpClient();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Erreur lors de la récupération des plans de test : " + response);
            }
            System.out.println("Plans de test disponibles : " + response.body().string());
        }
    }
    private static int createTestSuite(int testPlanId, String testSuiteName, int parentSuite) throws Exception {
            String url = String.format("https://dev.azure.com/%s/%s/_apis/testplan/Plans/%d/suites?api-version=5.0", Config.ORGANIZATION, Config.PROJECT, testPlanId);

            // Note la structure JSON
            String jsonBody = String.format("{\"name\": \"%s\", \"suiteType\": \"StaticTestSuite\", \"parentSuite\": { \"id\": "+parentSuite+" }}", testSuiteName);

            RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonBody);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .header("Authorization", Credentials.basic("", Config.PAT))
                    .build();

            OkHttpClient client = new OkHttpClient();
            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    throw new IOException("Erreur lors de la création de la suite de test : " + response);
                }
                //System.out.println("Suite de test créée : " + response.body().string());
                browserstack.shaded.com.google.gson.JsonObject jo = JsonParser.parseString(response.body().string()).getAsJsonObject();
                int ts = Integer.parseInt(jo.get("id").getAsString());
                System.out.println(ts);
                return ts;
            }
        }
    private static void addTestCasesToSuite(int planId, int testCaseIds) throws Exception {
        // URL pour ajouter les cas de test à la suite
        String url = String.format("https://dev.azure.com/%s/%s/_apis/test/Plans/%d/suites/%d/testcases/%s?api-version=5.0",
                Config.ORGANIZATION, Config.PROJECT, planId, getFirstTestSuiteId(planId), testCaseIds);

        // Créer une requête POST
        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(MediaType.parse("application/json"), "")) // Le corps est vide pour cette requête
                .header("Authorization", Credentials.basic("", Config.PAT))
                .build();
        OkHttpClient client = new OkHttpClient();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Erreur lors de l'ajout des cas de test à la suite : " + response);
            }
            System.out.println("Cas de test ajoutés à la suite avec succès");
        }
    }
    private static void getTestSuite(int testPlanId) throws Exception {
        String url = String.format("https://dev.azure.com/%s/%s/_apis/testplan/Plans/%d?api-version=7.1-preview.1", Config.ORGANIZATION, Config.PROJECT, testPlanId);

        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", Credentials.basic("", Config.PAT))
                .build();

        OkHttpClient client = new OkHttpClient();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Erreur lors de l'ajout du cas de test à la suite : " + response);
            }
            System.out.println("Cas de test ajouté à la suite : " + response.body().string());
        }
    }
    private static int createTestCase(String title, String description, String stepsXml,String State, String Reason) throws Exception {
    String url = String.format("https://dev.azure.com/%s/%s/_apis/wit/workitems/$Test%%20Case?api-version=7.1-preview.3", Config.ORGANIZATION, Config.PROJECT);

    OkHttpClient client = new OkHttpClient();

    // JSON Patch format for the request body
    String jsonBody = "[{\"op\": \"add\", \"path\": \"/fields/System.Title\", \"value\": \"" + title + "\"}," +
            "{\"op\": \"add\", \"path\": \"/fields/System.Description\", \"value\": \"" + description + "\"},"+
           // "{\"op\": \"add\", \"path\": \"/fields/Custom.IdKalios\", \"value\": \""+ CustomId +"\"},"+
           // "{\"op\": \"add\", \"path\": \"/fields/System.State\", \"value\": \"" + State + "\"}," +
            //"{\"op\": \"add\", \"path\": \"/fields/System.Reason\", \"value\": \"" + Reason + "\"},"+
            "{\"op\": \"add\", \"path\": \"/fields/Microsoft.VSTS.TCM.Steps\", \"value\": \"" + stepsXml + "\"}]";


    RequestBody body = RequestBody.create(MediaType.parse("application/json-patch+json"), jsonBody);

    Request request = new Request.Builder()
            .url(url)
            .post(body)
            .header("Authorization", Credentials.basic("", Config.PAT))
            .header("Content-Type", "application/json-patch+json")
            .build();

    try (Response response = client.newCall(request).execute()) {
        String json = response.body().string();
        if (!response.isSuccessful()) {
            throw new IOException("Erreur lors de la création du cas de test : " + json);
        }
        System.out.println(json);
        browserstack.shaded.com.google.gson.JsonObject jo = JsonParser.parseString(json).getAsJsonObject();
        int ts = Integer.parseInt(jo.get("id").getAsString());
        System.out.println("crée avec succès "+ts);
        return ts;
    }
}
    public static int UpdateTestCase(String title, int testCaseId, String stepsXml){
    String url = "https://dev.azure.com/" + Config.ORGANIZATION + "/" + Config.PROJECT + "/_apis/wit/workitems/" + testCaseId + "?api-version=7.1-preview.3";

    // Création du corps de la requête JSON
    String jsonBody = "[{\"op\": \"add\", \"path\": \"/fields/System.Title\", \"value\": \"" + title + "\"}," +
                       "{\"op\": \"add\", \"path\": \"/fields/Microsoft.VSTS.TCM.Steps\", \"value\": \"" + stepsXml + "\"}]";

    OkHttpClient client = new OkHttpClient();

    RequestBody body = RequestBody.create(MediaType.parse("application/json-patch+json"), jsonBody);

    Request request = new Request.Builder()
            .url(url)
            .patch(body) // Note: Utilisez .patch pour les mises à jour partielles
            .header("Authorization", Credentials.basic("", Config.PAT))
            .header("Content-Type", "application/json-patch+json")
            .build();

    // Exécuter la requête
    try (Response response = client.newCall(request).execute()) {
        if (response.isSuccessful()) {
            System.out.println("Test case modifié avec succès.");
        } else {
            System.out.println("Erreur lors de la modification du test case : " + response.code() + " - " + response.message());
        }
        browserstack.shaded.com.google.gson.JsonObject jo = JsonParser.parseString(response.body().string()).getAsJsonObject();
        int ts = Integer.parseInt(jo.get("id").getAsString());
        return ts;
    } catch (IOException e) {
        e.printStackTrace();
    }
    return -1;
}
    public static void getTC(int id){
    String url = String.format("https://dev.azure.com/%s/%s/_apis/wit/workitems/%d?api-version=7.1-preview.3", Config.ORGANIZATION, Config.PROJECT, id);

    OkHttpClient client = new OkHttpClient();

    Request request = new Request.Builder()
            .url(url)
            .get()
            .header("Authorization", Credentials.basic("", Config.PAT))
            .header("Content-Type", "application/json")
            .build();

    try (Response response = client.newCall(request).execute()) {
        if (!response.isSuccessful()) {
            String responseBody = response.body().string();
            System.err.println("Erreur lors de la récupération du cas de test : " + responseBody);
            throw new IOException("Erreur lors de la récupération du cas de test : " + responseBody);
        }
        System.out.println("Cas de test récupéré avec succès : " + response.body().string());
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    public static void addAttachment(){
        String baseUrl = "https://dev.azure.com";
        String organization = "matthieu0013";
        String project = "kalios";
        int runId = 188;  // Remplacez par votre runId
        int testCaseResultId = 100000;  // Remplacez par votre testCaseResultId
        String apiVersion = "7.1-preview.1";
        String personalAccessToken = Config.PAT;

        // URL de l'API construite
        String url = String.format("%s/%s/%s/_apis/test/runs/%d/results/%d/attachments?api-version=%s",
                baseUrl, organization, project, runId, testCaseResultId, apiVersion);

        // Création du client OkHttp
        OkHttpClient client = new OkHttpClient();

        // Option 2: Multipart
        File file = new File("C:\\javaproject\\TextEngineUpdate\\TextEngine\\results\\planTests\\exemple_parcours___4___1720105213261\\exemple_test_plan_recherche\\exemple_test_plan_recherche_1_46_1_2_KO.jpg");  // Remplacez par le chemin de votre fichier
        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(),
                        RequestBody.create(MediaType.parse("application/octet-stream"), file))
                .build();

        // Création de la requête
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", Credentials.basic("", personalAccessToken))
                .post(body)
                .build();

        // Exécution de la requête et gestion de la réponse
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            // Affichage du corps de la réponse
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static int NewTestPlan(String title, List<Integer> NewtestCaseIdsKalios) {
        try {
            HashMap<Integer, String> allTestPlans = getAllTestPlans();
            List<String> NewtestCaseIdsAzure = new ArrayList<>();
            for (int testcaseid:NewtestCaseIdsKalios) {
                NewtestCaseIdsAzure.add(String.valueOf(getIdAzureByIdKalios(String.valueOf(testcaseid))));
            }

            // Chercher un Test Plan existant avec le même titre
            Integer existingPlanId = null;
            for (Map.Entry<Integer, String> entry : allTestPlans.entrySet()) {
                if (entry.getValue().equalsIgnoreCase(title)) {
                    existingPlanId = entry.getKey();
                    break;
                }
            }

            if (existingPlanId != null) {
                updateTestPlan(existingPlanId, title);
                List<Integer> OldTestCases = listTestCases(existingPlanId);
                for (int testcase:OldTestCases) {
                    if(!NewtestCaseIdsAzure.contains(String.valueOf(testcase))) {
                        List<Integer> testcaseToDelete = new ArrayList<>();
                        if (testcase>=0) {
                            testcaseToDelete.add(testcase);
                            removeTestCaseFromTestPlan(existingPlanId, testcaseToDelete);
                        }
                    }
                }
                for (String testcase:NewtestCaseIdsAzure) {
                    if((!OldTestCases.contains(Integer.parseInt(testcase)))&& !testcase.equals("-1"))
                        addTestCasesToSuite(existingPlanId, Integer.parseInt(testcase));
                }
                System.out.println("Test Plan Updated");
                return existingPlanId;
            } else {
                int testplan = createTestPlan(title);
                for (int testcaseid: NewtestCaseIdsKalios) {
                    if (testcaseid>=0) {
                        addTestCasesToSuite(testplan,getIdAzureByIdKalios(String.valueOf(testcaseid)));
                    }
                }
                System.out.println("Test Plan Created");
                return testplan;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static int NewTestPlanIndepedent(String title, List<Integer> NewtestCaseIdsAzure) {
        try {
            HashMap<Integer, String> allTestPlans = getAllTestPlans();


            // Chercher un Test Plan existant avec le même titre
            Integer existingPlanId = null;
            for (Map.Entry<Integer, String> entry : allTestPlans.entrySet()) {
                if (entry.getValue().equalsIgnoreCase(title)) {
                    existingPlanId = entry.getKey();
                    break;
                }
            }

            if (existingPlanId != null) {
                updateTestPlan(existingPlanId, title);
                List<Integer> OldTestCases = listTestCases(existingPlanId);
                for (int testcase:OldTestCases) {
                    if(!NewtestCaseIdsAzure.contains(testcase)) {
                        List<Integer> testcaseToDelete = new ArrayList<>();
                        if (testcase>=0) {
                            testcaseToDelete.add(testcase);
                            removeTestCaseFromTestPlan(existingPlanId, testcaseToDelete);
                        }
                    }
                }
                for (int testcase:NewtestCaseIdsAzure) {
                    if((!OldTestCases.contains(testcase)))
                        addTestCasesToSuite(existingPlanId, testcase);
                }
                System.out.println("Test Plan Updated");
                return existingPlanId;
            } else {
                int testplan = createTestPlan(title);
                for (int testcaseid: NewtestCaseIdsAzure) {
                    if (testcaseid>=0) {
                        addTestCasesToSuite(testplan,testcaseid);
                    }
                }
                System.out.println("Test Plan Created");
                return testplan;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static HashMap<Integer, String> getAllTestPlans() throws IOException {
        // Configuration
        String organization = Config.ORGANIZATION;
        String project = Config.PROJECT;
        String patToken = Config.PAT;

        // URL de l'API pour les Test Plans
        String url = String.format(
                "https://dev.azure.com/%s/%s/_apis/testplan/plans?api-version=7.1-preview.1",
                organization, project
        );

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", Credentials.basic("", patToken))
                .header("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Erreur lors de la récupération des plans de test : " + response);
            }

            String responseBody = response.body().string();
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);
            JsonArray testPlans = jsonObject.getAsJsonArray("value");

            // HashMap pour stocker les Test Plans (ID et nom)
            HashMap<Integer, String> allTestPlans = new HashMap<>();
            for (JsonElement planElement : testPlans) {
                JsonObject plan = planElement.getAsJsonObject();
                int id = plan.get("id").getAsInt();  // ID du plan de test
                String name = plan.get("name").getAsString();  // Nom du plan de test

                // Ajout du plan au HashMap
                allTestPlans.put(id, name);
                //System.out.println("Plan Name: " + name + ", Plan ID: " + id);
            }
            return allTestPlans;
        }
    }
    public static void updateTestPlan(int testPlanId, String title) {
        String url = "https://dev.azure.com/" + Config.ORGANIZATION + "/" + Config.PROJECT + "/_apis/testplan/plans/" + testPlanId + "?api-version=7.1-preview.1";

        // Construction du corps de la requête
        String jsonBody = "[{\"op\": \"add\", \"path\": \"/name\", \"value\": \"" + title + "\"}]";

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(MediaType.parse("application/json-patch+json"), jsonBody);

        Request request = new Request.Builder()
                .url(url)
                .patch(body)
                .header("Authorization", Credentials.basic("", Config.PAT))
                .header("Content-Type", "application/json-patch+json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                System.out.println("Test Plan mis à jour avec succès.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static int createTestPlan(String title) throws Exception {
        String url = String.format("https://dev.azure.com/%s/%s/_apis/testplan/plans?api-version=7.1-preview.1", Config.ORGANIZATION, Config.PROJECT);

        OkHttpClient client = new OkHttpClient();

        // Construction du corps de la requête pour la création du Test Plan
        String jsonBody = "{\"name\": \""+title+"\"}";

        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonBody);

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .header("Authorization", Credentials.basic("", Config.PAT))
                .header("Content-Type", "application/json-patch+json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Erreur lors de la création du Test Plan : " + response.body().string());
            }

            browserstack.shaded.com.google.gson.JsonObject responseJson = JsonParser.parseString(response.body().string()).getAsJsonObject();
            int testPlanId = responseJson.get("id").getAsInt();
            System.out.println("Test Plan créé avec succès : " + testPlanId);
            return testPlanId;
        }
    }
    public static List<Integer> listTestCases(int testPlanId) throws IOException {
        OkHttpClient client = new OkHttpClient();

        String url = "https://dev.azure.com/" + Config.ORGANIZATION + "/" + Config.PROJECT +
                "/_apis/testplan/Plans/" + testPlanId + "/Suites/" + getFirstTestSuiteId(testPlanId) + "/TestCase?api-version=7.1";

        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", Credentials.basic("", Config.PAT))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Erreur lors de la récupération des cas de test : " + response);
            }

            String responseBody = response.body().string();
            List<Integer> testcaseids = getTestCaseIds(responseBody);
            System.out.println("Liste des cas de test : " + testcaseids);
            return testcaseids;
        }
    }
    public static List<Integer> getTestCaseIds(String jsonResponse) {
        List<Integer> testCaseIds = new ArrayList<>();

        // Convertir la chaîne JSON en un objet JSON
        JSONObject jsonObject = new JSONObject(jsonResponse);

        // Récupérer le tableau "value"
        JSONArray testCasesArray = jsonObject.getJSONArray("value");

        // Parcourir chaque élément du tableau
        for (int i = 0; i < testCasesArray.length(); i++) {
            JSONObject testCaseObject = testCasesArray.getJSONObject(i);

            // Récupérer l'objet "workItem" et l'ID du Test Case
            JSONObject workItem = testCaseObject.getJSONObject("workItem");
            int testCaseId = workItem.getInt("id");

            // Ajouter l'ID à la liste
            testCaseIds.add(testCaseId);
        }

        return testCaseIds;
    }
    public static Integer getTestCaseIdByName(String testCaseName) throws IOException {
        // Configurations
        String organization = Config.ORGANIZATION;
        String project = Config.PROJECT;
        String patToken = Config.PAT; // Jeton d'accès personnel

        // URL de l'API WIQL
        String url = String.format(
                "https://dev.azure.com/%s/%s/_apis/wit/wiql?api-version=7.1-preview.2",
                organization, project
        );

        // Corps de la requête WIQL pour obtenir le Test Case avec un nom spécifique
        String wiqlQuery = "{ \"query\": \"Select [System.Id] From WorkItems " +
                "Where [System.WorkItemType] = 'Test Case' And [System.Title] = '" + testCaseName + "'\" }";

        // Requête HTTP
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), wiqlQuery);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .header("Authorization", Credentials.basic("", patToken))
                .header("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Erreur lors de la récupération du Test Case ID : " + response);
            }
            // Parse la réponse JSON avec Gson
            String responseBody = response.body().string();
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);
            JsonArray workItems = jsonObject.getAsJsonArray("workItems");

            // Vérifie s'il y a au moins un résultat
            if (workItems.size() > 0) {
                JsonObject workItem = workItems.get(0).getAsJsonObject();
                return workItem.get("id").getAsInt();
            } else {
                System.out.println("Aucun Test Case trouvé avec le nom donné : " + testCaseName);
                return null;
            }
        }
    }


}
