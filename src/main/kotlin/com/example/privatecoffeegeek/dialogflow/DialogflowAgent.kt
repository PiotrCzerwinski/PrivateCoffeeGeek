package com.example.privatecoffeegeek.dialogflow

import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.dialogflow.cx.v3.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import java.io.FileInputStream
import java.util.UUID
import kotlin.properties.Delegates

class DialogflowAgent(
    private val projectId: String,
    private val agentId: String,
    private val locationId: String
) {
    private var sessionsClient: SessionsClient by Delegates.notNull()

    init {
        val credentials = GoogleCredentials.fromStream(FileInputStream(JSON_AUTH_PATH))
            .createScoped("https://www.googleapis.com/auth/cloud-platform")
        
        val sessionsSettingsBuilder = SessionsSettings.newBuilder()
        sessionsSettingsBuilder.endpoint = "$locationId-dialogflow.googleapis.com:443"
        sessionsSettingsBuilder.setCredentialsProvider {
            credentials
        }
        this.sessionsClient = SessionsClient.create(sessionsSettingsBuilder.build())
    }

    fun sendMessage(text: String): QueryResult {
        val session = SessionName.ofProjectLocationAgentSessionName(
            projectId, locationId, agentId, UUID.randomUUID().toString()
        )

        val queryInput = QueryInput.newBuilder()
            .setText(TextInput.newBuilder().setText(text))
            .setLanguageCode(LANGUAGE_CODE)
            .build()

        val detectIntentRequest =
            DetectIntentRequest.newBuilder()
                .setSession(session.toString())
                .setQueryInput(queryInput)
                .build()

        return sessionsClient.detectIntent(detectIntentRequest)
            .queryResult
    }
    companion object{
        const val JSON_AUTH_PATH="C:\\PRACA INŻYNIERSKA\\yourprivatecoffeegeek_auth.json"
        const val LANGUAGE_CODE="Polish-pl"
    }
}