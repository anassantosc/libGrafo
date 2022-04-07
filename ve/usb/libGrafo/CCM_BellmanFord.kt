//Ana Santos 17-10602
package ve.usb.libGrafo
import java.util.Stack

/*
DESCRIPCION: Clase que guarda la implementación del Algoritmo Bellman Ford, basado en el pseudo
codigo dado en el Cormen. 
*/
public class CCM_BellmanFord(val g: GrafoDirigidoCosto, val s: Int) {

    var n = g.obtenerNumeroDeVertices()
    var pred = Array(n){(-1)}
    var distancia = Array(n){(Double.POSITIVE_INFINITY)}
    var negFlag = false

    /*
    DESCRIPCION: Implementación del Algoritmo de Bellman-Ford para encontrar los
    caminos de costo mínimo desde un vértice fuente s fijo. Para ello, se usa dos arreglos uno para guardar las distancias y
    otro para lo predecesores. La distancia del vertice s es 0, mientras que para los otros vertices, se realiza una busqueda
    por cada vertices y por cada lado para aplicar relajación. Finalmente para verificar si hay ciclos negativos, se utiliza 
    un flag para verificar si después de aplicar relajación, todavía se puede encontrar un costo menor, indicando que hay un ciclo negativo.
    
    PRECONDICION: (para todo v en V : distancia[v] == infinito)
    
    POSTCONDICION: (para todo v en V: -infinito<=distancia[v]<=infinito)
    
    TIEMPO: O(V*E)
    */
    init {
        distancia[s] = 0.0
        for (i in 1..n-1) {
            g.iterator().forEach {arco ->
                var u = arco.fuente()
                var v = arco.sumidero()
                var w = arco.costo()
                var suma = distancia[u] + w
                if (distancia[v] > suma) {
                    distancia[v] = suma 
                    pred[v] = u
                }
            }
        }
        g.iterator().forEach {arco ->
            var u = arco.fuente()
            var v = arco.sumidero()
            var w = arco.costo()
            var suma = distancia[u] + w
            if (distancia[v] > suma) {
                distancia[v] = Double.NEGATIVE_INFINITY
                negFlag = true
            }
        }   
    }

    /*
    DESCRIPCION: Funcion que retorna la variable booleana usada como flag en el init para determinar la existencia de ciclos
    negativos
    
    PRECONDICION: (negFlag es booleana)
    
    POSTCONDICION: (BellanFord(g).tieneCicloNegativo() == negFlag)
    
    TIEMPO: O(1)
    */
    fun tieneCicloNegativo() : Boolean { 
        return negFlag
    }

    /*
    DESCRIPCION: Funcion que retorna un ciclo negativo moviendose por lo ciclos obtenidos por CFC, para verificar si la suma de
    los lados de los vertices pertenecientes a cada componente, es un numero negativo y por tanto se retorna ese numero de lados.
    
    PRECONDICION: (g tiene un ciclo)
    
    POSTCONDICION: (listaCiclos.size >= 0)
    
    TIEMPO: O(V + E)
    */
    fun obtenerCicloNegativo() : Iterable<ArcoCosto> { 
        var cfc = CFCcosto(g).obtenerCFC()
        var listaCiclos = mutableListOf<ArcoCosto>()
        var sumPesos = 0.0

        if (this.tieneCicloNegativo()) {
            cfc.forEach {comp ->
                var t = comp.size
                var auxArray = Array(t){(0)}
                comp.forEachIndexed {index,i ->
                    auxArray[index] = i
                }
                if (this.existeUnCamino(auxArray[0])) {
                    for (j in 0..t-1) {
                        if (j == t-1) {
                            var f = auxArray[j]
                            var x = auxArray[0]
                            g.adyacentes(f).forEach { arco ->
                                if (x == arco.sumidero()) {
                                    listaCiclos.add(arco)
                                    sumPesos = sumPesos + arco.costo()
                                }
                            }
                        } else {
                            var f = auxArray[j]
                            var x = auxArray[j+1]
                            g.adyacentes(f).forEach {arco ->
                                if (x == arco.sumidero()) {
                                    listaCiclos.add(arco)
                                    sumPesos = sumPesos + arco.costo()
                                }
                            }
                        }
                    }
                    if (sumPesos < 0) {
                        
                        return listaCiclos
                    } else {
                        sumPesos = 0.0
                        listaCiclos = mutableListOf<ArcoCosto>()
                    }
                }
            }
        }

        return listaCiclos
    }

    /*
    DESCRIPCION: Funcion que determina si existe un camino desde s hasta un vertice v, determinando si la distancia es distinta a
    infinito, por tanto existe un camino.
    
    PRECONDICION: (v en V)
    
    POSTCONDICION: (-infinito<=distancia[v]<=infinito)
    
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
    
    POSTCONDICION: (-infinito<=distancia[v]<=infinito)
    
    TIEMPO: O(V)
    */
    fun costo(v: Int) : Double { 
        if (!(v in 0..n-1)) {
            throw RuntimeException ("v no es un vertice del grafo")
        }

        var dV = distancia[v]

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
            var pop = stack.pop()
            camino.add(pop)
        }

        return camino
    }
}
