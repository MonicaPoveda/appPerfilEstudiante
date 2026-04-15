package com.example.aplicacion1udec.ui

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aplicacion1udec.ViewModel.PerfilViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

//perfilScreen es : (view)  : puente entre el usuario y la aplicación, definiendo la estructura,
// el diseño y la apariencia de lo que el usuario ve en pantalla
//
// AQUI EN VIEW:UI = Se encarga de MOSTRAR la informacion expuesta por el viewModel= (perfilViewModel)
// AQUI EN VIEW:UI = Captura las interacciones del usuario (como clics de botones o entradas de texto)
//  y las comunica al ViewModel para que este procese la logica de negocio correspondiente


@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PerfilView(viewModel: PerfilViewModel = viewModel()) {

    val perfil by viewModel.perfil
    val mostrarPerfilPersonal by viewModel.mostrarperfilpersonal
    val mostrarHobbies by viewModel.mostrarHobbies
    val mostrarpasatiempos by viewModel.mostrarpasatiempos
    val mostrarDeportes by viewModel.mostrarDeportes
    val mostrarIntereses by viewModel.mostrarIntereses
    val mostrarInfoAdicional by viewModel.mostrarInformacionAdicional
//colores que se van a usar durante todo el codigo
    val azulOscuro = Color(0xFF01579B)
    val verdOscuro = Color(0xFF004D40)
    val fondoCard  = Color(0xFFF1F8FF)

    // scaffold es la estructura principal de la pantalla
// permite tener elementos base como topbar, contenido, botones flotantes, etc
    Scaffold(
        containerColor = Color.White
    ) { paddingValues -> // padding que deja el scaffold para no tapar contenido

        // lazycolumn es una lista vertical desplazable (scroll)
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()                // ocupa toda la pantalla
                .padding(paddingValues),      // respeta espacio del scaffold

            horizontalAlignment = Alignment.CenterHorizontally, // centra contenido horizontalmente
            verticalArrangement = Arrangement.spacedBy(16.dp) // separacion entre items
        ) {
            // franja superior
            item {

                // aqui se usa para poner fondo + texto + imagen perfil
                Box(
                    modifier = Modifier
                        .fillMaxWidth()     // ocupa todo el ancho de la pantalla
                        .height(220.dp)     // altura total del header (contenedor general)
                ) {

                    // fondo azul (parte superior)
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()     // ocupa todo el ancho
                            .height(160.dp)     // altura SOLO del fondo (menos que el box principal)

                            // fondo con degradado
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(azulOscuro, Color(0xFF0277BD))
                                )
                            )

                    ) {

                        // texto centrado dentro del fondo
                        Text(
                            "Perfil del Estudiante",

                            modifier = Modifier
                                .align(Alignment.Center) // centra el texto dentro del box
                                .padding(bottom = 20.dp), // lo baja un poquito

                            color = Color.White,          // color blanco
                            fontSize = 24.sp,             // tamaño del texto
                            fontWeight = FontWeight.Bold, // negrilla
                            fontFamily = FontFamily.Serif // tipo de letra elegante
                        )
                    }

                    // foto de perfil del estudiante
                    Surface(

                        modifier = Modifier
                            .size(130.dp) // tamaño de la foto (ancho y alto iguales)

                            // posiciona la imagen en la PARTE INFERIOR CENTRADA del box principal
                            .align(Alignment.BottomCenter),

                        shape = CircleShape, // forma circular
                        color = Color.White, // fondo blanco
                        shadowElevation = 8.dp // sombra para efecto de elevacion
                    ) {

                        // imagen del perfil
                        Image(
                            painter = painterResource(id = perfil.imagenPerfil),
                            // carga la imagen desde drawable usando el id del perfil

                            contentDescription = "foto de perfil",

                            modifier = Modifier
                                .padding(7.dp)     // espacio alrededor de la imagen para efecto de borde blanco
                                .clip(CircleShape), // recorta la imagen en forma circular

                            contentScale = ContentScale.Crop
                            // ajusta la imagen para que llene el circulo sin deformarse
                        )
                    }
                }
            }

            // Nombre y detalles del estudiante  centrados
            item {
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        perfil.nombreCompleto,
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF003366)
                    )
                    Text(
                        perfil.programaAcademico,
                        fontSize = 14.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Medium
                    )
                    Row(
                        modifier = Modifier.padding(top = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.School, null, modifier = Modifier.size(18.dp), tint = azulOscuro)
                        Spacer(Modifier.width(6.dp))
                        Text(
                            "${perfil.semestre}° Semestre",
                            fontSize = 15.sp,
                            color = azulOscuro,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            //tarjeta de informacion basica; años ciudad y correo
            item {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)) {

                        Row(
                            modifier = Modifier.fillMaxWidth()) {

                            Row(
                                modifier = Modifier.weight(1f),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(Icons.Default.Cake, contentDescription = null)
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("${perfil.edad} años")

                                Spacer(modifier = Modifier.width(190.dp))
                                Icon(Icons.Default.LocationOn, contentDescription = null)
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(perfil.ciudad)

                            }


                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Email, contentDescription = null)
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(perfil.correo)
                        }
                    }
                }
            }

            //boton perfil personal: descripcion general
            item {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
                    Button(
                        onClick = { viewModel.toggleperfilpersonal() },
                        modifier = Modifier.fillMaxWidth().height(50.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = azulOscuro)
                    ) {
                        Icon(                   //icono que cambia segun el click del usuario
                            imageVector =
                                if (mostrarPerfilPersonal) Icons.Default.ExpandLess  //si lo abre muestra flecha
                                else Icons.Default.AccountCircle,                    // si lo cierra muestra el circulo de perfil

                            contentDescription = null
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            "Perfil Personal",      //nombre del boton
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold)
                    }
                    AnimatedVisibility(
                        visible = mostrarPerfilPersonal,            //controla cuando se ve y cuando no
                        enter = expandVertically() + fadeIn(),      //cuando hace click el usuario para abrir se extiende hacia abajo
                        exit = shrinkVertically() + fadeOut()       //cuando hace click el usuario para cerrar se contrae

                    ) {

                        //TARJETA QUE SE EXPANDE
                        //se muestra la descripcion del perfil personal
                        Card(
                            modifier = Modifier.padding(top = 10.dp).fillMaxWidth(),
                            shape = RoundedCornerShape(14.dp),
                            colors = CardDefaults.cardColors(containerColor = fondoCard)
                        ) {
                            Text(
                                perfil.perfilpersonal,  //se llama la descripcion del perfil
                                modifier = Modifier.padding(14.dp),
                                fontSize = 15.sp,
                                color = Color(0xFF37474F),
                                lineHeight = 22.sp
                            )
                        }
                    }
                }
            }

            //TARJETA DE HIBBIE
            item {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = fondoCard)
                ) {
                    Column {
                        // Encabezado con toggle
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween //divide el espacio en 2 == icono+Texto y boton de despliege

                        ) {
                            // parte izquierda icono+texto de hobbie
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Palette, null, tint = verdOscuro)
                                Spacer(Modifier.width(10.dp))
                                Text(
                                    "Hobbies",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 17.sp,
                                    color = verdOscuro)
                            }

                            //parte derecha el icono del boton de despliegue
                            IconButton(onClick = {    //cuando recibe el click de despliegue en hobbie, este se lo envia a
                                // viewModel para que este  procese el estado por medio del toggleHobbies=true o false
                                viewModel.toggleHobbies() }) {
                                Icon(
                                    if (mostrarHobbies) Icons.Default.KeyboardArrowUp //si el usuario hace click para abrirlo, muestra flecha arriba

                                    else Icons.Default.KeyboardArrowDown,           // si no, muestra flecha abajo
                                    null, tint = Color.Gray
                                )
                            }
                        }
                        // animacion para el despliegue del boton
                        //Controla si se ve el contenido
                        // si es true se muestra
                        // si es false no se muestra

                        AnimatedVisibility(visible = mostrarHobbies) {

                            //el flowrw es como un row, pero este se adapta automaticamnete de acuerdo al espacio de la fila , hace como un salto de linea

                            FlowRow(
                                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 14.dp),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                perfil.hobbies.forEach{ hobby -> // se usa foreach para traer o recorrer la lista de hobbies ->
                                    // se crea una variable "hobby" para mostrar cada hobbie automaticamente a medida que se añadan

                                    Surface( //boton visual de cada hobbie
                                        shape = RoundedCornerShape(20.dp),
                                        color = Color(0xFFE1F5FE)) {
                                        Text(
                                            hobby,    //muestra cada hobby que recibe y lo ubica automaticamente al espacio de la tarjeta
                                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                            fontSize = 13.sp,
                                            color = azulOscuro
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
                //tarjeta desplejable de pasatiempos
            item {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = fondoCard)
                ) {
                    Column {
                        // Encabezado con toggle
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween //divide el espacio en 2 == icono+Texto y boton de despliege

                        ) {
                            // parte izquierda icono+texto de hobbie
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.SportsKabaddi, null, tint = verdOscuro)
                                Spacer(Modifier.width(10.dp))
                                Text(
                                    "Pasatiempos",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 17.sp,
                                    color = verdOscuro)
                            }

                            //parte derecha el icono del boton de despliegue
                            IconButton(onClick = {    //cuando recibe el click de despliegue en pasatimepos, este se lo envia a
                                // viewModel para que este  procese el estado por medio del togglepasatiempos=true o false
                                viewModel.togglepasatiempos() }) {
                                Icon(
                                    if (mostrarpasatiempos) Icons.Default.KeyboardArrowUp //si el usuario hace click para abrirlo, muestra flecha arriba

                                    else Icons.Default.KeyboardArrowDown,           // si no, muestra flecha abajo
                                    null, tint = Color.Gray
                                )
                            }
                        }
                        // animacion para el despliegue del boton
                        //Controla si se ve el contenido
                        // si es true se muestra
                        // si es false no se muestra

                        AnimatedVisibility(visible = mostrarpasatiempos) {

                            //el flowrw es como un row, pero este se adapta automaticamnete de acuerdo al espacio de la fila , hace como un salto de linea

                            FlowRow(
                                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 14.dp),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                perfil.pasatiempos.forEach{ actividad -> // se usa foreach para traer o recorrer la lista de actividades ->
                                    // se crea una variable "actividad" para mostrar cada actividad automaticamente a medida que se añadan

                                    Surface( //boton visual de cada actividad
                                        shape = RoundedCornerShape(20.dp),
                                        color = Color(0xFFE1F5FE)) {
                                        Text(
                                            actividad,    //muestra cada actividad que recibe y lo ubica automaticamente al espacio de la tarjeta
                                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                            fontSize = 13.sp,
                                            color = azulOscuro
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            //tarjeta de deportes
            item {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = fondoCard)
                ) {
                    Column {

                        // encabezado con toggle
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween //divide el espacio en 2: icono+texto y boton
                        ) {

                            // parte izquierda icono + texto deportes
                            Row(
                                verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.SportsSoccer, null, tint = verdOscuro)
                                Spacer(Modifier.width(10.dp))
                                Text(
                                    "Deportes",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 17.sp,
                                    color = verdOscuro
                                )
                            }

                            // parte derecha boton de despliegue
                            IconButton(onClick = {
                                // envia el click al viewmodel para cambiar el estado true o false
                                viewModel.toggleDeportes()
                            }) {
                                Icon(
                                    if (mostrarDeportes) Icons.Default.KeyboardArrowUp // si esta abierto muestra flecha arriba
                                    else Icons.Default.KeyboardArrowDown,              // si esta cerrado muestra flecha abajo
                                    null,
                                    tint = Color.Gray
                                )
                            }
                        }

                        // animacion de despliegue
                        // si mostrarDeportes es true se muestra
                        // si es false no se muestra
                        AnimatedVisibility(visible = mostrarDeportes) {

                            // flowrow organiza los elementos y hace salto de linea automaticamente
                            FlowRow(
                                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 14.dp),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {

                                // recorre la lista de deportes
                                perfil.deportes.forEach { deporte ->

                                    // crea un chip visual por cada deporte
                                    Surface(
                                        shape = RoundedCornerShape(20.dp),
                                        color = Color(0xFFE1F5FE)
                                    ) {
                                        Text(
                                            deporte,
                                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                            fontSize = 13.sp,
                                            color = azulOscuro
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
            //tarjeta de intereses personales
            item {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = fondoCard)
                ) {
                    Column {

                        // encabezado con toggle
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            // parte izquierda icono + texto intereses
                            Row(
                                verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Lightbulb, null, tint = verdOscuro)
                                Spacer(Modifier.width(10.dp))
                                Text(
                                    "Intereses personales",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 17.sp,
                                    color = verdOscuro
                                )
                            }

                            // boton de despliegue
                            IconButton(onClick = {
                                // cambia el estado en el viewmodel
                                viewModel.toggleIntereses()
                            }) {
                                Icon(
                                    if (mostrarIntereses) Icons.Default.KeyboardArrowUp // abierto
                                    else Icons.Default.KeyboardArrowDown,               // cerrado
                                    null,
                                    tint = Color.Gray
                                )
                            }
                        }

                        // animacion de mostrar u ocultar
                        AnimatedVisibility(visible = mostrarIntereses) {

                            // organiza los intereses como chips adaptables
                            FlowRow(
                                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 14.dp),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {

                                // recorre la lista de intereses
                                perfil.interesesPersonales.forEach { interes ->

                                    // crea un chip por cada interes
                                    Surface(
                                        shape = RoundedCornerShape(20.dp),
                                        color = Color(0xFFE1F5FE)
                                    ) {
                                        Text(
                                            interes,
                                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                            fontSize = 13.sp,
                                            color = azulOscuro
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
            // boton para mostrar u ocultar informacion adicional
            item {
                Button(
                    onClick = {
                        // cuando el usuario hace click, se envia al viewmodel
                        // y este cambia el estado entre true y false
                        viewModel.toggleInformacionAdicional()
                    },
                    modifier = Modifier.fillMaxWidth().height(52.dp).padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(26.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E86AB))
                ) {

                    // icono cambia segun el estado
                    Icon(
                        if (mostrarInfoAdicional) Icons.Default.VisibilityOff // si esta visible muestra ocultar
                        else Icons.Default.Visibility,                         // si no esta visible muestra ver
                        contentDescription = null
                    )

                    Spacer(Modifier.width(10.dp))

                    // texto cambia dinamicamente segun el estado
                    Text(
                        if (mostrarInfoAdicional) "cultar informacion adicional"
                        else "Mostrar informacion adicional",
                        fontWeight = FontWeight.Bold
                    )
                }
            }// seccion que se muestra u oculta con animacion
            item {
                AnimatedVisibility(
                    // controla si se ve o no
                    visible = mostrarInfoAdicional,
                    // animaciones
                    enter = expandVertically() + fadeIn(),
                    exit = shrinkVertically() + fadeOut()
                ) {
                    Column(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {

                        // tarjeta de logros y metas
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = fondoCard)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {

                                // titulo con icono
                                Row(
                                    verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.School, null, tint = verdOscuro)
                                    Spacer(Modifier.width(10.dp))
                                    Text(
                                        "Logros y Metas Profesionales",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 17.sp,
                                        color = verdOscuro
                                    )
                                }

                                Spacer(Modifier.height(10.dp))

                                // recorre la lista de logros y metas
                                perfil.logrosYMetas.forEach { item -> //item es variable de cada elemento de la lista

                                    // cada elemento se muestra como fila con icono
                                    Row(
                                        modifier = Modifier.padding(vertical = 4.dp),
                                        verticalAlignment = Alignment.Top
                                    ) {
                                        Icon(
                                            Icons.Default.EmojiEvents,
                                            null,
                                            Modifier.size(16.dp),
                                            Color(0xFF00897B)
                                        )
                                        Spacer(Modifier.width(10.dp))
                                        Text(
                                            item, //llama a cada elemento de la lista de logros ymetas y las muestra
                                            fontSize = 14.sp,
                                            color = Color.DarkGray,
                                            lineHeight = 20.sp
                                        )
                                    }
                                }
                            }
                        }

                        // tarjeta de idiomas
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = fondoCard)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {

                                Row(
                                    verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.Translate, null, tint = verdOscuro)
                                    Spacer(Modifier.width(10.dp))
                                    Text(
                                        "Idiomas adicionales",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 17.sp,
                                        color = verdOscuro
                                    )
                                }

                                Spacer(Modifier.height(10.dp))

                                // recorre la lista de idiomas
                                perfil.idiomas.forEach { item ->

                                    Row(
                                        modifier = Modifier.padding(vertical = 4.dp),
                                        verticalAlignment = Alignment.Top
                                    ) {
                                        Icon(
                                            Icons.Default.CheckCircle,
                                            null,
                                            Modifier.size(16.dp),
                                            Color(0xFF00897B)
                                        )
                                        Spacer(Modifier.width(10.dp))
                                        Text(
                                            item,
                                            fontSize = 14.sp,
                                            color = Color.DarkGray,
                                            lineHeight = 20.sp
                                        )
                                    }
                                }
                            }
                        }

                        // tarjeta de cursos
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = fondoCard)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {

                                Row(
                                    verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.HistoryEdu, null, tint = verdOscuro)
                                    Spacer(Modifier.width(10.dp))
                                    Text(
                                        "cursos realizados",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 17.sp,
                                        color = verdOscuro
                                    )
                                }

                                Spacer(Modifier.height(10.dp))

                                // recorre la lista de cursos
                                perfil.cursosRealizados.forEach { item ->

                                    Row(
                                        modifier = Modifier.padding(vertical = 4.dp),
                                        verticalAlignment = Alignment.Top
                                    ) {
                                        Icon(
                                            Icons.Default.Bookmark,
                                            null,
                                            Modifier.size(16.dp),
                                            Color(0xFF00897B)
                                        )
                                        Spacer(Modifier.width(10.dp))
                                        Text(
                                            item,
                                            fontSize = 14.sp,
                                            color = Color.DarkGray,
                                            lineHeight = 20.sp
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}