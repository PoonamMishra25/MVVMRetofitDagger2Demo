package com.example.mvvmretofitdagger2demo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.mvvmretofitdagger2demo.adapters.CustomCardsAdapter
import com.example.mvvmretofitdagger2demo.databinding.FragmentDeckCardsBinding
import com.example.mvvmretofitdagger2demo.db.PokemonDatabase
import com.example.mvvmretofitdagger2demo.viewmodel.MainViewModel
import com.example.mvvmretofitdagger2demo.viewmodel.MainViewModelFactory
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class DeckCardsFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    lateinit var customCardsAdapter: CustomCardsAdapter
    private var _binding: FragmentDeckCardsBinding? = null
    private val binding: FragmentDeckCardsBinding get() = _binding!!

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory
    var deckId: Int = 1

    @Inject
    lateinit var pokemonDatabase: PokemonDatabase
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
    ): View? {
        _binding = FragmentDeckCardsBinding.inflate(layoutInflater)
        val application = requireActivity().application as PokemonApplication
        (application).applicationComponent.injectDeckCardFragment(this)
        mainViewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)
        deckId = arguments?.getInt("id")!!
        var deckName = arguments?.getString("deckName")
        getcards(deckId)
        binding.deckName.text = deckName + " Deck"
        customCardsAdapter = CustomCardsAdapter(getcards(deckId), openDetail = ::openDetail)

        binding.rvCards.adapter = customCardsAdapter
        return binding.root

    }

    private fun getcards(id: Int): List<String> {
        var allSpecificCards: List<String> = ArrayList()

        allSpecificCards = runBlocking {

            pokemonDatabase.specificCardsList().getAllSpecificCards(id)
        }

        return allSpecificCards

    }

    private fun openDetail(str: String) {
        var alertDialog: AlertDialog
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        val view2 = layoutInflater.inflate(

            R.layout.pop_card_image,
            null
        )

        val imageViewCard = view2.findViewById<ImageView>(R.id.imageViewCardImage)
        val cancelBtn = view2.findViewById<ImageButton>(R.id.btnCancel)
        builder.setView(view2)
        alertDialog = builder.create()
        alertDialog.show()
        Glide.with(requireContext()).load(str).into(imageViewCard)
        cancelBtn.setOnClickListener { alertDialog.dismiss() }
    }


    companion object {


        @JvmStatic
        fun newInstance(id: Int, deckName: String) =
            DeckCardsFragment().apply {
                arguments = Bundle().apply {
                    putInt("id", id)
                    putString("deckName", deckName)

                }
            }
    }
}