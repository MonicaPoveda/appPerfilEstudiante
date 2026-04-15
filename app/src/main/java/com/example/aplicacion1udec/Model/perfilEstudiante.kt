package com.example.aplicacion1udec.Model

data class PerfilEstudiante(
    //Al ser un data class (en Kotlin),
    // automáticamente genera métodos como copy(), equals(), hashCode() y toString().

    //foto de perfil del estudiante
    val imagenPerfil: Int,

    //informacion basica
    val nombreCompleto: String,
    val programaAcademico: String,
    val semestre: Int,
    val edad: Int,
    val ciudad: String,
    val correo: String,

    //descripcion corta del perfil del estudiante
    val perfilpersonal : String,

    //informacion del estudiante desplegable
    val hobbies: List<String>,
    val pasatiempos: List<String>,
    val deportes: List<String>,
    val interesesPersonales: List<String>,

    //informacion que se muestr/oculta
    val logrosYMetas: List<String>,
    val cursosRealizados: List<String>,
    val idiomas: List<String>
)
