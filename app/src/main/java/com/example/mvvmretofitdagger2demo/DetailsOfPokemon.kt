package com.example.mvvmretofitdagger2demo


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.mvvmretofitdagger2demo.databinding.FragmentDetailsOfPokemon2Binding
import com.example.mvvmretofitdagger2demo.model.DetailCardsModel
import com.example.mvvmretofitdagger2demo.model.PokemonDetailModelItem
import com.example.mvvmretofitdagger2demo.viewmodel.MainViewModel
import com.example.mvvmretofitdagger2demo.viewmodel.MainViewModelFactory
import com.example.mvvmretofitdagger2demo.adapters.PokemonCardAdapter
import kotlinx.coroutines.launch
import javax.inject.Inject



private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"



class DetailsOfPokemon : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var pokeType: String = ""
    val imageUrlList: ArrayList<DetailCardsModel> = ArrayList()

    private var _binding: FragmentDetailsOfPokemon2Binding? = null
    private val binding: FragmentDetailsOfPokemon2Binding get() = _binding!!

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory
    private lateinit var mainViewModel: MainViewModel

    lateinit var pokemonCardAdapter: PokemonCardAdapter

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
    ): View {
        _binding = FragmentDetailsOfPokemon2Binding.inflate(layoutInflater)

        val application = requireActivity().application as PokemonApplication
        (application).applicationComponent.injectDetaileFragment(this)
        pokeId = arguments?.getInt("pokeId")!!
        pokeName = arguments?.getString("pokeName")!!


        mainViewModel = ViewModelProvider(this, mainViewModelFactory)[MainViewModel::class.java]
        getObservers()


        addCustomView()


        return binding.root

    }

    private fun getObservers() {
        lifecycleScope.launch {
            mainViewModel.pokemonLiveData.observe(viewLifecycleOwner, Observer {
                if (it == null) {

                    Toast.makeText(requireContext(), "Error occured", Toast.LENGTH_SHORT).show()

                } else {
                    feedingViews(it)
                }
            })
        }
    }

    private fun openDetails(detailCardsModel: DetailCardsModel) {
        parentFragmentManager.beginTransaction()
            .replace(
                R.id.containerView,
                CardDetailsPokemon.newInstance(detailCardsModel.images.large)
            )
            .addToBackStack(null)
            .commit()
    }


    private fun feedingViews(item: PokemonDetailModelItem) {

        Glide.with(binding.ivDetailImage).load(item.sprite).into(binding.ivDetailImage)
        binding.apply {
            pokeName = item.name
            tvDetailedName.text = pokeName
            tvSpecies.text =  item.species

            pokeType = item.types.toString()

            tvType.text =  pokeType.substring(1, pokeType.length - 1)
            makeToast(pokeType)
            if (pokeType.contains(",")) {
                setBackColor(pokeType.substring(1, pokeType.indexOf(",")))
            } else {
                setBackColor(pokeType.substring(1, pokeType.length - 1))
            }
            val ability = item.abilities.normal.toString()
            tvAbility.text =  ability.substring(1, ability.length - 1)

            val gender = item.gender.toString()
            tvGender.text =  gender.substring(1, gender.length - 1)

            tvHeight.text =  item.height
            tvWeight.text = item.weight
            tvEvolutionStage.text =  item.family.evolutionStage.toString()
            val evolutionLine = item.family.evolutionLine.toString()
            tvEvolutionLine.text =  evolutionLine.substring(1, evolutionLine.length - 1)

            if (item.starter) {
                tvStarter.text =  item.starter.toString()
            } else {
                tvStarter.visibility = View.GONE
                labelStarter.visibility = View.GONE
            }
            if (item.legendary) {
                tvLegendary.text = item.legendary.toString()
            } else {
                tvLegendary.visibility = View.GONE
                labelLegendary.visibility = View.GONE
            }
            if (item.ultraBeast) {
                tvUltrablast.text = item.ultraBeast.toString()
            } else {
                tvUltrablast.visibility = View.GONE
                lableUltrablast.visibility = View.GONE
            }
            if (item.mega) {
                tvMega.text =  item.mega.toString()
            } else {
                tvMega.visibility = View.GONE
                labelMega.visibility = View.GONE
            }


            tvDesc.text = item.description
        }

    }

    companion object {
        var pokeId: Int = 1
        var pokeName: String = "Pidgeot"



        @JvmStatic
        fun newInstance(param1: Int, pokeName: String) =
            DetailsOfPokemon().apply {
                arguments = Bundle().apply {
                    putInt("pokeId", param1)
                    putString("pokeName", pokeName)

                }
            }
    }

    private fun addCustomView() {
        mainViewModel.pokemonCardLiveData.observe(viewLifecycleOwner, Observer {
            if (it == null) {
                makeToast("No Card Found!")
            } else {
                imageUrlList.addAll(it.data)

                pokemonCardAdapter = PokemonCardAdapter(imageUrlList, openDetails = ::openDetails)
                binding.rvCards.apply {

                    adapter = pokemonCardAdapter
                }
            }
        })

    }

    private fun setBackColor(str: String) {
        when (str) {
            "Grass" -> binding.scrollLayout.setBackgroundResource(R.drawable.backround)
            "Water" -> binding.scrollLayout.setBackgroundResource(R.drawable.backround_water)
            "Fairy" -> binding.scrollLayout.setBackgroundResource(R.drawable.backround_fairy)
            "Dark" -> binding.scrollLayout.setBackgroundResource(R.drawable.backround_dark)
            "Flying" -> binding.scrollLayout.setBackgroundResource(R.drawable.backround_flying)
            "Poison" -> binding.scrollLayout.setBackgroundResource(R.drawable.backround_poison)
            "Ice" -> binding.scrollLayout.setBackgroundResource(R.drawable.backround_ice)
            "Fighting" -> binding.scrollLayout.setBackgroundResource(R.drawable.backround_fighting)
            "Fire" -> binding.scrollLayout.setBackgroundResource(R.drawable.backround_fire)
            "Normal" -> binding.scrollLayout.setBackgroundResource(R.drawable.backround_normal)
            "Electric" -> binding.scrollLayout.setBackgroundResource(R.drawable.backround_electric)
            "Ground" -> binding.scrollLayout.setBackgroundResource(R.drawable.backround_ground)
            "Psychic" -> binding.scrollLayout.setBackgroundResource(R.drawable.backround_psychic)
            "Ghost" -> binding.scrollLayout.setBackgroundResource(R.drawable.backround_ghost)
            "Rock" -> binding.scrollLayout.setBackgroundResource(R.drawable.backround_rock)
            "Steel" -> binding.scrollLayout.setBackgroundResource(R.drawable.backround_steel)
            "Bug" -> binding.scrollLayout.setBackgroundResource(R.drawable.backround_bug)
            "Dragon" -> binding.scrollLayout.setBackgroundResource(R.drawable.backround_dragon)
        }
    }

    private fun makeToast(str: String) {
        Toast.makeText(requireContext(), str, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
