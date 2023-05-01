package com.example.marvelcomics.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelcomics.data.dto.CharactersDTO
import com.example.marvelcomics.databinding.CharactercardBinding
import com.example.marvelcomics.data.model.Character

class CharacterAdapter(body: CharactersDTO?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<Character> = ArrayList()

    class CharacterViewHolder(itemView: CharactercardBinding) :
        RecyclerView.ViewHolder(itemView.root) {


        private val characterTitle = itemView.Name
        private val characterAuthor = itemView.Author


        fun bind(character: Character) {
            characterTitle.text = character.name
            characterAuthor.text = character.id.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CharacterViewHolder(
            CharactercardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CharacterViewHolder -> {
                holder.bind(items[position])
            }
        }
    }
    override fun getItemCount(): Int {
        return items.size
    }

}