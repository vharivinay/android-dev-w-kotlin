package com.example.helloworld

import java.lang.IllegalArgumentException

fun main(){
    var myCar = Car()
    println("brand is : ${myCar.myBrand}")
    myCar.maxSpeed = 240
    println("Maxspeed is ${myCar.maxSpeed}")
    println("Model is ${myCar.myModel}")

}

class Car(){
    lateinit var owner : String

    val myBrand: String = "BMW"
        //Custom Getter
    get(){
        return field.toLowerCase()
    }

    var maxSpeed : Int = 250

        get() = field     //This is generated automatically
        //Custom Setter
        set(value){
            field = if(value > 0) value else throw IllegalArgumentException("Maxspeed cannot be less than 0")
        }

    var myModel : String = "i8"
        private set             //Only available in this class


    init {
        this.owner = "Kepler"
        this.myModel = "M5"     //myCar.myModel is set locally
    }
}