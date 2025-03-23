package com.pdrarth.arthmovies

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.pdrarth.arthmovies.View.Tela_Login

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //deixar o app todo na cor preta
        window.statusBarColor = Color.parseColor("#FF000000")
        val actionbar = supportActionBar
        actionbar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FF000000")))
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, Tela_Login::class.java)
            startActivity(intent)
            finish()
        },2000)
    }

}