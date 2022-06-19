package com.example.privatecoffeegeek.inbound.rest

import com.example.privatecoffeegeek.core.domain.Message
import com.example.privatecoffeegeek.core.usecase.CommunicateWithAgentUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class DialogflowController(
    private val useCase: CommunicateWithAgentUseCase
) {

    @PostMapping("/agent")
    fun sendMessageToAgent(@RequestBody request: Request): Mono<ResponseEntity<Message>> =
        useCase.sendToAgent(request.sessionId,request.text)
            .map { ResponseEntity.ok(it) }

    @GetMapping("/messages/{id}")
    fun getMessagesBySessionId(@PathVariable id: String): Flux<Message> =
        useCase.findAllBySessionId(id)

    class Request(val sessionId: String,
                  val text: String)
}