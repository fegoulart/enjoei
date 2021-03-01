package com.leapi.enjoei.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.fragment.NavHostFragment
import com.leapi.enjoei.R
import com.leapi.enjoei.databinding.AdDetailFragmentBinding
import com.leapi.enjoei.databinding.ListingFragmentBinding
import com.leapi.enjoei.model.AdditionalAdData
import com.leapi.enjoei.model.BasicAdData
import com.leapi.enjoei.model.SearchEdge
import com.leapi.enjoei.viewModel.AdDetailViewModel
import com.leapi.enjoei.viewModel.ListingViewModel

class AdDetailFragment : Fragment() {

    private var _binding: AdDetailFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AdDetailViewModel

    lateinit var backButton: ImageButton
    lateinit var adTitleTextView: TextView
    lateinit var storeNameTextView: TextView
    lateinit var adDescriptionTextView: TextView
    lateinit var productConditionTextView: TextView
    lateinit var brandTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AdDetailFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindVisualComponents()
        setupBackButton()
        setupViewModel()
        observeViewModel()

        arguments?.let {
            val basicAdDataFromListing = AdDetailFragmentArgs.fromBundle(it).selectedEdge
            viewModel.setBasicAdData(basicAdDataFromListing)
        }

    }

    private fun bindVisualComponents() {
        backButton = binding.backIcon
        adTitleTextView = binding.title
        storeNameTextView = binding.storeName
        adDescriptionTextView = binding.description
        productConditionTextView = binding.condition
        brandTextView = binding.brand
    }

    private fun setupBackButton() {
        backButton.setOnClickListener {
            NavHostFragment.findNavController(this).navigateUp()
        }
    }

    private fun observeViewModel() {

        viewModel.basicAdData.observe(
            this.viewLifecycleOwner, { basicAdData ->
                basicAdData?.let {
                    updateBasicDataUI(it)
                    viewModel.getAdditionalData()
                }
            })

        viewModel.additionalAdData.observe(
            this.viewLifecycleOwner, { additionalData ->
                additionalData?.let {
                    updateAdditionalDataUI(it)
                }
            }
        )
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(AdDetailViewModel::class.java)
    }

    private fun updateBasicDataUI(basicAdData: BasicAdData) {
        adTitleTextView.text  = basicAdData.adTitle
        storeNameTextView.text = basicAdData.storeName
        if (basicAdData.brand.isNotEmpty()) {
            brandTextView.text = basicAdData.brand
        } else {
            brandTextView.text = "-"
        }

    }

    private fun updateAdditionalDataUI(additionalAdData: AdditionalAdData) {
        adDescriptionTextView.text = additionalAdData.description
        when (additionalAdData.used) {
            true -> productConditionTextView.text = getString(R.string.usedProduct)
            false -> productConditionTextView.text = getString(R.string.newProduct)
        }
    }


    companion object {
        fun newInstance() = AdDetailFragment()
    }

}