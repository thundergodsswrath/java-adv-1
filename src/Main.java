import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Main class that demonstrates filtering, grouping, statistical analysis,
 * and price analysis of a list of clothes.
 */
public class Main {

    /**
     * Main entry point of the program.
     */
    public static void main(String[] args) {
        int N = 10;

        List<Clothes> clothes = ClothesGenerator.generateClothingItems()
                .gather(new ClothesGatherer(N, "Lviv"))
                .limit(500)
                .toList();
        for (Clothes cloth : clothes) {
            System.out.println(cloth);
        }

        Map<String, List<Clothes>> groupedClothingItems = clothes.stream()
                .filter(item -> item.manufacturingDate().isAfter(LocalDate.now().minusMonths(60)))
                .collect(Collectors.groupingBy(Clothes::fabricType));
        for (Map.Entry<String, List<Clothes>> entry : groupedClothingItems.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().size());
        }

        ClothesStatistics statistics = clothes.stream()
                .collect(new ClothesStatisticsCollector());
        System.out.println(statistics);

        Map<String, Long> priceAnalysis = PriceAnalysis.analyzePrices(clothes);
        System.out.println(priceAnalysis);
    }
}
