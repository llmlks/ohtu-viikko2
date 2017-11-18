package ohtu;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import java.io.IOException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.fluent.Request;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        String studentNr = "";
        if (args.length > 0) {
            studentNr = args[0];
        }

        String statsURL = "https://studies.cs.helsinki.fi/ohtustats/stats";
        String courseStats = Request.Get(statsURL).execute().returnContent().asString();

        JsonParser parser = new JsonParser();
        JsonObject parsedStats = parser.parse(courseStats).getAsJsonObject();
        int subsTotal = 0;
        int submittedExercises = 0;

        for (Map.Entry<String, JsonElement> member : parsedStats.entrySet()) {
            JsonObject parsedWeekStats = ((JsonObject) member.getValue());
            subsTotal += parsedWeekStats.get("students").getAsInt();
            submittedExercises += parsedWeekStats.get("exercise_total").getAsInt();
        }

        String courseURL = "https://studies.cs.helsinki.fi/ohtustats/courseinfo";
        String courseInfo = Request.Get(courseURL).execute().returnContent().asString();

        Gson mapper = new Gson();

        CourseInfo info = mapper.fromJson(courseInfo, CourseInfo.class);
        System.out.println(info + "\n");

        String url = "https://studies.cs.helsinki.fi/ohtustats/students/" + studentNr + "/submissions";

        try {
            String bodyText = Request.Get(url).execute().returnContent().asString();

            Submission[] subs = mapper.fromJson(bodyText, Submission[].class);

            System.out.println("opiskelijanumero: " + studentNr + "\n");
            int exercisesTotal = 0;
            int hoursTotal = 0;

            for (Submission sub : subs) {
                System.out.println("  " + sub.weekToString());
                System.out.println("\t" + sub.exerciseAmountToString() + " "
                        + info.weekMaxExercises(sub.getWeek()) + ", "
                        + sub.hoursToString() + ", " + sub.exercisesToString());
                exercisesTotal += sub.getExercises().length;
                hoursTotal += sub.getHours();
            }

            System.out.println("\nyhteensä: " + exercisesTotal + " tehtävää "
                    + hoursTotal + " tuntia");
        } catch (HttpResponseException e) {
            System.out.println("Annettua opiskelijanumeroa ei löytynyt järjestelmästä");
        }
        
        System.out.println("\nkurssilla yhteensä " + subsTotal
                + " palautusta, palautettuja tehtäviä " + submittedExercises
                + " kpl");
    }
}
