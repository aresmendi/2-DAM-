package com.jovanna.testapp.model

data class ErrorTopic(
    val id: String,
    val name: String,
    val errorCount: Int,
    var isSelected: Boolean = true
)