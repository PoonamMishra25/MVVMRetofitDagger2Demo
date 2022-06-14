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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
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



private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class CardDetailsPokemon : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    var url: String = ""
var position:Int=0
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
        position = arguments?.getInt("pos")!!
        Glide.with(binding.oneCardDetails).load(url).into(binding.oneCardDetails)

        setUpViews(position)

        binding.floatingActionButton2.setOnClickListener {


            var alertDialog: AlertDialog
            val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
            val view1 = layoutInflater.inflate(R.layout.decks_list, null)

            val swipeRefreshLayout:SwipeRefreshLayout = view1.findViewById(R.id.swipe_deckPop)
            val btn_add: Button = view1.findViewById(R.id.btn_add)
            val btnCancel = view1.findViewById<ImageButton>(R.id.btn_cancel1)
            val listView: ListView = view1.findViewById(R.id.lv_deckPlayList)
            builder.setView(view1)
            alertDialog = builder.create()
            alertDialog.show()
            populateListView(listView)
            btnCancel.setOnClickListener { alertDialog.dismiss() }
            swipeRefreshLayout.setOnRefreshListener {
                populateListView(listView)
            }


                listView.setOnItemClickListener { adapterView, view, i, l ->

                    CoroutineScope(Dispatchers.Main).launch {
                        pokemonDatabase.specificCardsList().addCards(
                            SavedCardModel(
                                url,
                                pokemonDatabase.deckListDao()
                                    .getIds(adapterView.getItemAtPosition(i).toString())
                            )
                        )

                        val view3 = layoutInflater.inflate(R.layout.tick, null)
                        val tickImage: ImageView = view3.findViewById(R.id.iv_tick)

                        builder.setView(view3)
                        val alertDialog1: AlertDialog = builder.create()
                        alertDialog1.show()
                        Glide.with(requireContext()).load(R.drawable.tick).into(tickImage)
                        delay(2000)
                        alertDialog1.dismiss()




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
                            val view3 = layoutInflater.inflate(R.layout.tick, null)
                            val tickImage: ImageView = view3.findViewById(R.id.iv_tick)

                            builder.setView(view3)
                            val alertDialog1: AlertDialog = builder.create()
                            alertDialog1.show()
                            Glide.with(requireContext()).load(R.drawable.tick).into(tickImage)
                          //  makeToast("List is Created Successfully")
                            delay(2000)
                            alertDialog.dismiss()
                            alertDialog1.dismiss()

                        }

                    }
                }
            }

        }
        return binding.root
    }

    private fun populateListView(listView: ListView) {
        mainViewModel.deckListLiveData.observe(viewLifecycleOwner, Observer {

            listAdapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, it)
            listView.adapter = listAdapter

        })
    }

    private fun setUpViews(pos:Int){
    mainViewModel.pokemonCardLiveData.observe(viewLifecycleOwner, Observer {
        binding.tvCardAttacks.text= it.data[pos].attacks[0].name
        binding.tvCardDamage.text= it.data[pos].attacks[0].damage
       val rule =it.data[pos].attacks[0].text
        if(rule.isNullOrBlank()){
            binding.card1.visibility=View.GONE
        }else{
            binding.tvCardText.text=rule

        }

        binding.tvCardHP.text = it.data[pos].hp
        binding.tvCardName.text = it.data[pos].name
        val type =it.data[pos].types.toString()
        binding.tvCardType.text = type.substring(1,type.length-1)
        binding.tvCardWeakness.text = it.data[pos].weaknesses[0].type
        val desc =it.data[pos].flavorText
        if(desc.isNullOrBlank()) {
            binding.card2.visibility=View.GONE
        }else{
            binding.tvCardFlavourText.text = desc

        }
        val evolveFrom = it.data[pos].evolvesFrom
        if(evolveFrom.isNullOrBlank()){
            binding.tvEvolveFrom.visibility=View.GONE
            binding.labelEvolve.visibility = View.GONE
        }else{

            binding.tvEvolveFrom.text=evolveFrom
        }

    })
}




    companion object {

        @JvmStatic
        fun newInstance(param1: String,position:Int) =
            CardDetailsPokemon().apply {
                arguments = Bundle().apply {
                    putString("ImageUrl", param1)
                putInt("pos",position)
                }
            }
    }
}