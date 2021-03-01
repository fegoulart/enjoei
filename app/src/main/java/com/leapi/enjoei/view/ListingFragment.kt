package com.leapi.enjoei.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.leapi.enjoei.viewModel.ListingViewModel
import com.leapi.enjoei.R
import com.leapi.enjoei.adapter.ListingAdapter
import com.leapi.enjoei.databinding.ListingFragmentBinding
import com.leapi.enjoei.di.adapter.ListingAdapterModule
import com.leapi.enjoei.di.view.DaggerListingFragmentComponent
import com.leapi.enjoei.model.SearchEdge
import com.leapi.enjoei.model.SearchNode
import javax.inject.Inject

class ListingFragment : Fragment(), ListingAdClickListener {

    private var _binding: ListingFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ListingViewModel

    private var injected = false
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private lateinit var progressBar: ProgressBar
    private lateinit var adsRecyclerView: RecyclerView


    @Inject
    lateinit var adsRecyclerViewAdapter: ListingAdapter

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

        bindVisualComponents()
        inject()
        setupAdsRecyclerView()
        setupViewModel()
        observeViewModel()

        viewModel.getStarWarsProducts()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun bindVisualComponents() {
        toolbar = binding.listingToolbar
        progressBar = binding.progressBar
        adsRecyclerView = binding.listingRecyclerView
    }

    private fun inject() {
        if (!injected) {
            DaggerListingFragmentComponent.builder()
                .listingAdapterModule(ListingAdapterModule(arrayListOf(),this))
                .build()
                .inject(this)
            injected = true
        }
    }

    private fun setupAdsRecyclerView() {
        adsRecyclerView.apply {
            layoutManager = GridLayoutManager(this.context,2)
            adapter = adsRecyclerViewAdapter
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(ListingViewModel::class.java)
    }


    private fun observeViewModel() {

        viewModel.listingAds.observe(
            this.viewLifecycleOwner, { listingAds ->
                listingAds?.let {
                    if (listingAds.isNotEmpty()) {
                        adsRecyclerViewAdapter.updateAds(listingAds)
                    }
                }
            })


        viewModel.loading.observe(
            this.viewLifecycleOwner, { loading ->
                loading?.let {
                    if (loading) {
                        progressBar.visibility = View.VISIBLE
                    } else {
                        progressBar.visibility = View.GONE
                    }
                }
            })

        viewModel.errorMessage.observe(
            this.viewLifecycleOwner, { errorMessage ->
                errorMessage?.let {
                    Toast.makeText(this.context, "Error fetching data", Toast.LENGTH_SHORT).show()
                }
            })
    }


    companion object {
        fun newInstance() = ListingFragment()
    }

    override fun onAdItemClicked(adItem: SearchEdge) {
        val edgeBundle = Bundle()
        edgeBundle.putParcelable("selectedEdge", adItem)
        Navigation.findNavController(this.requireView())
            .navigate(
                R.id.action_listingFragment_to_adDetailFragment,
                edgeBundle,
                NavOptions.Builder()
                    .setPopUpTo(
                        R.id.adDetailFragment,
                        true
                    ).build()
            )
    }

}