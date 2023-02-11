package com.example.tictactoe

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.FieldPosition

class PlaygroundActivity: AppCompatActivity() {

    private val combinationList = mutableListOf<IntArray>()

    private var boxPositions = mutableListOf(0,0,0,0,0,0,0,0,0)

    private var playerTurn = 1

    private var totalSelectedBoxes = 1

    private var playerOneLayout: LinearLayout? =null
    private var playerTwoLayout: LinearLayout? =null

    private lateinit var playerOne: TextView
    private lateinit var playerTwo: TextView

    private lateinit var img1: ImageView
    private lateinit var img2: ImageView
    private lateinit var img3: ImageView
    private lateinit var img4: ImageView
    private lateinit var img5: ImageView
    private lateinit var img6: ImageView
    private lateinit var img7: ImageView
    private lateinit var img8: ImageView
    private lateinit var img9: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playground)

        playerOne = findViewById(R.id.tv_player_one_name)
        playerTwo= findViewById(R.id.tv_player_two_name)

        playerOneLayout = findViewById(R.id.layout_player_one)
        playerTwoLayout = findViewById(R.id.layout_player_two)

        img1 = findViewById(R.id.img_1)
        img2 = findViewById(R.id.img_2)
        img3 = findViewById(R.id.img_3)
        img4 = findViewById(R.id.img_4)
        img5 = findViewById(R.id.img_5)
        img6 = findViewById(R.id.img_6)
        img7 = findViewById(R.id.img_7)
        img8 = findViewById(R.id.img_8)
        img9 = findViewById(R.id.img_9)

        combinationList.add(intArrayOf(0,1,2))
        combinationList.add(intArrayOf(3,4,5))
        combinationList.add(intArrayOf(6,7,8))
        combinationList.add(intArrayOf(0,3,6))
        combinationList.add(intArrayOf(1,4,7))
        combinationList.add(intArrayOf(2,5,8))
        combinationList.add(intArrayOf(0,4,8))
        combinationList.add(intArrayOf(2,4,6))


        val getPlayerOneName = intent.getStringExtra("playerOne").toString()
        val getPlayerTwoName = intent.getStringExtra("playerTwo").toString()

        playerOne.text = getPlayerOneName
        playerTwo.text = getPlayerTwoName

        img1.setOnClickListener {
            if (isBoxSelectable(0)){
                performAction(it as ImageView,0)
            }
        }

        img2.setOnClickListener {
            if (isBoxSelectable(1)){
                performAction(it as ImageView,1)
            }
        }

        img3.setOnClickListener {
            if (isBoxSelectable(2)){
                performAction(it as ImageView,2)
            }
        }

        img4.setOnClickListener {
            if (isBoxSelectable(3)){
                performAction(it as ImageView,3)
            }
        }

        img5.setOnClickListener {
            if (isBoxSelectable(4)){
                performAction(it as ImageView,4)
            }
        }

        img6.setOnClickListener {
            if (isBoxSelectable(5)){
                performAction(it as ImageView,5)
            }
        }

        img7.setOnClickListener {
            if (isBoxSelectable(6)){
                performAction(it as ImageView,6)
            }
        }

        img8.setOnClickListener {
            if (isBoxSelectable(7)){
                performAction(it as ImageView,7)
            }
        }

        img9.setOnClickListener {
            if (isBoxSelectable(8)){
                performAction(it as ImageView,8)
            }
        }
    }

    fun performAction(imageView: ImageView, selectedBoxPosition: Int){
        boxPositions[selectedBoxPosition] = playerTurn

        if (playerTurn == 1){
            imageView.setImageResource(R.drawable.cross_icon)

            if (checkPlayerWin()){
                showDialog(playerOne.text.toString() + "has won")
            } else if (totalSelectedBoxes == 9){
                showDialog("It's a Draw")
            } else{
                changePlayerTurn(2)
                totalSelectedBoxes++
            }
        } else{
            imageView.setImageResource(R.drawable.ic_zero)
            if (checkPlayerWin()){
                showDialog(playerTwo.text.toString() + "has won")
            } else if (totalSelectedBoxes == 9){
                showDialog("It's a Draw")
            } else{
                changePlayerTurn(1)
                totalSelectedBoxes++
            }
        }



    }

    fun checkPlayerWin(): Boolean {
        var response = false

        for (i in 0 until combinationList.size) {
            val combination: IntArray = combinationList[i]

            if (boxPositions[combination[0]] == playerTurn &&
                boxPositions[combination[1]] == playerTurn &&
                boxPositions[combination[2]] == playerTurn){
                response = true
            }
        }
        return response

    }

    fun changePlayerTurn(currentPlayerTurn: Int){
        playerTurn = currentPlayerTurn

        if (playerTurn == 1){
            playerOneLayout?.setBackgroundResource(R.drawable.rectangular_corner_border)
            playerTwoLayout?.setBackgroundResource(R.drawable.rectangular_corner)
        } else{
            playerOneLayout?.setBackgroundResource(R.drawable.rectangular_corner)
            playerTwoLayout?.setBackgroundResource(R.drawable.rectangular_corner_border)
        }
    }

    private fun showDialog(title: String) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_dailog)
        val msg = dialog.findViewById(R.id.msg_text) as TextView
        msg.text = title
        val restartBtn = dialog.findViewById(R.id.btn_start_again) as Button
        restartBtn.setOnClickListener {
            boxPositions = mutableListOf<Int>(0,0,0,0,0,0,0,0,0)

            playerTurn = 1

            totalSelectedBoxes = 1

            img1.setImageResource(R.drawable.tranparent)
            img2.setImageResource(R.drawable.tranparent)
            img3.setImageResource(R.drawable.tranparent)
            img4.setImageResource(R.drawable.tranparent)
            img5.setImageResource(R.drawable.tranparent)
            img6.setImageResource(R.drawable.tranparent)
            img7.setImageResource(R.drawable.tranparent)
            img8.setImageResource(R.drawable.tranparent)
            img9.setImageResource(R.drawable.tranparent)

            dialog.dismiss()
        }

        dialog.show()

    }

    fun isBoxSelectable(boxPosition: Int): Boolean{
        val response = false

        if (boxPositions[boxPosition] == 0){
            return true
        }
        return response
    }
}