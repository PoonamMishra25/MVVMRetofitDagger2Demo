package com.example.mvvmretofitdagger2demo

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.mvvmretofitdagger2demo.databinding.FragmentDeckBinding
import com.example.mvvmretofitdagger2demo.db.PokemonDatabase
import com.example.mvvmretofitdagger2demo.model.DeckListModel
import com.example.mvvmretofitdagger2demo.viewmodel.MainViewModel
import com.example.mvvmretofitdagger2demo.viewmodel.MainViewModelFactory
import kotlinx.coroutines.*
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DeckFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DeckFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentDeckBinding? = null
    private val binding: FragmentDeckBinding get() = _binding!!
    lateinit var listAdapter: ArrayAdapter<String>

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

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
        _binding = FragmentDeckBinding.inflate(layoutInflater)
        val application = requireActivity().application as PokemonApplication
        (application).applicationComponent.injectDeckFragment(this)

        mainViewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)
        listItems()
        binding.swipe.setOnRefreshListener {
            listItems()
        }

        createNewDeck()
        //Listview clickListner
        binding.deckLists.setOnItemClickListener { adapterView, view, i, l ->

                val pokeid = runBlocking {
                     pokemonDatabase.deckListDao().getIds(adapterView.getItemAtPosition(i).toString())

                }

            parentFragmentManager.beginTransaction()
                .replace(com.example.mvvmretofitdagger2demo.R.id.containerView, DeckCardsFragment.
                    newInstance(pokeid,adapterView.getItemAtPosition(i).toString()))
                .addToBackStack(null)
                .commit()
        }
        return binding.root
    }
//populating listview
    private fun listItems() {
        mainViewModel.deckListLiveData.observe(viewLifecycleOwner, Observer {
            if (it.size > 0) {
                listAdapter =
                    ArrayAdapter(requireContext(), R.layout.simple_list_item_1, it)
                binding.deckLists.adapter = listAdapter
            } else {
                binding.tvInfo.apply {
                    visibility = View.VISIBLE
                    text = "No deck Found! Please create new.."
                }
            }

        })
    }
//Create a dialog to create new Deck
    private fun createNewDeck() {
        binding.btnNewDeck.setOnClickListener {
            val alertDialog: AlertDialog
            val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
            val view2 = layoutInflater.inflate(
                com.example.mvvmretofitdagger2demo.R.layout.popup_lists,
                null
            )

            val editText: EditText =
                view2.findViewById(com.example.mvvmretofitdagger2demo.R.id.editTextTextPersonName)

            val btn_cancel: Button =
                view2.findViewById(com.example.mvvmretofitdagger2demo.R.id.button2)
            val btn_Create: Button =
                view2.findViewById(com.example.mvvmretofitdagger2demo.R.id.button)
            builder.setView(view2)
            alertDialog = builder.create()
            alertDialog.show()
            btn_cancel.setOnClickListener { alertDialog.dismiss() }
            btn_Create.setOnClickListener {
                CoroutineScope(Dispatchers.Main).launch {

                    if (editText.text.isNotEmpty()) {

                        pokemonDatabase.deckListDao()
                            .addDeckLists(DeckListModel(editText.text.toString()))
                        var alertDialog1 = alertDialog
                        val view3 = layoutInflater.inflate(
                            com.example.mvvmretofitdagger2demo.R.layout.tick,
                            null
                        )
                        val tickImage: ImageView =
                            view3.findViewById(com.example.mvvmretofitdagger2demo.R.id.iv_tick)

                        builder.setView(view3)
                        alertDialog1 = builder.create()
                        alertDialog1.show()
                        Glide.with(requireContext())
                            .load(com.example.mvvmretofitdagger2demo.R.drawable.tick)
                            .into(tickImage)

                        delay(2000)
                        alertDialog.dismiss()
                        alertDialog1.dismiss()

                    }

                }
            }
        }
    }

    companion object {
        var deckListId:Int =1

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DeckFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}