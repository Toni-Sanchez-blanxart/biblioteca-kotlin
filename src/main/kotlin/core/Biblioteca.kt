package core

import models.Lector
import models.Llibre

/**
 * Classe que gestiona el catàleg de llibres i els lectors registrats.
 */
class Biblioteca {

    /**
     * Catàleg de llibres de la biblioteca.
     */
    val cataleg: MutableList<Llibre> = mutableListOf()

    /**
     * Lectors registrats a la biblioteca.
     */
    val lectors: MutableList<Lector> = mutableListOf()

    /**
     * Afegeix un llibre al catàleg.
     */
    fun afegirLlibre(llib: Llibre) {
        cataleg.add(llib)
        println("Afegit al catàleg: ${llib.info()}")
    }

    /**
     * Registra un lector a la biblioteca.
     */
    fun registrarLector(lector: Lector) {
        lectors.add(lector)
        println("Lector registrat: ${lector.nom}")
    }

    /**
     * Mostra només els llibres que NO estan prestats.
     */
    fun llistarDisponibles() {
        val disponibles = cataleg.filter { it.disponible }
        if (disponibles.isEmpty()) {
            println("No hi ha llibres disponibles ara mateix.")
        } else {
            println("Llibres disponibles al catàleg:")
            disponibles.forEach { llibre ->
                println(" - ${llibre.info()}")
            }
        }
    }

    /**
     * Retorna tots els llibres del catàleg escrits per un autor concret.
     */
    fun cercarPerAutor(autor: String): List<Llibre> {
        return cataleg.filter { it.autor.equals(autor, ignoreCase = true) }
    }

    /**
     * Mostra el catàleg complet (llibres disponibles i prestats).
     */
/*    fun llistarCatalegComplet() {
        if (cataleg.isEmpty()) {
            println("El catàleg és buit.")
        } else {
            println("Catàleg complet de la biblioteca:")
            cataleg.forEach { llibre ->
                println(" - ${llibre.info()}")
            }
        }
    }

    /**
     * Retorna una còpia de només-lectura del catàleg.
     * Serveix per passar la llista a Utils sense exposar la llista interna.
     */
    fun getCataleg(): List<Llibre> = cataleg.toList()*/
}
