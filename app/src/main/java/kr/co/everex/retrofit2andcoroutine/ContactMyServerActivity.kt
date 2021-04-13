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
import kr.co.everex.retrofit2andcoroutine.databinding.ActivityContactMyServerBinding
import kr.co.everex.retrofit2andcoroutine.databinding.ActivityMainBinding
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory


class ContactMyServerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactMyServerBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactMyServerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view) // 뷰 바인딩 적용 완료

        getCurrentData()
        binding.button1.setOnClickListener{
            getCurrentData()
        }

    }

    private fun getCurrentData() {


        // Retrofit.Builder 생성
        val api = Retrofit.Builder()
            .baseUrl(MY_SERVER_URL)
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
//                        binding.tvTimeStamp.text = data.createdAt
//                        binding.tvTextView.text = data.text
                    }
                }
//                else if (response.errorBody()){
//
//                }
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


    companion object {
        /*** 상수 데이터 */
        const val MY_SERVER_URL = "http://14.63.90.32:3000"
        const val TAG = "ContactMyServerActivity"
    }
}