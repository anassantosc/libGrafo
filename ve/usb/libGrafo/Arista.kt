//Ana Santos 17-10602
package ve.usb.libGrafo

/*
DESCRICIÓN: Implementación de la Clase Arista, la cual pertenece a la Clase Abstracta
Lado de los Grafos No Dirigidos
*/

public open class Arista(val v: Int, val u: Int) : Lado(v, u) {

    /*
    DESCRIPCIÓN: Función que convierte el arco en un String

    PRECONDICIÓN: ((v,u) == par)

    POSTCONDICIÓN: (toString() retorna un dato tipo String)
    
    TIEMPO: O(1)
    */
    override fun toString() : String {
        var lista = mutableListOf(v,u)
        var str = lista.joinToString()

        return "("+str+")"
    }

} 
