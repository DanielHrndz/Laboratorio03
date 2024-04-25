package com.alexisflop.labo03.ui.component

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import com.alexisflop.labo03.MainActivity
import com.alexisflop.labo03.data.objectList
import com.alexisflop.labo03.model.ObjectClass
import com.alexisflop.labo03.viewmodel.DataViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun ObjectListComponent(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val isLoading = remember { mutableStateOf(false) }
    val data: MutableState<MutableList<ObjectClass>> = remember {
        mutableStateOf(mutableListOf())
    }
    val viewModel = DataViewModel()
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            isLoading.value = true
            GlobalScope.launch(Dispatchers.IO) {
                Thread.sleep(3000)
                val newData = viewModel.getData()
                withContext(Dispatchers.Main) {
                    isLoading.value = false
                    if(newData.size > 0){
                        data.value = newData
                        Toast.makeText(context, "Datos cargados con éxito", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(context, "No hay datos por mostrar", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }) {
            Text(text = "Get OBJECTS from database")
        }

        if (isLoading.value) {
            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
        } else {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 8.dp, end = 8.dp),
            ) {
                items(data.value) { item ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp, top = 5.dp, bottom = 5.dp)
                            .background(Color.LightGray)
                            .clip(RoundedCornerShape(15.dp)),
                    ){
                        Text(
                            text = "Property 1: ${item.PropertyOne}",
                            Modifier
                                .padding(start = 20.dp, end = 20.dp, top = 15.dp),
                            fontSize = 16.sp
                        )
                        Text(
                            text = "Property 2: ${item.PropertyTwo}",
                            Modifier
                                .padding(start = 20.dp, end = 20.dp, bottom = 15.dp),
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = false)
@Composable
private fun ObjectListComponentPreview() {
    ObjectListComponent()
}