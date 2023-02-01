package com.example.blogger.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.blogger.R
import com.example.blogger.blogModels.blogModel
import com.example.blogger.databinding.RowHomeBinding
import com.example.blogger.ui.activity.DetailActivity
import com.google.gson.Gson
import org.jsoup.Jsoup

class AdapterHome(private val context: Context, private val postArrayData: ArrayList<blogModel>) :
    RecyclerView.Adapter<AdapterHome.CustomViewHolder>() {
    lateinit var binding: RowHomeBinding

    class CustomViewHolder(binding: RowHomeBinding) : RecyclerView.ViewHolder(binding.root) {
//        fun bind(holder: CustomViewHolder) {
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        binding = RowHomeBinding.inflate(LayoutInflater.from(context), parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val allItems = postArrayData[0].items

        Glide.with(context).load(getPicImage(allItems[position].content))
            .into(holder.itemView.findViewById(R.id.ivPostImages));

        holder.itemView.findViewById<TextView>(R.id.tvTitle).text = allItems[position].title
        holder.itemView.findViewById<TextView>(R.id.tvCreatedDate).text =
            "Published -" + (allItems[position].published).substring(0,10)
        holder.itemView.findViewById<TextView>(R.id.tvUpdatedDate).text =
            "Created -" + (allItems[position].updated).substring(0,11)
        //on item click
        Log.d("rawat", "onBindViewHolder: ${getPicImage(allItems[position].content)}")
        holder.itemView.setOnClickListener {
            val intent = Intent(Intent(context, DetailActivity::class.java))
            intent.putExtra("cardItem", allItems[position])
            context.startActivity(intent)
        }
//        getContentText(allItems[position].content)
    }

    override fun getItemCount(): Int {
        return postArrayData[0].items.size
    }

    private fun getPicImage(html: String): String? {

        val document = Jsoup.parse(html)
        val body = document.body()
        val elements = body.getElementsByAttribute("href")
        return elements.select("a").attr("abs:href")
    }

    private fun getContentText(html: String) {
        val document = Jsoup.parse(html)
        val body = document.body()
        Log.d("rawat", "getContentText: $body")
        val elements = body.getElementsByAttribute("p")
//        Log.d("rawat", "element: $elements")
        Log.d("rawat", "inside element: ${elements.select("p")}")
    }
}