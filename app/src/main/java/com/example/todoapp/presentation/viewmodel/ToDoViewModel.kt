package com.example.todoapp.presentation.viewmodel

import com.example.todoapp.domain.UseCase.GetTodosUseCase


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.domain.model.Todo

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class TodoListViewModel(
    private val getTodosUseCase: GetTodosUseCase
) : ViewModel() {
    private val _todos = MutableStateFlow<List<Todo>>(emptyList())
    val todos: StateFlow<List<Todo>> = _todos

    init {
        viewModelScope.launch {
            getTodosUseCase().collect {
                _todos.value = it
            }
        }
    }
}

