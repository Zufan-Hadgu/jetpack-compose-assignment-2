package com.example.todoapp.presentation.Screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todoapp.presentation.viewmodel.TodoDetailViewModel


@Composable
fun TodoDetailScreen(
    todoId: Int,
    viewModel: TodoDetailViewModel,
    navController: NavController
) {
    LaunchedEffect(todoId) {
        viewModel.loadTodo(todoId)
    }

    val todo by viewModel.todo.collectAsState()

    todo?.let {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("ID: ${it.id}")
            Text("Title: ${it.title}")
            Text("Status: ${if (it.completed) "Completed" else "Pending"}")
            Button(onClick = { navController.popBackStack() }) {
                Text("Back")
            }
        }
    }
}
