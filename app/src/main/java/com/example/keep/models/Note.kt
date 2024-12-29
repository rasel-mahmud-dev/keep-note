package com.example.keep.models

data class Note(
    val title: String,
    val content: String,
    val createdAt: String,
    val updatedAt: String,
    val id: String?,
    val deviceId: String
)
