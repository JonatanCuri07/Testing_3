package unitTests;

import com.mirai.importback.services.impl.PriceCalculationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class PriceCalculationServiceTest {

    PriceCalculationService priceCalculationService;

    public PriceCalculationServiceTest() {
        this.priceCalculationService = new PriceCalculationService(750, 10, LocalDate.of(2022, 12, 5), LocalDate.of(2023, 3, 5));
    }

    @Test
    void testAmountOfDays() {
        int result = priceCalculationService.calculatePeriod();
        Assertions.assertEquals(90, result);
    }

    @Test
    void testOrderFee() {
        float result = priceCalculationService.calculateFee();
        Assertions.assertEquals(1.025f, result);
    }

    @Test
    void testOrderPrice() {
        float result = priceCalculationService.calculatePrice();
        System.out.println(result);
        Assertions.assertEquals(768.75f, result);
    }
}
