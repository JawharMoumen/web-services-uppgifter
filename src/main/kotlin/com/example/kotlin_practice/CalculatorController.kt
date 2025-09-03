import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/calc")
class CalculatorController {

    // http://localhost:8080/calc/add/10/5
    @GetMapping("/add/{a}/{b}")
    fun add(@PathVariable a: Int, @PathVariable b: Int): Int {
        return a + b
    }

    // http://localhost:8080/calc/subtract/10/5
    @GetMapping("/subtract/{a}/{b}")
    fun subtract(@PathVariable a: Int, @PathVariable b: Int): Int {
        return a - b
    }

    // http://localhost:8080/calc/multiply/10/5
    @GetMapping("/multiply/{a}/{b}")
    fun multiply(@PathVariable a: Int, @PathVariable b: Int): Int {
        return a * b
    }

    // http://localhost:8080/calc/divide/10/5
    @GetMapping("/divide/{a}/{b}")
    fun divide(@PathVariable a: Int, @PathVariable b: Int): Double {
        if (b == 0) throw IllegalArgumentException("Division by zero is not allowed!")
        return a.toDouble() / b
    }
}
