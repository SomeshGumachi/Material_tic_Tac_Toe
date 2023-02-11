package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val playerOne: TextView = findViewById(R.id.edt_player_one)
        val playerTwo: TextView = findViewById(R.id.edt_player_two)
        val startBtn: TextView = findViewById(R.id.btn_start)

        val playerOneName = playerOne.text
        val playerTwoName = playerTwo.text

        startBtn.setOnClickListener{
            val intent = Intent(this,PlaygroundActivity::class.java)
            intent.putExtra("playerOne",playerOneName.toString())
            intent.putExtra("playerTwo",playerTwoName.toString())
            startActivity(intent)
        }
    }
}