package aeee.api.junitInAction.controller

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DefaultControllerTest {

    private var controller: DefaultController? = null

    @BeforeEach
    fun init() {
        controller = DefaultController()
    }


    @Test
    fun addHandler() {
        val sampleRequest = SampleRequest()
        this.controller!!.addHandler(sampleRequest, SampleRequestHandler())
    }

    private class SampleRequest: Request {
        override fun getName(): String = "sample"
    }

    private class SampleResponse : Response

    private class SampleRequestHandler: RequestHandler {
        override fun process(request: Request): Response = SampleResponse()
    }
}