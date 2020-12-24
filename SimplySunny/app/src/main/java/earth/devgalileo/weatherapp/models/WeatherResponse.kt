package earth.devgalileo.weatherapp.models

import java.io.Serializable
import java.util.*

data class WeatherResponse (
    val coord : Coord,
    val weather : List<Weather>,
    val base : String,
    val main : Main,
    val visibility : Int,
    val wind : Wind,
    val clouds : Clouds,
    val dt : Int,
    val sys : Sys,
    val timeZone: TimeZone,
    val id : Int,
    val name : String,
    val cod : Int
) : Serializable