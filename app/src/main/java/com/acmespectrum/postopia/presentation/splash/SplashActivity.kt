package com.acmespectrum.postopia.presentation.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.acmespectrum.postopia.R
import com.acmespectrum.postopia.data.local.SharedPreferencesManager
import com.acmespectrum.postopia.presentation.auth.LoginActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private lateinit var sharedPreferencesManager: SharedPreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)

        sharedPreferencesManager = SharedPreferencesManager.getInstance(this)

        val user = sharedPreferencesManager.getUserDetails()

        lifecycleScope.launch {
            delay(2500)
//            var intent = Intent(this@SplashActivity, MainActivity::class.java)
//            if (user == null)
                intent = Intent(this@SplashActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
            finishAffinity()
        }
    }
}