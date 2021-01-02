package com.example.football.ui.table

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.football.R
import com.example.football.databinding.FragmentTableBinding


class TableFragment : Fragment() {

    /**
     * init viewModel and dataBinding
     */
    private lateinit var viewModel: TableViewModel
    private lateinit var binding: FragmentTableBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_table, container, false)

        //vm
        val activity = requireNotNull(this.activity)
        viewModel = ViewModelProvider(this, TableViewModel.TableFactory(activity.application))
            .get(TableViewModel::class.java)

        //connect xml layout to viewModel
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        //init leagueTable Adapter
        // use Recycler View Adapter and bind the adapter
        val adapter = LeagueTableAdapter()
        binding.tableRecyclerView.adapter = adapter

        //observe the response from server and set the returned data in the adapter to display
        viewModel.databaseLeagueTable.observe(viewLifecycleOwner, Observer { table ->
            if (table.isEmpty()) {
                showAnimation(R.raw.loading_yellow)
            } else {
                table.apply {
                    adapter.submitList(table)
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