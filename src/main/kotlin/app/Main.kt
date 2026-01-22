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

fun main() {

    val biblioteca = Biblioteca()
    biblioteca.carregar() // Carrega dades des de fitxers TXT
    var opcio: Int

    do {
        mostrarMenu()
        opcio = readln().toIntOrNull() ?: -1

        when (opcio) {

            1 -> { // Registrar llibre
                print("ID llibre: ")
                val id = readln()
                print("Títol: ")
                val titol = readln()
                print("Autor: ")
                val autor = readln()

                val llibre = Llibre(id, titol, autor)
                if (biblioteca.afegirLlibre(llibre)) {
                    println("Llibre registrat correctament.")
                } else {
                    println("Error: ja existeix un llibre amb aquest ID.")
                }
            }

            2 -> { // Registrar lector
                print("ID lector: ")
                val id = readln()
                print("Nom del lector: ")
                val nom = readln()

                val lector = Lector(id, nom)
                if (biblioteca.registrarLector(lector)) {
                    println("Lector registrat correctament.")
                } else {
                    println("Error: ja existeix un lector amb aquest ID.")
                }
            }

            3 -> { // Prestar llibre
                print("ID llibre: ")
                val idLlibre = readln()
                print("ID lector: ")
                val idLector = readln()

                val llibre = biblioteca.obtenirLlibre(idLlibre)
                val lector = biblioteca.obtenirLector(idLector)

                if (llibre == null) println("Llibre no trobat.")
                else if (lector == null) println("Lector no trobat.")
                else lector.prestarLlibre(llibre)
            }

            4 -> { // Retornar llibre
                print("ID llibre: ")
                val idLlibre = readln()
                print("ID lector: ")
                val idLector = readln()

                val llibre = biblioteca.obtenirLlibre(idLlibre)
                val lector = biblioteca.obtenirLector(idLector)

                if (llibre == null) println("Llibre no trobat.")
                else if (lector == null) println("Lector no trobat.")
                else lector.retornarLlibre(llibre)
            }

            5 -> { // Llistar llibres disponibles
                biblioteca.llistarDisponibles()
            }

            6 -> { // Cercar llibres per autor
                print("Autor: ")
                val autor = readln()
                val resultats = biblioteca.cercarPerAutor(autor)
                if (resultats.isEmpty()) println("No s'han trobat llibres d'aquest autor.")
                else {
                    resultats.forEach { println(it.info()) }
                    println("Total: ${Utils.comptarPerAutor(resultats, autor)}")
                }
            }

            0 -> println("Sortint de l'aplicació...")

            else -> println("Opció no vàlida.")
        }

    } while (opcio != 0)

    // Desa les dades abans de sortir
    biblioteca.guardar()
    println("Dades desades correctament.")
}


