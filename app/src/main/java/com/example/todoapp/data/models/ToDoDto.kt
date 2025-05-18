package com.example.todoapp.data.models


import com.example.todoapp.domain.model.Todo


data class TodoDto(
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean
)

fun TodoDto.toDomain(): Todo = Todo(userId, id, title, completed)
