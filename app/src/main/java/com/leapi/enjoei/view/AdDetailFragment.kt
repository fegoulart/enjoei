package com.leapi.enjoei.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.leapi.enjoei.R
import com.leapi.enjoei.viewModel.AdDetailViewModel

class AdDetailFragment : Fragment() {

    companion object {
        fun newInstance() = AdDetailFragment()
    }

    private lateinit var viewModel: AdDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.ad_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AdDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}