package ac.kr.tukorea.bus_application.Activity

import ac.kr.tukorea.bus_application.databinding.ActivityKakaoMapBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class KakaoMapActivity : AppCompatActivity() {
    private lateinit var binding: ActivityKakaoMapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKakaoMapBinding.inflate(layoutInflater)

        setContentView(binding.root)

    }
}