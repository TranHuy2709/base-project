package com.huytq.baseproject.base.basefragment

import androidx.databinding.ViewDataBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.huytq.baseproject.base.utils.SharedPreferenceUtils

abstract class BaseFragment<V : ViewDataBinding> : Fragment() {
    protected var dataPref: SharedPreferenceUtils? = null
    protected lateinit var binding: V
    protected abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataPref = SharedPreferenceUtils.getInstance(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        initView()
        return binding.root
    }

    protected abstract fun initView()

    override fun onDestroy() {
        super.onDestroy()
        binding.unbind()
    }
}