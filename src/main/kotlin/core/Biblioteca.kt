package core

import models.Llibre
import models.Lector
import java.io.File

class Biblioteca {

    val cataleg: MutableList<Llibre> = mutableListOf()
    val lectors: MutableList<Lector> = mutableListOf()

    private val fitxerLlibres = "llibres.txt"
    private val fitxerLectors = "lectors.txt"

    // -------------------
    // Funcions de gestió
    // -------------------

    fun afegirLlibre(llib: Llibre): Boolean {
        if (cataleg.any { it.id == llib.id }) return false
        cataleg.add(llib)
        return true
    }

    fun registrarLector(lector: Lector): Boolean {
        if (lectors.any { it.id == lector.id }) return false
        lectors.add(lector)
        return true
    }

    fun obtenirLlibre(id: String): Llibre? = cataleg.find { it.id == id }
    fun obtenirLector(id: String): Lector? = lectors.find { it.id == id }

    fun llistarDisponibles() {
        val disponibles = cataleg.filter { it.disponible }
        if (disponibles.isEmpty()) println("No hi ha llibres disponibles ara mateix.")
        else {
            println("Llibres disponibles al catàleg:")
            disponibles.forEach { println(it.info()) }
        }
    }

    fun cercarPerAutor(autor: String): List<Llibre> =
        cataleg.filter { it.autor.equals(autor, ignoreCase = true) }

    // -------------------
    // Funcions de persistència
    // -------------------

    fun guardar() {
        // Desa llibres
        File(fitxerLlibres).printWriter().use { pw ->
            cataleg.forEach { llibre ->
                pw.println("${llibre.id};${llibre.titol};${llibre.autor};${llibre.estaPrestat}")
            }
        }

        // Desa lectors
        File(fitxerLectors).printWriter().use { pw ->
            lectors.forEach { lector ->
                val prestecs = lector.getPrestecs().joinToString(",") { it.id }
                pw.println("${lector.id};${lector.nom};$prestecs")
            }
        }
    }

    fun carregar() {
        val llibresMap = mutableMapOf<String, Llibre>()

        // Carrega llibres
        File(fitxerLlibres).takeIf { it.exists() }?.forEachLine { linia ->
            val parts = linia.split(";")
            if (parts.size >= 4) {
                val id = parts[0]
                val titol = parts[1]
                val autor = parts[2]
                val prestat = parts[3].toBoolean()
                val llibre = Llibre(id, titol, autor)
                if (prestat) llibre.prestar()
                afegirLlibre(llibre)
                llibresMap[id] = llibre
            }
        }

        // Carrega lectors
        File(fitxerLectors).takeIf { it.exists() }?.forEachLine { linia ->
            val parts = linia.split(";")
            if (parts.size >= 3) {
                val id = parts[0]
                val nom = parts[1]
                val prestecsIds = parts[2].split(",").filter { it.isNotBlank() }
                val lector = Lector(id, nom)
                registrarLector(lector)
                prestecsIds.forEach { llibreId ->
                    llibresMap[llibreId]?.let { llibre ->
                        lector.prestarLlibre(llibre)
                    }
                }
            }
        }
    }
}



