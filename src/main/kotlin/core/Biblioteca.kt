package core

import models.Lector
import models.Llibre

/**
 * Classe que gestiona la biblioteca.
 * Controla el catàleg de llibres i els lectors registrats.
 */
class Biblioteca {

    /**
     * Catàleg privat de llibres.
     */
    private val cataleg: MutableList<Llibre> = mutableListOf()

    /**
     * Llista privada de lectors registrats.
     */
    private val lectors: MutableList<Lector> = mutableListOf()

    /**
     * Afegeix un llibre al catàleg.
     * Retorna false si l'ID ja existeix.
     */
    fun afegirLlibre(llib: Llibre): Boolean {
        if (cataleg.any { it.id == llib.id }) {
            return false
        }
        cataleg.add(llib)
        return true
    }

    /**
     * Registra un lector a la biblioteca.
     * Retorna false si l'ID ja existeix.
     */
    fun registrarLector(lector: Lector): Boolean {
        if (lectors.any { it.id == lector.id }) {
            return false
        }
        lectors.add(lector)
        return true
    }

    /**
     * Retorna un llibre a partir del seu ID.
     * Si no existeix, retorna null.
     */
    fun obtenirLlibre(id: String): Llibre? {
        return cataleg.find { it.id == id }
    }

    /**
     * Retorna un lector a partir del seu ID.
     * Si no existeix, retorna null.
     */
    fun obtenirLector(id: String): Lector? {
        return lectors.find { it.id == id }
    }

    /**
     * Mostra tots els llibres disponibles.
     */
    fun llistarDisponibles() {
        val disponibles = cataleg.filter { it.disponible }
        if (disponibles.isEmpty()) {
            println("No hi ha llibres disponibles.")
        } else {
            disponibles.forEach { println(it.info()) }
        }
    }

    /**
     * Cerca llibres d'un autor concret.
     */
    fun cercarPerAutor(autor: String): List<Llibre> {
        return cataleg.filter { it.autor.equals(autor, ignoreCase = true) }
    }
}
