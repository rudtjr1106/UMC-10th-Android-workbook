package com.umc.workbook

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.umc.workbook.databinding.ActivityMainBinding
import com.umc.workbook.model.Emotion

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var emotion: Emotion = Emotion.NONE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.apply {
            imgBest.setOnClickListener {
                emotion = Emotion.BEST
                type = emotion
            }
            imgGood.setOnClickListener {
                emotion = Emotion.GOOD
                type = emotion
            }
            imgSoso.setOnClickListener {
                emotion = Emotion.SOSO
                type = emotion
            }
            imgBad.setOnClickListener {
                emotion = Emotion.BAD
                type = emotion
            }
            imgWorst.setOnClickListener {
                emotion = Emotion.WORST
                type = emotion
            }
        }
    }
}