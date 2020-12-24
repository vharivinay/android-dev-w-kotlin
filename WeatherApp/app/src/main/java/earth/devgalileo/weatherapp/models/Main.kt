package earth.devgalileo.weatherapp.models

import java.io.Serializable

data class Main(
    val temp: Double,
    val pressure: Double,
    val humidity: Int,
    val tempMin: Double,
    val tempMax: Double,
    val sea_level: Double,
    val ground_level: Double
) : Serializable