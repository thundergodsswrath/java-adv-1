import java.time.LocalDate;

public record Clothes(String name, String fabricType, String manufacturingCity,
                      LocalDate manufacturingDate, int price) {
}
