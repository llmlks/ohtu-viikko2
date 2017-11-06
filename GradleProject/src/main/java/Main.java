import java.util.Scanner;
import ohtu.Multiplier;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Multiplier three = new Multiplier(3);
        System.out.println("Enter a number: ");
        int number = scanner.nextInt();

        System.out.println(number + " times " + 3 + " equals " + three.multipliedBy(number));
        scanner.close();
    }
}