package com.example.blogger.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.blogger.data.Repositary

class BlogViewModelFactory(private val repositary: Repositary) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BlogViewModel(repositary) as T
    }
}