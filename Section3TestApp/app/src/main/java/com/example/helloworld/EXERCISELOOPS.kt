package com.example.helloworld

fun main(){
    //Exercise 1

    for(i in 0 until 10000){
        if(i==9001){
        print(" $i Its over 9000!\n")
        }
    }

    //Exercise 2

    var humidityLevel = 80
    var humidity = "humid"

    while (humidity == "humid"){
        humidityLevel -= 5
        print(" humidityLevel = $humidityLevel")
        if(humidityLevel < 60){
            humidity = "comfy"
            println("\nIts comfy now!")
            println("humidity = $humidity")
        }
    }
}
