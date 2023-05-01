package com.example.marvelcomics.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvelcomics.api.RetrofitService
import com.example.marvelcomics.data.dto.CharactersDTO
import com.example.marvelcomics.databinding.ActivityMainBinding
import com.example.marvelcomics.localstorage.CharacterViewModel
import com.example.marvelcomics.localstorage.CharacterViewModelFactory
import com.example.marvelcomics.localstorage.CharactersRepository
import com.example.marvelcomics.view.adapter.CharacterAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CharacterViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        viewModel = ViewModelProvider(this, CharacterViewModelFactory(CharactersRepository(RetrofitService())))[CharacterViewModel::class.java]

        val service = RetrofitService.createPostService()
        val call: Call<CharactersDTO> = service.getAllCharacters()
        call.enqueue(object : Callback<CharactersDTO> {

            override fun onResponse(call: Call<CharactersDTO>, Response: Response<CharactersDTO>) {
                if (Response.isSuccessful) {
                    val recyclerView = binding.sourcerecycle
                    recyclerView.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = CharacterAdapter(Response.body())
                    }
                } else if (Response.body() == null) {
                    Toast.makeText(
                        MainActivity(),
                        "Essa sigla parece não existir.",
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
            }

            override fun onFailure(call: Call<CharactersDTO>, t: Throwable) {
                Log.d("MainActivity", "onFail: " + t.message)
                Toast.makeText(MainActivity(), "Ops, algo deu errado.", Toast.LENGTH_LONG).show()
            }
        })

    }

}