package com.example.mvvmretofitdagger2demo.adapters

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmretofitdagger2demo.R
import com.example.mvvmretofitdagger2demo.databinding.CardListBinding


class CustomCardsAdapter(
    private val mList: List<String>,
    private val openDetail: (String)-> Unit
) : RecyclerView.Adapter<CustomCardsAdapter.ViewHolder>() {


    inner class ViewHolder(
        private val binding: CardListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(str: String) {

            if (str.isNotEmpty()) {
                Glide.with(binding.ivCardImage).load(str.toString()).into(binding.ivCardImage)
            } else {
                binding.ivCardImage.setImageResource(R.drawable.poke_ball)
            }

            binding.root.setOnClickListener {
openDetail(str)

            }
        }
    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            CardListBinding.inflate(
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