package com.example.privatecoffeegeek.core.domain

import java.time.LocalDateTime
import java.util.UUID

data class Message(
    val id: String = UUID.randomUUID().toString(),
    val sessionId: String,
    val sentAt: LocalDateTime,
    val text: String,
    val author: String
)
