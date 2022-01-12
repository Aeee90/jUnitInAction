package aeee.api.junitInAction.calculator

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CalculatorTest {

    @Test
    @DisplayName("더하기 테스트")
    fun testAdd() {
        val calculator = Calculator()
        val result = calculator.add(10.0, 50.0)

        Assertions.assertEquals(60.0, result)
    }
}