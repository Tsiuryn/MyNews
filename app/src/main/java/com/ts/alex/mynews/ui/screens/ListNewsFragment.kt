package com.ts.alex.mynews.ui.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ts.alex.mynews.databinding.ListNewsFragmentBinding
import com.ts.alex.mynews.domain.entity.news.Article
import com.ts.alex.mynews.ui.MainViewModel
import com.ts.alex.mynews.ui.adapters.ListNewsAdapter
import com.ts.alex.mynews.ui.util.isNetworkAvailable
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ListNewsFragment : Fragment() {

    private val viewModel by sharedViewModel<MainViewModel>()
    private lateinit var binding: ListNewsFragmentBinding
    private val args: ListNewsFragmentArgs by navArgs()
    private lateinit var navController: NavController

    private lateinit var adapter: ListNewsAdapter
    private lateinit var recycler: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ListNewsFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        recycler = binding.vRecycler
        observe()
        if(isNetworkAvailable(requireContext())){
            viewModel.getNewsByCountry(args.countryDomain)
        }else{
            viewModel.getNewsFromDB(args.countryDomain)
        }
    }

    private fun observe() {
        viewModel.apply {
            newsByCountry.observe({ lifecycle }, { listArticles ->
                if (listArticles != null && listArticles.isNotEmpty()) {
                    setUpRecycler(listArticles)
                }
            })

            error.observe({ lifecycle }, { message ->
                Toast.makeText(requireContext(), "Error: $message", Toast.LENGTH_LONG).show()
            })
        }
    }

    private fun setUpRecycler(list: List<Article>) {
        val llm = LinearLayoutManager(context)
        recycler.layoutManager = llm
        llm.scrollToPosition(viewModel.positionScroll)


        adapter = ListNewsAdapter(list) { article ->
            viewModel.setArticle(article)
            val action = ListNewsFragmentDirections.actionListNewsFragmentToNewsFragment()
            navController.navigate(action)

            viewModel.positionScroll = llm.findFirstVisibleItemPosition()
        }
        recycler.adapter = adapter
    }
}