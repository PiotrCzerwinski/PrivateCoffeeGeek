package com.example.privatecoffeegeek.inbound.rest

import com.example.privatecoffeegeek.core.usecase.CommunicateWithAgentUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class DialogflowController(
    private val useCase: CommunicateWithAgentUseCase
) {

    @PostMapping("/agent")
    fun sendMessageToAgent(@RequestBody request: Request): Mono<ResponseEntity<String>> {
        val response = useCase.sendToAgent(request.sessionId,request.text)
        return if(response.isNotBlank()) Mono.just(ResponseEntity(response,HttpStatus.OK)) else Mono.just(ResponseEntity(HttpStatus.NOT_FOUND))
    }

    class Request(val sessionId: String,
                  val text: String)
}