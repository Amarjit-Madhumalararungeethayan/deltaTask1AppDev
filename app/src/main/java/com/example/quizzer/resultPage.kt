package com.example.quizzer

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.example.quizzer.databinding.ActivityResultPageBinding

class resultPage : AppCompatActivity() {
    private lateinit var binding: ActivityResultPageBinding

    lateinit var sharedPrefs: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultPageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val score = intent.getIntExtra("SCORE", 0)

        sharedPrefs = getSharedPreferences("HighScore", MODE_PRIVATE)
        var highscore = sharedPrefs.getInt("highscore", 0)

        if (highscore >= score) {
            binding.textView13.text = highscore.toString()
        }
        else {
            binding.textView13.text = score.toString()
            val editor = getSharedPreferences("HighScore", MODE_PRIVATE).edit()
            editor.putInt("highscore", score)
            editor.commit()
        }
        binding.textView6.text = score.toString()
        total = 0
        mainCountX(6000, 1000)
    }


    fun mainCountX(x: Int, y: Int) {
        val countDown: CountDownTimer
        val X = x.toLong()
        val Y = y.toLong()
        countDown = object : CountDownTimer(X, Y) {
            override fun onTick(millisecsToFinish: Long) {
                binding.goBack.text = (millisecsToFinish / 1000).toString()

            }

            override fun onFinish() {
                val intent = Intent(baseContext, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        countDown.start()
    }

}
