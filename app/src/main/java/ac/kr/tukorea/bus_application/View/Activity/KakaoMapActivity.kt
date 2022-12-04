package ac.kr.tukorea.bus_application.View.Activity

import ac.kr.tukorea.bus_application.databinding.ActivityKakaoMapBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class KakaoMapActivity : AppCompatActivity() {
    private lateinit var binding: ActivityKakaoMapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKakaoMapBinding.inflate(layoutInflater)

        setContentView(binding.root)

    }
}