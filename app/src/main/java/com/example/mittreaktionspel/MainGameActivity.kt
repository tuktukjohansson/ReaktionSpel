package com.example.mittreaktionspel

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.os.CountDownTimer
import android.provider.ContactsContract
import android.widget.*
class MainGameActivity : AppCompatActivity() {
    // ---------------------------------Variabler --------------------------------------\\
    var kort = 0
    var milirandomdelaytime = 0
    var listofcardimages = listOf(
        R.drawable.redheart, R.drawable.reddiamond,
        R.drawable.blackclubs, R.drawable.blackspade
    )
    var listofbuttons = listOf<ImageButton>()
    lateinit var Cardimage : ImageView
    // ---------------------------------------------------------------------------------\\
    // ---------------------------------On Create-------------------------------------- \\
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_game)

        Cardimage = findViewById<ImageView>(R.id.imagecard)
        resetgame()
        listofbuttons = listOf<ImageButton>(
            findViewById(R.id.heartbutton), findViewById(R.id.diamondbutton),
            findViewById(R.id.clubsbutton), findViewById(R.id.spadebutton)
        )
    }
    // --------------------------------------------------------------------------------- \\
    // ------------------------------------Main Game------------------------------------ \\
    fun Startgamebutton(view: View) {
        runcalcs()

        val Difficultytextbox = findViewById<TextView>(R.id.difficulty)
        val Answertextbox = findViewById<TextView>(R.id.Answerbox)
        Answertextbox.setText("")
        Difficultytextbox.setText("Difficulty ${Datamanager.difficulty}")
        val timer2 = object : CountDownTimer(milirandomdelaytime.toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                startgamegone()
            }
            override fun onFinish() {
                val timer = object : CountDownTimer(Datamanager.milishowtime.toLong(), 1000) {
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
        Datamanager.milishowtime = Datamanager.milishowtime / 10
        Datamanager.milishowtime = Datamanager.milishowtime * 9
        milirandomdelaytime = (500..3500).random()
    }
    // ------------------------------------GAME HANDLER--------------------------------- \\
    var scoreplus = 50
    fun rattsvar() {
        val Answertextbox = findViewById<TextView>(R.id.Answerbox)
        startgamevisable()
        iconbuttonsgone()
        Datamanager.score = Datamanager.score + 50
        Datamanager.difficulty++
        scoreplus = scoreplus + 20
        showscores()
        Answertextbox.text = "Correct!"
        Answertextbox.setTextColor(Color.parseColor("#26FF2F"))
    }
    fun felsvar() {
        val openendgame = Intent(this, Endgame::class.java)
        startActivity(openendgame)
    }
    fun resetgame() {
        kort = 0
        Datamanager.milishowtime = 1500
        milirandomdelaytime = 0
        Datamanager.difficulty = 1
        Datamanager.score = 0
        showscores()
    }
    fun showscores() {
        val Textbox = findViewById<TextView>(R.id.scoretextbox)
        Textbox.text = "Score ${Datamanager.score}"
    }
    // --------------------------------------------------------------------------------- \\
}