package com.example.todoapp.presentation.Screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todoapp.presentation.viewmodel.TodoDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
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
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Task Detail") },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.Filled.ArrowBack, contentDescription = "Go Back")
                        }
                    }
                )
            }
        ) { paddingValues ->
            Column(modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
            ) {
                Text("ID: ${it.id}")
                Text("Title: ${it.title}")
                Text("Status: ${if (it.completed) "✅ Completed" else "⏳ Pending"}")
            }
        }
    }
}
