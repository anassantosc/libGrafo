//Ana Santos 17-10602
package ve.usb.libGrafo

/*
DESCRICIÓN: Implementación de la Clase ArcoCosto, la cual pertenece a la Clase Abstracta
Arco de Grafos dirigidos con Costo
*/
public class ArcoCosto(val x: Int, val y: Int, val costo: Double) : Arco(x, y) {

    /*
    DESCRIPCIÓN: Función encargada de retornar el peso o costo del lado (x,y)
    
    PRECONDICIÓN: (peso es un dato tipo Double)
    
    POSTCONDICIÓN: (peso() retorna una variable tipo Double)
    
    TIEMPO: O(1)
    */
    fun costo() : Double {

        return costo
    }

    /*
    DESCRIPCIÓN: Función que convierte el arco en un String

    PRECONDICIÓN: ((x,y,costo) == tripleta)

    POSTCONDICIÓN: (toString() retorna un dato tipo String)
    
    TIEMPO: O(1)
    */
    override fun toString() : String {
        var lista = mutableListOf(x, y, costo)
        var str = lista.joinToString()

        return "("+str+")"
    }
} 
