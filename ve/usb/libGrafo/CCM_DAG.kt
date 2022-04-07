//Ana Santos 17-10602
package ve.usb.libGrafo
import java.util.Stack

/*
DESCRIPCION: Clase que guarda la implementación del Algoritmo Camino de Costo Mínimo en DAG, basado en el pseudo
codigo dado en el Cormen. 
*/
public class CCM_DAG(val g: GrafoDirigidoCosto, val s: Int) {

    var n = g.obtenerNumeroDeVertices()
    var pred = Array(n){(-1)}
    var distancia = Array(n){(Double.POSITIVE_INFINITY)}
    var ot = OrdenamientoTopologicoCosto(g)
    var orden = ot.obtenerOrdenTopologico()
    var esDAG = ot.esDAG()

    /*
    DESCRIPCION: Implementacion para hallar el camino de costo minimo de un DAG, tal que se usa dos arreglos uno para guardar 
    las distancias yotro para lo predecesores. La distancia del vertice s es 0, mientras que para los otros vertices, se realiza 
    una busqueda por cada vertices y por cada lado para aplicar relajación
    
    PRECONDICION: (esDAG == true)
    
    POSTCONDICION: ((para todo v en V: 0<=distancia[v]<=infinito)
    
    TIEMPO: O(V*E)
    */
    init {
        if (!esDAG) {
            throw RuntimeException ("El grafo no es un DAG")
        } else {
            distancia[s] = 0.0
            orden.forEach{u ->
                g.adyacentes(u).forEach{arco ->                   
                    var v = arco.sumidero()
                    var w = arco.costo()
                    var suma = distancia[u] + w
                    if (distancia[v] > suma) {
                        distancia[v] = suma 
                        pred[v] = u
                    }
                }
            }
        }
    }

    /*
    DESCRIPCION: Funcion que determina si existe un camino desde s hasta un vertice v, determinando si la distancia es distinta a
    infinito, por tanto existe un camino.
    
    PRECONDICION: (v en V)
    
    POSTCONDICION: (0<=distancia[v]<=infinito)
    
    TIEMPO: O(V)
    */
    fun existeUnCamino(v: Int) : Boolean { 
        if (!(v in 0..n-1)) {
            throw RuntimeException ("v no es un vertice del grafo")
        }

        var dV = distancia[v]
        var verificar = (dV != Double.POSITIVE_INFINITY)

        return verificar
    }

    /*
    DESCRIPCION: Funcion que retorna el costo minimo de un vertice s hasta un vertice v, almacenado en el arreglo de distancia.
    
    PRECONDICION: (v en V)
    
    POSTCONDICION: (0<=distancia[v]<=infinito)
    
    TIEMPO: O(V)
    */
    fun costo(v: Int) : Double { 
        if (!(v in 0..n-1)) {
            throw RuntimeException ("v no es un vertice del grafo")
        }

        var dV = 0.0
        if (this.existeUnCamino(v)) {
            dV = distancia[v]
        }

        return dV
    }

    /*
    DESCRIPCION: Funcion que retorna una lista de arcos, lo cuales indican el camino de s hasta v en caso de que exista camino, para ello 
    se mueve por los predecesores, empezando por v hasta llegar a s, y guardando en la lista los arcos que sean (predecesor[v], v). En caso 
    contrario, devuelve una lista vacia.
    
    PRECONDICION: (v en V)
    
    POSTCONDICION: (camino.size >= 0)
    
    TIEMPO: O(E)
    */
    fun obtenerCaminoDeCostoMinimo(v: Int) : Iterable<ArcoCosto> { 
        if (!(v in 0..n-1)) {
            throw RuntimeException ("v no es un vertice del grafo")
        }

        var c = mutableListOf<ArcoCosto>()
        var camino = mutableListOf<ArcoCosto>()
        var stack = Stack<ArcoCosto>()
        var existe = this.existeUnCamino(v)

        if (existe) {
           var aux = v
           while (aux != s) {
               g.iterator().forEach {arco ->
                    var fuente = arco.fuente()
                    var sumidero = arco.sumidero()
                    var peso = arco.costo()
                    if ((fuente == pred[aux]) && (sumidero == aux)) {
                        c.add(arco)
                        aux = pred[aux]
                    }
               }
           }
        }
        stack.addAll(c)
        while (!stack.isEmpty()) {
            var aux = stack.pop()
            camino.add(aux)
        }

        return camino
    }
}
