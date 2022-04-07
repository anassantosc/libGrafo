//Ana Santos 17-10602
package ve.usb.libGrafo
import java.util.LinkedList
import java.util.Queue

/*
DESCRIPCION: Implementación de un árbol mínimo cobertor de un grafo no dirigido usando el algoritmo de Prim.
*/
public class MSTPrim(val g: GrafoNoDirigidoCosto) {
    
    var n = g.obtenerNumeroDeVertices()
    var key = Array<Double>(n){(Double.MAX_VALUE)}
    var pred = Array<Int?>(n){(null)}
    var r = n-1
    var queue : Queue<Int> = LinkedList<Int>()
    var arbol = listOf<AristaCosto>()
    
    /*
    DESCRIPCIÓN: Funcion que implementa la formación del árbol Prim tal que se reserva los costos minimos de los lados
    en el arreglo key 
    
    PRECONDICIÓN:(para todo i : key[i] == Double.MAX_VALUE)
    
    POSTCONDICIÓN: (queue.isEmpty())
    
    TIEMPO: O(|V|)
    */
    init {
        key[r] = 0.0
        for (i in 0..n-1) {
            queue.add(i)
        }
        
        while (!queue.isEmpty()) {
            var min = Double.POSITIVE_INFINITY
            var este = 0
            for (i in queue) {
                if (min > key[i]) {
                    min = key[i]
                    este = i
                }
            }
            var u = este

            g.adyacentes(u).forEach {arista ->
                var v = arista.elOtroVertice(u)
                var w = arista.costo()

                if ((queue.contains(v)) && (w < key[v])) {
                    pred[v] = u
                    key[v] = w
                }
            }
            queue.remove(u)
        }
    }

    /*
    DESCRIPCIÓN: Funcion que retorna los lasdos pertenecientes al arbol ordenados por su costo
    
    PRECONDICIÓN: (aristas.size > 0)
    
    POSTCONDICIÓN: (aristas.size >= arbol.size)
    
    TIEMPO: O(|V||E|)
    */
    fun obtenerLados() : Iterable<AristaCosto> {
        var aristas = g.iterator()
        aristas.forEach {i ->
            var x = i.cualquieraDeLosVertices()
            var y = i.elOtroVertice(x)
            var p = i.costo()
            
            for (j in 0..n-1) {
                if((x == pred[j] && y == j && p == key[j]) || (y == pred[j] && x == j && p == key[j])) {
                    arbol += i
                }
            }
        }
        var ordenado = arbol.sortedBy() {it.costo}
        return ordenado
    }
    
    /*
    DESCRIPCIÓN: Funcion que retorna la suma de los costos de los lados pertenecientes al arbol Prim
    
    PRECONDICIÓN: (key.size > 0)
    
    POSTCONDICIÓN: (sumacosto > 0.0)
    
    TIEMPO: O(|V|)
    */ 
    fun obtenerCosto() : Double {
        var sumacosto = 0.0
        for (i in 0..n-1) {
            if(key[i] != Double.MAX_VALUE) {
                sumacosto += key[i]
            }
        }
        return sumacosto
    }
}
