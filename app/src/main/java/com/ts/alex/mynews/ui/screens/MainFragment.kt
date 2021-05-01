package com.ts.alex.mynews.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.ts.alex.mynews.databinding.FragmentMainBinding
import com.ts.alex.mynews.ui.MainViewModel
import com.ts.alex.mynews.ui.dialogs.MenuDialog
import com.ts.alex.mynews.ui.util.CountryDomain
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MainFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentMainBinding
    private lateinit var navController: NavController
    private val viewModel by sharedViewModel<MainViewModel> ()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(requireView())
        setUpClickListener()
        viewModel.positionScroll = 0
        viewModel.cleanListArticle()
    }

    private fun setUpClickListener() {
        binding.vFrance.setOnClickListener(this)
        binding.vRussia.setOnClickListener(this)
        binding.vUSA.setOnClickListener(this)
        binding.vMenu.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.vFrance -> {
                val action =
                    MainFragmentDirections.actionMainFragmentToListNewsFragment(CountryDomain.FRANCE)
                navController.navigate(action)
            }
            binding.vRussia -> {
                val action =
                    MainFragmentDirections.actionMainFragmentToListNewsFragment(CountryDomain.RUSSIA)
                navController.navigate(action)
            }
            binding.vUSA -> {
                val action =
                    MainFragmentDirections.actionMainFragmentToListNewsFragment(CountryDomain.USA)
                navController.navigate(action)
            }
            binding.vMenu ->{
                MenuDialog().show(requireActivity().supportFragmentManager, "Menu_Dialog")
            }
        }
    }
}