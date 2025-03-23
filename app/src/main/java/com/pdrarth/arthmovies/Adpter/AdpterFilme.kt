package com.pdrarth.arthmovies.Adpter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pdrarth.arthmovies.Model.Filme
import com.pdrarth.arthmovies.View.Detalhesdofilme
import com.pdrarth.arthmovies.databinding.FilmeItemBinding

class AdpterFilme ( val context: Context,  val listadeFilmes:MutableList<Filme>)
    : RecyclerView.Adapter<AdpterFilme.FilmeViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmeViewHolder {

        val item_lista = FilmeItemBinding.inflate(LayoutInflater.from(context),parent,false)


        return FilmeViewHolder(item_lista)
    }

    override fun getItemCount() = listadeFilmes.size

    override fun onBindViewHolder(holder: FilmeViewHolder, position: Int) {
        Glide.with(context).load(listadeFilmes[position].capa).centerCrop().into(holder.capa)

        holder.capa.setOnClickListener {

            val intent = Intent(context,Detalhesdofilme::class.java)
            intent.putExtra("capa", listadeFilmes[position].capa)
            intent.putExtra("nome", listadeFilmes[position].nome)
            intent.putExtra("descricao", listadeFilmes[position].descricao)
            intent.putExtra("elenco", listadeFilmes[position].elenco)
            context.startActivity(intent)
        }
    }

    inner class FilmeViewHolder(binding: FilmeItemBinding):RecyclerView.ViewHolder(binding.root){
        val capa  =  binding.holderCapa

    }
}