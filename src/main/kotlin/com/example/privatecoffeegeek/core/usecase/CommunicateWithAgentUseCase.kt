package com.example.privatecoffeegeek.core.usecase

import com.example.privatecoffeegeek.dialogflow.DialogflowAgent
import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.dialogflow.cx.v3.SessionsClient
import com.google.cloud.dialogflow.cx.v3.SessionsSettings
import org.springframework.beans.factory.annotation.Value
import java.io.FileInputStream

class CommunicateWithAgentUseCase(
) {

    @Value("\${dialogflow.cx.project.id}")
    private lateinit var projectId: String

    @Value("\${dialogflow.cx.agent.id}")
    private lateinit var agentId: String

    @Value("\${dialogflow.cx.location.id}")
    private lateinit var locationId: String

    private var agent: DialogflowAgent? = null

    fun sendToAgent(sessionId: String, text: String): String {
        if(agent == null ) agent = DialogflowAgent(projectId,agentId,locationId)

        return agent?.askAndGetResponse(sessionId,text)?.joinToString(separator = " "){ it } ?: ""
    }
}