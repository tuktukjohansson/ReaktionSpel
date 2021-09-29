package com.example.mittreaktionspel

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.os.CountDownTimer
import android.widget.*

class MainGameActivity : AppCompatActivity() {
    // ---------------------------------Variabler ---------------------------------\\
    var kort = 0
    var milishowtime = 1500
    var milirandomdelaytime = 0
    var score = 0
    var difficulty = 1
    var listofcardimages = listOf(
        R.drawable.redheart, R.drawable.reddiamond,
        R.drawable.blackclubs, R.drawable.blackspade
    )
    var listofbuttons = listOf<ImageButton>()
    // --------------------------------------------------------------------------\\

    // ---------------------------------On Create--------------------------------- \\
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_game)
        listofbuttons = listOf<ImageButton>(
            findViewById(R.id.heartbutton), findViewById(R.id.diamondbutton),
            findViewById(R.id.clubsbutton), findViewById(R.id.spadebutton)
        )
    }

    // --------------------------------------------------------------------------- \\
    // ------------------------------------Main Game------------------------------------ \\
    fun Startgamebutton(view: View) {
        runcalcs()
        val Cardimage = findViewById<ImageView>(R.id.imagecard)
        val Difficultytextbox = findViewById<TextView>(R.id.difficulty)
        val Answertextbox = findViewById<TextView>(R.id.Answerbox)
        Answertextbox.setText("")
        Difficultytextbox.setText("Difficulty $difficulty")

        val timer2 = object : CountDownTimer(milirandomdelaytime.toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                startgamegone()
            }

            override fun onFinish() {
                val timer = object : CountDownTimer(milishowtime.toLong(), 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                        Cardimage.setImageResource(listofcardimages[kort])
                    }

                    override fun onFinish() {
                        buttonsvisable()
                        Cardimage.setImageResource(R.drawable.cards)
                    }
                }
                timer.start()
            }
        }
        timer2.start()

    }

    // ------------------------------GONE-VISIBILITY----------------------------------- \\
    //startgamebutton.visibility = View.VISIBLE//
    fun buttonsvisable() {
        var i = 0
        for (x in listofbuttons) {

            listofbuttons[i].visibility = View.VISIBLE
            i++
        }
    }

    fun iconbuttonsgone() {
        var i = 0
        for (x in listofbuttons) {
            listofbuttons[i].visibility = View.GONE
            i++
        }
    }

    fun startgamevisable() {
        val startgamebutton = findViewById<Button>(R.id.startgamebutton)
        startgamebutton.visibility = View.VISIBLE
    }

    fun startgamegone() {
        val startgamebutton = findViewById<Button>(R.id.startgamebutton)
        startgamebutton.visibility = View.GONE
    }

    // --------------------------------ACTION ON ANSWER---------------------------------- \\
    fun Answerheart(view: View) {
        if (kort == 0) {
            rattsvar()
        } else {
            felsvar()
        }
    }

    fun Answerdiamond(view: View) {
        if (kort == 1) {
            rattsvar()
        } else {
            felsvar()
        }
    }

    fun Answerclubs(view: View) {
        if (kort == 2) {
            rattsvar()
        } else {
            felsvar()
        }
    }

    fun Answerspade(view: View) {
        if (kort == 3) {
            rattsvar()
        } else {
            felsvar()
        }
    }

    // ------------------------------REPEATING CALCULATIONS----------------------------- \\
    fun runcalcs() {
        kort = (0..3).random()
        milishowtime = milishowtime / 10
        milishowtime = milishowtime * 9
        milirandomdelaytime = (500..3500).random()
    }

    // ------------------------------------GAME HANDLER--------------------------------- \\
    fun rattsvar() {
        val Answertextbox = findViewById<TextView>(R.id.Answerbox)
        startgamevisable()
        showscores()
        iconbuttonsgone()
        score = score + 50
        score = score * difficulty
        difficulty++
        Answertextbox.text = "Correct!"
        Answertextbox.setTextColor(Color.parseColor("#26FF2F"))
    }

    fun felsvar() {
        val resetbutton = findViewById<Button>(R.id.startgamebutton)
        val Answertextbox = findViewById<TextView>(R.id.Answerbox)
        resetbutton.setText("Play Again!")
        iconbuttonsgone()
        startgamevisable()
        showscores()
        if (difficulty == 1) {
            Answertextbox.text = "First level... Really?"
            Answertextbox.setTextColor(Color.parseColor("#C10000"))
            resetgame()

        }
        else {
            Answertextbox.text = "Wrong"
            Answertextbox.setTextColor(Color.parseColor("#C10000"))
            resetgame()
        }
    }

    fun resetgame() {
        kort = 0
        milishowtime = 1500
        milirandomdelaytime = 0
        difficulty = 1
        score = 0
    }

    fun showscores() {
        val Textbox = findViewById<TextView>(R.id.scoretextbox)
        Textbox.text = "Score $score"
    }
    // --------------------------------------------------------------------------------- \\
}




