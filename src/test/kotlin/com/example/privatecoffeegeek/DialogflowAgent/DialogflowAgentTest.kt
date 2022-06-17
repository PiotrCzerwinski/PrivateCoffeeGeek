package com.example.privatecoffeegeek.DialogflowAgent

import com.example.privatecoffeegeek.dialogflow.DialogflowAgent
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class DialogflowAgentTest {

    @Value("\${dialogflow.cx.project.id}")
    private lateinit var projectId: String

    @Value("\${dialogflow.cx.agent.id}")
    private lateinit var agentId: String

    @Value("\${dialogflow.cx.location.id}")
    private lateinit var locationId: String

    @Test
    fun `should connect with agent and get response`(){
        val agent = getAgent()
        val test = agent.sendMessage("Hej")
        val text = test.responseMessagesList
            .flatMap { it.text.textList}.joinToString()
        println(text)
        assert(text.isNotEmpty())
    }

    fun getAgent(): DialogflowAgent =
        DialogflowAgent(
            projectId,
            agentId,
            locationId
        )
}