package com.example.aplicacion1udec.theme.Screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavType
import androidx.navigation.navArgument

@Composable
fun Tarea() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "busqueda") {
        composable("busqueda") {
            ScreenBusqueda(
                onNavigateToResult = { nombre, producto, tienda ->
                    navController.navigate("resultado/$nombre/$producto/$tienda")
                }
            )
        }
        composable(
            route = "resultado/{nombre}/{producto}/{tienda}",
            arguments = listOf(
                navArgument("nombre") { type = NavType.StringType },
                navArgument("producto") { type = NavType.StringType },
                navArgument("tienda") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombre") ?: ""
            val producto = backStackEntry.arguments?.getString("producto") ?: ""
            val tienda = backStackEntry.arguments?.getString("tienda") ?: ""
            ScreenResultado(
                nombre = nombre,
                producto = producto,
                tienda = tienda,
                onBack = { navController.popBackStack() }
            )
        }
    }
}

@Composable
fun ScreenBusqueda(onNavigateToResult: (String, String, String) -> Unit) {
    var nombre by remember { mutableStateOf("") }
    var producto by remember { mutableStateOf("") }
    var opcionSeleccionada by remember { mutableStateOf("Centro") }
    var check by remember { mutableStateOf(false) }
    var switchState by remember { mutableStateOf(true) }

    // Animacion
    val alphaAnim by animateFloatAsState(targetValue = 1f)

    // Fondo
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFD0BCFF), // Azul oscuro
                        Color(0xFF5A189A)  // Morado
                    )
                )
            )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            // titulo principal
            Text(
                text = "Busca tu producto",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.alpha(alphaAnim)
            )

            Spacer(modifier = Modifier.height(25.dp))

            // textfield
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(15.dp)) {

                    TextField(
                        value = nombre,
                        onValueChange = { nombre = it },
                        label = { Text("Nombre del cliente") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    TextField(
                        value = producto,
                        onValueChange = { producto = it },
                        label = { Text("Producto a buscar") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            //  BOTONES EN FILA
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        if (nombre.isNotBlank() && producto.isNotBlank() && check) {
                            onNavigateToResult(nombre, producto, opcionSeleccionada)
                        }
                    },
                    enabled = check
                ) {
                    Text("Buscar")
                }
                Button(onClick = {
                    nombre = ""
                    producto = ""
                    opcionSeleccionada = "Centro"
                    check = false
                }) { Text("Limpiar") }
                Button(onClick = {}) { Text("Reservar") }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // RADIO BUTTON
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(15.dp)) {

                    Text("Selecciona tienda:", fontWeight = FontWeight.Bold)

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = opcionSeleccionada == "D1",
                            onClick = { opcionSeleccionada = "D1" }
                        )
                        Text("Tienda D1")
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = opcionSeleccionada == "Ara",
                            onClick = { opcionSeleccionada = "Ara" }
                        )
                        Text("Tienda Ara")
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            //  CHECKBOX Y SWITCH
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(15.dp)) {

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = check,
                            onCheckedChange = { check = it }
                        )
                        Text("Acepto terminos y condiciones")
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Switch(
                            checked = switchState,
                            onCheckedChange = { switchState = it }
                        )
                        Text("Activar notificaciones")
                    }
                }
            }
        }
    }
}

@Composable
fun ScreenResultado(nombre: String, producto: String, tienda: String, onBack: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF5A189A),
                        Color(0xFFD0BCFF)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Resumen de Búsqueda",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF5A189A)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Hola, $nombre", fontSize = 18.sp)
                Text(text = "Buscaste: $producto", fontSize = 18.sp)
                Text(text = "En la tienda: $tienda", fontSize = 18.sp)
                Spacer(modifier = Modifier.height(24.dp))
                Button(onClick = onBack) {
                    Text("Volver")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TareaPreview() {
    Tarea()
}
