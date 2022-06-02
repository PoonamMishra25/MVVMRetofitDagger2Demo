package com.example.mvvmretofitdagger2demo.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmretofitdagger2demo.R
import com.example.mvvmretofitdagger2demo.model.DataModel

class DataAdapter :RecyclerView.Adapter<DataAdapter.DataAdapterViewHolder>() {
    private val adapterData = mutableListOf<DataModel>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DataAdapterViewHolder {

        val layout = when (viewType) {
            TYPE_FAMILY -> R.layout.pokemon_list
            TYPE_FRIEND -> R.layout.detailed_pokemon_list
           // TYPE_COLLEAGUE -> R.layout.item_colleague
            TYPE_HEADER -> R.layout.list_item
            else -> throw IllegalArgumentException("Invalid type")
        }

        val view = LayoutInflater
            .from(parent.context)
            .inflate(layout, parent, false)

        return DataAdapterViewHolder(view)
    }


    override fun onBindViewHolder(
        holder: DataAdapterViewHolder,
        position: Int
    ) {
        holder.bind(adapterData[position])
    }

    override fun getItemCount(): Int = adapterData.size

    override fun getItemViewType(position: Int): Int {
        return when (adapterData[position]) {
            is DataModel.PokemonList -> TYPE_FAMILY
            is DataModel.Friend -> TYPE_FRIEND
            is DataModel.Colleague -> TYPE_COLLEAGUE
            else -> TYPE_HEADER
        }
    }

    fun setData(data: List<DataModel>) {
        adapterData.apply {
            clear()
            addAll(data)
        }
    }
//    fun setTypeData(datamodel: ArrayList<String>) {
//        adapterData.apply {
//            clear()
//            addAll(datamodel)
//        }
//    }

    companion object {
        private const val TYPE_FAMILY = 0
        private const val TYPE_FRIEND = 1
        private const val TYPE_COLLEAGUE = 2
        const val TYPE_HEADER = 3
        var types: ArrayList<String> = ArrayList()

    }

   inner class DataAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private fun bindFamily(item: DataModel.PokemonList) {
            //Do your view assignment here from the data model
            TODO()
        }
        private fun bindFriend(item: DataModel.Friend) {
            TODO()
        }
        private fun bindColleague(item: DataModel.Colleague) {
            TODO()
        }
        private fun bindHeader(item: DataModel.TypeList) {

            types.also{
                it.add("Normal")
                it.add("Grass")
                it.add("Fire")
                it.add("Flying")
                it.add("Fighting")
                it.add("Poison")
                it.add("Electric")
                it.add("Ground")
                it.add("Rock")
                it.add("Psychic")
                it.add("Ice")
                it.add("Ghost")
                it.add("Steel")
                it.add("Dragon")
                it.add("Dark")
                it.add("Fairy")
            }
            types=item.typeOfList

        }
        fun bind(dataModel: DataModel) {
            when (dataModel) {
                is DataModel.TypeList -> bindHeader(dataModel)
                is DataModel.PokemonList -> bindFamily(dataModel)
                is DataModel.Friend -> bindFriend(dataModel)
                is DataModel.Colleague -> bindColleague(dataModel)

            }
        }
    }
}