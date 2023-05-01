package com.example.marvelcomics.localstorage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CharacterViewModelFactory constructor(private val repository: CharactersRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CharacterViewModel::class.java)) {
            CharacterViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("Viewmodel Not Found")
        }
    }
}