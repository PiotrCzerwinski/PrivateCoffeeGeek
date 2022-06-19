package com.example.privatecoffeegeek.outband.mongo

import com.example.privatecoffeegeek.core.domain.Message
import com.example.privatecoffeegeek.core.require.MessageRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class MongoMessageRepository(
    private val repository: MongoMessageEntityRepository
): MessageRepository {
    override fun findAllBySessionId(sessionId: String): Flux<Message> =
        repository.findAllBySessionId(sessionId)

    override fun save(message: Message): Mono<Message> =
        repository.save(message)
}