package com.example.gameapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.gameapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var player = "p1"
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.apply {
            b1.setOnClickListener { buttonClick(b1) }
            b2.setOnClickListener { buttonClick(b2) }
            b3.setOnClickListener { buttonClick(b3) }
            b4.setOnClickListener { buttonClick(b4) }
            b5.setOnClickListener { buttonClick(b5) }
            b6.setOnClickListener { buttonClick(b6) }
            b7.setOnClickListener { buttonClick(b7) }
            b8.setOnClickListener { buttonClick(b8) }
            b9.setOnClickListener { buttonClick(b9) }
            reset.setOnClickListener {
                b1.text = ""
                b1.isEnabled = true
                b2.text = ""
                b2.isEnabled = true
                b3.text = ""
                b3.isEnabled = true
                b4.text = ""
                b4.isEnabled = true
                b5.text = ""
                b5.isEnabled = true
                b6.text = ""
                b6.isEnabled = true
                b7.text = ""
                b7.isEnabled = true
                b8.text = ""
                b8.isEnabled = true
                b9.text = ""
                b9.isEnabled = true
                reset.visibility = View.INVISIBLE
            }
        }
        binding.finish.setOnClickListener {
            finish()
        }
    }

    fun buttonClick(btn: Button) {
        if (btn.text == "") {
            if (player == "p1") {
                player = "p2"
                btn.text = "X"
                btn.setTextColor(Color.parseColor("#FFFF4081"))
            } else {
                player = "p1"
                btn.text = "0"
                btn.setTextColor(Color.parseColor("#FFFFFF00"))
            }
        }
        btn.isEnabled = false
        win()
        verifyWin()
    }

    fun verifyWin() {
        if (binding.b1.text != "" && binding.b2.text != "" && binding.b3.text != "" &&
            binding.b4.text != "" && binding.b5.text != "" && binding.b6.text != "" &&
            binding.b7.text != "" && binding.b8.text != "" && binding.b9.text != ""
        ) {
            binding.reset.visibility = View.VISIBLE
        }
    }

    fun win() {
        if (binding.b1.text == "X" && binding.b2.text == "X" && binding.b3.text == "X" ||
            binding.b4.text == "X" && binding.b5.text == "X" && binding.b6.text == "X" ||
            binding.b7.text == "X" && binding.b8.text == "X" && binding.b9.text == "X" ||
            binding.b1.text == "X" && binding.b4.text == "X" && binding.b7.text == "X" ||
            binding.b2.text == "X" && binding.b5.text == "X" && binding.b8.text == "X" ||
            binding.b3.text == "X" && binding.b6.text == "X" && binding.b9.text == "X" ||
            binding.b1.text == "X" && binding.b5.text == "X" && binding.b9.text == "X" ||
            binding.b3.text == "X" && binding.b5.text == "X" && binding.b7.text == "X"
        ) {
            desactiveAllButton()
            toast("X Won Player")
            binding.reset.visibility = View.VISIBLE

        }
        if (binding.b1.text == "0" && binding.b2.text == "0" && binding.b3.text == "0" ||
            binding.b4.text == "0" && binding.b5.text == "0" && binding.b6.text == "0" ||
            binding.b7.text == "0" && binding.b8.text == "0" && binding.b9.text == "0" ||
            binding.b1.text == "0" && binding.b4.text == "0" && binding.b7.text == "0" ||
            binding.b2.text == "0" && binding.b5.text == "0" && binding.b8.text == "0" ||
            binding.b3.text == "0" && binding.b6.text == "0" && binding.b9.text == "0" ||
            binding.b1.text == "0" && binding.b5.text == "0" && binding.b9.text == "0" ||
            binding.b3.text == "0" && binding.b5.text == "0" && binding.b7.text == "0"
        ) {
            desactiveAllButton()
            toast("0 Won Player")
            binding.reset.visibility = View.VISIBLE

        }
    }

    fun toast(msg: String) {
        Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
    }

    fun desactiveAllButton() {
        binding.b1.isEnabled = false
        binding.b2.isEnabled = false
        binding.b3.isEnabled = false
        binding.b4.isEnabled = false
        binding.b5.isEnabled = false
        binding.b6.isEnabled = false
        binding.b7.isEnabled = false
        binding.b8.isEnabled = false
        binding.b9.isEnabled = false
    }
}