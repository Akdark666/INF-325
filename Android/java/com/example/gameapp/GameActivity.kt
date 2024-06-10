package com.example.gameapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.gameapp.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {
    private lateinit var gameBinding: ActivityGameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gameBinding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(gameBinding.root)

        gameBinding.simons.setOnClickListener {
            startActivity(Intent(this@GameActivity, Simons::class.java))
        }
        gameBinding.tictactoe.setOnClickListener {
            startActivity(Intent(this@GameActivity, MainActivity::class.java))
        }
    }
}