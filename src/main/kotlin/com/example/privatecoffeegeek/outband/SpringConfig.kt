package com.example.privatecoffeegeek.outband

import com.example.privatecoffeegeek.core.usecase.CommunicateWithAgentUseCase
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SpringConfig {

    @Bean
    fun communicateWithAgentUseCase() = CommunicateWithAgentUseCase()
}