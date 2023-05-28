package com.example.submissionjetpack.ui.screen.cart

import com.example.submissionjetpack.model.OrderIndomie

data class CartState(
    val orderIndomie: List<OrderIndomie>,
    val totalPrice: Int
)