package com.payl0adk.mathquizapp

import org.junit.Test

class ExampleUnitTest {
    var iterations: Int = 0
    var firstnum: Int = 0
    var secondnum: Int = 0
    var randomize: Int = 0
    var correctanswer: Int = 0
    var equation: String = ""
    @Test
    fun addition_isCorrect() {
        while(iterations < 20){
            println("Iteration: $iterations")
            firstnum = (1..11).random()
            secondnum = (1..11).random()
            randomize = (1..3).random()
            if(randomize == 1){ correctanswer = firstnum + secondnum; equation="$firstnum + $secondnum"}
            if(randomize == 2){ correctanswer = firstnum - secondnum; equation="$firstnum - $secondnum"}
            if(randomize == 3){ correctanswer = firstnum * secondnum; equation="$firstnum * $secondnum"}
            println("Generated equation: $equation")
            println("Correct answer is: $correctanswer")
            iterations++
        }
    }
    @Test
    fun testmutable() {
        var testlist = mutableListOf<Int>(1, 2, 3, 4)
        testlist.shuffle()
        println(testlist)
    }
}