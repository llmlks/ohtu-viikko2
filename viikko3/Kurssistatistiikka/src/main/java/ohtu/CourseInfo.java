package ohtu;

public class CourseInfo {

    private String name;
    private String term;
    private int[] exercises;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public int[] getExercises() {
        return exercises;
    }

    public void setExercises(int[] exercises) {
        this.exercises = exercises;
    }

    public String weekMaxExercises(int week) {
        return "(maksimi " + exercises[week + 1] + ")";
    }

    @Override
    public String toString() {
        return "Kurssi: " + name + ", " + term;
    }
}
