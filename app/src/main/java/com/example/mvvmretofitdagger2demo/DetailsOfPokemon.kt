package com.example.mvvmretofitdagger2demo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.mvvmretofitdagger2demo.databinding.FragmentDetailsOfPokemonBinding
import com.example.mvvmretofitdagger2demo.model.Abilities
import com.example.mvvmretofitdagger2demo.model.Family
import com.example.mvvmretofitdagger2demo.model.PokemonDetailModelItem
import com.example.mvvmretofitdagger2demo.viewmodel.MainViewModel
import com.example.mvvmretofitdagger2demo.viewmodel.MainViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [DetailsOfPokemon.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailsOfPokemon : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentDetailsOfPokemonBinding? = null
    private val binding: FragmentDetailsOfPokemonBinding get() = _binding!!

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory
    private lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsOfPokemonBinding.inflate(layoutInflater)

        val application = requireActivity().application as PokemonApplication
        (application).applicationComponent.injectDetaileFragment(this)
        pokeId = arguments?.getInt("pokeId")!!

        mainViewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)
        getObservers()
        // feedingViews(detailsList)
        return binding.root
    }

    private fun getObservers() {
        lifecycleScope.launch {
            mainViewModel.pokemonLiveData.observe(viewLifecycleOwner, Observer {

                // detailsList= it
                feedingViews(it)
            })
        }
    }

    private fun feedingViews(item: PokemonDetailModelItem) {

        Glide.with(binding.ivDetailImage).load(item.sprite).into(binding.ivDetailImage)
        binding.apply {
            tvDetailedName.text = item.name.toString()
            tvSpecies.text = "  "+item.species.toString()
            tvType.text = "  "+item.types[0].toString()
            tvAbility.text ="  "+
                item.abilities.normal[0].toString() //+ item.abilities.hidden[0].toString()

            tvGender.text = "  "+item.gender[0].toString()+" : "+item.gender[1]
            tvHeight.text = "  "+item.height.toString()
            tvWeight.text ="  "+ item.weight.toString()
            tvEvolutionStage.text ="  "+ item.family.evolutionStage.toString()
            tvEvolutionLine.text = "  "+item.family.evolutionLine.toString()
            tvStarter.text ="  "+ item.starter.toString()
            tvLegendary.text = "  "+item.legendary.toString()
            tvUltrablast.text ="  "+ item.ultraBeast.toString()
            tvMega.text = "  "+item.mega.toString()
         //   tvGen.text = item.gen.toString()
            tvDesc.text = "  "+item.description.toString()
        }
    }

    companion object {
        var pokeId: Int = 1

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetailsOfPokemon.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Int) =
            DetailsOfPokemon().apply {
                arguments = Bundle().apply {
                    putInt("pokeId", param1)

                }
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
//}      var detailsList: PokemonDetailModelItem = PokemonDetailModelItem(Abilities(emptyList(),
//    emptyList()),"", Family(emptyList(),0,0),0, emptyList(),"",false,false,false,""
//    ,"","","",false, emptyList(),false,""
//)