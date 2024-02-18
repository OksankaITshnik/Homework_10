import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;


public class WordFrequencyCounter {

    public static void main(String[] args) {
        countWordFrequency("C:\\Users\\vital\\IdeaProjects\\mventesting\\src\\main\\words.txt");
    }

    public static void countWordFrequency(String filePath) {
        Map<String, Integer> wordCounts = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Розділяємо рядок на слова та обробляємо кожне слово
                Stream.of(line.split("\\s+"))
                        .filter(word -> !word.isEmpty())
                        .forEach(word -> wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Сортуємо та виводимо результат
        wordCounts.entrySet().stream()
                .sorted((entry1, entry2) -> Integer.compare(entry2.getValue(), entry1.getValue()))
                .forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));
    }
}

