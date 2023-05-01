package com.example.marvelcomics.localstorage

import android.util.Log
import com.example.marvelcomics.api.RetrofitService
import com.example.marvelcomics.data.model.Character

class CharactersRepository constructor(private val retrofitService: RetrofitService) {

    private val service = RetrofitService.createPostService()

    fun saveCharacter(character: Character) {
        Log.i("Pedro", character.toString())
    }

    fun getALlCharacters() = service.getAllCharacters()
}