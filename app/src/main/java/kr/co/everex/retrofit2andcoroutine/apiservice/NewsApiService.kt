package kr.co.everex.retrofit2andcoroutine.apiservice

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kr.co.everex.retrofit2andcoroutine.`interface`.NewsApiInterface
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Singleton Pattern 사용.
API 요청 시 interceptor 변수가 파라미터 쿼리로 API_KEY 를 추가해서 보냄.
.addCallAdapterFactory(CoroutineCallAdapterFactory()) 는
Deferred<Response<LatestNews>> 리턴타입을 사용하기 위함.
 */
object NewsApiService {
    //creating a Network Interceptor to add api_key in all the request as authInterceptor
    private val interceptor = Interceptor { chain ->
        val url = chain.request().url.newBuilder().addQueryParameter("apiKey", "API_KEY").build()
        val request = chain.request()
            .newBuilder()
            .url(url)
            .build()
        chain.proceed(request)
    }

    // we are creating a networking client using OkHttp and add our authInterceptor.
    private val apiClient = OkHttpClient().newBuilder().addInterceptor(interceptor).build()

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().client(apiClient)
            .baseUrl("https://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
        /**
        .addCallAdapterFactory(CoroutineCallAdapterFactory()) 없이
        suspend 추가 시 Response<LatestNews> 로 사용 가능.
         */
    }

    val newsApi: NewsApiInterface = getRetrofit().create(NewsApiInterface::class.java)

}