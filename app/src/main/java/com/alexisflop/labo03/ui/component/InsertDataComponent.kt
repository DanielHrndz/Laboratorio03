package com.alexisflop.labo03.ui.component

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alexisflop.labo03.model.ObjectClass
import com.alexisflop.labo03.viewmodel.DataViewModel

@Composable
fun InsertDataComponent(modifier: Modifier = Modifier) {
    val textFieldPropertyOne = rememberSaveable { mutableStateOf("") }
    val textFieldPropertyTwo = rememberSaveable { mutableStateOf("") }
    val viewModel = DataViewModel()
    val myList = remember { mutableStateListOf<ObjectClass>() }
    val focusRequester = remember { FocusRequester() }
    val context = LocalContext.current

    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {

        Text(modifier = Modifier.padding(bottom = 5.dp), text = "Insert your data here")

        TextField(
            modifier = Modifier.padding(bottom = 5.dp).focusRequester(focusRequester),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            value = textFieldPropertyOne.value,
            onValueChange = { textFieldPropertyOne.value = it },
            placeholder = {
                Text(text = "Insert first property here")
            }
        )

        TextField(
            modifier = Modifier.padding(bottom = 5.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            value = textFieldPropertyTwo.value,
            onValueChange = { textFieldPropertyTwo.value = it },
            placeholder = {
                Text(text = "Insert second property here")
            }
        )

        Button(modifier = Modifier.padding(bottom = 5.dp), onClick = {
            val newObject = ObjectClass(
                propertyOne = textFieldPropertyOne.value,
                propertyTwo = textFieldPropertyTwo.value
            )
            if (textFieldPropertyOne.value == "" || textFieldPropertyTwo.value == ""){
                Toast.makeText(context, "No hay datos por ingresar", Toast.LENGTH_SHORT).show()
            }else{
                myList.add(newObject)
                Toast.makeText(context, "Datos ingresados con exito!", Toast.LENGTH_SHORT).show()
            }
            textFieldPropertyOne.value = ""
            textFieldPropertyTwo.value = ""
            viewModel.setData(myList)
            focusRequester.requestFocus()
        }) {
            Text(text = "Set data to object")
        }
    }
}


@Preview(showSystemUi = false)
@Composable
private fun InsertDataComponentPreview() {
    InsertDataComponent(modifier = Modifier)
}