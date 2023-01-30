package com.example.blogger.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.blogger.R
import com.example.blogger.data.blogModels.Item
import com.example.blogger.databinding.ActivityDetailBinding
import org.jsoup.Jsoup

class DetailActivity : AppCompatActivity() {
    lateinit var ivDetail: ImageView
    lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        ivDetail = findViewById(R.id.ivDetail)
        supportActionBar?.hide()
        val intent = intent
        val blogData: Item = intent.getSerializableExtra("cardItem") as Item

//        Log.d("DetailActivity", "onCreate: ${getPicImage(blogData.toString())}")
//        Log.d("DetailActivity", "onCreate: $blogData")
//        val arryList = ArrayList<Item>()
//        arryList.addAll(listOf(blogData))
//        for (element in arryList){
//            Log.d("DetailActivity", "loop: ${getPicImage(element.content)}")
//        }
        binding.textView3.text = blogData.title
        Glide.with(this).load(getPicImage(blogData.content)).into(ivDetail)

    }

    private fun getPicImage(html: String): String? {
        val document = Jsoup.parse(html)
        val body = document.body()
        val elements = body.getElementsByAttribute("href")
        return elements.select("a").attr("abs:href")
    }
}