package com.example.helloworld

import java.util.ArrayList

fun main(){

       val myArrayList: ArrayList<Double> = ArrayList<Double>(5)
    myArrayList.add(24.5)
    myArrayList.add(87.5)
    myArrayList.add(65.9)
    myArrayList.add(34.2)
    myArrayList.add(31.9)

    println(myArrayList)

    var j = 0
    var sumArray: Double = 0.0

    for(i in myArrayList){

        println(myArrayList.get(index = j))

        sumArray += myArrayList.get(index = j)
        j++
    }

    var ave: Double = sumArray/j
    println("Average is $ave")

    /* Simple Way!!
    var total = 0.0
    for (i in myArrayList){
        total += i
    }
    var average = total / myArrayList.size
    println("Avarage is " + average)
    */


}

