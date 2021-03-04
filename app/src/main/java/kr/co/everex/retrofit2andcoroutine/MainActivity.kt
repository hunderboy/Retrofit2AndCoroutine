package kr.co.everex.retrofit2andcoroutine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.everex.retrofit2andcoroutine.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view) // 뷰 바인딩 적용 완료

        binding.button1.setOnClickListener{
            val intent = Intent(this, CatFactsActivity::class.java)
            startActivity(intent)
        }
        binding.button2.setOnClickListener{
            val intent = Intent(this, ContactMyServerActivity::class.java)
            startActivity(intent)
        }

    }


}