package com.example.aplicacion1udec.ViewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.aplicacion1udec.Model.PerfilEstudiante
import com.example.aplicacion1udec.R

class PerfilViewModel : ViewModel() {
    // la vista solo observa estados
    // el viewmodel maneja la logica
    // el modelo solo contiene datos
    // se encarga de manejar datos y logica entre modelo y vista
    // evita que la vista maneje datos directamente
//-------------------------------------------------------------------
    // estado privado del perfil
    // contiene toda la informacion del estudiante
    // se usa mutablestateof para que compose detecte cambios
    private val _perfil = mutableStateOf(
        PerfilEstudiante(
            // datos iniciales del perfil

            nombreCompleto = "Monica Yulihet Poveda Carrasco",// nombre del estudiante
            programaAcademico = "Ingenieria de Sistemas y Computacion", // programa academico
            semestre = 5,// semestre actual
            // descripcion del perfil
            perfilpersonal = "Estudiante de ingenieria de sistemas alumna del area de Ingenieria de software " +
                    "Busco aprender sobre el desarrollo de software para trabajar en soluciones junto con el avance de la tecnologia con impacto social",
            edad = 19,// edad del estudiante
            ciudad = "Cajica",// ciudad de residencia
            correo = "myulihetpoveda@ucundinamarca.edu.co",// correo electronico
            hobbies = listOf("Jugar voleibol", "Bailar", "Viajar ","aprender cosas nuevas"), // lista de hobbies
            pasatiempos = listOf("ver peliculas", "Cocinar","conocer nuevas tecnologias"),  // lista de pasatiempos
            deportes = listOf("Voleibol", "Natacion","Patinaje"),// lista de deportes
            // intereses personales
            interesesPersonales = listOf("Inteligencia Artificial", "Ciberseguridad","Automatizacion","Desarrollo de software"),
            imagenPerfil = R.drawable.perfil_usuario_default, // imagen tomada desde drawable

            // logros y metas
            logrosYMetas = listOf(
                "Ganadora Hackathon Unicundi 2024 - Categoria Innovacion Social",
                "Voluntaria en Club de Programacion Enseñanza de Kotlin a principiantes",
                "Asistente de catedra en Fundamentos de Ingenieria de Software",
                "Meta 2025 certificacion en Desarrollo Android con Compose y arquitectura limpia",
                "Proyecto actual Aplicacion para gestion de reservas de espacios deportivos"
            ),

            // cursos realizados
            cursosRealizados = listOf(
                "Introduccion a la Programacion", "Programacion Orientada a Objetos", "IA Generativa"),

            // idiomas

            idiomas = listOf("Ingles ", "Espanol Nativo")

        )
    )

    // estado publico solo lectura
    // la vista puede observar pero no modificar
    val perfil: State<PerfilEstudiante> = _perfil

//-------------------------------------------------------------
    // estados booleanos para mostrar u ocultar secciones
//------------------------------------------------------------
    private val _mostrarperfilpersonal = mutableStateOf(false)
    // controla si se muestra el perfil personal
    val mostrarperfilpersonal: State<Boolean> = _mostrarperfilpersonal
    // estado observable para la vista
//-----------------------------------------------------------------------
    private val _mostrarpasatiempos = mutableStateOf(false)
    val mostrarpasatiempos: State<Boolean> = _mostrarpasatiempos
    //-----------------------------------------------------------------------
    private val _mostrarHobbies = mutableStateOf(false)
    val mostrarHobbies: State<Boolean> = _mostrarHobbies
    //-----------------------------------------------------------------------
    private val _mostrarDeportes = mutableStateOf(false)
    val mostrarDeportes: State<Boolean> = _mostrarDeportes
    //-----------------------------------------------------------------------
    private val _mostrarIntereses = mutableStateOf(false)
    val mostrarIntereses: State<Boolean> = _mostrarIntereses
    //-----------------------------------------------------------------------
    private val _mostrarInformacionAdicional = mutableStateOf(false)
    val mostrarInformacionAdicional: State<Boolean> = _mostrarInformacionAdicional

//-----------------------------------------------------------------------
    // funciones que responden a acciones del usuario
//-----------------------------------------------------------------------
    fun toggleperfilpersonal() {
        // cambia el valor actual entre verdadero y falso
        _mostrarperfilpersonal.value = !_mostrarperfilpersonal.value
        // al cambiar el estado la interfaz se actualiza automaticamente
    }
    //------------------------- funcion que responden a desplegar lista hobbies----------------
    fun toggleHobbies() {
        _mostrarHobbies.value = !_mostrarHobbies.value
    }
    //----------------------------funcion que responden a desplegar lista pasatiempos-------------------------------------------
    fun togglepasatiempos() {
        _mostrarpasatiempos.value = !_mostrarpasatiempos.value
    }
    //---------------------------funcion que responden a desplegar lista deportes--------------------------------------------
    fun toggleDeportes() {
        _mostrarDeportes.value = !_mostrarDeportes.value
    }
    //---------------------------funcion que responden a desplegar lista interes personales--------------------------------------------
    fun toggleIntereses() {
        _mostrarIntereses.value = !_mostrarIntereses.value
    }
    //------------------------------funcion que responden a mostrar/ocultar informacion adicional del estudiante-----------------------------------------
    fun toggleInformacionAdicional() {
        _mostrarInformacionAdicional.value = !_mostrarInformacionAdicional.value
    }

}