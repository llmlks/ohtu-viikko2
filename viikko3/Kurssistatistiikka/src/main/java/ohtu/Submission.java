package ohtu;

public class Submission {

    private int week;
    private int hours;
    private int[] exercises;

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int[] getExercises() {
        return exercises;
    }

    public void setExercises(int[] exercises) {
        this.exercises = exercises;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    private String weekToString() {
        return "viikko " + week + ": ";
    }

    private String exercisesToString() {
        String result = "tehdyt tehtävät:";
        for (int exercise : exercises) {
            result += " " + exercise;
        }
        return result;
    }

    private String hoursToString() {
        return "aikaa kului " + hours + " tuntia, ";
    }

    private String exerciseAmountToString() {
        return "tehtyjä tehtäviä yhteensä: " + exercises.length + ", ";
    }

    @Override
    public String toString() {
        return weekToString() + exerciseAmountToString() + hoursToString()
                + exercisesToString();
    }
}
