package com.example.mittreaktionspel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Variables
        val buttonplay = findViewById<Button>(R.id.buttonp)
        val buttonrules = findViewById<Button>(R.id.buttoni)
        val instructionimage = findViewById<ImageView>(R.id.infoview)
        val infocloser = findViewById<ImageButton>(R.id.infoclose1)
        // Variables

        // ClickEvents
        buttonplay.setOnClickListener(){
            val intent = Intent(this, MainGameActivity::class.java)
            startActivity(intent)
        }
        buttonrules.setOnClickListener(){
            instructionimage.setVisibility(View.VISIBLE)
            buttonplay.setVisibility(View.GONE)
            buttonrules.setVisibility(View.GONE)
            infocloser.setVisibility(View.VISIBLE)
        }
        infocloser.setOnClickListener(){
            instructionimage.setVisibility(View.GONE)
            buttonplay.setVisibility(View.VISIBLE)
            buttonrules.setVisibility(View.VISIBLE)
            infocloser.setVisibility(View.GONE)
        }
        // ClickEvents
    }
    fun quitgame(view: View){
        finishAffinity()
    }
}


