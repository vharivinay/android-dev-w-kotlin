package com.example.helloworld

fun main(){
    //argument
    var result = addUp(5,3)
    println("result is $result")

    var mean = average(2.717,3.145)
    println("Average is $mean")

}
//Method - a Method is a Function within a class
//Parameter (input)
fun addUp(a: Int, b: Int) : Int{
    //output
    return a+b
}

fun average(x: Double, y: Double) : Double{
    //op
    return (x+y)/2
}

fun myFunction(){
    println("myFunction is called")
}