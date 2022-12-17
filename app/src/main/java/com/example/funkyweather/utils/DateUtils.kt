package com.example.funkyweather.utils


import java.util.*
import kotlin.math.abs

fun getDurationBetweenTwoDates(start: Date?, end: Date?): Long {
    if (start == null || end == null) return 0

    return abs(start.time - end.time)
}