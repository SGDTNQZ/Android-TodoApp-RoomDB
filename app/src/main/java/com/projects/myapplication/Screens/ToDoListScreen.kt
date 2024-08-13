package com.projects.myapplication.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.projects.myapplication.R
import com.projects.myapplication.ToDo
import com.projects.myapplication.ViewModels.ToDoViewModel
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun ToDoListScreen(viewModel: ToDoViewModel){

    val todoList by viewModel.todoList.observeAsState()
    var inputText by remember{
        mutableStateOf("")
    }

    Column (modifier = Modifier
        .fillMaxHeight()
        .padding(8.dp)){

        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly) {
            OutlinedTextField(value = inputText, onValueChange = { inputText = it })
            Button(onClick = { viewModel.addToDo(inputText)
            inputText = ""}) {
                Text(text = "Add")
            }
        }

        todoList?.let {
            LazyColumn (
                content = {
                    itemsIndexed(it){
                            index: Int, item: ToDo ->  ToDoItem(item = item,
                                onDelete = {viewModel.deleteToDo(item.id )})
                    }
                }
            )
        }?: Text(text = "No items yet",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 16.sp)

    }
}

@Composable
fun ToDoItem(item : ToDo, onDelete : () -> Unit){
    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clip(RoundedCornerShape(16.dp))
        .background(MaterialTheme.colorScheme.primary)
        .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically){
        Column ( modifier = Modifier.weight(1f)) {
            Text(text = SimpleDateFormat("HH:mm:ss dd/MM",
                Locale.ENGLISH).format(item.createdAt),
                fontSize = 12.sp,
                color = Color.LightGray)
            Text(text = item.title,
                fontSize = 20.sp,
                color = Color.White)
        }
        IconButton(onClick = onDelete) {
            Icon(painter = painterResource(id = R.drawable.baseline_delete_24),
                contentDescription = "Delete",
                tint = Color.White)
        }
    }
}