package com.example.helloworld

var c = 7
fun main(){
    myFunction(5)
}
//________This a is parameter
fun myFunction(a:Int){
    // this a is a variable
    var b = a   // variable a = a parameter
    println("a is $b")
}