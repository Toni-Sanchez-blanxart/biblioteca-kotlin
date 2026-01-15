package utils

import models.Llibre

/**
 * Classe d'utilitats amb mètodes "estàtics" implementats amb companion object.
 */
class Utils {
    companion object {

        /**
         * Retorna quants llibres d'una llista estan marcats com a prestats.
         */

        /**
         * Retorna quants llibres d'un autor concret hi ha en una llista.
         */
        fun comptarPerAutor(llista: List<Llibre>, autor: String): Int {
            return llista.count { it.autor.equals(autor, ignoreCase = true) }
        }
    }
}
