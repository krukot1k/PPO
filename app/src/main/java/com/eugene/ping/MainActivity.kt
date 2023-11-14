package com.eugene.ping

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.eugene.ping.components.GameView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(GameView(this))
    }
}