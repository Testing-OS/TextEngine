package scripts_textengine;
import okhttp3.*;
import com.google.gson.*;
import scripts_techniques.Config;

import java.io.IOException;
import java.util.Base64;

public class JiraTicketCreator {
    public static void main(String[] args) {
        String organization = "matthieu0013";
        String project = "kalios";
        String pat = Config.PAT;

        // URL de l'API
        String url = "https://dev.azure.com/"+organization+"/"+project+"/_apis/wit/workitems/$Test%20Case?api-version=6.0";

        // Encodage du PAT en base64
        String encodedPat = Base64.getEncoder().encodeToString((":" + pat).getBytes());

        // Configuration du client OkHttp
        OkHttpClient client = new OkHttpClient();

        // Création du corps de la requête en JSON
        JsonArray body = new JsonArray();

        JsonObject title = new JsonObject();
        title.addProperty("op", "add");
        title.addProperty("path", "/fields/System.Title");
        title.addProperty("value", "Sample Test Case");
        body.add(title);

        JsonObject state = new JsonObject();
        state.addProperty("op", "add");
        state.addProperty("path", "/fields/System.State");
        state.addProperty("value", "Design");
        body.add(state);

        JsonObject steps = new JsonObject();
        steps.addProperty("op", "add");
        steps.addProperty("path", "/fields/Microsoft.VSTS.TCM.Steps");
        steps.addProperty("value", "<steps><step id=\"1\" type=\"Action\"><parameterizedString isformatted=\"true\">Step 1</parameterizedString><parameterizedString isformatted=\"true\"></parameterizedString></step></steps>");
        body.add(steps);

        // Conversion du corps en chaîne JSON
        String jsonBody = new Gson().toJson(body);

        // Création de la requête POST
        Request request = new Request.Builder()
                .url(url)
                .header("Content-Type", "application/json-patch+json")
                .header("Authorization", "Basic " + encodedPat)
                .post(RequestBody.create(MediaType.parse("application/json-patch+json"), jsonBody))
                .build();

        // Envoi de la requête
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                System.out.println("Test case created successfully: " + response.body().string());
            } else {
                System.err.println("Failed to create test case: " + response.body().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
