//Ana Santos 17-10602
package ve.usb.libGrafo

/*
DESCRICIÓN: Implementación de la Clase AristaCosto, la cual pertenece a la Clase Abstracta
Arista de Grafos no dirigidos con Costo
*/

public class AristaCosto(val x: Int, val y: Int, val costo: Double) : Comparable<AristaCosto>, Arista(x, y) {

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

    PRECONDICIÓN: ((v,u) == par)

    POSTCONDICIÓN: (toString() retorna un dato tipo String)
    
    TIEMPO: O(1)
    */
    override fun toString() : String {
        var lista = mutableListOf(x, y, costo)
        var str = lista.joinToString()

        return "("+str+")"
    }

    /*
    DESCRIPCIÓN: Función que compara el peso de la arista con otro peso, en el caso
    de que el peso sea mayor al ingresdado se retorna 1, en caso contrario se retorna -1
    y si son iguales retorna 0
    ingresado en el argumento
    
    PRECONDICIÓN: (other es un dato tipo Double)
    
    POSTCONDICIÓN: (-1 <= comparador <=1)
    
    TIEMPO: O(1)
    */
     override fun compareTo(other: AristaCosto): Int {
          var comparador : Int

          if (costo > other.costo()) {
               comparador = 1
          } else if (costo < other.costo()) {
               comparador = -1
          } else {
               comparador = 0
          }
          
          return comparador
     }
} 
