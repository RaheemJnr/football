package com.example.football.ui.live

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.football.R
import com.example.football.databinding.FragmentLiveScoreBinding


class LiveScore : Fragment() {

    private lateinit var viewModel: LiveViewModel
    private lateinit var binding: FragmentLiveScoreBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_live_score, container, false)

        //vm
        viewModel = ViewModelProvider(this).get(LiveViewModel::class.java)

        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        val adapter = LiveScoreAdapter()
        binding.liveScoreList.adapter = adapter

        //observe the response from server and set the returned data in the adapter to display
        viewModel.run {
            binding.liveScoreList.adapter = adapter

            //observe the response from server and set the returned data in the adapter to display
            liveScore.observe(viewLifecycleOwner, Observer { live ->
                live?.apply {
                    adapter.submitList(live)
                }
            })

            // observing loading state
            status.observe(viewLifecycleOwner, Observer {
                val statusImage = binding.loadingAnim
                when (it) {
                    NetworkState.LOADING -> showLoading()
                    NetworkState.FAILURE -> showError()
                    NetworkState.SUCCESS -> {
                        statusImage.visibility = View.GONE

                    }
                }
            })
        }
        return binding.root
    }

    //show load state error
    private fun showError() {
        showAnimation(R.raw.error_anim)
        binding.retryButton.visibility = View.VISIBLE
    }

    //show load state loading
    private fun showLoading(): Boolean {
        showAnimation(R.raw.loading_yellow)
        binding.retryButton.visibility = View.GONE
        return true
    }

    //method to display the animation
    private fun showAnimation(animRes: Int) {
        val statusLoading = binding.loadingAnim
        statusLoading.visibility = View.VISIBLE
        statusLoading.setAnimation(animRes)
        statusLoading.playAnimation()
    }
}