package com.example.privatecoffeegeek.outband.mongo

import com.example.privatecoffeegeek.core.domain.Message
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface MongoMessageEntityRepository: ReactiveCrudRepository<Message,String>{

    fun findAllBySessionId(sessionId: String): Flux<Message>
}