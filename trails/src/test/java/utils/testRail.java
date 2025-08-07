package utils;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class testRail {
    private static final String BASE_URL = "https://veeraswamyk2602.testrail.io/";
    private static final String USERNAME = "veeraswamy.kalluri@optimworks.com";
    private static final String API_KEY = "fDUZKLeHzo7fpZ96TqLL-2UiRQDvVMtbbk4ao2ugg";

    public static int runId = 1;

    public static void updateTestCaseStatus(int runId, int caseId, int statusId, String comment) throws IOException {
        String url = BASE_URL + "index.php?/api/v2/add_result_for_case/" + runId + "/" + caseId;
        HttpPost post = new HttpPost(url);
        String auth = Base64.getEncoder().encodeToString((USERNAME + ":" + API_KEY).getBytes());

        post.setHeader("Content-Type", "application/json");
        post.setHeader("Authorization", "Basic " + auth);

        String body = String.format("{\"status_id\": %d, \"comment\": \"%s\"}", statusId, comment);
        post.setEntity(new StringEntity(body, StandardCharsets.UTF_8));

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            var response = client.execute(post);
            int statusCode = response.getCode();
            String responseBody = new String(response.getEntity().getContent().readAllBytes());

            System.out.println("TestRail Status: " + statusCode);
            System.out.println("Response: " + responseBody);
        }
    }

    public static Map<String, Integer> getTitleAndIdToMap(int projectId) throws IOException {
        Map<String, Integer> caseMap = new HashMap<>();
        String url = BASE_URL + "index.php?/api/v2/get_cases/" + projectId;

        HttpGet get = new HttpGet(url);
        String auth = Base64.getEncoder().encodeToString((USERNAME + ":" + API_KEY).getBytes());
        get.setHeader("Content-Type", "application/json");
        get.setHeader("Authorization", "Basic " + auth);

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            var response = client.execute(get);
            String body = new String(response.getEntity().getContent().readAllBytes());
            JSONArray cases = new JSONObject(body).getJSONArray("cases");

            for (int i = 0; i < cases.length(); i++) {
                JSONObject c = cases.getJSONObject(i);
                caseMap.put(c.getString("title").trim(), c.getInt("id"));
            }
        }

        return caseMap;
    }
}
