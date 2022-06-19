package com.example.privatecoffeegeek.core.usecase

import com.example.privatecoffeegeek.core.domain.Message
import com.example.privatecoffeegeek.core.require.MessageRepository
import com.example.privatecoffeegeek.dialogflow.DialogflowAgent
import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.dialogflow.cx.v3.SessionsClient
import com.google.cloud.dialogflow.cx.v3.SessionsSettings
import org.springframework.beans.factory.annotation.Value
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.io.FileInputStream
import java.time.LocalDateTime

class CommunicateWithAgentUseCase(
    private val repository: MessageRepository
) {

    @Value("\${dialogflow.cx.project.id}")
    private lateinit var projectId: String

    @Value("\${dialogflow.cx.agent.id}")
    private lateinit var agentId: String

    @Value("\${dialogflow.cx.location.id}")
    private lateinit var locationId: String

    private var agent: DialogflowAgent? = null

    fun sendToAgent(sessionId: String, text: String): Mono<Message> {
        if(agent == null ) agent = DialogflowAgent(projectId,agentId,locationId)
        repository.save(
            Message(sessionId = sessionId,
                sentAt = LocalDateTime.now(),
                text = text,
                author = "user"
            )
        )
        return repository.save(
            Message(
                sessionId = sessionId,
                sentAt = LocalDateTime.now(),
                text = agent?.askAndGetResponse(sessionId,text)
                    ?.joinToString(separator = " ")
                    { it } ?: "",
                author = "assistant"
            )
        )
    }

    fun findAllBySessionId(sessionId: String): Flux<Message> =
        repository.findAllBySessionId(sessionId)
}