package com.srbh.questions.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class QuestionViewModelFactory :ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(QuestionViewModel::class.java)){
            val key = "QuestionViewModel"
            if(hashMapViewModel.containsKey(key))
                hashMapViewModel.remove(key)
            addViewModel(key, QuestionViewModel())
            return getViewModel(key) as T
        }
        throw IllegalArgumentException("Couldn't recognize ViewModel class")
    }

    companion object{
        val hashMapViewModel = HashMap<String,ViewModel>()
        fun addViewModel(key: String, viewModel: ViewModel) = hashMapViewModel.put(key,viewModel)
        fun getViewModel(key: String): ViewModel? = hashMapViewModel.get(key)
    }
}