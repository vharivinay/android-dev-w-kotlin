package com.example.helloworld

fun main() {

    // type String
    var myInput = "Universe"

    myInput = "Multiverse"

    val myInput2 = "Observable" //Immutable variable, i.e., Cannot be changed live var

    // TODO: Mark places for tasks like "Add more functionality here."

    // type int
    var favNumber = 73

    /*
    Type    size    Min Value                       Max Value
    Byte	8	    -128	                        127
    Short	16	    -32768	                        32767
    Int 	32	-2,147,483,648 (-231)	            2,147,483,647 (231 - 1)
    Long	64	-9,223,372,036,854,775,808 (-263)	9,223,372,036,854,775,807 (263 - 1)

    Underscores can be used to make them more readable

     Type	Size (bits)	Significant bits	Exponent bits	Decimal digits
    Float	32	            24	                8	            6-7
    Double	64	            53	                11	            15-16
     */

    val myInt = 1
    val myDouble = 1.1
    val myFloat = 1.1f

    // Booleans: The type boolean is used to represent logical values.
    // I can have two possible values: true or false

    var isSunny = true
    isSunny = false

    // Character
    val letterChar = 'A'
    val digitChar = '7'

    // Strings

    val myStr = "Hello Universe"
    var firstCharInStr = myStr[0]
    var lastCharInStr = myStr[myStr.length - 1]

    println("First Character " + firstCharInStr)
    println("Last Charcater " + lastCharInStr)


    //print("Hello " + myInput)

}