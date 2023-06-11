package com.huytq.baseproject.view.samplefragment

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.huytq.baseproject.R
import com.huytq.baseproject.base.basefragment.BaseFragment
import com.huytq.baseproject.databinding.FragmentSampleBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SampleFragment: BaseFragment<FragmentSampleBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_sample
    private val viewModel: SampleFragmentViewModel by viewModels()
    override fun initView() {
        viewModel.showToast(requireContext())
    }
}