package statistics.matcher;

import java.util.ArrayList;
import java.util.List;

public class QueryBuilder {

    private List<Matcher> matchers;

    public QueryBuilder() {
        this.matchers = new ArrayList<>();
    }

    public QueryBuilder playsIn(String team) {
        this.matchers.add(new PlaysIn(team));
        return this;
    }

    public QueryBuilder hasAtLeast(int number, String field) {
        this.matchers.add(new HasAtLeast(number, field));
        return this;
    }

    public QueryBuilder hasFewerThan(int number, String field) {
        this.matchers.add(new HasFewerThan(number, field));
        return this;
    }

    public QueryBuilder not(Matcher matcher) {
        this.matchers.add(new Not(matcher));
        return this;
    }

    public QueryBuilder oneOf(Matcher matcher1, Matcher matcher2) {
        this.matchers.add(new Or(matcher1, matcher2));
        return this;
    }

    public Matcher build() {
        Matcher[] andMatchers = new Matcher[matchers.size()];
        matchers.toArray(andMatchers);
        this.matchers.clear();

        return new And(andMatchers);
    }
}
