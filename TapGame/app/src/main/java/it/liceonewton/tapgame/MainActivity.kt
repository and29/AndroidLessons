package it.liceonewton.tapgame

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.math.RoundingMode

class MainActivity : AppCompatActivity() {
    private lateinit var timerTextView: TextView
    private lateinit var player1Counter: TextView
    private lateinit var player2Counter: TextView
    private lateinit var resultTextView: TextView
    private lateinit var buttonPlayer1: Button
    private lateinit var buttonPlayer2: Button
    private lateinit var restartButton: Button

    private var countPlayer1 = 0
    private var countPlayer2 = 0
    private var gameRunning = false
    private lateinit var timer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        timerTextView = findViewById(R.id.timerTextView)
        player1Counter = findViewById(R.id.player1Counter)
        player2Counter = findViewById(R.id.player2Counter)
        resultTextView = findViewById(R.id.resultTextView)
        buttonPlayer1 = findViewById(R.id.buttonPlayer1)
        buttonPlayer2 = findViewById(R.id.buttonPlayer2)
        restartButton = findViewById(R.id.restartButton)

        restartButton.text = "Start"

        restartButton.setOnClickListener { startGame() }

        buttonPlayer1.setOnClickListener {
            if (gameRunning) {
                countPlayer1++
                player1Counter.text = "Player 1: $countPlayer1"
            }
        }

        buttonPlayer2.setOnClickListener {
            if (gameRunning) {
                countPlayer2++
                player2Counter.text = "Player 2: $countPlayer2"
            }
        }
    }

    fun startGame(){
        if(!gameRunning) {
            countPlayer1 = 0
            countPlayer2 = 0
            gameRunning = true
            resultTextView.text = ""
            player1Counter.text = "Player 1: 0"
            player2Counter.text = "Player 2: 0"
            restartButton.isEnabled = !gameRunning

            timer = object : CountDownTimer(10000, 100) {
                override fun onTick(millisUntilFinished: Long) {
                    var timeLeft = millisUntilFinished / 1000.0
                    timeLeft = timeLeft.toBigDecimal().setScale(2, RoundingMode.UP).toDouble()
                    timerTextView.text = "Time: ${timeLeft}"
                }

                override fun onFinish() {
                    gameRunning = false
                    restartButton.text = "Restart"
                    restartButton.isEnabled = !gameRunning
                    timerTextView.text = "Time: 0"
                    showWinner()
                }
            }
            timer.start()
        }

    }
    private fun showWinner() {
        resultTextView.text = when {
            countPlayer1 > countPlayer2 -> "Player 1 wins! 🎉"
            countPlayer2 > countPlayer1 -> "Player 2 wins! 🏆"
            else -> "It's a draw! 🤝"
        }
    }

}