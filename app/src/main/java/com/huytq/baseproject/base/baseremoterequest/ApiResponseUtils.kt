package com.huytq.baseproject.base.baseremoterequest

import androidx.lifecycle.MutableLiveData
import com.huytq.baseproject.base.baseresponse.DataResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ApiResponseUtils {

    fun <T> retrofitCallBackResponse(
        requestResponse: MutableLiveData<DataResponse<T>>): Callback<T> {
        return object :Callback<T>{
            override fun onResponse(
                call: Call<T>,
                response: Response<T>
            ) {

                if (response.code() in 200..299){
                    requestResponse.value= DataResponse.DataSuccessResponse(response.body()!!)
                }else{
                    if(response.code() == 401){
                        requestResponse.value= DataResponse.DataExpireResponse()
                    }else{
                        requestResponse.value= DataResponse.DataErrorResponse(Throwable("Error"))
                    }
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                requestResponse.value= DataResponse.DataErrorResponse(t)
            }

        }
    }
}