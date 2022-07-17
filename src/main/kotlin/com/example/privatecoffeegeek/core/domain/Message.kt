package com.example.privatecoffeegeek.core.domain

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime
import java.util.UUID

data class Message(
    val id: String = UUID.randomUUID().toString(),
    val sessionId: String,
    @JsonFormat(
        shape = JsonFormat.Shape.STRING,
        pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
        timezone = JsonFormat.DEFAULT_TIMEZONE
    )
    val sentAt: LocalDateTime,
    val message: String,
    val author: String
)
