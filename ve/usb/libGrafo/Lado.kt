//Ana Santos 17-10602
package ve.usb.libGrafo

/*
DESCRIPCIÓN: Implementación de la Clase Lado, la cual define el lado de un Grafo no dirgido o dirigido
*/

abstract class Lado(val a: Int, val b: Int) {

    /*
    DESCRIPCIÓN: Función que retorna cualquier vértice pertenciente al lado, por defecto se retorna
    el primer vertice ingresado

    PRECONDICIÓN: (a y b son del tipo Entero)

    POSTCONDICIÓN: (cualquieraDeLosVertices() retorna una variable tipo Entero)

    TIEMPO: O(1)
    */
    fun cualquieraDeLosVertices() : Int {
        
        return a
    }

    /*
    DESCRIPCION: Función que retorna el vértice contrario, pertenciente al lado, del que se encuentra 
    en el argumento.

    PRECONDICION: (w == a || w == b)

    POSTCONDICION:  (vertice == a || vertice == b )

    TIEMPO: O(1)
    */
    fun elOtroVertice(w: Int) : Int {
        var vertice : Int

        if (w == a) {
            vertice = b
        } else if (w == b) {
            vertice = a
        } else {
            throw RuntimeException("w no se encuentra entre los vertices")
        }

        return vertice
    }
}
