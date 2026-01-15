package models

/**
 * Representa un lector que pot demanar llibres en préstec.
 * Hereta de Persona (RA4.7).
 */
class Lector(
    nom: String
) : Persona(nom) {

    /**
     * Llibres actualment prestats per aquest lector.
     * La llista és privada (encapsulació).
     */
    private val llibresPrestats: MutableList<Llibre> = mutableListOf()

    /**
     * Presta un llibre a aquest lector, si està disponible.
     */
    fun prestarLlibre(llib: Llibre) {
        if (!llib.disponible) {
            println("No es pot prestar el llibre \"${llib.titol}\" perquè ja està prestat.")
            return
        }

        llib.prestar()
        llibresPrestats.add(llib)
        println("$nom ha prestat el llibre \"${llib.titol}\".")
    }

    /**
     * Retorna un llibre que aquest lector tenia en préstec.
     */

    /**
     * Mostra tots els llibres que aquest lector té actualment prestats.
     */


    /**
     * Retorna el nombre de llibres actualment prestats per aquest lector.
     */
}
