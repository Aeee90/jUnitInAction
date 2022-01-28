package aeee.api.junitInAction.controller

import kotlin.jvm.Throws

interface Request {
    fun getName(): String
}

interface Response {}

interface RequestHandler {
    @Throws(Exception::class)
    fun process(request: Request): Response
}

interface Controller {
    fun processRequest(request: Request): Response
    fun addHandler(request: Request, requestHandler: RequestHandler)
}

class ErrorResponse(
        val request: Request,
        val exception: Exception
): Response

class DefaultController: Controller {

    private val requestHandler = HashMap<String, RequestHandler>()

    private fun getHandler(request: Request): RequestHandler = this.requestHandler.getOrElse(request.getName()) {
        throw RuntimeException("Cannot find handler for request name [${request.getName()}]")
    }

    override fun processRequest(request: Request): Response {
        return try {
            this.getHandler(request).process(request)
        } catch (exception: Exception) {
            ErrorResponse(request, exception)
        }
    }

    override fun addHandler(request: Request, requestHandler: RequestHandler) {
        if(this.requestHandler.containsKey(request.getName())) {
            throw RuntimeException("A request handler has already been registered for request name [${request.getName()}]")
        } else {
            this.requestHandler[request.getName()] = requestHandler;
        }
    }
}