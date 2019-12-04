package com.exail.intervaltimer.data

import com.exail.intervaltimer.R

class Sound private constructor(val name: String, val soundResource: Int) {

    companion object {
        fun AnalogWatch() = Sound("Analog Watch", R.raw.analog_watch)
        fun AnsweringMachine() = Sound("Answering Machine", R.raw.answering_machine_bee)
        fun FireTruck() = Sound("Fire Truck", R.raw.fire_truck)
        fun FrontDeskBell() = Sound("Front Desk Bell", R.raw.front_desk_bells)
        fun SchoolBell() = Sound("School Bell", R.raw.school_bell)
        fun TollingBell() = Sound("Tolling Bell", R.raw.tolling_bell)

        fun soundList() = listOf<Sound>(
            AnalogWatch(),
            AnsweringMachine(),
            FireTruck(),
            FrontDeskBell(),
            SchoolBell(),
            TollingBell()
        )
    }
}