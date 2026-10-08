fun main() {
	val admin = Usuario(id = 1, nombre = "Ana", rol = Rol.ADMIN)
    val editor = Usuario(id = 2, nombre = "Carlos", rol = Rol.EDITOR)
    val visitanteInactivo = Usuario(id = 3, nombre = "Luis", rol = Rol.VISITANTE, estaActivo = false)

    println("=== Intentando registrar usuarios ===")
    GestorUsuarios.agregarUsuario(admin)
    GestorUsuarios.agregarUsuario(editor)
    GestorUsuarios.agregarUsuario(visitanteInactivo) 

    GestorUsuarios.listarUsuarios()
}

enum class Rol {
    ADMIN, EDITOR, VISITANTE
}

interface Autenticable {
    fun autenticar(): Boolean 
}

data class Usuario (
    val id: Int,
    val nombre: String,
    val rol: Rol,
    val estaActivo: Boolean = true ): Autenticable{
    override fun autenticar(): Boolean{
        return estaActivo
    }
}

object GestorUsuarios {
    private val usuarios = mutableListOf<Usuario>()

    fun agregarUsuario(usuario: Usuario) {
        if (usuario.autenticar()) {
            usuarios.add(usuario)
            println("Usuario registrado con éxito: ${usuario.nombre}")
        } else {
            println("Error: El usuario ${usuario.nombre} está inactivo y no se puede agregar.")
        }
    }

    fun listarUsuarios() {
        println("Lista de Usuarios Registrados")
            for (u in usuarios) {
                println("ID: ${u.id} - Nombre: ${u.nombre} - Rol: ${u.rol}")
            }
        }
}
