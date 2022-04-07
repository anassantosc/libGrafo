//Ana Santos 17-10602
package ve.usb.libGrafo

/*
DESCRICIÓN: Implementación de la Clase Arco, la cual pertenece a la Clase Abstracta
Lado de los Grafos Dirigidos
*/

public open class Arco(val inicio: Int, val fin: Int) : Lado(inicio, fin) {

    /*
    DESCRIPCIÓN: Función que retorna el vértice fuente del Arco

    PRECONDICIÓN: (inicio es un dato tipo Entero)

    POSTCONDICIÓN: (fuente() retorna un dato tipo Entero)
    
    TIEMPO: O(1)
    */
    fun fuente() : Int {

        return inicio
    }

    /*
    DESCRIPCIÓN: Función que retorna el vértice sumidero del Arco

    PRECONDICIÓN: (fin es un entero)
    
    POSTCONDICIÓN: (sumidero() retorna una variable tipo entero)
    
    TIEMPO: O(1)
    */
    fun sumidero() : Int {

        return fin
    }

    /*
    DESCRIPCIÓN: Función que convierte el arco en un String

    PRECONDICIÓN: ((inicio,fin) == par)

    POSTCONDICIÓN: (toString() retorna un dato tipo String)
    
    TIEMPO: O(1)
    */
    override fun toString() : String {
        var lista = mutableListOf(inicio, fin)
        var str = lista.joinToString()

        return "("+str+")"
    }
} 
