package models

/**
 * Representa un llibre del catàleg de la biblioteca.
 * Cada llibre té un identificador únic.
 */
class Llibre(
    val id: String,        // ID únic del llibre
    val titol: String,     // Títol del llibre
    val autor: String      // Autor del llibre
) {

    /**
     * Indica si el llibre està prestat.
     * És privat perquè només el llibre pot modificar el seu estat.
     */
    private var prestat: Boolean = false

    /**
     * Propietat calculada.
     * Retorna true si el llibre NO està prestat.
     */
    val disponible: Boolean
        get() = !prestat

    /**
     * Marca el llibre com a prestat.
     */
    fun prestar() {
        prestat = true
    }

    /**
     * Marca el llibre com a retornat.
     */
    fun retornar() {
        prestat = false
    }

    /**
     * Retorna una descripció del llibre.
     */
    fun info(): String {
        val estat = if (prestat) "PRESTAT" else "DISPONIBLE"
        return "ID: $id | \"$titol\" - $autor | Estat: $estat"
    }
}

