package com.example.helloworld

fun main(){
    var phone1 = MobilePhone("Android", "Samsung", "Galaxy s10")
    var phone2 = MobilePhone("Android", "Samsung", "Galaxy Note 9")
    var phone3 = MobilePhone("iOS", "Apple", "iPhone 11 Pro")

    var nowBattery = MobilePhone().chargeBattery(45)

    println(nowBattery)

}

class MobilePhone  (osName : String = "OS", brand : String = "Brand", model : String = "QWERT123"){

    private var battery = 40

    fun chargeBattery(chargedBy : Int){
        println("Battery was at $battery and after charging, it is at ${battery + chargedBy}")
        battery += chargedBy

    }

    init {
        println("Initialized a new Mobile Phone object with " +
                "osName = $osName brand = $brand model = $model")
    }
}