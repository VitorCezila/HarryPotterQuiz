package com.example.hpquiz

import android.animation.Animator
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_question.*

lateinit var questionList: MutableList<Question>
var quesNum: Int = 0
lateinit var countDown: CountDownTimer

class QuestionActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)


        option1.setOnClickListener(this)
        option2.setOnClickListener(this)
        option3.setOnClickListener(this)
        option4.setOnClickListener(this)

        getQuestionLists()


    }

    private fun getQuestionLists() {
        questionList = arrayListOf()

        questionList.add(Question("Question 1", "A", "B", "C", "D", 1))
        questionList.add(Question("Question 2", "B", "B", "D", "A", 1))
        questionList.add(Question("Question 3", "C", "B", "A", "D", 1))
        questionList.add(Question("Question 4", "A", "D", "C", "B", 1))
        questionList.add(Question("Question 5", "C", "D", "A", "D", 1))

        setQuestion()
    }

    private fun setQuestion() {
        countdown.text = 10.toString()

        question.text = questionList[0].question
        option1.text = questionList[0].optionA
        option2.text = questionList[0].optionB
        option3.text = questionList[0].optionC
        option4.text = questionList[0].optionD

        quest_num.text = (1.toString() + "/" + questionList.size.toString())

        startTimer()

        quesNum = 0
    }

    private fun startTimer() {
        val countDown = object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                countdown.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                changeQuestion()
            }
        }
        countDown.start()
    }


    override fun onClick(v: View) {

        var selectedOption = 0

        when (v.id) {
            R.id.option1 -> selectedOption = 1
            R.id.option2 -> selectedOption = 2
            R.id.option3 -> selectedOption = 3
            R.id.option4 -> selectedOption = 4
        }

        countDown.cancel()
        checkAnswer(selectedOption, v)
    }

    private fun checkAnswer(selectedOption: Int, view: View) {

        if (selectedOption == questionList.get(quesNum).correctAns) {
            //Right Answer
            when(questionList.get(quesNum).correctAns) {
                1 -> option1.setBackgroundColor(Color.GREEN)
                2 -> option2.setBackgroundColor(Color.GREEN)
                3 -> option2.setBackgroundColor(Color.GREEN)
                4 -> option2.setBackgroundColor(Color.GREEN)
            }

        } else {
            //Wrong Answer
            when(selectedOption) {
                1 -> option1.setBackgroundColor(Color.RED)
                2 -> option2.setBackgroundColor(Color.RED)
                3 -> option2.setBackgroundColor(Color.RED)
                4 -> option2.setBackgroundColor(Color.RED)
            }
        }

        changeQuestion()

    }

    private fun changeQuestion() {
        if (quesNum < questionList.size - 1) {

            quesNum++

            playAnim(question, 0, 0)
            playAnim(option1, 0, 1)
            playAnim(option2, 0, 2)
            playAnim(option3, 0, 3)
            playAnim(option4, 0, 4)

            quest_num.text = (quesNum + 1).toString() + "/" + questionList.size.toString()

            countdown.text = (10).toString()
            startTimer()


        } else {
            val intent4 = Intent(this, ScoreActivity::class.java)
            startActivity(intent4)
            this.finish()
        }

    }

    private fun playAnim(view: View, value: Int, viewNum: Int) {
        view.animate().alpha(value.toFloat()).scaleX(value.toFloat()).scaleY(value.toFloat())
            .setDuration(500).setStartDelay(100).setInterpolator(DecelerateInterpolator()).setListener(object: Animator.AnimatorListener{
                override fun onAnimationStart(p0: Animator?) {

                }

                override fun onAnimationEnd(p0: Animator) {
                    if (value == 0) {
                        when (viewNum) {
                            0 -> question.text = questionList.get(quesNum).question
                            1 -> option1.text = questionList.get(quesNum).optionA
                            2 -> option2.text = questionList.get(quesNum).optionB
                            3 -> option3.text = questionList.get(quesNum).optionC
                            4 -> option4.text = questionList.get(quesNum).optionD
                        }

                        playAnim(view, 1, viewNum)
                    }
                }

                override fun onAnimationCancel(p0: Animator?) {

                }

                override fun onAnimationRepeat(p0: Animator?) {

                }

            })
    }

}
