package com.example.todoapp.data.remote


import com.example.todoapp.data.models.TodoDto
import retrofit2.http.GET



interface TodoApi {
    @GET("/todos")
    suspend fun getTodos(): List<TodoDto>
}
