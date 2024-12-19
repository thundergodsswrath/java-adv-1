import java.util.*;
import java.util.stream.Collectors;

/**
 * Utility class for analyzing clothing item prices and identifying outliers.
 */
public class PriceAnalysis {

  /**
   * Analyzes clothing item prices to find data and outliers.
   *
   * @param clothes List of clothes.
   * @return A map with counts of data and outliers.
   */
  public static Map<String, Long> analyzePrices(List<Clothes> clothes) {
    List<Integer> prices = clothes.stream()
        .map(Clothes::price)
        .sorted()
        .toList();

    double Q1 = prices.get((int) Math.ceil(0.25 * prices.size()) - 1);
    double Q3 = prices.get((int) Math.ceil(0.75 * prices.size()) - 1);
    double IQR = Q3 - Q1;

    Map<Boolean, Long> result = clothes.stream()
        .collect(Collectors.partitioningBy(
            item -> item.price() >=  Q1 - 1.5 * IQR && item.price() <= Q3 + 1.5 * IQR,
            Collectors.counting()
        ));

    Map<String, Long> finalResult = new HashMap<>();
    finalResult.put("data", result.get(true));
    finalResult.put("outliers", result.get(false));

    return finalResult;
  }
}
