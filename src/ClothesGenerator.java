import java.time.LocalDate;
import java.util.Random;
import java.util.stream.Stream;

/**
 * Generates random ClothingItem objects using Stream API.
 */
public class ClothesGenerator {

  private static final String[] NAMES = {"Shirt", "Pants", "Jacket", "Dress", "Sweater"};
  private static final String[] FABRIC = {"Cotton", "Wool", "Silk", "Polyester", "Linen"};
  private static final String[] CITIES = {"Kyiv", "Zaporizhzhia", "Lviv", "New-York", "Dnipro"};

  /**
   * Generates a stream of random clothes.
   *
   * @return A stream of clothes.
   */
  public static Stream<Clothes> generateClothingItems() {

    Random random = new Random();
    return Stream.generate(() -> new Clothes(
        NAMES[random.nextInt(NAMES.length)],
        FABRIC[random.nextInt(FABRIC.length)],
        CITIES[random.nextInt(CITIES.length)],
        LocalDate.now().minusMonths(random.nextInt(240)),
        100 + random.nextInt(1000)
    ));
  }
}
