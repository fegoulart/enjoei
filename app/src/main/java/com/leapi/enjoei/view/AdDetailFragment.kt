package com.leapi.enjoei.view

import android.content.res.Resources
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.NavHostFragment
import com.bumptech.glide.Glide
import com.leapi.enjoei.R
import com.leapi.enjoei.databinding.AdDetailFragmentBinding
import com.leapi.enjoei.di.util.fromHtml
import com.leapi.enjoei.di.util.toBrazilianCurrency
import com.leapi.enjoei.model.AdPricingData
import com.leapi.enjoei.model.AdditionalAdData
import com.leapi.enjoei.model.BasicAdData
import com.leapi.enjoei.model.StoreData
import com.leapi.enjoei.viewModel.AdDetailViewModel

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
    lateinit var priceTextView: TextView
    lateinit var priceConditionsTextView: TextView
    lateinit var availableItemsTextView: TextView
    lateinit var soldItemsTextView: TextView
    lateinit var storeAvatarImageView: ImageView
    lateinit var adPhoto: ImageView

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
        priceTextView = binding.priceTextview
        priceConditionsTextView = binding.priceCondition
        soldItemsTextView = binding.soldCount
        availableItemsTextView = binding.availableCount
        storeAvatarImageView = binding.storeLogo
        adPhoto = binding.adImage
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
                    viewModel.getPricingInformation()
                    viewModel.getStoreCounters()
                }
            })

        viewModel.additionalAdData.observe(
            this.viewLifecycleOwner, { additionalData ->
                additionalData?.let {
                    updateAdditionalDataUI(it)
                }
            }
        )

        viewModel.pricingAdData.observe(
            this.viewLifecycleOwner, { pricingData ->
                pricingData?.let {
                    updatePricingDataUI(it)
                }
            }
        )

        viewModel.storeCounters.observe(
            this.viewLifecycleOwner, { counters ->
                counters?.let {
                    updateStoreCountersUI(it)
                }
            }
        )
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(AdDetailViewModel::class.java)
    }

    private fun updateBasicDataUI(basicAdData: BasicAdData) {
        adTitleTextView.text = basicAdData.adTitle
        storeNameTextView.text = basicAdData.storeName
        if (basicAdData.brand.isNotEmpty()) {
            brandTextView.text = basicAdData.brand
        } else {
            brandTextView.text = "-"
        }

        val avatarUrl = "https://photos.enjoei.com.br/public/75x75/${basicAdData.storeAvatar}.jpg"

        Glide.with(this)
            .load(avatarUrl)
            .circleCrop()
            .into(storeAvatarImageView)

    }

    private fun updateAdditionalDataUI(additionalAdData: AdditionalAdData) {
        adDescriptionTextView.text = additionalAdData.description.fromHtml()
        when (additionalAdData.used) {
            true -> productConditionTextView.text = getString(R.string.usedProduct)
            false -> productConditionTextView.text = getString(R.string.newProduct)
        }

        val screenWidth = Resources.getSystem().displayMetrics.widthPixels

        val imageSize: String = if (screenWidth <= 240) {
            "240x240"
        } else {
            if (screenWidth <= 460) {
                "460x460"
            } else {
                if (screenWidth <= 500) {
                    "500x500"
                } else {
                    "1200x1200"
                }
            }
        }

        val imageUrl =
            "https://photos.enjoei.com.br/public/${imageSize}/${additionalAdData.photos[0]}.jpg"

        Glide.with(this)
            .load(imageUrl)
            .centerCrop()
            .into(adPhoto)
    }

    private fun updatePricingDataUI(pricingData: AdPricingData) {
        if (pricingData.regular_price.values.price.sale > 0) {
            val salePrice = pricingData.regular_price.values.price.sale.toBrazilianCurrency()
            priceTextView.text = salePrice
        } else {
            val listedPrice = pricingData.regular_price.values.price.listed.toBrazilianCurrency()
            priceTextView.text = listedPrice
        }
        priceConditionsTextView.text = pricingData.regular_price.values.price_subtitle
    }

    private fun updateStoreCountersUI(storeData: StoreData) {
        soldItemsTextView.text = storeData.counters.sold_products.toString()
        availableItemsTextView.text = storeData.counters.available_products.toString()
    }

    companion object {
        fun newInstance() = AdDetailFragment()
    }

}