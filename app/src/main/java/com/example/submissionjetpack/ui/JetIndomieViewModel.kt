package com.example.submissionjetpack.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.submissionjetpack.data.IndomieRepository
import com.example.submissionjetpack.ui.screen.cart.CartViewModel
import com.example.submissionjetpack.ui.screen.detail.DetailIndomieViewModel
import com.example.submissionjetpack.ui.screen.home.HomeViewModel

class JetIndomieViewModel(private val repository: IndomieRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailIndomieViewModel::class.java)) {
            return DetailIndomieViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            return CartViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}
