package com.example.mittreaktionspel

import android.content.Intent
import android.graphics.Color
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.os.CountDownTimer
import android.provider.ContactsContract
import android.widget.*


class MainGameActivity : AppCompatActivity() {
    //-----Spawn obstacle at Round-----\\
    var firstobstacle = 10
    var secondobstacle = 18
    //-----Spawn obstacle at Round-----\\
    // ---------------------------------Variabler --------------------------------------\\
    var kort = 0
    var milirandomdelaytime = 0
    var listofcardimages = listOf(
        R.drawable.redheart, R.drawable.reddiamond,
        R.drawable.blackclubs, R.drawable.blackspade
    )
    var listofalerts = listOf(R.drawable.blacknow,R.drawable.rednblacknow)
    lateinit var alertview : ImageView
    lateinit var closealertview : ImageButton
    var listofbuttons = listOf<ImageButton>()
    lateinit var Cardimage: ImageView

    // ---------------------------------------------------------------------------------\\
    // ---------------------------------On Create-------------------------------------- \\
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_game)
        alertview = findViewById<ImageView>(R.id.alert)
        closealertview = findViewById<ImageButton>(R.id.closealert)
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
        Difficultytextbox.setText("Round ${Datamanager.round}")
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
                        if (kort > 3){
                            kort = kort - 4
                        }
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
        if (Datamanager.round > secondobstacle) {
            kort = (0..7).random()
            Datamanager.milishowtime = Datamanager.milishowtime / 10
            Datamanager.milishowtime = Datamanager.milishowtime * 9
            milirandomdelaytime = (500..2500).random()
        }
        else {
            kort = (0..3).random()
            Datamanager.milishowtime = Datamanager.milishowtime / 10
            Datamanager.milishowtime = Datamanager.milishowtime * 9
            milirandomdelaytime = (500..3500).random()
        }
    }

    // ------------------------------------GAME HANDLER--------------------------------- \\
    var scoreplus = 50
    fun rattsvar() {
        val Answertextbox = findViewById<TextView>(R.id.Answerbox)
        startgamevisable()
        iconbuttonsgone()
        Datamanager.round++
        scoreplus = scoreplus + 20
        Datamanager.score = Datamanager.score + scoreplus
        showscores()
        Answertextbox.text = "Correct!"
        Answertextbox.setTextColor(Color.parseColor("#26FF2F"))
        if (Datamanager.round == firstobstacle) {
            listofcardimages = listOf(
                R.drawable.blackheart, R.drawable.blackdiamond,
                R.drawable.blackclubs, R.drawable.blackspade
            )
            alertview.setImageResource(listofalerts[0])
            alertview.visibility = View.VISIBLE
            closealertview.visibility = View.VISIBLE
            listofbuttons[0].setImageResource(R.drawable.bhearticon)
            listofbuttons[1].setImageResource(R.drawable.bdiamondicon)
            startgamegone()
        }
        else if (Datamanager.round == secondobstacle){
            listofcardimages = listOf(
                R.drawable.redheart, R.drawable.reddiamond,
                R.drawable.blackclubs, R.drawable.blackspade,
                R.drawable.blackheart, R.drawable.blackdiamond,
                R.drawable.redclubs, R.drawable.redspade
            )
            alertview.setImageResource(listofalerts[1])
            alertview.visibility = View.VISIBLE
            closealertview.visibility = View.VISIBLE
            startgamegone()
        }
    }

    fun felsvar() {
        val openendgame = Intent(this, Endgame::class.java)
        startActivity(openendgame)
    }

    fun resetgame() {
        kort = 0
        Datamanager.milishowtime = 1500
        milirandomdelaytime = 0
        Datamanager.round = 1
        Datamanager.score = 0
        showscores()
    }

    fun showscores() {
        val Textbox = findViewById<TextView>(R.id.scoretextbox)
        Textbox.text = "Score ${Datamanager.score}"
    }
    fun closeinfo( view: View ) {
        alertview.visibility = View.GONE
        closealertview.visibility = View.GONE
        startgamevisable()
    }
    // --------------------------------------------------------------------------------- \\
}