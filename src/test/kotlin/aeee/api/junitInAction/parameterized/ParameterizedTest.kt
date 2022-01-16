package aeee.api.junitInAction.parameterized

import aeee.api.junitInAction.calculator.Calculator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@ExtendWith
class ParameterizedTest {

    private var expected: Double? = null
    private var valueOne: Double? = null
    private var valueTwo: Double? = null

    companion object {
        @JvmStatic
        fun getTestParameters(): Stream<Arguments> = Stream.of(
            Arguments.of(2.0, 1.0, 1.0),
            Arguments.of(3.0, 2.0, 1.0),
            Arguments.of(4.0, 3.0, 1.0)
        )
    }

    @ParameterizedTest
    @MethodSource("getTestParameters")
    fun sum(expected: Double, valueOne: Double, valueTwo: Double) {
        val calculator = Calculator()
        Assertions.assertEquals(expected, calculator.add(valueOne, valueTwo))
    }
}