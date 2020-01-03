package rdc.move_test.propertyapi

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import rdc.move_test.BuildConfig
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface JSONPropertyAPI {

    @GET("properties?limit=10&city=Napa&state_code=CA&sort=relevance&client_id=rdc_mobile_native")

    fun getProperties(): Deferred<Response<PropertyResponse>>


    companion object {
        private const val BASE_URL = "https://mapi-ng.rdc.moveaws.com/api/v1/"
        fun getApi(): JSONPropertyAPI {
            return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .client(getClient())
                    .build()
                    .create(JSONPropertyAPI::class.java)
        }

        private fun getClient(): OkHttpClient {
            val client = OkHttpClient.Builder()
            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                client.addInterceptor(logging)
            }
            return client.build()
        }
    }
}