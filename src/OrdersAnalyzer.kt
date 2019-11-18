import java.math.BigDecimal
import java.time.DayOfWeek
import java.time.LocalDateTime

class OrdersAnalyzer {

    data class Order(val orderId: Int, val creationDate: LocalDateTime, val orderLines: List<OrderLine>)

    data class OrderLine(val productId: Int, val name: String, val quantity: Int, val unitPrice: BigDecimal)

    fun totalDailySales(orders: List<Order>): Map<DayOfWeek, Int> {
        val sumOfOrders : MutableMap<DayOfWeek, Int> = hashMapOf()

        orders.forEach { addOrder(it , sumOfOrders) }

        return sumOfOrders
    }

    private fun addOrder(order: Order, sumOfOrders: MutableMap<DayOfWeek, Int>) {
        var sum = 0
        val day = order.creationDate.dayOfWeek
        val orderLine = order.orderLines

        orderLine.forEach {
            sum += it.quantity
        }

        if(sumOfOrders.containsKey(day)){
            sum += sumOfOrders.getValue(day)
        }
        sumOfOrders[day] = sum
    }
}
