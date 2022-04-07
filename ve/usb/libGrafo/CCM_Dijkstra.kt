//Ana Santos 17-10602
package ve.usb.libGrafo
import java.util.Stack

/*
DESCRIPCION: Implementación del algoritmo de Dijkstra para encontrar los caminos de costo mínimo desde un vértice 
fuente s fijo, a partir de una clases que contiene funciones relacionada y/o necesarias para la
implementacion.
*/
public class CCM_Dijkstra(val g: GrafoDirigidoCosto, val s: Int) {

    var n = g.obtenerNumeroDeVertices()
    var pred = Array(n){(-1)}
    var distancia = Array(n){(Double.POSITIVE_INFINITY)}
    var ese = mutableListOf<Int>()
    var q = mutableListOf<Int>()
    
    /*
    DESCRIPCION: Implementacion del algoritmo Dijkstra a través de una cola de prioridad, para reservar 
    en orden de costos (de menor a mayor), de manera que se obtiene el costo de los caminos mínimos desde 
    s a cualquier vertice, lo cual se guarda en un arreglo mientras que los predecesores se reserva en otro
    arreglo.
    
    PRECONDICION: (para todo (u,v) perteneciente E : w(u,v) >= 0)
    
    POSTCONDICION: (para todo v perteniciente V : distancia[v] >= 0)
    
    TIEMPO: O(|E|log|V|)
    */
    init {
        if (this.existeCostoNeg()) {
            throw RuntimeException("El grafo tiene costo negativo")
        }

        distancia[s] = 0.0
        var vertice  =  s 
        for (i in 0..n-1) {
            q.add(i)
        }

        while (!q.isEmpty()) {
            for (i in q) {
                var min = Double.POSITIVE_INFINITY
                if (min > distancia[i]) {
                    min = distancia[i]
                    vertice = i
                }
            }
            var u = vertice
            q.remove(vertice)
            ese.add(u)
            g.adyacentes(u).forEach { arco ->
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

    /*
    DESCRIPCION: Funcion que retorna si existe un camino desde la fuente s hasta el vertice v
    
    PRECONDICION: (v pertenece a V)
    
    POSTCONDICION: (verificar == (dV != Double.POSITIVE_INFINITY))
    
    TIEMPO: O(|V|)
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
    DESCRIPCION: Funcion que retorna el costo del CCM hasta el vertice v desde la fuente, el cual 
    se encuentra en el arreglo distancia.
    
    PRECONDICION: (v pertence a V)
    
    POSTCONDICION: (dV == distancia[v])
    
    TIEMPO: O(|V|)
    */
    fun costo(v: Int) : Double { 
        if (!(v in 0..n-1)) {
            throw RuntimeException ("v no es un vertice del grafo")
        }

        var dV = distancia[v]

        return dV
    }

    /*
    DESCRIPCION: Funcion que retorna el camino de costo minimo de Dijkstra devolviendose por los predecesores.
    
    PRECONDICION: (no hay costo negativo)
    
    POSTCONDICION: (para todo vertice v, distancia[v] > -1)
    
    TIEMPO: O (|V|*|E|)
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

    /*
    DESCRIPCION: Funcion que encuentra un costo negativo en los lados de un digrafo, retornando una 
    variable booleana.
    
    PRECONDICION: (existe w: E -> R)
    
    POSTCONDICION: (neg == (existe w(u,v) : (para todo u,v perteneciente a V : 0 > w(u,v) )))
    
    TIEMPO: O(|E|)
    */
    fun existeCostoNeg() : Boolean {
        var neg = false

        g.iterator().forEach {arco ->
            var w = arco.costo()
            if (w < 0) {
                neg = true

                return neg
            }
        }

        return neg
    }
}
