package com.example.blogger.ui.activity.ui.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.blogger.MainActivity
import com.example.blogger.R
import com.example.blogger.blogModels.googleSignModel
import com.example.blogger.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

class LoginActivity : AppCompatActivity() {
    private val TAG = "loginTag"
    val RC_SIGN_IN = 22
    var time = 4
    lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val gso: GoogleSignInOptions =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        binding.googleButton?.setOnClickListener {
            signIn()
        }
//        val hander = Handler()
//        hander.postDelayed(
//            kotlinx.coroutines.Runnable {
//                time -= 1
//                binding.tvAutoLogin!!.text = (time).toString()
//                startActivity(Intent(this, MainActivity::class.java))
//            }, 4000
//        )
    }


    private fun signIn() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent

        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            Log.d(TAG, "onActivityResult: ${data}")
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val acct = completedTask.getResult(ApiException::class.java)

            // Signed in successfully, show authenticated UI.
            if (acct != null) {
                val idToken: String? = acct.idToken
                val personGivenName: String? = acct.givenName
                val personFamilyName: String? = acct.familyName
                val personEmail: String? = acct.email
                val personId: String? = acct.id
                val personPhoto: Uri? = acct.photoUrl

                val intent = Intent(this, MainActivity::class.java)
                Log.d(
                    TAG,
                    "handleSignInResult:  +$personPhoto"
                )
//                intent.putExtra("googleCred",googleSignModel(personGivenName, personPhoto))
                intent.putExtra("googleName",personGivenName)
                intent.putExtra("googleImg",personPhoto.toString())
                startActivity(intent)

            }
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.d(TAG, "signInResult:failed code=" + e.stackTraceToString())
//            updateUI(null)
        }
    }
}
