package com.example.mittreaktionspel

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.system.exitProcess

class Endgame : AppCompatActivity() {
    lateinit var difficultyview: TextView
    lateinit var scoreview: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.endgame_activity)
        difficultyview = findViewById(R.id.difficultyview)
        scoreview = findViewById(R.id.scoreview)
        val losttext = findViewById<TextView>(R.id.losttext)
        difficultyview.setText("Round reached : ${Datamanager.round}")
        scoreview.setText("Score : ${Datamanager.score}")
        if (Datamanager.round == 1) {
            losttext.setText("First Level... Really. Play again?")
        } else {
            losttext.setText("You've lost, Play again?")
        }
    }

    fun RestartGame(view: View) {
        val reset = Intent(this, com.example.mittreaktionspel.MainGameActivity::class.java)
        startActivity(reset)
    }

    fun QuitGame(view: View) {
        val elon = findViewById<ImageView>(R.id.elon)
        val timer = object : CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                elon.visibility = View.VISIBLE
            }

            override fun onFinish() {
                finishAffinity()
            }
        }
        timer.start()
    }
}