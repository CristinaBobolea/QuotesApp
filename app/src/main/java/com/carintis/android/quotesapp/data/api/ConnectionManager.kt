package com.carintis.android.quotesapp.data.api

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object ConnectionManager {

  private var context: Context? = null

  fun setContext(newContext: Context) {
    context = newContext
  }

  fun isConnected(): Boolean {
    val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
    return activeNetwork?.isConnectedOrConnecting == true
  }
}