package com.huytq.baseproject.data.repositoryimpl

import android.content.Context
import android.widget.Toast
import com.huytq.baseproject.data.remote.api.ApiService
import com.huytq.baseproject.domain.repository.SampleRepository
import javax.inject.Inject

class SampleRepositoryImpl @Inject constructor(private val apiService: ApiService) :SampleRepository {

    override fun showToast(context: Context) {
        Toast.makeText(context, "api service instance $apiService", Toast.LENGTH_SHORT).show()
    }
}