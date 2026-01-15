package app

import core.Biblioteca
import models.Lector
import models.Llibre

/**
 * Funció principal del programa.
 * Mostra el menú i gestiona les opcions de l'usuari.
 */
fun main() {

    val biblioteca = Biblioteca()
    var opcio: Int

    do {
        println("""
            -------------------------
            GESTIÓ DE BIBLIOTECA
            -------------------------
            1. Registrar llibre
            2. Registrar lector
            3. Prestar llibre
            4. Retornar llibre
            5. Llistar llibres disponibles
            6. Cercar llibres per autor
            0. Sortir
        """.trimIndent())

        opcio = readln().toIntOrNull() ?: -1

        when (opcio) {

            // Registrar un llibre nou
            1 -> {
                print("ID llibre: ")
                val id = readln()
                print("Títol: ")
                val titol = readln()
                print("Autor: ")
                val autor = readln()

                if (biblioteca.afegirLlibre(Llibre(id, titol, autor)))
                    println("Llibre registrat correctament.")
                else
                    println("Error: ja existeix un llibre amb aquest ID.")
            }

            // Registrar un lector nou
            2 -> {
                print("ID lector: ")
                val id = readln()
                print("Nom: ")
                val nom = readln()

                if (biblioteca.registrarLector(Lector(id, nom)))
                    println("Lector registrat correctament.")
                else
                    println("Error: ja existeix un lector amb aquest ID.")
            }

            // Prestar un llibre a un lector
            3 -> {
                print("ID llibre: ")
                val idLlibre = readln()
                print("ID lector: ")
                val idLector = readln()

                val llibre = biblioteca.obtenirLlibre(idLlibre)
                val lector = biblioteca.obtenirLector(idLector)

                if (llibre == null || lector == null) {
                    println("Dades incorrectes.")
                } else {
                    lector.prestarLlibre(llibre)
                }
            }

            // Retornar un llibre
            4 -> {
                print("ID llibre: ")
                val idLlibre = readln()
                print("ID lector: ")
                val idLector = readln()

                val llibre = biblioteca.obtenirLlibre(idLlibre)
                val lector = biblioteca.obtenirLector(idLector)

                if (llibre == null || lector == null) {
                    println("Dades incorrectes.")
                } else {
                    lector.retornarLlibre(llibre)
                }
            }

            // Llistar llibres disponibles
            5 -> biblioteca.llistarDisponibles()

            // Cercar llibres per autor
            6 -> {
                print("Autor: ")
                biblioteca.cercarPerAutor(readln())
                    .forEach { println(it.info()) }
            }

            0 -> println("Sortint de l'aplicació...")

            else -> println("Opció no vàlida.")
        }

    } while (opcio != 0)
}

