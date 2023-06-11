package com.huytq.baseproject.data.remote.utils

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ApiResponseUtils {

//    fun onResponseError(context: Context, errors: List<ErrorModel>?):String{
//        return if(errors?.isNotEmpty() ==true){
//            errors[0].message
//        }else{
//            context.resources.getString(R.string.error)
//        }
//    }
//
//    fun logOut(context: Context, dataPref: SharedPreferenceUtils, navController: NavController){
//        Toast.makeText(context, context.getString(R.string.expire_logout_message), Toast.LENGTH_SHORT).show()
//        navController.popBackStack(R.id.nav_home, inclusive = true)
//        navController.navigate(R.id.action_to_nav_login)
//        dataPref.putBooleanValue(Constants.LOGGED_IN, false)
//    }

    fun <T> retrofitCallBackResponse(
        requestResponse: MutableLiveData<DataResponse<T>>): Callback<ApiDataResponse<T>> {
        return object :Callback<ApiDataResponse<T>>{
            override fun onResponse(
                call: Call<ApiDataResponse<T>>,
                response: Response<ApiDataResponse<T>>
            ) {

                if (response.body()?.status == "success"){
                    requestResponse.value= DataResponse.DataSuccessResponse(response.body()!!.data)
                }else{
                    if(response.code() == 401){
                        requestResponse.value= DataResponse.DataExpireResponse()
                    }else{
                        requestResponse.value= DataResponse.DataErrorResponse(
                            Throwable(
//                            onResponseError(context, response.body()?.errors)
                            )
                        )
                    }
                }
            }

            override fun onFailure(call: Call<ApiDataResponse<T>>, t: Throwable) {
//                requestResponse.value= DataResponseStatus.DataErrorResponse(t)
            }

        }
    }
}