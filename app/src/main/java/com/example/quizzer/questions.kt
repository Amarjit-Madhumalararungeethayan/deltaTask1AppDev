package com.example.quizzer

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.os.*
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import androidx.appcompat.app.AppCompatActivity
import com.example.quizzer.databinding.ActivityQuestionsBinding
import java.util.*


var total = 0
var timeleft : Long = 0
var jff : CountDownTimer? = null
var temp = 0
class questions : AppCompatActivity() {

    private lateinit var binding: ActivityQuestionsBinding


    override fun onBackPressed() {
     // super.onBackPressed();
     // Not calling **super**, disables back button in current screen.
    }
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionsBinding.inflate(layoutInflater)
        val view = binding.root
        binding.timeChange.text = " "
        setContentView(view)

        binding.result.text = " Day of the Week is ... "
        mainCount(60000)
    }

    //countdown for turning screen black again
    private fun countDown1() {
        val countDown: CountDownTimer
        countDown = object : CountDownTimer(200, 10) {
            override fun onTick(millisUntil: Long) {
            }

            override fun onFinish() {
                binding.abc.setBackgroundResource(R.color.lightYellow)
            }
        }
        countDown.start()
    } //❤️

    //countdown to clear the inc/dec in time
    private fun countDown2() {
        val countDown: CountDownTimer
        countDown = object : CountDownTimer(1500, 1000) {
            override fun onTick(millisUntil: Long) {
            }

            override fun onFinish() {
                binding.timeChange.text = " "
            }
        }
        countDown.start()
    } //❤️

    //main timer with ranDate function enclosed
    private fun mainCount(x: Int):Int {
        val countDown: CountDownTimer
        val a = x.toLong()

        countDown = object : CountDownTimer(a, 1000) {



            override fun onTick(millisecsToFinish: Long) {
                binding.timer.text = (millisecsToFinish / 1000).toString()
                timeleft = millisecsToFinish
            }

            override fun onFinish() {
                val intent = Intent(baseContext, resultPage::class.java)
                finish()
                intent.putExtra("SCORE",total)
                startActivity(intent)

            }
        }

        countDown.start()


        //main function
        @SuppressLint("SetTextI18n")
        fun ranDate() {

            //choosing a random date and displaying it
            val w = (1..30).random()
            val b = (0..11).random()

            val monthName = when (b) {
                0 -> "January"
                1 -> "February"
                2 -> "March"
                3 -> "April"
                4 -> "May"
                5 -> "June"
                6 -> "July"
                7 -> "August"
                8 -> "September"
                9 -> "October"
                10 -> "November"
                11 -> "December"
                else -> "Error"
            }
            val c = (1900..2000).random()

            binding.day.text = "   " + w.toString() + "   "
            binding.month.text = "   " + monthName + "   "
            binding.year.text = "   " + c.toString() + "   "

            val resultDay = idDay(w, b, c)
            val days = mutableListOf(
                    "Monday",
                    "Tuesday",
                    "Wednesday",
                    "Thursday",
                    "Friday",
                    "Saturday",
                    "Sunday"
                )

            //choosing random options with the correct answer being one among them
            days.remove(resultDay)
            val dayOpt1Name = days[(0..5).random()]
            days.remove(dayOpt1Name)
            val dayOpt2Name = days[(0..4).random()]
            days.remove(dayOpt2Name)
            val dayOpt3Name = days[(0..3).random()]

            val newList = mutableListOf(resultDay, dayOpt1Name, dayOpt2Name, dayOpt3Name)

            val option1 = newList[(0..3).random()]
            newList.remove(option1)
            val option2 = newList[(0..2).random()]
            newList.remove(option2)
            val option3 = newList[(0..1).random()]
            newList.remove(option3)
            val option4 = newList[0]

            //displaying the random options
            binding.opt1?.text = option1
            binding.opt2.text = option2
            binding.opt3?.text = option3
            binding.opt4.text = option4



            //if option 1 is clicked
            binding.opt1?.setOnClickListener() {
                if (option1 == resultDay) {         //correct answer
                    total += 3
                    binding.score.text = " " + total.toString() + " "
                    vibrateNow()
                    binding.abc.setBackgroundResource(R.color.green)        //change bg to green
                    countDown1()             //changes bg back to black after a time period

                    successSound()                                  // gives a success ping

                    countDown2()




                    ranDate()

                } else {
                    binding.abc.setBackgroundResource(R.color.red)  //sets bg to red
                    countDown1()                             // changes bg to black after a short span
                    total -= 1
                    binding.score.text = " " + total.toString() + " "    //displays new score
                    failSound()                             //gives a wrong sound


                    binding.timeChange.text = "-5"              //results in a -5 (time left)
                    countDown2()

                    countDown.cancel()
                    binding.timer.text = " "
                    timeleft = timeleft - 5000
                    mainCount(timeleft.toInt() ) //reduces the time left by 5 seconds


                }
            }
            binding.opt2.setOnClickListener() {
                if (option2 == resultDay) {
                    total += 3
                    binding.score.text = " " + total.toString() + " "
                    vibrateNow()
                    binding.abc.setBackgroundResource(R.color.green)
                    countDown1()

                    successSound()

                    countDown2()

                    ranDate()

                } else {
                    //total = 0
                    binding.abc.setBackgroundResource(R.color.red)
                    countDown1()
                    total -= 1
                    binding.score.text = " " + total.toString() + " "
                    failSound()


                    binding.timeChange.text = "-5"
                    countDown2()

                    countDown.cancel()
                    binding.timer.text = " "

                    timeleft = timeleft - 5000
                    mainCount(timeleft.toInt())

                }

            }
            binding.opt3?.setOnClickListener() {
                if (option3 == resultDay) {
                    total += 3
                    binding.score.text = " " + total.toString() + " "
                    vibrateNow()
                    countDown1()
                    binding.abc.setBackgroundResource(R.color.green)

                    successSound()

                    countDown2()

                    ranDate()
                } else {
                    binding.abc.setBackgroundResource(R.color.red)
                    countDown1()
                    total -= 1
                    binding.score.text = " " + total.toString() + " "
                    failSound()

                    binding.timeChange.text = "-5"
                    countDown2()

                    countDown.cancel()
                    binding.timer.text = " "

                    timeleft = timeleft - 5000
                    mainCount(timeleft.toInt())


                }
            }
            binding.opt4.setOnClickListener() {
                if (option4 == resultDay) {
                    total += 3
                    binding.score.text = " " + total.toString() + " "
                    vibrateNow()
                    binding.abc.setBackgroundResource(R.color.green)
                    countDown1()

                    successSound()

                    countDown2()

                    ranDate()
                } else {
                    //total = 0
                    binding.abc.setBackgroundResource(R.color.red)
                    countDown1()
                    total -= 1
                    binding.score.text = " " + total.toString() + " "
                    failSound()

                    binding.timeChange.text = "-5"
                    countDown2()

                    countDown.cancel()
                    binding.timer.text = " "

                    timeleft = timeleft - 5000
                    mainCount(timeleft.toInt())

                }
            }
        }

        //starts showing questions
        ranDate()
        jff = countDown
        return 0
    }


    @SuppressLint("SetTextI18n", "ResourceAsColor")

        //all audios

        private fun successSound() {
            val ping = MediaPlayer.create(this, R.raw.success_ping)
            ping.start()
        } //❤️

        private fun failSound() {
            val ping = MediaPlayer.create(this, R.raw.wrong_ping)
            ping.start()
        } //❤️

        // for vibrations
        private fun vibrateNow() {
            val v = getSystemService(VIBRATOR_SERVICE) as Vibrator

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                v.vibrate(300)
            }
        } //❤️


        //function that identifies the day of the week
        private fun idDay(day: Int, month: Int, year: Int): String {

            val theDay = Date(year, month, day)

            return when (theDay.day) {
                5 -> "Wednesday"
                0 -> "Friday"
                2 -> "Sunday"
                6 -> "Thursday"
                4 -> "Tuesday"
                1 -> "Saturday"
                3 -> "Monday"
                else -> "Error"

            }
        } //❤️

    // function that saves the total and timeleft on changing orientation
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt("S1",total)
        outState.putLong("S2",timeleft)
    }

    //function that displays the saved data from prev orientation
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        val output1 = savedInstanceState.getInt("S1",0)
        val output2 = savedInstanceState.getLong("S2",0)

        binding.score.text = output1.toString()
        jff?.cancel()
        mainCount(output2.toInt())
    }
}

