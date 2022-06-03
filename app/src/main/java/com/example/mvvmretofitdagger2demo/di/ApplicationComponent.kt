package com.example.mvvmretofitdagger2demo.di

import android.content.Context
import com.example.mvvmretofitdagger2demo.DetailsOfPokemon
import com.example.mvvmretofitdagger2demo.ListOfPokemons
import com.example.mvvmretofitdagger2demo.MainActivity
import com.example.mvvmretofitdagger2demo.databinding.FragmentListOfPokemonsBinding
import com.example.mvvmretofitdagger2demo.viewmodel.MainViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DatabaseModule::class])

interface ApplicationComponent {

   fun inject(mainActivity: MainActivity)
   fun injectViewModel(fragment: ListOfPokemons)
   fun injectDetaileFragment(fragment:DetailsOfPokemon)
   @Component.Factory
   interface Factory{
      fun create(@BindsInstance context: Context):ApplicationComponent
   }
}