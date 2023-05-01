package com.example.marvelcomics.localstorage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marvelcomics.data.dto.CharactersDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterViewModel constructor(private val repository: CharactersRepository) : ViewModel() {

    val characterList = MutableLiveData<CharactersDTO>()
    val errorMessage = MutableLiveData<String>()

    fun getAllCharacters() {

        val request = this.repository.getALlCharacters()
        request.enqueue(object : Callback<CharactersDTO> {

            override fun onResponse(call: Call<CharactersDTO>, response: Response<CharactersDTO>) {
                if (response.code() == 200) {
                    characterList.postValue(response.body())
                } else {
                    errorMessage.postValue("Algo deu errado")
                }
            }

            override fun onFailure(call: Call<CharactersDTO>, t: Throwable) {
                errorMessage.postValue(t.message)
            }

        })
    }
}

