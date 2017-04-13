import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Pr25AdvertisementMessage {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int number = Integer.parseInt(reader.readLine());
        String[] phrases = { "Excellent product.", "Such a great product.", "I always use that product.",
                "Best product of its category.", "Exceptional product.", "I can’t live without this product." };

        String[] events = { "Now I feel good.", "I have succeeded with this product.", "Makes miracles. I am happy of the results!",
                "I cannot believe but now I feel awesome.", "Try it yourself, I am very satisfied.", "I feel great!" };

        String[] authors = { "Diana", "Petya", "Stella", "Elena", "Katya", "Iva", "Annie", "Eva" };

        String[] cities = { "Burgas", "Sofia", "Plovdiv", "Varna", "Ruse" };

        Random random = new Random();

        for (int i = 0; i < number; i++) {
            System.out.println((phrases[random.nextInt(6)] + events[random.nextInt(6)] +
                    authors[random.nextInt(6)] + " - " +
            cities[random.nextInt(6)]));
        }
    }
}