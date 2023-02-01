package com.example.blogger.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.blogger.MainActivity
import com.example.blogger.R
import com.google.firebase.messaging.FirebaseMessaging

class SplashActivity : AppCompatActivity() {
    lateinit var ivSplash: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()

//        FirebaseMessaging.getInstance().token.addOnCompleteListener {
//            if (it.isSuccessful) {
//                return@addOnCompleteListener
//            }
//            val token = it.result
//            Log.d("FCM78", "getFCMToken: $token")
//        }


        ivSplash = findViewById(R.id.imageView3)

        ivSplash.alpha = 0f
        ivSplash.animate().duration = 1000
        ivSplash.animate().alpha(1f).withEndAction {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }


    }

}