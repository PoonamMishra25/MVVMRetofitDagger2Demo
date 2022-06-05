package com.example.mvvmretofitdagger2demo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mvvmretofitdagger2demo.databinding.FragmentBlankBinding
import com.example.mvvmretofitdagger2demo.model.LogoType
import com.example.mvvmretofitdagger2demo.views.CustomAdapter

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

        val list: ArrayList<LogoType> = ArrayList()
        customAdapter = CustomAdapter(openDetails = ::openDetails)
        customAdapter.setList(populateList(list))
        binding.rvTypes
            .apply {
                layoutManager = GridLayoutManager(requireContext(), 2)
                hasFixedSize()
                this.adapter = customAdapter

            }


//        val application = requireActivity().application as PokemonApplication
//        (application).applicationComponent.injectViewModel(this)

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

    private fun openDetails(str: String) {
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
        return list

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}