package com.irfannurrizki.githubsearcher

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.githubuser.R

class SplashScreen : AppCompatActivity () {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        supportActionBar?.hide()

        var handler= Handler()
        handler.postDelayed({
//            val intent = Intent (this, MainActivity::class.java)
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        },3000)
    }
}