package com.example.mvvmretofitdagger2demo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Query
import com.example.mvvmretofitdagger2demo.databinding.FragmentListOfPokemonsBinding
import com.example.mvvmretofitdagger2demo.db.PokemonDatabase
import com.example.mvvmretofitdagger2demo.model.PokemonDb
import com.example.mvvmretofitdagger2demo.repository.PokemonRepository
import com.example.mvvmretofitdagger2demo.viewmodel.MainViewModel
import com.example.mvvmretofitdagger2demo.viewmodel.MainViewModelFactory
import com.example.mvvmretofitdagger2demo.views.PokemonTypesAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ListOfPokemons : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    @Inject
    lateinit var repository: PokemonRepository

    @Inject
    lateinit var pokemonDatabase: PokemonDatabase
    lateinit var mainViewModel: MainViewModel
    private var _binding: FragmentListOfPokemonsBinding? = null
    private val binding: FragmentListOfPokemonsBinding get() = _binding!!
    var type: String = "Normal"
    //var pokeId:Int=1
    lateinit var pokemonTypesAdapter: PokemonTypesAdapter

//    private val viewModel: MainViewModel by lazy {
//        // repository=PokemonRepository()
//        object : ViewModelProvider.Factory {
//            override fun <T : ViewModel> create(modelClass: Class<T>): T {
//                return MainViewModel(repository) as T
//            }
//        }.create(MainViewModel::class.java)
//    }

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
        _binding = FragmentListOfPokemonsBinding.inflate(layoutInflater)
        type = arguments?.getString(ARG_PARAM1)!!

        val application = requireActivity().application as PokemonApplication
        (application).applicationComponent.injectViewModel(this)

        mainViewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)
        binding.tv2.text=type+" Type Pokemon"
        configureObserver1()

        return binding.root
    }

    private fun configureObserver1() {
        pokemonTypesAdapter = PokemonTypesAdapter(openDetails = ::openDetails)


        CoroutineScope(Dispatchers.Default).launch {
            pokemonTypesAdapter.setPokeList(pokemonDatabase.pokemonDao().getSpecificPokemon(type))

        }

        binding.apply {
            //rvPoke.layoutManager = LinearLayoutManager(requireContext())
            rvPoke.adapter = pokemonTypesAdapter
        }

    }

//    private fun configureObserver() {
//        pokemonTypesAdapter = PokemonTypesAdapter(openDetails = ::openDetails)
//
//        CoroutineScope(Dispatchers.Main).launch {
//            //pokemonTypesAdapter.setPokeList(pokemonDatabase.pokemonDao().getSpecificPokemon(type))
//
//            mainViewModel.pokemonSpecificLiveData.observe(viewLifecycleOwner, Observer {
//                pokemonTypesAdapter.setPokeList(it)
//            })
//
//        }
//
//        binding.apply {
//            rvPoke.layoutManager = GridLayoutManager(requireContext(),2)
//            rvPoke.adapter = pokemonTypesAdapter
//        }
//
//    }


    private fun openDetails(pokemonDb: PokemonDb) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.containerView, DetailsOfPokemon.newInstance(pokemonDb.id))
            .addToBackStack(null)
            .commit()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListOfPokemons.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String) =
            ListOfPokemons().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    // putString(ARG_PARAM2, param2)
                }
            }
    }
}