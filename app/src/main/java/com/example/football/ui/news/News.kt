package com.example.football.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.football.R
import com.example.football.databinding.NewsFragmentBinding

class News : Fragment() {


    private lateinit var viewModel: NewsViewModel
    private lateinit var binding: NewsFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        //binding
        binding = DataBindingUtil.inflate(inflater, R.layout.news_fragment, container, false)

        //vm
        val activity = requireNotNull(this.activity)
        viewModel = ViewModelProvider(this, NewsViewModel.NewsFactory(activity.application))
            .get(NewsViewModel::class.java)

        //connect xml layout to viewModel
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        //init leagueTable Adapter
        // use Recycler View Adapter and bind the adapter
        val adapter = NewsAdapter()
        binding.newList.adapter = adapter


        //observe the response from server and set the returned data in the adapter to display
        viewModel.news.observe(viewLifecycleOwner, Observer { news ->
            if (news.isEmpty()) {
                showAnimation(R.raw.loading_yellow)
            } else {
                news?.apply {
                    adapter.submitList(news)
                }
                binding.loadingAnim.visibility = View.GONE
            }
        })



        return binding.root
    }

    //method to display the animation
    private fun showAnimation(animRes: Int) {
        val statusLoading = binding.loadingAnim
        statusLoading.visibility = View.VISIBLE
        statusLoading.setAnimation(animRes)
        statusLoading.playAnimation()
    }
}