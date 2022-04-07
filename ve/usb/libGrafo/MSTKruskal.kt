//Ana Santos 17-10602
package ve.usb.libGrafo

/*
DESCRIPCION: Implementación de un árbol mínimo cobertor de un grafo no dirigido usando el algoritmo de Kruskal.
*/
public class MSTKruskal(val g: GrafoNoDirigidoCosto) {
    
    var arbol = listOf<AristaCosto>()
    var n = g.obtenerNumeroDeVertices()
    var compCD = ConjuntosDisjuntos(n)
    var aristas = g.iterator()

    /*
    DESCRIPCIÓN: Funcion que implementa el algoritmo del Arbol Kruskal, ordenando los lados por el costo de forma 
    ascendente tal que si los representantes de conjuntos disjuntos son del vertice fuente y sumidero son distintos
    se ingresa a la lista arbol y se hace union(u,v)
    
    PRECONDICIÓN: (!arista.isEmpty())
    
    POSTCONDICIÓN: (arbol es un arbol Kruskal)
    
    TIEMPO: O(|V|)
    */
    init {
        var ordenadas = listOf<AristaCosto>()
        aristas.forEach { it ->
            ordenadas += it
        }
        ordenadas = ordenadas.sortedBy() { it.costo }

        ordenadas.forEach { i ->
            var u = i.cualquieraDeLosVertices()
            var v = i.elOtroVertice(u)

            if (compCD.representante[u] != compCD.representante[v]) {
                arbol += i
                compCD.union(u,v)
            }
        }
    }

    /*
    DESCRIPCIÓN: Funcion que retorna los lados pertenecientes al arbol Kruskal
    
    PRECONDICIÓN: (arbol es un arbol Kruskal)
    
    POSTCONDICIÓN: (arbol subconjunto de aristas)
    
    TIEMPO: O(1)
    */
    fun obtenerLados() : Iterable<Arista> {
        
        return arbol
    }
    
    /*
    DESCRIPCIÓN: Funcion que retorna la suma de los costos de los lados pertenecientes al arbol Prim
    PRECONDICIÓN: (arbol.size > 0)
    POSTCONDICIÓN: (sumaPeso > 0.0)
    TIEMPO: O(|V|)
    */   
    fun obtenerCosto() : Double {
        var sumacosto = 0.0
        arbol.forEach {i ->
            sumacosto = sumacosto + i.costo()
        }

        return sumacosto
    }

}
