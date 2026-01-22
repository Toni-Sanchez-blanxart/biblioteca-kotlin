package models

/**
 * Representa un llibre del catàleg de la biblioteca.
 */
class Llibre(
    val id: String,
    val titol: String,
    val autor: String
) {

    /**
     * Indica si el llibre està prestat o no.
     * El setter és privat: només la mateixa classe pot canviar aquest valor.
     */
    var prestat: Boolean = false
        private set

    /**
     * Getter públic per a persistència
     */
    val estaPrestat: Boolean
        get() = prestat

    /**
     * Retorna un text descriptiu del llibre.
     */
    fun info(): String {
        val estat = if (prestat) "PRESTAT" else "DISPONIBLE"
        return "Llibre: \"$titol\", autor: $autor, estat: $estat"
    }

    /**
     * Marca el llibre com a prestat.
     */
    fun prestar() {
        if (!prestat) {
            prestat = true
        }
    }

    /**
     * Marca el llibre com a retornat.
     */
    fun retornar() {
        if (prestat) {
            prestat = false
        }
    }

    /**
     * Indica si el llibre està disponible
     */
    val disponible: Boolean
        get() = !prestat
}
