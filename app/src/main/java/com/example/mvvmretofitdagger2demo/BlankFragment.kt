package com.example.mvvmretofitdagger2demo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmretofitdagger2demo.databinding.FragmentBlankBinding
import com.example.mvvmretofitdagger2demo.db.PokemonDao
import com.example.mvvmretofitdagger2demo.db.PokemonDatabase
import com.example.mvvmretofitdagger2demo.model.PokemonDb
import com.example.mvvmretofitdagger2demo.viewmodel.MainViewModel
import com.example.mvvmretofitdagger2demo.views.CustomAdapter
import com.example.mvvmretofitdagger2demo.views.DataAdapter
import com.example.mvvmretofitdagger2demo.views.PokemonTypesAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BlankFragment : Fragment() {

    private var _binding: FragmentBlankBinding? = null
    private val binding: FragmentBlankBinding get() = _binding!!
    private lateinit var customAdapter: CustomAdapter

    //  private lateinit var viewModel: MainViewModel

    //    private val dataAdapter: DataAdapter by lazy {
//        DataAdapter()
//    }
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        //  findNavController().navigate(R.id.action_blankFragment_to_listOfPokemons)
        //findNavController().navigate(ListOfPokemons).action
        val list: ArrayList<String> = ArrayList()

        customAdapter = CustomAdapter(openDetails = :: openDetails )
        customAdapter.setList(populateList(list))
        binding.rvTypes
            .apply {
                layoutManager = LinearLayoutManager(requireContext())
                hasFixedSize()
                this.adapter = customAdapter

            }

//        binding.rvTypes.setOnClickListener {
//            val action = BlankFragmentDirections.actionBlankFragmentToListOfPokemons()
//            findNavController().navigate(action)
//        }

        return binding.root
    }
//
//    private fun configureObserver() {
//        pokemonTypesAdapter = PokemonTypesAdapter(openDetails = ::openDetails)
//        val list: ArrayList<PokemonDb> = ArrayList()
//
//        //pokemonTypesAdapter.setUserList(list)
//        // viewLifecycleOwner -> points to the owner of the ViewModel
//        viewModel.pokemonTypeLiveData.observe(viewLifecycleOwner) {
//            pokemonTypesAdapter.setUserList(list)
//            binding.apply {
//                rvTypes.layoutManager = LinearLayoutManager(requireContext())
//                rvTypes.adapter = pokemonTypesAdapter
//            }

//            if (it.isEmpty()) {
////                binding.tvErrorTextView.visibility=View.VISIBLE
////                binding.tvErrorTextView.text = "Network call failed"
////                binding.pbLoading.visibility = View.GONE
//            } else {
//                pokemonTypesAdapter.setUserList(response)
//
//                binding.apply {
//                    rvTypes.layoutManager= LinearLayoutManager(requireContext())
//                    rvTypes.adapter = pokemonTypesAdapter
////                    pbLoading.visibility = View.GONE
////                    tvErrorTextView.visibility = View.GONE
//                }
//            }
//        }
//}
//}

    private fun openDetails(str:String) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.containerView, ListOfPokemons.newInstance(str))
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
         * @return A new instance of fragment BlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BlankFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun populateList(list: ArrayList<String>): ArrayList<String> {
        list.also {
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
        return list

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}