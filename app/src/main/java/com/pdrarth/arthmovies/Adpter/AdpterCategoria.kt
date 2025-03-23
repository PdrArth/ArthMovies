package com.pdrarth.arthmovies.Adpter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pdrarth.arthmovies.Model.Categoria
import com.pdrarth.arthmovies.Model.Filme
import com.pdrarth.arthmovies.databinding.CategoriaItemBinding

class AdpterCategoria (private val context: Context, val listaCategorias: MutableList<Categoria>)
    : RecyclerView.Adapter<AdpterCategoria.CategoriaViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaViewHolder {
        val itemLista = CategoriaItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return CategoriaViewHolder(itemLista)

    }
    override fun getItemCount() = listaCategorias.size

    override fun onBindViewHolder(holder: CategoriaViewHolder, position: Int) {
        holder.titulo.text = listaCategorias[position].titulo

        val categoria = listaCategorias[position]

        holder.recyclerviewFilmes.adapter = AdpterFilme(context,categoria.listMovies)
        holder.recyclerviewFilmes.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    inner class CategoriaViewHolder (binding: CategoriaItemBinding): RecyclerView.ViewHolder(binding.root){
        val titulo = binding.textfilmespopulares
        val recyclerviewFilmes = binding.recyclerviewFilmes
    }

}


