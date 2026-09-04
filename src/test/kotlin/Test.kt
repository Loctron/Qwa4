import org.junit.Assert.assertEquals
import org.junit.Test
import ru.netology.calculateCommission

class CommissionCalculatorTest {

    @Test
    fun testVkPayValid() {
        assertEquals(0, calculateCommission("VK Pay", 0, 1000_00))
    }

    @Test
    fun testVkPayOneTimeLimitExceeded() {
        assertEquals(-1, calculateCommission("VK Pay", 0, 20_000_00))
    }

    @Test
    fun testVkPayMonthlyLimitExceeded() {
        assertEquals(-1, calculateCommission("VK Pay", 30_000_00, 15_000_00))
    }

    @Test
    fun testCardDailyLimitExceeded() {
        assertEquals(-1, calculateCommission("Visa", 0, 200_000_00))
    }

    @Test
    fun testCardMonthlyLimitExceeded() {
        assertEquals(-1, calculateCommission("Mastercard", 500_000_00, 150_000_00))
    }

    @Test
    fun testMastercardNoCommission() {
        // Перевод 500 руб (от 300 до 75000)
        assertEquals(0, calculateCommission("Mastercard", 0, 500_00))
    }

    @Test
    fun testMastercardCommissionAmountBelow300() {
        // Перевод 200 руб: 200 * 0.006 + 20 = 21.2 руб = 2120 коп
        assertEquals(2120, calculateCommission("Mastercard", 0, 200_00))
    }

    @Test
    fun testMastercardCommissionMonthlyExceeded() {
        // Перевод 80 000 руб: 80000 * 0.006 + 20 = 500 руб = 50000 коп
        assertEquals(50000, calculateCommission("Mastercard", 0, 80_000_00))
    }

    @Test
    fun testMaestroNoCommission() {
        assertEquals(0, calculateCommission("Maestro", 0, 500_00))
    }

    @Test
    fun testVisaMinimumCommission() {
        // Перевод 100 руб: комиссия 0.75 руб. Минимум 35 руб = 3500 коп
        assertEquals(3500, calculateCommission("Visa", 0, 100_00))
    }

    @Test
    fun testVisaStandardCommission() {
        // Перевод 10 000 руб: комиссия 75 руб = 7500 коп
        assertEquals(7500, calculateCommission("Visa", 0, 10_000_00))
    }

    @Test
    fun testMirStandardCommission() {
        assertEquals(7500, calculateCommission("Мир", 0, 10_000_00))
    }

    @Test
    fun testUnknownCard() {
        assertEquals(-1, calculateCommission("Unknown", 0, 1000_00))
    }
}