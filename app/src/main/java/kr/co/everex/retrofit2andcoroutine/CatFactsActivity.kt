package kr.co.everex.retrofit2andcoroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kr.co.everex.retrofit2andcoroutine.`interface`.ApiRequests
import kr.co.everex.retrofit2andcoroutine.databinding.ActivityCatFactsBinding
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory


/**
 * 상수 데이터
 */
const val BASE_URL = "https://cat-fact.herokuapp.com"
const val TAG = "CatFactsActivity"


class CatFactsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCatFactsBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCatFactsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view) // 뷰 바인딩 적용 완료

        getCurrentData()
        binding.layoutGenerateNewFact.setOnClickListener{
            getCurrentData()
        }
    }// onCreate 끝


    private fun getCurrentData() {
        binding.tvTextView.visibility = View.GONE
        binding.tvTimeStamp.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE


        // Retrofit.Builder 생성
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiRequests::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = api.getCatFacts().awaitResponse()
                if (response.isSuccessful) {

                    val data = response.body()!!
                    Log.e(TAG, data.toString())

                    withContext(Dispatchers.Main) {
                        binding.tvTextView.visibility = View.VISIBLE
                        binding.tvTimeStamp.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        binding.tvTimeStamp.text = data.createdAt
                        binding.tvTextView.text = data.text
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main){
                    Toast.makeText(
                        applicationContext,
                        "Seems like something went wrong...",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    }

}