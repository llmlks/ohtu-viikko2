package ohtu;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class TennisGame {

    private int difference;
    private boolean scoreFourOrMore;
    private final String player1;
    private final String player2;
    private final TreeMap<String, Integer> points;
    private static final Map<Integer, String> POINTS_TO_STRING
            = new HashMap<>();

    public TennisGame(String player1Name, String player2Name) {
        points = new TreeMap<>();
        points.put(player1Name, 0);
        points.put(player2Name, 0);

        player1 = player1Name;
        player2 = player2Name;
        difference = 0;
        scoreFourOrMore = false;
        initialisePointStrings();
    }

    private static void initialisePointStrings() {
        POINTS_TO_STRING.put(0, "Love");
        POINTS_TO_STRING.put(1, "Fifteen");
        POINTS_TO_STRING.put(2, "Thirty");
        POINTS_TO_STRING.put(3, "Forty");
        POINTS_TO_STRING.put(4, "Deuce");

    }

    public void wonPoint(String playerName) {
        if (points.containsKey(playerName)) {
            int newPoints = points.get(playerName) + 1;
            points.put(playerName, newPoints);

            scoreFourOrMore = newPoints >= 4 ? true : scoreFourOrMore;
            difference += playerName.equals(player1) ? 1 : -1;
        }
    }

    public String getScore() {
        if (difference == 0) {
            return getScoreForDraw();
        } else if (scoreFourOrMore) {
            return getScoreForEndGame();
        } else {
            return getScoreForMiddleGame();
        }
    }

    private String getScoreForDraw() {
        String result = POINTS_TO_STRING.get(points.get(player1));
        if (!result.equals("Deuce")) {
            result += "-All";
        }
        return result;
    }

    private String getScoreForEndGame() {
        boolean firstPlayerWinning = points.get(player1) > points.get(player2);
        boolean advantage
                = Math.abs(points.get(player1) - points.get(player2)) == 1;

        if (advantage) {
            return getScoreForAdvantage(firstPlayerWinning);
        }
        return firstPlayerWinning ? "Win for player1" : "Win for player2";
    }

    private String getScoreForAdvantage(boolean firstPlayerHasAdvantage) {
        return firstPlayerHasAdvantage ? "Advantage player1" : "Advantage player2";
    }

    private String getScoreForMiddleGame() {
        return POINTS_TO_STRING.get(points.get(player1)) + "-"
                + POINTS_TO_STRING.get(points.get(player2));
    }
}
