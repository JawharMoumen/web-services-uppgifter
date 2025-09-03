fun main() {
	// Variabler (kan ändras)
	var heltal: Int = 10
	var text: String = "Hej"
	var decimaltal: Double = 3.14
	var stortTal: Long = 1000000000L
	var santEllerFalskt: Boolean = true

	// Konstanter (val = kan inte ändras)
	val KONSTANT_INT: Int = 42
	val KONSTANT_STRING: String = "Konstant text"
	val KONSTANT_DOUBLE: Double = 2.71
	val KONSTANT_LONG: Long = 9999999999L
	val KONSTANT_BOOLEAN: Boolean = false

	// Testa utskrifter
	println(heltal + KONSTANT_INT)           // Int + Int
	println(text + " " + KONSTANT_STRING)    // String + String
	println(decimaltal + KONSTANT_DOUBLE)    // Double + Double
	println(stortTal + KONSTANT_LONG)        // Long + Long
	println(santEllerFalskt && KONSTANT_BOOLEAN) // Boolean + Boolean (logiskt OCH)
}
