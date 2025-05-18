package com.example.todoapp.presentation.Screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.example.todoapp.presentation.viewmodel.TodoListViewModel


@Composable
fun TodoListScreen(viewModel: TodoListViewModel, navController: NavController) {
    val todos by viewModel.todos.collectAsState()

    LazyColumn {
        items(todos) { todo ->
            TodoCard(todo = todo) {
                navController.navigate("detail/${todo.id}")
            }
        }
    }
}
