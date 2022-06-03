package com.example.mvvmretofitdagger2demo.views

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmretofitdagger2demo.ListOfPokemons
import com.example.mvvmretofitdagger2demo.R
import com.example.mvvmretofitdagger2demo.databinding.ListItemBinding
import com.example.mvvmretofitdagger2demo.model.LogoType
import com.example.mvvmretofitdagger2demo.model.PokemonDb
import java.time.format.DateTimeFormatter

class CustomAdapter(
    private val mList: MutableList<LogoType> = mutableListOf(),
    private val openDetails: (String) -> Unit
) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    fun setList(list: List<LogoType>) {
        mList.clear()
        mList.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        private val binding: ListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(logoType: LogoType) {


            binding.tvType.text = logoType.type
            binding.ivIcons.setImageResource(logoType.icon)


            binding.root.setOnClickListener {
                openDetails(logoType.type)

            }
        }
    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mList[position])


    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

}