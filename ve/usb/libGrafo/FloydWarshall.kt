//Ana Santos 17-10602
package ve.usb.libGrafo
import java.util.Stack

/*
DESCRIPCION: Implementacion del algoritmo de Floyd-warshall para encontrar los caminos de costo mínimo todos los pares 
de vértices de un grafo. La matriz de costos es construida con la función dada en clase, usando una clase con funciones
relacionadas a la implementacion.
*/
public class FloydWarshall(val W : Array<Array<Double>>) {
    
    var n = W[0].size
    var p = Array<Array<Int?>>(n){Array<Int?>(n){null}}
    var d = Array<Array<Double>>(n){Array<Double>(n){Double.POSITIVE_INFINITY}}

    /*
    DESCRIPCION: Implementacion del algoritmo de Floyd-Warshall para encontrar los CCM entre los pares de un digrafo, 
    usando dos matrices como arreglos de arreglos, una para guardar los costos entre pares y otro para los predecesores.
    Primero se inicializan las matrices d = w  y predecesores con los predecesores directos de segun cada lado del digrafo.
    Luego, verifica si en cualquier posicion de la matriz de costos es menor a la suma de los costos de los vertices 
    intermedios entre los indices i y j, que indican los vertices. En caso contrario, se cambia el valor por la suma y 
    y se guarda el predecesor segun el nuevo costo minimo.
    
    PRECONDICION: (w.size == w[0].size)
    
    POSTCONDICION: (d != w)
    
    TIEMPO: 0(|V^2|)
    */
    init {
        if (W.size != n) {
            throw RuntimeException("No es una matriz cuadrada")
        }

        d = W
        p = this.llenarMatrixPred(p)
        
        for (k in 0..n-1) {
            for (i in 0..n-1) {
                for (j in 0..n-1) {
                    var suma = d[i][k] + d[k][j]

                    if (d[i][j] > suma) {
                        d[i][j] = suma
                        p[i][j] = p[k][j]
                    }
                }
            }
        }
    }

    /*
    DESCRIPCION: Funcion que retorna la matriz de los costos.
    
    PRECONDICION: (!d.isEmpty())
    
    POSTCONDICION: (d != w)
    
    TIEMPO: O(1)
    */
    fun obtenerMatrizDistancia() : Array<Array<Double>> { 
        
        return d
    } 

    /*
    DESCRIPCION: Funcion que retorna la matriz de los costos.
    
    PRECONDICION: (!p.isEmpty())
    
    POSTCONDICION: (p(k) != p(0))
    
    TIEMPO: O(1)
    */
    fun obtenerMatrizPredecesores() : Array<Array<Int>> { 
        
        return p as Array<Array<Int>>
    } 
    
    /*
    DESCRIPCION: Funcion que retorna el costo del CCM entre los vertices u y v.
    
    PRECONDICION: (v && u in 0..n-1)
    
    POSTCONDICION: (d[u][v] es un CCM)

    TIEMPO: O(|V^2|)
    */
    fun costo(u: Int, v: Int) : Double { 
        if (!(u in 0..n-1) || !(v in 0..n-1)) {
            throw RuntimeException("Uno de los vertices ingresados no pertenece al grafo")
        }

        return d[u][v]
    }

    /*
    DESCRIPCION: Funcion que retorna si existe un camino entre los vertice u y v.
    
    PRECONDICION: (v && u in 0..n-1)
    
    POSTCONDICION: (existe == (d[u][v] != 0.0 && d[u][v] != Double.POSITIVE_INFINITY))
    
    TIEMPO: O(|V^2|)
    */
    fun existeUnCamino(u: Int, v: Int) : Boolean { 
        if (!(u in 0..n-1) || !(v in 0..n-1)) {
            throw RuntimeException("Uno de los vertices ingresados no pertenece al grafo")
        }

        var existe = false

        if (d[u][v] != 0.0 && d[u][v] != Double.POSITIVE_INFINITY) {
            existe = true
        }

        return existe
    }

    /*
    DESCRIPCION: Funcion que retorna una lista de arcos, lo cuales indican el camino de s hasta v en caso de que exista camino, para ello 
    se mueve por los predecesores, empezando por v hasta llegar a u, y guardando en la lista los arcos que sean (predecesor[v], v). En caso 
    contrario, devuelve una lista vacia.
    
    PRECONDICION: (v && u in 0..n-1)
    
    POSTCONDICION: (ccm.size > 0)
    
    TIEMPO: 0(|V^2|)
    */
    fun obtenerCaminoDeCostoMinimo(u: Int, v: Int) : Iterable<ArcoCosto> { 
        if (!(u in 0..n-1) || !(v in 0..n-1)) {
            throw RuntimeException("Uno de los vertices ingresados no pertenece al grafo")
        }

        var ccm = mutableListOf<ArcoCosto>()
        var camino = mutableListOf<ArcoCosto>()
        var stack = Stack<ArcoCosto>()

        if (this.existeUnCamino(u,v)) {
            var aux1 = u
            var aux2 = v
            while (aux2 != u && aux2 != null) {
                var costo1 = p[aux1][aux2] as Int
                var x = ArcoCosto(costo1, aux2, W[costo1][aux2])
                if (!(x in camino)) {
                    camino.add(x)
                }
                aux2 = costo1
            }
        }
        stack.addAll(camino)
        while (!stack.isEmpty()) {
            var aux = stack.pop()
            ccm.add(aux)
        }

        return ccm
    }

    /*
    DESCRIPCION: Funcion que retorna la matriz de predecesores inicial con los pedecesores directos de los lados
    
    PRECONDICION: (!w.isEmpty())
    
    POSTCONDICION: (m.size >= 0)
    
    TIEMPO: O(|V^2|)
    */
    fun llenarMatrixPred(m: Array<Array<Int?>>) : Array<Array<Int?>> {
        for (i in 0..n-1) {
            for (j in 0..n-1) {
                if (W[i][j] != 0.0 && W[i][j] != Double.POSITIVE_INFINITY) {
                    m[i][j] = i
                }
            }
        }

        return m
    }
}
