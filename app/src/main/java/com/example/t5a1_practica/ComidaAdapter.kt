package com.example.t5a1_practica

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.t5a1_practica.databinding.ItemComidasBinding

class ComidaAdapter (
    private val comidas: List<Comida>,
    private val onItemClicked: (Comida) -> Unit
): RecyclerView.Adapter<ComidaAdapter.ViewHolder>(){

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_comidas, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comida = comidas.get(position)
        with(holder){
            binding.tvOrder.text = (position + 1).toString()
            binding.tvName.text = comida.name.toString()
            Glide.with(context)
                .load(comida.url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .circleCrop()
                .into(binding.imgPhoto)

            itemView.setOnClickListener {
                onItemClicked(comida)
            }
        }
    }

    override fun getItemCount(): Int = comidas.size

    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val binding = ItemComidasBinding.bind(view) //Vinculamos la vista a nuestro adapter
    }

}