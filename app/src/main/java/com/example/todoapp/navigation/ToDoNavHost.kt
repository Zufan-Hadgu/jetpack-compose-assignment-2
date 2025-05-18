package com.example.todoapp.navigation


import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.*

import com.example.todoapp.data.local.TodoDatabase

import com.example.todoapp.presentation.viewmodel.TodoDetailViewModel
import com.example.todoapp.presentation.viewmodel.TodoListViewModel
import android.content.Context
import com.example.todoapp.data.RetrofitInstance
import com.example.todoapp.data.remote.TodoRepositoryImpl
import com.example.todoapp.domain.UseCase.GetTodosUseCase
import com.example.todoapp.presentation.Screen.TodoDetailScreen
import com.example.todoapp.presentation.Screen.TodoListScreen

@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun NavGraph(context: Context, startDestination: String = "list") {
    val navController = rememberNavController()


    val dao = TodoDatabase.getInstance(context).todoDao()
    val api = RetrofitInstance.api
    val repository = TodoRepositoryImpl(api, dao)
    val useCase = GetTodosUseCase(repository)

    val listViewModel = TodoListViewModel(useCase)
    val detailViewModel = TodoDetailViewModel(useCase)

    NavHost(navController = navController, startDestination = startDestination) {
        composable("list") {
            TodoListScreen(viewModel = listViewModel, navController = navController)
        }
        composable("detail/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: return@composable
            TodoDetailScreen(todoId = id, viewModel = detailViewModel, navController = navController)
        }
    }
}
