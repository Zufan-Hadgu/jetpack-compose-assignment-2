package com.example.todoapp.presentation.viewmodel



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.domain.UseCase.GetTodosUseCase
import com.example.todoapp.domain.model.Todo


import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class TodoDetailViewModel(
    private val getTodosUseCase: GetTodosUseCase
) : ViewModel() {
    private val _todo = MutableStateFlow<Todo?>(null)
    val todo: StateFlow<Todo?> = _todo

    fun loadTodo(id: Int) {
        viewModelScope.launch {

            getTodosUseCase().collect { todos ->
                val foundTodo = todos.find { it.id == id }
                _todo.value = foundTodo
            }
        }
    }
}