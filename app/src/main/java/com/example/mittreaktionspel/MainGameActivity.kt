package com.example.mittreaktionspel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.os.CountDownTimer
import android.widget.TextView
import android.widget.Toast


public class MainGameActivity : AppCompatActivity() {
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------\\
    //Variabler\\
    var kort = (0..4).random()
    var milishowtime = 1500
    var milirandomshowtime = (1500..4000).random()
    var difficulty = 0

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------\\
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_game)

//----------------------------------------------------------------------------------------------------------------------------------------------------------------\\

    }
    fun Startgamebutton(view: View) {
        val timer2 = object: CountDownTimer(milirandomshowtime.toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val textbox = findViewById<TextView>(R. id. textviewnew)
                textbox.text = milirandomshowtime.toString()
            }

            override fun onFinish() {
                val timer = object: CountDownTimer(milishowtime.toLong(), 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                        val textbox = findViewById<TextView>(R. id. textviewnew)
                        textbox.text = kort.toString()
                    }

                    override fun onFinish() {
                        val textbox = findViewById<TextView>(R. id. textviewnew)
                        textbox.text = "Hidden"
                    }
                }
                timer.start()
                kort = (0..4).random()
                milishowtime = milishowtime / 10
                milishowtime = milishowtime * 9
                difficulty++
                milirandomshowtime = (1500..4000).random()

            }
        }
        timer2.start()
    }
}
