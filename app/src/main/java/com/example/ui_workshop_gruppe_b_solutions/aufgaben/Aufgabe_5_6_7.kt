package com.example.ui_workshop_gruppe_b_solutions.aufgaben

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph

@Composable
fun WorkshopApp(
    tasks: List<String>,
    onChecked:(Boolean)->Unit,
    hours:Int,
    minutes:Int,
    seconds:Int,
    onPlay:()->Unit,
    onReset:()->Unit,
    onFloatingActionButtonClick:()->Unit,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val navGraph = remember(navController){
        navController.createGraph("taskList"){
            composable(route="taskList"){
                TaskList(
                    taskList = tasks,
                    onChecked = onChecked
                )
            }
            composable(route="timer"){
                Timer(
                    hours = hours,
                    minutes = minutes,
                    seconds = seconds,
                    onPlay = onPlay,
                    onReset = onReset)
            }
        }
    }
    Scaffold(
        bottomBar = {BottomNavigationBar(
            onListClick = {
                navController.navigate("taskList"){launchSingleTop = true}
            },
            onPlayClick = {
                navController.navigate("timer"){launchSingleTop = true}
            })},
        floatingActionButton = {
            FloatingActionButton(onClick = onFloatingActionButtonClick) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "")
            }
        }
    ) {
        NavHost(
            navController = navController,
            graph = navGraph,
            modifier = Modifier.padding(it))
    }
}

@Preview
@Composable
private fun WorkshopAppPreview() {
    val taskList = List(30){
        ""
    }
    val takListState = remember {
        taskList.toMutableStateList()
    }
    WorkshopApp(
        tasks = takListState,
        onChecked = {},
        hours = 12,
        minutes = 12,
        seconds = 12,
        onPlay = {},
        onReset = {},
        onFloatingActionButtonClick = {})
}

