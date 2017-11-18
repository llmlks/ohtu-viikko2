package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        String studentNr = "";
        if (args.length > 0) {
            studentNr = args[0];
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
    }
}
