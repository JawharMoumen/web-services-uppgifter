package com.example.kotlin_practice

fun main() {
    // 1. Räkna upp från 0 till 25
    for (i in 0..25) {
        println(i)
    }

    println("----------")

    // 2. Räkna ner från 20 till 5
    for (i in 20 downTo 5) {
        println(i)
    }

    println("----------")

    // 3. Räkna upp vartannat värde till 50
    for (i in 0..50 step 2) {
        println(i)
    }

    println("----------")

    // 4. Skriv ut varje element i en array
    val minArray = arrayOf("Kotlin", "är", "kul", "att", "lära sig")
    for (element in minArray) {
        println(element)
    }
}
