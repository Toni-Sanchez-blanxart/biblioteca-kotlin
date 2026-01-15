package models

/**
 * Representa un lector de la biblioteca.
 * Hereta de la classe Persona.
 */
class Lector(id: String, nom: String) : Persona(id, nom) {

    /**
     * Llista de llibres que el lector té en préstec.
     */
    private val prestecs: MutableList<Llibre> = mutableListOf()

    /**
     * Presta un llibre al lector.
     * El llibre passa a estat prestat i s'afegeix a la llista de préstecs.
     */
    fun prestarLlibre(llib: Llibre) {
        if (llib.disponible) {
            llib.prestar()
            prestecs.add(llib)
            println("Llibre prestat correctament al lector $nom.")
        } else {
            println("El llibre no està disponible.")
        }
    }

    /**
     * Retorna un llibre prestat.
     * El llibre torna a estar disponible.
     */
    fun retornarLlibre(llib: Llibre) {
        if (prestecs.contains(llib)) {
            llib.retornar()
            prestecs.remove(llib)
            println("Llibre retornat correctament.")
        } else {
            println("Aquest llibre no pertany als préstecs del lector.")
        }
    }

    /**
     * Mostra tots els llibres que el lector té en préstec.
     */
    fun llistarPrestecs() {
        if (prestecs.isEmpty()) {
            println("El lector no té llibres en préstec.")
        } else {
            println("Llibres prestats al lector $nom:")
            prestecs.forEach { println(it.info()) }
        }
    }
}
