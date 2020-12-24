package com.example.helloworld

/*Super Class, Parent Class, Base Class
open class Vehicle{         //
    //Properties
    //Methods
} */

interface Derivable{
    val maxSpeed: Double
    fun drive(): String
    fun brake(){
        println("The drivable is braking")
    }
}

//Sub Class, Derived Class, Child Class of Vehicle
//Super Class, Parent Class, Base Class of ElectricCar
open class Cars(override val maxSpeed: Double, val name: String, val brand: String): Derivable{
    open var range: Double = 0.0

    fun extendRange(amount: Double){
        if (amount > 0)
            range += amount
    }

    //override fun drive(): String =  "Driving the Interface dirve"
    override fun drive(): String{
        return "Driving the Interface dirve"
    }

    open fun drive(distance: Double){
        println("Drove for $distance KM")
    }

}

//Sub Class, Derived Class, Child Class of Cars
class ElectricCar(maxSpeed: Double, name: String, brand: String,
                  batteryLife: Double) : Cars(maxSpeed,name,brand){

    var chargerType = "Type1"

    override var range = batteryLife * 6

    override fun drive(distance: Double) {
        println("Drove for $distance on Electricity")
    }

    override fun drive(): String{
        return "Drove for $range on Electricity"
    }

    override fun brake() {
        super.brake()
        println("brake inside of electric car")
    }

}

fun main(){
    var audiA3 = Cars(200.0,"A3", "Audi")
    var teslaS = ElectricCar(240.0,"Model S", "Tesla", 85.0)

    teslaS.extendRange(200.0)

    teslaS.drive()

    teslaS.brake()
    audiA3.brake()

    //Polymorphism
    audiA3.drive(200.0)
    teslaS.drive(200.0)
}