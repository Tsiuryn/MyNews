package com.ts.alex.mynews.ui.screens

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.ts.alex.mynews.databinding.FragmentNewsBinding
import com.ts.alex.mynews.domain.entity.news.Article
import com.ts.alex.mynews.ui.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding
    private lateinit var navController: NavController
    private val viewModel by sharedViewModel<MainViewModel> ()
    private var article: Article? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        article = viewModel.getArticle()
        setUpFragment()
        setUpButtonClick()
    }

    private fun setUpButtonClick() {
        binding.vWebView.setOnClickListener{
            if(article != null ){
                val action = NewsFragmentDirections.actionNewsFragmentToWebViewFragment(article?.url?: "")
                navController.navigate(action)
            }
        }
        binding.vBrowser.setOnClickListener{
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(article?.url))
            startActivity(browserIntent)
        }
    }

    private fun setUpFragment() {
        if(article != null){
            binding.vTitle.text = article!!.title
            Glide
                .with(requireContext())
                .load(article!!.urlToImage)
                .transform(RoundedCorners(100))
                .into(binding.vImage)
            binding.vDescription.text = article!!.description
        }
    }
}