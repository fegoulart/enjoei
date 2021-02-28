package com.leapi.enjoei.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.leapi.enjoei.viewModel.ListingViewModel
import com.leapi.enjoei.R
import com.leapi.enjoei.databinding.ListingFragmentBinding

class ListingFragment : Fragment() {

    private var _binding: ListingFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ListingViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ListingFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        setupViewModel()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupToolbar() {
        val toolbar = binding.listingToolbar
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.listingFragment
//            )
//        )
//        val navHostFragment = NavHostFragment.findNavController(this);
//        NavigationUI.setupWithNavController(toolbar, navHostFragment, appBarConfiguration)
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(ListingViewModel::class.java)
    }

    companion object {
        fun newInstance() = ListingFragment()
    }

}