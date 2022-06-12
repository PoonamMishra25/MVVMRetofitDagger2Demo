package com.example.mvvmretofitdagger2demo.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmretofitdagger2demo.databinding.ListItemBinding
import com.example.mvvmretofitdagger2demo.model.LogoType


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
//                val colors = intArrayOf(-0xdaa887, -0x593f33)
//                val gradientDrawable = GradientDrawable(
//                    GradientDrawable.Orientation.TOP_BOTTOM, colors
//                )
//                l.setBackground(gradientDrawable)
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