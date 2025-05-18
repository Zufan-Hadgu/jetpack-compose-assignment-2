package com.example.todoapp.data.remote



import com.example.todoapp.data.local.dao.TodoDao
import com.example.todoapp.data.local.entitiy.TodoEntity
import com.example.todoapp.data.models.TodoDto
import com.example.todoapp.data.models.toDomain
import com.example.todoapp.data.remote.TodoApi
import com.example.todoapp.domain.model.Todo
import com.example.todoapp.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TodoRepositoryImpl(
    private val api: TodoApi,
    private val dao: TodoDao
) : TodoRepository {
    override fun getTodos(): Flow<List<Todo>> = flow {
        val cached = dao.getAllTodos()
        emit(cached.map { it.toDomain() })

        try {
            val remote = api.getTodos()
            dao.insertTodos(remote.map { it.toEntity() })
            emit(remote.map { it.toDomain() })
        } catch (e: Exception) {
            emit(cached.map { it.toDomain() })
        }
    }
}

fun TodoEntity.toDomain() = Todo(userId, id, title, completed)
fun TodoDto.toEntity() = TodoEntity(userId, id, title, completed)
