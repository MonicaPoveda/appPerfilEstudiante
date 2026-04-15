package com.example.aplicacion1udec.theme.Screen

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import java.text.DecimalFormat

@Preview(showBackground = true)
@Composable
fun Calculadora() {
    val context = LocalContext.current

    var numero1 by remember { mutableStateOf("") }
    var numero2 by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("0") }
    var operacion by remember { mutableStateOf("") }

    val colorPrimario = Color(0xFF7B1FA2)
    val colorSecundario = Color(0xFFE1BEE7)
    val colorFondoPantalla = Color(0xFFF3E5F5)
    val colorTextoPrincipal = Color(0xFF4A148C)
    val colorBlanco = Color.White
    
    // Requesters para el control de foco al eliminar todo
    val focusNumero1 = remember { FocusRequester() }
    val focusNumero2 = remember { FocusRequester() }

    // Estados para el scroll horizontal de la pantalla de resultado
    val scrollStateOp = rememberScrollState()
    val scrollStateRes = rememberScrollState()

    // Configuración de Coil para imagen gif
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (Build.VERSION.SDK_INT >= 28) add(ImageDecoderDecoder.Factory()) 
            else add(GifDecoder.Factory())
        }.build()

    // operacion
    LaunchedEffect(numero1, numero2, operacion) {
        if (numero1.isNotEmpty() && numero2.isNotEmpty() && operacion.isNotEmpty()) {
            val n1 = numero1.toDoubleOrNull() ?: 0.0
            val n2 = numero2.toDoubleOrNull() ?: 0.0

            val calculoRaw = when (operacion) {
                "+" -> n1 + n2
                "-" -> n1 - n2
                "*" -> n1 * n2
                "/" -> if (n2 != 0.0) n1 / n2 else null
                else -> 0.0
            }

            resultado = if (calculoRaw == null) {
                "Error"
            } else {
                val df = DecimalFormat("#,###.#####") 
                val formatted = df.format(calculoRaw)
                if (calculoRaw % 1 != 0.0) "≈ $formatted" else formatted
            }
        } else {
            resultado = "0"
        }
    }

    Box(
        //fondo
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(colors = listOf(colorSecundario, colorBlanco))),
        contentAlignment = Alignment.Center
    ) {
        //caja blanca del diseño de la calculadora
        Card(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            shape = RoundedCornerShape(32.dp),
            colors = CardDefaults.cardColors(containerColor = colorBlanco),
            elevation = CardDefaults.cardElevation(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // ubicacion del gif
                AsyncImage(
                    model = "https://i.pinimg.com/originals/ee/ab/1d/eeab1d8c583d57c2a29639b9bf781797.gif",
                    contentDescription = "Animación decorativa",
                    imageLoader = imageLoader,
                    modifier = Modifier.size(110.dp).padding(bottom = 10.dp)
                )
                //titulo de calculadora

                Text(
                    text = "CALCULADORA",
                    color = colorPrimario,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily.Serif
                )

                Spacer(modifier = Modifier.height(15.dp))


                //pantalla de operaciones y resultados
                Surface(
                    modifier = Modifier.fillMaxWidth().height(150.dp),
                    color = colorFondoPantalla,
                    shape = RoundedCornerShape(20.dp)
                ) {
                    //aqui ubica los numeroy operandos y el resultado
                    Column(
                        modifier = Modifier.fillMaxSize().padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.End
                    ) {

                        // se puede hacer scroll si se añade un numero alrgo en la operacion
                        Row(modifier = Modifier.horizontalScroll(scrollStateOp)) {
                            Text(
                                text = if (operacion.isEmpty()) "" else "$numero1 $operacion $numero2",
                                fontSize = 18.sp,
                                color = Color.Gray,
                                maxLines = 1
                            )
                        }
                        
                        Spacer(modifier = Modifier.height(10.dp))
                        // se puede hacer scroll cuando el resultado es muy largo
                        Row(modifier = Modifier.horizontalScroll(scrollStateRes)) {
                            Text(
                                text = resultado,
                                fontSize = 42.sp,
                                fontWeight = FontWeight.Bold,
                                color = colorTextoPrincipal,
                                maxLines = 1
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
                //ingreso de numeros a operar- ingreso numero 1

                TextField(
                    value = numero1,
                    onValueChange = { numero1 = it },
                    label = { Text("Número 1") },
                    modifier = Modifier.fillMaxWidth().focusRequester(focusNumero1),
                    shape = RoundedCornerShape(15.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                Spacer(modifier = Modifier.height(12.dp))

                //ingreso de operadores, al seleccionarlo automaticamente pasa al campo 2 de numero2
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    val operaciones = listOf("+", "-", "*", "/")
                    operaciones.forEach { op ->
                        Button(
                            onClick = { 
                                operacion = op
                                focusNumero2.requestFocus() //enfoca el campo de numero 2
                            },
                            modifier = Modifier.size(60.dp),
                            shape = RoundedCornerShape(15.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (operacion == op) colorTextoPrincipal else colorPrimario
                            )
                        ) {
                            Text(text = op,
                                fontSize = 24.sp,
                                color = colorBlanco,
                                fontWeight = FontWeight.Bold)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))
                //ingreso de numeros a operar- ingreso numero 2

                TextField(
                    value = numero2,
                    onValueChange = { numero2 = it },
                    label = { Text("Número 2") },
                    modifier = Modifier.fillMaxWidth().focusRequester(focusNumero2),
                    shape = RoundedCornerShape(15.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                Spacer(modifier = Modifier.height(20.dp))
                //boton de borrar todo en la calculadora

                Button(
                    onClick = {
                        numero1 = ""
                        numero2 = ""
                        operacion = ""
                        resultado = "0"
                        focusNumero1.requestFocus() //al borrar todo se vuelve a enfocar el campo 1
                    },
                    modifier = Modifier.fillMaxWidth().height(55.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = colorSecundario),
                    shape = RoundedCornerShape(15.dp)
                ) {
                    Text(text = "BORRAR TODO",
                        color = colorTextoPrincipal,
                        fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
