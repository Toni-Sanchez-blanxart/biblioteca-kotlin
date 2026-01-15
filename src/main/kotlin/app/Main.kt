package app

import core.Biblioteca
import models.Llibre
import models.Lector
import utils.Utils
fun mostrarMenu() {
    println(
        """
        -------------------------------
        GESTIÓ DE BIBLIOTECA
        -------------------------------
        1. Registrar llibre
        2. Registrar lector
        3. Prestar llibre
        4. Retornar llibre
        5. Llistar llibres disponibles
        6. Cercar llibres per autor
        0. Sortir
        -------------------------------
        """.trimIndent()
    )
}

fun registrarLlibre(biblioteca: Biblioteca) {
    print("Títol: ")
    val titol = readln()
    print("Autor: ")
    val autor = readln()
    val llibre = Llibre(titol, autor)
    biblioteca.afegirLlibre(llibre)
    println("Llibre registrat correctament.")
}

fun registrarLector(biblioteca: Biblioteca) {
    print("Nom del lector: ")
    val nom = readln()

    val lector = Lector(nom)
    biblioteca.registrarLector(lector)

    println("Lector registrat correctament.")
}

fun prestarLlibre(biblioteca: Biblioteca) {
    println("Seguidament es mostren els llibres disponibles.")
    biblioteca.llistarDisponibles()
    println()
    print("Títol del llibre: ")
    val titol = readln()

    val llibre = biblioteca.cataleg.find { it.titol.equals(titol, ignoreCase = true) }

    if (llibre == null) {
        println("Llibre no trobat.")
        return
    }

    if (llibre.prestat) {
        println("El llibre ja està prestat.")
        return
    }

    print("Nom del lector: ")
    val nomLector = readln()

    val lector = biblioteca.lectors.find { it.nom.equals(nomLector, ignoreCase = true) }
    if (lector == null) {
        println("Lector no trobat.")
        return
    }

    lector.prestarLlibre(llibre)
    println("Llibre prestat correctament.")
}

fun retornarLlibre(biblioteca: Biblioteca) {
    print("Títol del llibre: ")
    val titol = readln()

    val llibre = biblioteca.cataleg.find { it.titol.equals(titol, ignoreCase = true) }

    if (llibre == null || !llibre.prestat) {
        println("El llibre no està prestat.")
        return
    }

    llibre.retornar()
    println("Llibre retornat correctament.")
}

fun cercarPerAutor(biblioteca: Biblioteca) {
    print("Autor: ")
    val autor = readln()

    val resultats = biblioteca.cercarPerAutor(autor)

    if (resultats.isEmpty()) {
        println("No s'han trobat llibres d'aquest autor.")
    } else {
        resultats.forEach {
            println(it.info())
        }
        println("Total: ${Utils.comptarPerAutor(resultats, autor)}")
    }
}








fun main() {

    val biblioteca = Biblioteca()
    var opcio: Int

    do {
        mostrarMenu()
        opcio = readln().toIntOrNull() ?: -1

        when (opcio) {
            1 -> registrarLlibre(biblioteca)
            2 -> registrarLector(biblioteca)
            3 -> prestarLlibre(biblioteca)
            4 -> retornarLlibre(biblioteca)
            5 -> biblioteca.llistarDisponibles()
            6 -> cercarPerAutor(biblioteca)
            0 -> println("Sortint de l'aplicació...")
            else -> println("Opció no vàlida.")
        }

    } while (opcio != 0)
}
