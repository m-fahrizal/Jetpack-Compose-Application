package com.example.submissionjetpack.di

import com.example.submissionjetpack.data.IndomieRepository

object Injection {
    fun provideRepository(): IndomieRepository {
        return IndomieRepository.getInstance()
    }
}