package ru.netology

fun calculateCommission(
    cardType: String = "VK Pay",
    previousTransfers: Int = 0,
    transferAmount: Int
): Int {
    // Лимиты в копейках (1 рубль = 100 копеек)
    val dailyLimitCards = 150_000_00
    val monthlyLimitCards = 600_000_00
    val oneTimeLimitVkPay = 15_000_00
    val monthlyLimitVkPay = 40_000_00

    // Проверка лимитов
    when (cardType) {
        "VK Pay" -> {
            if (transferAmount > oneTimeLimitVkPay || previousTransfers + transferAmount > monthlyLimitVkPay) {
                return -1
            }
        }
        else -> {
            if (transferAmount > dailyLimitCards || previousTransfers + transferAmount > monthlyLimitCards) {
                return -1
            }
        }
    }

    // Расчёт комиссии в копейках
    return when (cardType) {
        "Mastercard", "Maestro" -> {
            if (transferAmount >= 300_00 && previousTransfers + transferAmount <= 75_000_00) {
                0
            } else {
                (transferAmount * 0.006).toInt() + 20_00
            }
        }
        "Visa", "Мир" -> {
            val commission = (transferAmount * 0.0075).toInt()
            if (commission < 35_00) 35_00 else commission
        }
        "VK Pay" -> 0
        else -> -1
    }
}