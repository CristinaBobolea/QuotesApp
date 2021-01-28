/*
 * Copyright (c) 2020 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.carintis.android.quotesapp.data.api

import android.util.Log
import com.carintis.android.quotesapp.domain.Quote
import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

interface Api {

//  @GET("{numberOfImages}")
//  suspend fun getQuotesImages(@Path("numberOfImages") numberOfImages: Int): QuotesResponse

    @GET("?size=medium")
    suspend fun getQuote(@Query("maxR") numberOfQuotes : Int ): List<Quote>

    @GET("?size=medium")
    suspend fun getQuotes(@Query("maxR") numberOfQuotes : Int ) : List<Quote>

    @GET()
    suspend fun getFilteredQuotes(@Query("maxR") numberOfQuotes : Int, @Query("t") topic : String, @Query("size") size : String = "medium") : List<Quote>

    companion object {
    private const val TAG = "Api"

    fun create(): Api {
      val retrofit = Retrofit.Builder()
          .baseUrl(ApiConstants.API)
          .client(okHttpClient)
          .addConverterFactory(MoshiConverterFactory.create(moshi))
          .build()

      return retrofit.create(Api::class.java)
    }

        private val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    private val okHttpClient: OkHttpClient
      get() = OkHttpClient.Builder()
          .addInterceptor(httpLoggingInterceptor)
          .addInterceptor(
              Interceptor { chain ->
                  val builder = chain.request().newBuilder()
                  builder.header("x-rapidapi-key", ApiConstants.KEY)
                  builder.header("x-rapidapi-host", ApiConstants.HOST)
                  return@Interceptor chain.proceed(builder.build())
              }
          )
          .build()

    private val httpLoggingInterceptor: HttpLoggingInterceptor
      get() {
        val interceptor = HttpLoggingInterceptor { message -> Log.i(TAG, message) }

          interceptor.level = HttpLoggingInterceptor.Level.BASIC

        return interceptor
      }
  }
}