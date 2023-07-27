//Nombre: Gabriel Garcia
//Nombre del programa: Lab_2
class Hobby(val titulo: String, val descripcion: String, val urlPhoto: String?){

}

class PerfilUsuario(val id: Int, val nombres: String, val apellidos: String, val urlPhoto: String?, val edad: Int, val correo: String, val biografia: String?, var estado: String
) {
    val hobbies: MutableList<Hobby> = mutableListOf()

    fun agregarHobby(hobby: Hobby) {
        hobbies.add(hobby)
    }
}

fun main() {
    val listaPerfiles: MutableList<PerfilUsuario> = mutableListOf(
        //Perfiles creados para que no empiece vacio
        PerfilUsuario(1, "Javier", "Prado", null, 21, "prado@gmail.com", null, "Activo"),
        PerfilUsuario(2, "Bryan", "España", null, 20, "esp@gmail.com", null, "Pendiente")
    )

    while (true) {
        println("------------- Menu -------------")
        println("1. Crear perfil")
        println("2. Buscar perfil de usuario(s)")
        println("3. Eliminar perfil")
        println("4. Agregar Hobby")
        println("5. Salir")
        print("Ingrese una opción: ")
        val opcion = readLine()?.toInt()

        when (opcion) {
            1 -> crearPerfil(listaPerfiles)
            2 -> buscarPerfil(listaPerfiles)
            3 -> eliminarPerfil(listaPerfiles)
            4 -> agregarHobby(listaPerfiles)
            5 -> {
                println("Adios")
                return
            }
            else -> println("Ingrese correctamente una opción")
        }
    }
}

fun crearPerfil(listaPerfiles: MutableList<PerfilUsuario>) {
    println("------------- Crear perfil -------------")
    print("ID: ")
    val id = readLine()?.toInt() ?: return
    print("Nombres: ")
    val nombres = readLine()?.trim() ?: return
    print("Apellidos: ")
    val apellidos = readLine()?.trim() ?: return
    print("URL de la foto de perfil (O puede no ingresarla): ")
    val urlPhoto = readLine()?.trim()
    print("Edad: ")
    val edad = readLine()?.toInt() ?: return
    print("Correo electrónico: ")
    val correo = readLine()?.trim() ?: return
    print("Biografía: ")
    val biografia = readLine()?.trim()
    print("Estado (Activo, Pendiente o Inactivo): ")
    val estado = readLine()?.trim()?.capitalize() ?: return

    val nuevoPerfil = PerfilUsuario(id, nombres, apellidos, urlPhoto, edad, correo, biografia, estado)
    listaPerfiles.add(nuevoPerfil)
    println("Se ha creado su perfil")
}

fun buscarPerfil(listaPerfiles: List<PerfilUsuario>) {
    println("------------- Buscar perfil de usuario(s) -------------")
    print("Ingrese el ID o los nombres o los apellidos: ")
    val query = readLine()?.trim() ?: return

    //Busca en la lista o el id, o los nombres o los apellidos
    val resultados = listaPerfiles.filter { perfil ->
        perfil.id.toString() == query || perfil.nombres.equals(query, ignoreCase = true) || perfil.apellidos.equals(query, ignoreCase = true)
    }

    if (resultados.isNotEmpty()) {
        for (perfil in resultados) {
            println("----------------- Perfil encontrado -----------------")
            println("ID: ${perfil.id}")
            println("Nombres: ${perfil.nombres}")
            println("Apellidos: ${perfil.apellidos}")
            println("URL de la foto de perfil: ${perfil.urlPhoto ?: "N/A"}")
            println("Edad: ${perfil.edad}")
            println("Correo electrónico: ${perfil.correo}")
            println("Biografía: ${perfil.biografia ?: "N/A"}")
            println("Estado: ${perfil.estado}")
            if (perfil.hobbies.isNotEmpty()) {
                println("Hobbies:")
                for (hobby in perfil.hobbies) {
                    println("  Título: ${hobby.titulo}")
                    println("  Descripción: ${hobby.descripcion}")
                    println("  URL de la foto: ${hobby.urlPhoto ?: "N/A"}")
                }
            } else {
                println("Aun no ha ingresado sus hobbies")
            }
            println("---------------------------")
        }
    } else {
        println("No se encontro el perfil")
    }
}

fun eliminarPerfil(listaPerfiles: MutableList<PerfilUsuario>) {
    println("----------------- Eliminar perfil -----------------")
    print("Ingrese el ID del perfil para eliminar: ")
    val id = readLine()?.toIntOrNull() ?: return

    val perfilAEliminar = listaPerfiles.find { perfil -> perfil.id == id }

    if (perfilAEliminar != null) {
        listaPerfiles.remove(perfilAEliminar)
        println("Perfil eliminado exitosamente.")
    } else {
        println("No se encontró un perfil con el ID ingresado.")
    }
}

fun agregarHobby(listaPerfiles: MutableList<PerfilUsuario>) {
    println("---- Agregar Hobby ----")
    print("Ingrese el ID, nombres o apellidos del perfil al que desea agregar el Hobby: ")
    val query = readLine()?.trim() ?: return

    val perfilEncontrado = listaPerfiles.find { perfil ->
        perfil.id.toString() == query || perfil.nombres.equals(query, ignoreCase = true) || perfil.apellidos.equals(query, ignoreCase = true)
    }

    if (perfilEncontrado != null) {
        println("---- Perfil encontrado ----")
        println("ID: ${perfilEncontrado.id}")
        println("Nombres: ${perfilEncontrado.nombres}")
        println("Apellidos: ${perfilEncontrado.apellidos}")

        println("Hobbies disponibles:")
        for ((index, hobby) in hobbiesDisponibles.withIndex()) {
            println("${index + 1}. ${hobby.titulo}")
        }

        print("Ingrese el número del Hobby que desea agregar: ")
        val hobbyNum = readLine()?.toIntOrNull() ?: return

        if (hobbyNum in 1..hobbiesDisponibles.size) {
            val hobbySeleccionado = hobbiesDisponibles[hobbyNum - 1]
            perfilEncontrado.agregarHobby(hobbySeleccionado)
            println("Hobby agregado exitosamente.")
        } else {
            println("Hobby no válido")
        }
    } else {
        println("No se encontró un perfil ")
    }
}

val hobbiesDisponibles: List<Hobby> = listOf(
    Hobby("Fotografía", "Me gusta tomar fotos", null),
    Hobby("Cocina", "Me gusta preparar recetas", null),
    Hobby("Viajes", "Me gusta viajar con amigos", null),
)
