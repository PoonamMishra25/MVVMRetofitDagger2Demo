package com.example.mvvmretofitdagger2demo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.mvvmretofitdagger2demo.databinding.FragmentCardDetailsPokemonBinding
import com.example.mvvmretofitdagger2demo.databinding.FragmentDetailsOfPokemon2Binding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CardDetailsPokemon.newInstance] factory method to
 * create an instance of this fragment.
 */
class CardDetailsPokemon : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var url:String=""
    private var _binding: FragmentCardDetailsPokemonBinding? = null
    private val binding: FragmentCardDetailsPokemonBinding get() = _binding!!

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
        _binding = FragmentCardDetailsPokemonBinding.inflate(layoutInflater)
        url=arguments?.getString("ImageUrl")!!
        Glide.with(binding.oneCardDetails).load(url).into(binding.oneCardDetails)
        // Inflate the layout for this fragment
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CardDetailsPokemon.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String) =
            CardDetailsPokemon().apply {
                arguments = Bundle().apply {
                    putString("ImageUrl", param1)

                }
            }
    }
}