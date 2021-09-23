package com.example.mittreaktionspel

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.os.CountDownTimer
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlin.properties.Delegates


class MainGameActivity : AppCompatActivity() {
    // Variabler \\
    var kort = 0
    var milishowtime = 1500
    var milirandomdelaytime = 0
    var difficulty = 0
    var listofcards = listOf(
        R.drawable.redheart, R.drawable.reddiamond,
        R.drawable.blackclubs, R.drawable.blackspade)
    // --------- \\

    // On Create \\
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_game)
    }
    // --------- \\

    // ------------------------------------Main Game------------------------------------ \\
    fun Startgamebutton(view: View) {
        runcalcs()

        val textbox = findViewById<TextView>(R.id.textviewnew)
        val startgamebutton = findViewById<Button>(R.id.startgamebutton)
        val Cardimage = findViewById<ImageView>(R.id.imagecard)

        val timer2 = object : CountDownTimer(milirandomdelaytime.toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                textbox.text = milirandomdelaytime.toString()
                startgamebutton.visibility = View.GONE
            }

            override fun onFinish() {

                val timer = object : CountDownTimer(milishowtime.toLong(), 1000) {

                    override fun onTick(millisUntilFinished: Long) {
                        textbox.text = kort.toString()
                        Cardimage.setImageResource(listofcards[kort])
                    }

                    override fun onFinish() {
                        textbox.text = "Hidden"
                        startgamebutton.visibility = View.VISIBLE
                        Cardimage.setImageResource(R.drawable.cards)
                    }
                }
                timer.start()
            }
        }
        timer2.start()

    }
    fun Answerheart(view: View) {
        val textbox = findViewById<TextView>(R.id.textviewnew)
        if (kort == 0){
            textbox.setText("Rätt!")
        }
        else{
            textbox.setText("Fel!")
        }
    }
    fun Answerdiamond(view: View) {
        val textbox = findViewById<TextView>(R.id.textviewnew)
        if (kort == 1){
            textbox.setText("Rätt!")
        }
        else{
            textbox.setText("Fel!")
        }
    }
    fun Answerclubs(view: View) {
        val textbox = findViewById<TextView>(R.id.textviewnew)
        if (kort == 2){
            textbox.setText("Rätt!")
        }
        else{
            textbox.setText("Fel!")
        }
    }
    fun Answerspade(view: View) {
        val textbox = findViewById<TextView>(R.id.textviewnew)
        if (kort == 3){
            textbox.setText("Rätt!")
        }
        else{
            textbox.setText("Fel!")
        }
    }
    // --------------------------------------------------------------------------------- \\
    fun runcalcs() {
        kort = (0..3).random()
        milishowtime = milishowtime / 10
        milishowtime = milishowtime * 9
        difficulty++
        milirandomdelaytime = (500..3500).random()
    }
}



