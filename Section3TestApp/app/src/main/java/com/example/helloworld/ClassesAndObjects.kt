package com.example.helloworld

import android.app.backup.BackupAgent

fun main(){
    //Each of the following use of class is called an Instance
    var batman = Person("Bruce", "Wayne", 30)
    batman.hobby = "Fight Crime"
    batman.age = 32
    println("Bruce is ${batman.age} years old")
    batman.stateHobby()
    var john = Person()
    john.hobby = "Being no one"
    john.stateHobby()
    var superman = Person(lastName = "Kent")
}

//class Person constructor(Parameter1: DataType = "Default1", Parameter2: DataType = "Default2")
class Person constructor(firstName: String = "John", lastName: String = "Doe"){
    //Member Variables or Properties
    var age : Int? = null
    var hobby : String = "Code"
    var firstName : String? = null

    //Initialize Block
    init {
        this.firstName = firstName
        println("Initialized a new Person object with " +
                "firstName = $firstName and lastName = $lastName")
    }

    // Member Secondary Constructor
    constructor(firstName: String, lastName: String, age: Int)
            : this(firstName, lastName){
        this.age = age

        println("Initialized a new Person object with " +
                "firstName = $firstName and lastName = $lastName and age $age")
    }

    // Member Functions - Methods
    fun stateHobby(){
        println("$firstName\'s Hobby is $hobby")
    }
}