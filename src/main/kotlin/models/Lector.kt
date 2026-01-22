package models

/**
 * Representa un lector de la biblioteca.
 * Hereta de Persona.
 */
class Lector(id: String, nom: String) : Persona(id, nom) {

    private val prestecs: MutableList<Llibre> = mutableListOf()

    fun prestarLlibre(llib: Llibre) {
        if (llib.disponible) {
            llib.prestar()
            prestecs.add(llib)
            println("Llibre prestat correctament al lector $nom.")
        } else {
            println("El llibre no està disponible.")
        }
    }

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
     * Mètodes per a persistència
     */
    fun getPrestecs(): List<Llibre> = prestecs.toList()
}

