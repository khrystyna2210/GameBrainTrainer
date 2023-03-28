package com.example.gamebraintrainer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var buttonStart: Button
    private lateinit var buttonPlayAgain: Button
    private lateinit var textTimer: TextView
    private lateinit var textScore: TextView
    private lateinit var textSequence: TextView
    private lateinit var textComment: TextView
    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button
    var answerArray = mutableListOf<Int>()
    val random_min: Int = 1
    val random_max: Int = 30
    var positionOfCorrectAnswer: Int = 0
    var score: Int = 0
    var numberOfQuest:Int = 0
    private lateinit var countDownTimer:CountDownTimer


    fun init(){
        buttonStart = findViewById(R.id.btnStart)
        buttonPlayAgain = findViewById(R.id.btnPlayAgain)
        button1 = findViewById(R.id.btn1)
        button2 = findViewById(R.id.btn2)
        button3 = findViewById(R.id.btn3)
        button4 = findViewById(R.id.btn4)
        textTimer = findViewById(R.id.tvTimer)
        textScore = findViewById(R.id.tvScore)
        textSequence = findViewById(R.id.tvSequince)
        textComment = findViewById(R.id.tvComment)

        buttonPlayAgain.visibility = View.INVISIBLE
        textComment.visibility = View.INVISIBLE
        button1.isEnabled = false
        button2.isEnabled = false
        button3.isEnabled = false
        button4.isEnabled = false

    }

    fun randomUpdate(){
        var random_number1 = (random_min..random_max).random()
        var random_number2 = (random_min..random_max).random()
        textSequence.text = random_number1.toString()+"+"+random_number2.toString()
        positionOfCorrectAnswer = (0..3).random()
        answerArray.clear()

        for(i in 0..3) {
            if (i == positionOfCorrectAnswer) {
                answerArray.add(random_number1+random_number2);
            } else {
                var wrongAnswer = (random_min..random_max).random()

                while (wrongAnswer == random_number1+random_number2) {
                    wrongAnswer = (random_min..random_max).random()
                }

                answerArray.add(wrongAnswer);
            }

        }

        button1.setText(Integer.toString(answerArray.get(0)));
        button2.setText(Integer.toString(answerArray.get(1)));
        button3.setText(Integer.toString(answerArray.get(2)));
        button4.setText(Integer.toString(answerArray.get(3)));
    }

    fun onClickStartButton(view: View){
        buttonStart.visibility = View.INVISIBLE
        randomUpdate()
        startTimer();
    }

    fun onClickPlayButton(view: View){
        textComment.visibility = View.VISIBLE
        if(view.tag.toString() == positionOfCorrectAnswer.toString()){
            textComment.setText("Right!");
            score++;
        }else{
            textComment.setText("Wrong!");
        }
        numberOfQuest++;
        textScore.text = score.toString()+"/"+numberOfQuest.toString();
        randomUpdate();
    }

    fun onClickPlayAgainButton(view: View){
        countDownTimer.cancel();
        textTimer.setText("30s");
        randomUpdate();
        score=0;
        numberOfQuest=0;
        textScore.setText("0/0");
        startTimer();
    }

    private fun startTimer(){
        buttonPlayAgain.setVisibility(View.INVISIBLE);
        textComment.setVisibility(View.INVISIBLE);
        button1.isEnabled = true;
        button2.isEnabled = true;
        button3.isEnabled = true;
        button4.isEnabled = true;

        countDownTimer = object : CountDownTimer(30000+100,1000){
            override fun onTick(p0: Long) {
                textTimer.text = (p0/1000).toString()+"s"
            }

            override fun onFinish() {
                textTimer.text = "0s"
                buttonPlayAgain.visibility = View.VISIBLE
                button1.isEnabled = false;
                button2.isEnabled = false;
                button3.isEnabled = false;
                button4.isEnabled = false;
            }
        }.start()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }
}