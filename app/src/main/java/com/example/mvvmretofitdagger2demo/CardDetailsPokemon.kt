package com.example.mvvmretofitdagger2demo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.view.size
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.mvvmretofitdagger2demo.databinding.FragmentCardDetailsPokemonBinding
import com.example.mvvmretofitdagger2demo.db.PokemonDatabase
import com.example.mvvmretofitdagger2demo.model.DeckListModel
import com.example.mvvmretofitdagger2demo.model.SavedCardModel
import com.example.mvvmretofitdagger2demo.viewmodel.MainViewModel
import com.example.mvvmretofitdagger2demo.viewmodel.MainViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


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
    var url: String = ""

    lateinit var listAdapter: ArrayAdapter<String>

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    @Inject
    lateinit var pokemonDatabase: PokemonDatabase
    lateinit var mainViewModel: MainViewModel
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
        val application = requireActivity().application as PokemonApplication
        (application).applicationComponent.injectCardDetailFragment(this)

        mainViewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)
        url = arguments?.getString("ImageUrl")!!

        Glide.with(binding.oneCardDetails).load(url).into(binding.oneCardDetails)



        binding.floatingActionButton2.setOnClickListener {

            binding.progressBar.visibility = View.GONE
            var alertDialog: AlertDialog
            val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
            val view1 = layoutInflater.inflate(R.layout.decks_list, null)

            val btn_add: ImageButton = view1.findViewById(R.id.btn_add)

            val listView: ListView = view1.findViewById(R.id.lv_deckPlayList)
            builder.setView(view1)
            alertDialog = builder.create()
            alertDialog.show()
            mainViewModel.deckListLiveData.observe(viewLifecycleOwner, Observer {
                val list: ArrayList<String> = ArrayList()
                for (i in 0..(it.size - 1)) {
                    list.add(it[i].deckListName)
                }
                listAdapter =
                    ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, list)
                listView.adapter = listAdapter

            })
            if (listView.size < 0) {
                makeToast("No List Found!  Please create a new list")
            } else {
                listView.setOnItemClickListener { adapterView, view, i, l ->

                    CoroutineScope(Dispatchers.Main).launch {


                        pokemonDatabase.specificCardsList().addCards(
                            SavedCardModel(
                                url,
                                pokemonDatabase.deckListDao()
                                    .getIds(adapterView.getItemAtPosition(i).toString())
                            )
                        )
                        var alertDialog1 = alertDialog
                        val view3 = layoutInflater.inflate(R.layout.tick, null)
                        val tickImage: ImageView = view3.findViewById(R.id.iv_tick)

                        builder.setView(view3)
                        alertDialog1 = builder.create()
                        alertDialog1.show()
                        Glide.with(requireContext()).load(R.drawable.tick).into(tickImage)
                        delay(2000)
                        alertDialog1.dismiss()
                        makeToast("Card Added to your Deck!")
                    }


                }
            }

            btn_add.setOnClickListener {

                val view2 = layoutInflater.inflate(R.layout.popup_lists, null)

                val editText: EditText = view2.findViewById(R.id.editTextTextPersonName)

                val btn_cancel: Button = view2.findViewById(R.id.button2)
                val btn_Create: Button = view2.findViewById(R.id.button)
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
                            val view3 = layoutInflater.inflate(R.layout.tick, null)
                            val tickImage: ImageView = view3.findViewById(R.id.iv_tick)

                            builder.setView(view3)
                            alertDialog1 = builder.create()
                            alertDialog1.show()
                            Glide.with(requireContext()).load(R.drawable.tick).into(tickImage)
                            makeToast("List is Created Successfully")
                            delay(2000)
                            alertDialog.dismiss()
                            alertDialog1.dismiss()

                        }

                    }
                }
            }

        }
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun alertDialog(
        builder: AlertDialog.Builder,
        alertDialog: AlertDialog
    ) {
        var alertDialog1 = alertDialog
        val view3 = layoutInflater.inflate(R.layout.tick, null)
        val tickImage: ImageView = view3.findViewById(R.id.iv_tick)

        builder.setView(view3)
        alertDialog1 = builder.create()
        alertDialog1.show()
        Glide.with(requireContext()).load(R.drawable.tick).into(tickImage)

    }


    private fun makeToast(str: String) {
        Toast.makeText(requireContext(), str, Toast.LENGTH_LONG).show()
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