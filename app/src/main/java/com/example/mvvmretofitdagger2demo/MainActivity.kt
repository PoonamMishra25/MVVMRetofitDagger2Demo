package com.example.mvvmretofitdagger2demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.mvvmretofitdagger2demo.model.PokemonDetailModel
import com.example.mvvmretofitdagger2demo.model.PokemonDetailModelItem
import com.example.mvvmretofitdagger2demo.viewmodel.MainViewModel
import com.example.mvvmretofitdagger2demo.viewmodel.MainViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
lateinit var list:ArrayList<String>
    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    private val textview:TextView
    get() = findViewById(R.id.textview)
lateinit var imageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageView=findViewById(R.id.imageView)
        list= ArrayList()
        (application as PokemonApplication).applicationComponent.inject(this)

        mainViewModel=ViewModelProvider(this,mainViewModelFactory).get(MainViewModel::class.java)
        mainViewModel.pokemonLiveData.observe(this, Observer {
          list.addAll(it.types)

            val commaSeperatedString =list.joinToString { it -> "\'${it}\'" }

            textview.text="Name- "+it.name+"\n"+"Type-  "+commaSeperatedString+"\n"+
                    "Description- "+it.description

            Glide.with(this).load(it.sprite).into(imageView)
        })
       // println("***"+list.toString())
    }
}