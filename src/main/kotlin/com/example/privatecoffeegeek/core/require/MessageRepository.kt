package com.example.privatecoffeegeek.core.require

import com.example.privatecoffeegeek.core.domain.Message
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface MessageRepository {
    fun findAllBySessionId(sessionId:String): Flux<Message>
    fun save(message: Message): Mono<Message>
}