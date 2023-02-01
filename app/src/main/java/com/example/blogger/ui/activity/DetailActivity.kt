package com.example.blogger.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.blogger.R
import com.example.blogger.blogModels.Item
import com.example.blogger.databinding.ActivityDetailBinding
import org.jsoup.Jsoup
import kotlin.math.log

class DetailActivity : AppCompatActivity() {
    lateinit var ivDetail: ImageView
    lateinit var binding: ActivityDetailBinding
    private val TAG = "DetailActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        ivDetail = findViewById(R.id.ivDetail)
        supportActionBar?.hide()
        val intent = intent
        val blogData: Item = intent.getSerializableExtra("cardItem") as Item
        Log.d(TAG, "onCreate: $blogData")
        binding.textView3.text = getTextContent(blogData.content)
        Glide.with(this).load(getPicImage(blogData.content)).into(ivDetail)
        getTextContent(blogData.content)
    }

    private fun getPicImage(html: String): String? {
        val document = Jsoup.parse(html)
        val body = document.body()
        val elements = body.getElementsByAttribute("href")
        return elements.select("a").attr("abs:href")
    }

    private fun getTextContent(html: String): String? {
        val document = Jsoup.parse(html)
        val body = document.body()
        return body.text()
    }
}