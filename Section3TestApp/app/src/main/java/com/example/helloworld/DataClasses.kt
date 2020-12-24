package com.example.helloworld

import android.text.style.UpdateAppearance

data class User(val id: Long, var name: String) // Must have at least one parameter

fun main(){
    val user1 = User(1, "Newton")

    val name = user1.name
    println(name)
    user1.name = "Tesla"
    val user2 = User(1, "Tesla")

    println(user1 == (user2)) //Can also be compared by using user1.equals(user2)

    println("User Details : $user1")

    val updatedUser = user1.copy(name = "Elon")

    println(user1)
    println(updatedUser)

    println(updatedUser.component1()) //Gives id 1
    println(updatedUser.component2()) //Gives Elon

    val (id,name1) = updatedUser
    println("id=$id, name1=$name")


}