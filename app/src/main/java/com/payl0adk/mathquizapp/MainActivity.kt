package com.payl0adk.mathquizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {
    // add variables
    private var correctanswer: Int = 0
    private var equation: String = ""
    private var firstnum = 0
    private var secondnum = 0
    // Stats
    private var corrects = 0
    private var wrong = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        training() // Start
    }
    private fun generatenum(): ArrayList<Int> {
        // Complication
        // Easy
        firstnum = (1..11).random()
        secondnum = (1..11).random()
        // Medium
        if(corrects - wrong > 20){
            firstnum = (11..40).random()
            secondnum = (1..11).random()
        }
        // Hard
        if(corrects - wrong > 35){
            firstnum = (11..40).random()
            secondnum = (11..40).random()
        }
        // If 1 is the sum, if 2 is the difference, if 3 is the multiplication
        val randomize = (1..3).random()
        if(randomize == 1){ correctanswer = firstnum + secondnum; equation="$firstnum + $secondnum"}
        if(randomize == 2){ correctanswer = firstnum - secondnum; equation="$firstnum - $secondnum"}
        if(randomize == 3){ correctanswer = firstnum * secondnum; equation="$firstnum * $secondnum"}
        // Generating the options
        val option1 = correctanswer
        val option2 = correctanswer - (1..13).random()
        val option3 = correctanswer + (1..5).random()
        val option4 = correctanswer + (6..14).random()
        // Creating the options list
        val optionlist = ArrayList<Int>()
        optionlist.add(0, option1)
        optionlist.add(1, option2)
        optionlist.add(2, option3)
        optionlist.add(3, option4)
        return optionlist
    }
    private fun training(){
        val mylist = generatenum().shuffled() // Mix the list
        val adapter = ArrayAdapter<Int>(this, R.layout.my_list_item, mylist) // Creating adapter
        val listview: ListView = findViewById(R.id.mylistview)
        listview.adapter = adapter
        val equationtext = findViewById<TextView>(R.id.textView2) // Creating equation(string)
        equationtext.text = equation
        // Stats
        val correctsanswers = findViewById<TextView>(R.id.correctanswers)
        val wronganswers = findViewById<TextView>(R.id.wronganswers)
        correctsanswers.text = "${resources.getString(R.string.correct)} $corrects"
        wronganswers.text = "${resources.getString(R.string.wrong)} $wrong"
        // Adding on click listener for check the answers
        listview.setOnItemClickListener{parent, view, position, id ->
            val clickeditem = mylist[position] // We get answer
            if(clickeditem == correctanswer){
                corrects++
                training()
            }
            else{
                wrong++
                training()
            }
        }
    }
}
