package com.example.mvvmretofitdagger2demo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mvvmretofitdagger2demo.adapters.CustomAdapter
import com.example.mvvmretofitdagger2demo.databinding.FragmentBlankBinding
import com.example.mvvmretofitdagger2demo.db.PokemonDatabase
import com.example.mvvmretofitdagger2demo.model.LogoType
import com.example.mvvmretofitdagger2demo.repository.PokemonRepository
import com.example.mvvmretofitdagger2demo.viewmodel.MainViewModel
import com.example.mvvmretofitdagger2demo.viewmodel.MainViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class BlankFragment : Fragment() {

    private var _binding: FragmentBlankBinding? = null
    private val binding: FragmentBlankBinding get() = _binding!!
    private lateinit var customAdapter: CustomAdapter


    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    @Inject
    lateinit var repository: PokemonRepository

    @Inject
    lateinit var pokemonDatabase: PokemonDatabase
    private var param1: String? = null
    private var param2: String? = null
    lateinit var mainViewModel: MainViewModel
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
        _binding = FragmentBlankBinding.inflate(layoutInflater)
        val application = requireActivity().application as PokemonApplication
        (application).applicationComponent.injectViewModel2(this)

        mainViewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)
        val list: ArrayList<LogoType> = ArrayList()
        customAdapter = CustomAdapter(openDetails = ::openDetails)
        customAdapter.setList(populateList(list))
        binding.rvTypes
            .apply {
                layoutManager = GridLayoutManager(requireContext(), 2)
                hasFixedSize()
                this.adapter = customAdapter

            }

        listOfPoke()
        openDeckList()
        return binding.root
    }

    private fun openDeckList() {
        binding.decksButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.containerView, DeckFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    private fun listOfPoke() {
        binding.ibtnSearch.setOnClickListener {
            binding.autoComplete.visibility = View.VISIBLE
            val list1: ArrayList<String> = ArrayList()
            CoroutineScope(Dispatchers.Main).launch {
                list1.addAll(pokemonDatabase.pokemonDao().getAllPokemon())
                val arrayAdapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_dropdown_item,
                    list1
                )
                val autoComplete = binding.autoComplete
                autoComplete.setAdapter(arrayAdapter)

                autoComplete.setOnItemClickListener { adapterView, view, i, l ->

                    Toast.makeText(requireContext(), "Name-: " + list1[i], Toast.LENGTH_SHORT)
                        .show()
                    CoroutineScope(Dispatchers.IO).launch {
                        parentFragmentManager.beginTransaction()
                            .replace(
                                R.id.containerView, DetailsOfPokemon.newInstance(
                                    pokemonDatabase.pokemonDao()
                                        .getAllPokemonID(autoComplete.text.toString()),
                                    autoComplete.text.toString()
                                )
                            )
                            .addToBackStack(null)
                            .commit()
                    }
                    autoComplete.setText("")

                }
            }
        }
    }


    private fun openDetails(str: String) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.containerView, ListOfPokemons.newInstance(str))
            .addToBackStack(null)
            .commit()
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BlankFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun populateList(list: ArrayList<LogoType>): ArrayList<LogoType> {
        list.add(LogoType("Water", R.drawable.water))
        list.add(LogoType("Grass", R.drawable.grass))
        list.add(LogoType("Fire", R.drawable.fire))
        list.add(LogoType("Normal", R.drawable.normal_icon))
        list.add(LogoType("Flying", R.drawable.flying))
        list.add(LogoType("Fighting", R.drawable.fighting))
        list.add(LogoType("Poison", R.drawable.poison))
        list.add(LogoType("Electric", R.drawable.electric))
        list.add(LogoType("Ground", R.drawable.ground))
        list.add(LogoType("Rock", R.drawable.rock))
        list.add(LogoType("Psychic", R.drawable.psychic))
        list.add(LogoType("Ice", R.drawable.ice))
        list.add(LogoType("Ghost", R.drawable.ghost))
        list.add(LogoType("Steel", R.drawable.steel))
        list.add(LogoType("Dark", R.drawable.dark))
        list.add(LogoType("Fairy", R.drawable.fairy))
        list.add(LogoType("Bug", R.drawable.bug))
        list.add(LogoType("Dragon", R.drawable.dragon))
        return list

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}