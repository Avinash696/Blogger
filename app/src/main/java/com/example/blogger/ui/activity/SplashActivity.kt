package com.example.blogger.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import com.example.blogger.MainActivity
import com.example.blogger.R

class SplashActivity : AppCompatActivity() {
    lateinit var ivSplash: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()

        ivSplash = findViewById(R.id.imageView3)

        ivSplash.alpha = 0f
        ivSplash.animate().duration = 3000
        ivSplash.animate().alpha(1f).withEndAction {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
//        val handler = Handler(Looper.getMainLooper())
//
//       handler.postDelayed({
//
//       },4000)


    }
}