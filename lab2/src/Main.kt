import Currency.EUR
import Currency.UAH
import Currency.USD

data class Trader(val name: String, val city: String)
data class Transaction(val trader: Trader, val year: Int, val month: Int, val value: Int, val currency: Currency)

enum class Currency {
    UAH, USD, EUR
}

// Завдання 1: Повернути список квадратів чисел
fun squareList(numbers: List<Int>): List<Int> {
    return numbers.map { it * it }
}

// Завдання 1.а: Знайти суму квадратів
fun sumOfSquares(numbers: List<Int>): Int {
    return numbers.map { it * it }.sum()
}

// Завдання 2: Знайти всі пари чисел з двох списків
fun getAllPairs(list1: List<Int>, list2: List<Int>): List<Pair<Int, Int>> {
    return list1.flatMap { x -> list2.map { y -> Pair(x, y) } }
}

// Основний клас для обробки транзакцій
fun main() {
    // Трейдери
    val raoul = Trader("Raoul", "Cambridge")
    val mario = Trader("Mario", "Milan")
    val alan = Trader("Alan", "Cambridge")
    val brian = Trader("Brian", "Cambridge")

    // Список транзакцій
    val transactions = listOf(
        Transaction(brian, 2011, 12, 300, UAH),
        Transaction(raoul, 2012, 10, 1000, UAH),
        Transaction(raoul, 2011, 11, 400, USD),
        Transaction(mario, 2012, 9, 710, UAH),
        Transaction(mario, 2012, 7, 700, USD),
        Transaction(alan, 2012, 4, 950, EUR)
    )

    // Завдання 1: Повернути список квадратів
    val numbers = listOf(1, 2, 3, 4, 5)
    val squaredNumbers = squareList(numbers)
    println("Squared Numbers: $squaredNumbers")  // [1, 4, 9, 16, 25]

    // Завдання 1.а: Знайти суму квадратів
    val sumOfSquaredNumbers = sumOfSquares(numbers)
    println("Sum of Squares: $sumOfSquaredNumbers")  // 55

    // Завдання 2: Знайти всі пари чисел з двох списків
    val list1 = listOf(1, 2, 3)
    val list2 = listOf(3, 4)
    val pairs = getAllPairs(list1, list2)
    println("All Pairs: $pairs")  // [(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]

    // Завдання 1 для транзакцій
    val transactions2011 = getTransactions2011(transactions)
    println("Transactions in 2011 sorted by value: $transactions2011")

    // Завдання 2: Унікальні міста
    val uniqueCities = getUniqueCities(transactions)
    println("Unique Cities: $uniqueCities")

    // Завдання 3: Трейдери з Кембриджа
    val cambridgeTraders = getTradersFromCambridge(transactions)
    println("Traders from Cambridge sorted by name: $cambridgeTraders")

    // Завдання 4: Рядок імен трейдерів
    val traderNames = getTradersNames(transactions)
    println("Traders names sorted alphabetically: $traderNames")

    // Завдання 5: Чи є трейдери в Мілані?
    val hasMilanTraders = hasTradersInMilan(transactions)
    println("Are there traders in Milan? $hasMilanTraders")

    // Завдання 6: Транзакції трейдерів з Кембриджу
    val cambridgeTransactions = getTransactionsFromCambridge(transactions)
    println("Transactions from traders in Cambridge: $cambridgeTransactions")

    // Завдання 7: Транзакція з найбільшою вартістю
    val maxTransaction = getTransactionWithMaxValue(transactions)
    println("Transaction with maximum value: $maxTransaction")

    // Завдання 8: Групування транзакцій за валютою
    val groupedByCurrency = groupTransactionsByCurrency(transactions)
    println("Transactions grouped by currency: $groupedByCurrency")

    // Завдання 9: Сума транзакцій у гривнях
    val sumUAH = sumTransactionsInUAH(transactions)
    println("Sum of transactions in UAH: $sumUAH")

    // Завдання 10: Форматування транзакцій
    val formattedTransactions = formatTransactionString(transactions)
    println("Formatted transactions: $formattedTransactions")
}

// Завдання 1 для транзакцій: Транзакції за 2011 рік
fun getTransactions2011(transactions: List<Transaction>): List<Transaction> {
    return transactions.filter { it.year == 2011 }.sortedBy { it.value }
}

// Завдання 2: Унікальні міста
fun getUniqueCities(transactions: List<Transaction>): Set<String> {
    return transactions.map { it.trader.city }.toSet()
}

// Завдання 3: Трейдери з Кембриджа
fun getTradersFromCambridge(transactions: List<Transaction>): List<Trader> {
    return transactions.filter { it.trader.city == "Cambridge" }
        .map { it.trader }
        .sortedBy { it.name }
}

// Завдання 4: Рядок імен трейдерів
fun getTradersNames(transactions: List<Transaction>): String {
    return transactions.map { it.trader.name }.distinct().sorted().joinToString(", ")
}

// Завдання 5: Чи є трейдери в Мілані?
fun hasTradersInMilan(transactions: List<Transaction>): Boolean {
    return transactions.any { it.trader.city == "Milan" }
}

// Завдання 6: Транзакції трейдерів з Кембриджу
fun getTransactionsFromCambridge(transactions: List<Transaction>): List<Transaction> {
    return transactions.filter { it.trader.city == "Cambridge" }
}

// Завдання 7: Транзакція з найбільшою вартістю
fun getTransactionWithMaxValue(transactions: List<Transaction>): Transaction? {
    return transactions.maxByOrNull { it.value }
}

// Завдання 8: Групування транзакцій за валютою
fun groupTransactionsByCurrency(transactions: List<Transaction>): Map<Currency, List<Transaction>> {
    return transactions.groupBy { it.currency }
}

// Завдання 9: Сума транзакцій у гривнях
fun sumTransactionsInUAH(transactions: List<Transaction>): Int {
    return transactions.filter { it.currency == UAH }.sumBy { it.value }
}

// Завдання 10: Форматування транзакцій
fun formatTransactionString(transactions: List<Transaction>): String {
    val sortedTransactions = transactions.sortedWith(compareBy({ it.year }, { it.month }))
    return sortedTransactions.mapIndexed { index, transaction ->
        "${index + 1}. ${transaction.month}-${transaction.year}: ${transaction.value} ${transaction.currency} ->"
    }.joinToString(" ", postfix = " ")
}
