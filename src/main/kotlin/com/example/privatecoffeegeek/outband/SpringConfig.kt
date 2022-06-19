package com.example.privatecoffeegeek.outband

import com.example.privatecoffeegeek.core.require.MessageRepository
import com.example.privatecoffeegeek.core.usecase.CommunicateWithAgentUseCase
import com.example.privatecoffeegeek.outband.mongo.MongoMessageEntityRepository
import com.example.privatecoffeegeek.outband.mongo.MongoMessageRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SpringConfig {
    @Bean
    fun messageRepository(
        repository: MongoMessageEntityRepository
    ) = MongoMessageRepository(repository)

    @Bean
    fun communicateWithAgentUseCase(repository: MessageRepository) = CommunicateWithAgentUseCase(repository)
}