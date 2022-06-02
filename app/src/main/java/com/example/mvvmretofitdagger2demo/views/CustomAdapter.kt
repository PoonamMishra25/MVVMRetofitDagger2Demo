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
import com.example.mvvmretofitdagger2demo.model.PokemonDb
import java.time.format.DateTimeFormatter

class CustomAdapter(private val mList: MutableList<String> = mutableListOf()
,private val openDetails:(String)->Unit) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    fun setList(list:List<String>){
        mList.clear()
        mList.addAll(list)
        notifyDataSetChanged()
    }
    inner class ViewHolder(
        private val binding: ListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(str: String) {



binding.tvType.text=str

            //binding.tvWindRV.text = forecastModel.wind.speed.toString() + "Km/h"
            binding.root.setOnClickListener {
                openDetails(str)
                // this ^ is calling this \/ from the UsersFragment
//                    fun openDetails(WeatherReportModel: WeatherReportModel) {
//                        parentFragmentManager.beginTransaction()
//                            .replace(R.id.fragment_container, DetailsFragment.newInstance(WeatherReportModel))
//                            .addToBackStack(null)
//                            .commit()
//                    }
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

    // Holds the views for adding it to image and text
//    class ViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
//       fun bind(str: String){
//            binding.tvType.text=str
//
//            binding.root.setOnClickListener{
//             //   openDetail(str)
////               val activity=it!!.context as AppCompatActivity
////                activity.supportFragmentManager.beginTransaction().replace(R.id.rv_types,ListOfPokemons())
////                    .commit()
//
//
//            }
//        }
//
//    }

}