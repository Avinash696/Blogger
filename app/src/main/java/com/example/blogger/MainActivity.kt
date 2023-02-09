package com.example.blogger


import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.blogger.adapter.AdapterHome
import com.example.blogger.blogModels.blogModel
import com.example.blogger.data.Repositary
import com.example.blogger.databinding.ActivityLoginBinding
import com.example.blogger.ui.activity.ui.login.LoginActivity
import com.example.blogger.utils.InternetConnection
import com.example.blogger.viewModels.BlogViewModel
import com.example.blogger.viewModels.BlogViewModelFactory
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging


class MainActivity : AppCompatActivity() {
    private val TAG = "mainActivityTag"

    private lateinit var blogViewModel: BlogViewModel
    lateinit var repositary: Repositary
    lateinit var rv: RecyclerView
    lateinit var logout: FloatingActionButton

    //    lateinit var listView :ListView
    lateinit var status: ImageView
    lateinit var profile: ImageView
    lateinit var logerName: TextView
    private lateinit var auth: FirebaseAuth
    lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var gso: GoogleSignInOptions


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        initilize()

        gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        //google data set
        val googleIntent = intent
        if (googleIntent != null) {
            val gn = googleIntent.getStringExtra("googleName")
            val gp = googleIntent.getStringExtra("googleImg")
//            val googleData :googleSignModel= googleIntent.getSerializableExtra("googleCred") as googleSignModel
//            setGlideImg.setImg(this,gp,profile)
            Log.d(TAG, "onCreate: $gn $gp")
            Glide.with(this)
                .load("https://lh3.googleusercontent.com/a/AEdFTp7Tg_dHkJQafmfAVxBYdbBrJdz2QJrQPK3LtNW8lw")
                .into(profile)
            logerName.text = gn
        }


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
            Log.d(TAG, "onCreate: $it")
            recyclerViewSet(it)
        }
        logout.setOnClickListener {
//            auth.signOut()
            mGoogleSignInClient.signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        val account : GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(this)
            // can featch details

        }
        getFCMToken()
        checkStatus()
    }

    private fun initilize() {
        rv = findViewById(R.id.rvMain)
        status = findViewById(R.id.ivStatus)
        profile = findViewById(R.id.imageView4)
        logerName = findViewById(R.id.tvName)
        logout = findViewById(R.id.floatingActionButton)
        auth = Firebase.auth
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
        } else {
            status.setColorFilter(Color.DKGRAY)
        }
    }
}