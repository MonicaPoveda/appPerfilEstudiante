package com.example.aplicacion1udec.theme.Screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.*
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Label
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun Principal() {

    var numero1 by remember { mutableStateOf("") }
    var numero2 by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(80.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "HOLA CLASE",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "ADIOS JAVA",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "APRENDIENDO",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Button(
            {},
            modifier = Modifier.fillMaxWidth() .padding(20.dp)

        ){
            Text(text = "Boton 1")
        }
        Button(
            {},
            modifier = Modifier.fillMaxWidth() .padding(20.dp)

        ){
            Text(text = "Boton 2")
        }
        Button(
            {},
            modifier = Modifier.fillMaxWidth() .padding(20.dp)


        ){
            Text(text = "Boton 3")
        }

        TextField(
            value = numero1,
            onValueChange = { numero1 = it },
            label = { Text("Numero 1") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Text(
            text = "APRENDIENDO",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "APRENDIENDO",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "APRENDIENDO",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "APRENDIENDO",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "APRENDIENDO",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "APRENDIENDO",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "APRENDIENDO",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "APRENDIENDO",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "APRENDIENDO",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "APRENDIENDO",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Divider()
        TextField(
            value = numero2,
            onValueChange = { numero2 = it },
            label = { Text("Numero 2") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Text(
            text = "APRENDIENDO",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )




    }
}