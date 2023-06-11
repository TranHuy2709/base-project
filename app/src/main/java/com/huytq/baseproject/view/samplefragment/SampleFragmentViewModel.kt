package com.huytq.baseproject.view.samplefragment

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.huytq.baseproject.domain.repository.SampleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SampleFragmentViewModel @Inject constructor(private val sampleRepository: SampleRepository) : ViewModel() {

        fun showToast(context: Context){
            sampleRepository.showToast(context)
        }
}