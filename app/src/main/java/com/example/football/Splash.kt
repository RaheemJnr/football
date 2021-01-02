package com.example.football

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        //setTheme(R.style.SplashTheme)
        //setContentView(R.layout.activity_splash)
        super.onCreate(savedInstanceState)


        //
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        //
        finish()
    }
}