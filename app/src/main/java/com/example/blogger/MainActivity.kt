package com.example.blogger


import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.blogger.adapter.AdapterHome
import com.example.blogger.data.Repositary
import com.example.blogger.blogModels.blogModel
import com.example.blogger.utils.InternetConnection
import com.example.blogger.viewModels.BlogViewModel
import com.example.blogger.viewModels.BlogViewModelFactory
import com.google.firebase.messaging.FirebaseMessaging
import java.security.AccessController.getContext


class MainActivity : AppCompatActivity() {
    private val TAG = "mainActivityTag"

    private lateinit var blogViewModel: BlogViewModel
    lateinit var repositary: Repositary
    lateinit var rv: RecyclerView

    //    lateinit var listView :ListView
    lateinit var status: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        initilize()
//        if (Build.VERSION.SDK_INT > 9) {
//            val policy = ThreadPolicy.Builder().permitAll().build()
//            StrictMode.setThreadPolicy(policy)
//        }

        var list = ArrayList<Int>()
        list.add(R.drawable.ic_launcher_background)
        list.add(R.drawable.ic_launcher_background)
        list.add(R.drawable.ic_launcher_background)
        list.add(R.drawable.ic_launcher_background)



//        listView = findViewById(R.id.lvMmain)
//        var adapter = ArrayAdapter<Int>(this,android.R.layout.simple_list_item_1,list)
//        listView.adapter = adapter
        repositary = (application as BlogApplication).repositary

        blogViewModel =
            ViewModelProvider(this, BlogViewModelFactory(repositary))[BlogViewModel::class.java]

        blogViewModel.allBlogLiveData.observe(this) {
//            Log.d(TAG, "onCreate: $it")
            recyclerViewSet(it)
        }
        getFCMToken()
        checkStatus()
    }

    private fun initilize() {
        rv = findViewById(R.id.rvMain)
        status = findViewById(R.id.ivStatus)
    }

    private fun recyclerViewSet(data: blogModel) {

        val arrayList = ArrayList<blogModel>()
        arrayList.addAll(listOf(data))
        Log.d(TAG, "getRey: $arrayList")
        val adapter = AdapterHome(this, arrayList)
        rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv.adapter = adapter
    }

    private fun getFCMToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener {
            if (it.isSuccessful) {
                val token = it.result
                Log.d("FCM78", "getFCMToken: $token")
                return@addOnCompleteListener
            }
            val token = it.result
            Log.d("FCM78", "getFCMToken: $token")
        }
    }

    private fun checkStatus() {
        if (InternetConnection.isInternetAvailable(applicationContext)) {
            status.setColorFilter(Color.GREEN)
        }    else {
            status.setColorFilter(Color.DKGRAY)
        }
    }
}