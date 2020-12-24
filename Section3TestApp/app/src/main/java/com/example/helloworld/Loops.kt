package com.example.helloworld

fun main(){
    //while Loops
    // While loop executes a block of code repeatedly as long as a given condition is true
    var x = 1
    while (x <= 10){
        print("$x")
        x += 2
    }
    println("\nWhile loop is done")

    x = 100
    while (x >= 0){
        print(" $x ")
        x -= 2
    }
    println("\nExercise While loop is done")

    //Do While Loops
    // The do-while loop is similar to while loop except that it
    // tests the condition at the end of the loop.
    // This means that it will at least execute the body once
    x = 1
    do{
        print(" $x ")
        x++
    }while (x <= 10)
    println("\nDo while loop is done")

    //More While Loops
    var feltTemp = "Cold"
    var roomTemp = 10
    while (feltTemp == "Cold"){
        print(" $roomTemp ")
        roomTemp++
        if(roomTemp >= 20) {
            feltTemp = "Comfy"
            println("\nIt's comfy now")
        }else println("\n I'm Freezing")
    }

    //For Loops
    // A for-loop is used to iterate through ranges, arrays, collections, or anything
    // that provides an iterator (You’ll learn about iterator, arrays, ranges and collections
    // in a future lecture
    for (num in 1..10){
        print(" $num ")
    }
    println("____")

    for (i in 1 until 10){
        print(" $i ")
    }
    println("____")

    for (i in 10 downTo 1){
        print(" $i ")
    }
    println("____")

    for (i in 10 downTo 1 step 2){ //for (i in 10.downTo.(1).step(2))
        print(" $i ")
    }
    println("____")


}
