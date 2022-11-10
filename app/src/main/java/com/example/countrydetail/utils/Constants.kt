package com.example.countrydetail.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

object Constants {
    const val BASE_URL: String = "https://restcountries.com/v3.1/all"

    fun isNetworkAvailable(context: Context): Boolean{
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ->true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ->true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ->true
                else -> return false
            }
        }else{
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo !=null && networkInfo.isConnectedOrConnecting
        }
    }
}