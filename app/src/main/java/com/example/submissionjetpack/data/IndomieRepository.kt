package com.example.submissionjetpack.data

import com.example.submissionjetpack.model.FakeIndomieDataSource
import com.example.submissionjetpack.model.OrderIndomie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class IndomieRepository {
    private val orderIndomies = mutableListOf<OrderIndomie>()

    init {
        if (orderIndomies.isEmpty()) {
            FakeIndomieDataSource.dummyIndomies.forEach {
                orderIndomies.add(OrderIndomie(it, 0))
            }
        }
    }

    fun getAllIndomie(): Flow<List<OrderIndomie>> {
        return flowOf(orderIndomies)
    }

    fun getOrderindomieById(indomieId: Long): OrderIndomie {
        return orderIndomies.first {
            it.indomie.id == indomieId
        }
    }

    fun updateOrderIndomie(indomieId: Long, newCountValue: Int): Flow<Boolean> {
        val index = orderIndomies.indexOfFirst { it.indomie.id == indomieId }
        val result = if (index >= 0) {
            val orderIndomie = orderIndomies[index]
            orderIndomies[index] =
                orderIndomie.copy(indomie = orderIndomie.indomie, count = newCountValue)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getAddedOrderIndomie(): Flow<List<OrderIndomie>> {
        return getAllIndomie()
            .map { orderIndomie ->
                orderIndomie.filter { orderIndomie ->
                    orderIndomie.count != 0
                }
            }
    }

    companion object {
        @Volatile
        private var instance: IndomieRepository? = null

        fun getInstance(): IndomieRepository =
            instance ?: synchronized(this) {
                IndomieRepository().apply {
                    instance = this
                }
            }
    }
}