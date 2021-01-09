package com.example.quiz

import android.graphics.Color
import android.graphics.Typeface
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var mcp: Int = 1 //setting current position for question
    private var mql: ArrayList<Questions>? = null//variable for the questions list
    private var msop: Int = 0 //selected option position

    private val tvq = findViewById<TextView>(R.id.tvQuestionID)
    private val image = findViewById<ImageView>(R.id.ivImage)
    private val pb = findViewById<ProgressBar>(R.id.pb)
    private val tvpb = findViewById<TextView>(R.id.tvProgress)

    private val questionOption1 = findViewById<TextView>(R.id.tvOP1)
    private val questionOption2 = findViewById<TextView>(R.id.tvOP2)
    private val questionOption3 = findViewById<TextView>(R.id.tvOP3)
    private val questionOption4 = findViewById<TextView>(R.id.tvOP4)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        //  mql = Constants.getQuestions() priting out the array
        //    Log.i("Questions Size: ", "${questionList.size}")
        mql = Constants.getQuestions()
        setQuestion()

        questionOption1.setOnClickListener(this)
        questionOption2.setOnClickListener(this)
        questionOption3.setOnClickListener(this)
        questionOption4.setOnClickListener(this)
    }


    private fun setQuestion() {
//selecting the first object of the array
        val questions = mql!![mcp - 1]

        defaultOptionsView()
//setting the values
        pb.progress = mcp
        tvpb.text = "${mcp}/${pb.max}"
        tvq.text = questions.question
        image.setImageResource(questions.image)

        questionOption1.text = questions.optionOne
        questionOption2.text = questions.optionTwo
        questionOption3.text = questions.optionThree
        questionOption4.text = questions.optionFour
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tvOP1 -> {
                selectedOptionView(questionOption1, 1)
            }
            R.id.tvOP2 -> {
                selectedOptionView(questionOption2, 2)
            }
            R.id.tvOP3 -> {
                selectedOptionView(questionOption3, 3)
            }
            R.id.tvOP4 -> {
                selectedOptionView(questionOption4, 4)
            }
        }
    }


    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        options.add(0, questionOption1)
        options.add(1, questionOption2)
        options.add(2, questionOption3)
        options.add(3, questionOption4)


        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT //set text type and style like it should be displayed
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )//setting the background to the background we created
        }
    }




    private fun selectedOptionView(tv: TextView, selectedOptionNumber: Int) {
        defaultOptionsView()
        msop = selectedOptionNumber

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(
            tv.typeface,
            Typeface.BOLD
        )//set text type and style like it should be displayed
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )//setting the background to the background we created in the drawable
    }


}