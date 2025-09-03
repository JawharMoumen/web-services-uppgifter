fun main() {
    // Uppgift #3

    // Vanlig Array
    val minArray: Array<Int> = arrayOf(10, 20, 30, 40, 50)

    // ArrayList
    val minArrayList: ArrayList<String> = arrayListOf("Hej", "Kotlin", "ArrayList")

    // Sista elementet i Array
    println("Sista elementet i array: ${minArray[minArray.size - 1]}")

    // Sista elementet i ArrayList
    println("Sista elementet i ArrayList: ${minArrayList[minArrayList.size - 1]}")

    println("----------")



    //  Uppgift #5

    // Skapa en array med initial storlek 5 (alla värden börjar som 0)
    val nyArray = Array(5) { 0 }

    // Tilldela nya värden till arrayen
    for (i in nyArray.indices) {
        nyArray[i] = i * 10   // fyller med 0, 10, 20, 30, 40
    }

    // Skriv ut alla element i arrayen
    println("Element i nyArray:")
    for (element in nyArray) {
        println(element)
    }
}
