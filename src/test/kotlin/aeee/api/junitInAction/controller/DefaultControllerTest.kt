package aeee.api.junitInAction.controller

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class DefaultControllerTest {

    private lateinit var controller: Controller
    private lateinit var request: Request

    @BeforeEach
    fun init() {
        this.controller = DefaultController()
        this.request = SampleRequest()
    }

    @Test
    @DisplayName("핸들러 추가 테스트")
    fun testAddHandler() {
        controller.addHandler(request, SampleRequestHandler())
        val sampleResponse = controller.processRequest(request)

        Assertions.assertNotEquals("Must not return a null response", sampleResponse)
        Assertions.assertEquals( SampleResponse(), sampleResponse, "Response should be of type SampleResponse")
    }

    @Test
    @DisplayName("처리 핸들러 테스트")
    fun testProcessHandler() {
        controller.addHandler(request, SampleExceptionHandler())
        val errorResponse = controller.processRequest(request)

        Assertions.assertNotEquals("Must not return a null response", errorResponse)
        Assertions.assertEquals(ErrorResponse::class.java, errorResponse.javaClass, "Response should be of type SampleResponse")
    }

    @Test
    @DisplayName("에러 처리 테스트")
    fun testProcessRequestAnswersErrorResponse() {
        controller.addHandler(request, SampleExceptionHandler())

        Assertions.assertThrows(RuntimeException::class.java) {
            controller.processRequest(request)
        }
    }

    private class SampleRequest: Request {
        override fun getName(): String = "sample"
    }

    private class SampleResponse : Response {
        companion object {
            private const val NAME = "Test"
        }

        fun getName() = NAME

        override fun equals(other: Any?): Boolean {
            return if(other == null || other !is SampleResponse) {
                false
            } else {
                getName() == other.getName()
            }
        }

        override fun hashCode(): Int {
            return NAME.hashCode()
        }
    }

    private class SampleRequestHandler: RequestHandler {
        override fun process(request: Request): Response = SampleResponse()
    }

    private class SampleExceptionHandler: RequestHandler {
        override fun process(request: Request): Response {
            throw Exception("error processing request")
        }
    }
}