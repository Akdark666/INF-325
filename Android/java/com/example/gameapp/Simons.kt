package com.example.gameapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.view.forEach
import androidx.lifecycle.lifecycleScope
import com.example.gameapp.databinding.ActivitySimonsBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Simons : AppCompatActivity(), OnClickListener {
    private lateinit var sbinding: ActivitySimonsBinding

    private var score = 0
    private var result: String = ""
    private var userAnswer: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        sbinding = ActivitySimonsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(sbinding.root)
        //initViews
        sbinding.apply {
            panel1.setOnClickListener(this@Simons)
            panel2.setOnClickListener(this@Simons)
            panel3.setOnClickListener(this@Simons)
            panel4.setOnClickListener(this@Simons)
            startGame()
        }
    }

    private fun startGame() {
        result = ""
        userAnswer = ""
        disableButtons()
        lifecycleScope.launch {
            val round = (3..5).random()
            repeat(round) {
                delay(400)
                val randomPanel = (1..4).random()
                result += randomPanel
                val panel = when (randomPanel) {
                    1 -> sbinding.panel1
                    2 -> sbinding.panel2
                    3 -> sbinding.panel3
                    else -> sbinding.panel4
                }
                val drawableYellow =
                    ActivityCompat.getDrawable(this@Simons, R.drawable.btn_yellow)
                val drawableDefault =
                    ActivityCompat.getDrawable(this@Simons, R.drawable.btn_state)
                panel.background = drawableYellow
                delay(1000)
                panel.background = drawableDefault

            }
            runOnUiThread {
                enableButtons()
            }
        }
    }

    private fun loseAnimation() {
        sbinding.apply {
            score = 0
            tvScore.text = "0"
            disableButtons()
            val drawableLose = ActivityCompat.getDrawable(this@Simons, R.drawable.btn_lose)
            val drawableDefault =
                ActivityCompat.getDrawable(this@Simons, R.drawable.btn_state)
            lifecycleScope.launch {
                sbinding.root.forEach { view ->
                    if (view is Button) {
                        view.background = drawableLose
                        delay(200)
                        view.background = drawableDefault
                    }
                }
                delay(1000)
                startGame()
            }
        }
    }

    private fun enableButtons() {
        sbinding.root.forEach { view ->
            if (view is Button) {
                view.isEnabled = true
            }
        }
    }

    private fun disableButtons() {
        sbinding.root.forEach { view ->
            if (view is Button) {
                view.isEnabled = false
            }
        }
    }

    override fun onClick(view: View?) {
        view?.let {
            userAnswer += when (it.id) {
                R.id.panel1 -> "1"
                R.id.panel2 -> "2"
                R.id.panel3 -> "3"
                R.id.panel4 -> "4"
                else -> ""
            }
            if (userAnswer == result) {
                Toast.makeText(this@Simons, "W I N :)", Toast.LENGTH_SHORT).show()
                score++
                sbinding.tvScore.text = score.toString()
                startGame()
            } else if (userAnswer.length >= result.length) {
                loseAnimation()
            }
        }
    }
}