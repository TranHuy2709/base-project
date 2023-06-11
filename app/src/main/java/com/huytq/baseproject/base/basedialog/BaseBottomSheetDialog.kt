package com.huytq.baseproject.base.basedialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetDialog<V: ViewDataBinding>: BottomSheetDialogFragment() {

    protected lateinit var binding: V
    abstract val layoutId: Int
    protected var curOwnerId:Int=0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    fun setDialogCurOwnerId(ownerId:Int){
        this.curOwnerId= ownerId
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    abstract fun initView()

    override fun onDestroy() {
        super.onDestroy()
        binding.unbind()
    }

}