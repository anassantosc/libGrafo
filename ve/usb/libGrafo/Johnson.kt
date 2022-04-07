//Ana Santos 17-10602
package ve.usb.libGrafo
import java.util.Stack

/*
DESCRIPCION: Implementación de la Clase Johnson para encontrar los caminos de costo mínimo todos los pares de vértices de un grafo,
usando como base el pseudocodigo del Cormen sobre algoritmo de Johnson 
*/
public class Johnson(val g: GrafoDirigidoCosto) {
    
    var n = g.obtenerNumeroDeVertices()
    var n1 = n+1
    var g1 = GrafoDirigidoCosto(n1)
    var s = n
    var h = Array(n1){(0.0)}
    var d = Array<Array<Double>>(n){Array<Double>(n){Double.POSITIVE_INFINITY}}
    var p = Array<Array<Int?>>(n){Array<Int?>(n){null}}
    var cicloneg = false

    /*
    DESCRIPCION: Implementacion del algoritmo Johnson para obtener los caminos de costo minimo entre todos los vertices. Esta implementacion utiliza 
    un grafo g1 donde se le agregar un vertice s que pasa a ser una fuente del grafo, después se calculan los caminos con BellmanFord(s,v) para todos los
    v del grafo, y en cicloneg se guarda, la existencia de un ciclo negativo (ya que la implementacio de las funciones no es posible en caso de haberlo). 
    En otro grafo gaux se guardan los arcos con los nuevos costos en h (los cuales se guardan en este arreglo con BellmanFord), para posteriormente
    llenar la matriz de distancia usando Dijkstra(g, u) para todos los vertices u del grafo y tambien la matriz de predecesores.
    
    PRECONDICION: (|E| >= 0)
    
    POSTCONDICION: (para todo u,v perteneciente a |V| : Double.POSITIVE_INFINTY >= d[u][v] )
    
    TIEMPO: O(|V|*|E|log|V|)
    */
    init {
        g.iterator().forEach {arco ->
            g1.agregarArcoCosto(arco)
        }
        for (i in 0..n-1) {
            var arco = ArcoCosto(s,i,0.0)
            g1.agregarArcoCosto(arco)
        }

        var bf = CCM_BellmanFord(g1,s)
        cicloneg = bf.tieneCicloNegativo()
        for (i in 0..n1-1) {
            h[i] = bf.costo(i)
        }

        var gaux = GrafoDirigidoCosto(n)
        g1.iterator().forEach {arco ->
            var u = arco.fuente()
            if (u != s) {
                var v = arco.sumidero()
                var w = arco.costo()
                var w1 = w + h[u] - h[v]
                var new = ArcoCosto(u,v,w1)
                gaux.agregarArcoCosto(new) 
            }
        }

        for (u in 0..n-1) {
            var dij = CCM_Dijkstra(gaux,u)
            var daux = Array(n){(0.0)}
            var paux = Array<Int?>(n){(null)}
            for (v in 0..n-1) {
                daux[v] = dij.costo(v)
                paux[v] = dij.pred[v]
                d[u][v] = daux[v] + h[v] - h[u]
                p[u][v] = paux[v]
            }
        }
    }

    /*
    DESCRIPCION: Funcion que retorna si existe un ciclo negativo, informacion que se encuentra en la variable cicloneg
    
    PRECONDICION: (para todo u,v perteneciente a |V| : Double.POSITIVE_INFINTY >= d[u][v])
    
    POSTCONDICION: (para todo s,v perteneciente a |V| : cicloneg == BellmanFord(s,v).tieneCicloNegativo())
    
    TIEMPO: O(1)
    */
    fun hayCicloNegativo() : Boolean { 
        
        return cicloneg
    }
    
    /*
    DESCRIPCION: Funcion que retorna la matriz de distancia obtenida en el init, d.
    
    PRECONDICION: (|V| > 0)
    
    POSTCONDICION: (para todo u,v perteneciente a |V| : Double.POSITIVE_INFINTY >= d[u][v])
    
    TIEMPO: O(1)
    */
    fun obtenerMatrizDistancia() : Array<Array<Double>> { 
        
        return d
    } 
    
    /*
    DESCRIPCION: Funcion que retorna el costo del camino de costo minimo, lo cual es equivalente a retornar d[u][v]
    
    PRECONDICION: ((v && u in 0..n-1) && this.hayCicloNegativo())
    
    POSTCONDICION: (para todo u,v perteneciente a |V| : Double.POSITIVE_INFINTY >= d[u][v])
    
    TIEMPO: O(|V|^2)
    */
    fun costo(u: Int, v: Int) : Double { 
       if (!(u in 0..n-1) && !(v in 0..n-1)) {
            throw RuntimeException ("Al menos uno de los vertices engresados no pertenece al grafo")
        }
        
        if (this.hayCicloNegativo()) {
            throw RuntimeException ("Existe un ciclo negativo no se puede devolver un costo")
        }

        return d[u][v]
    }

    /*
    DESCRIPCION: Funcion que retorna si existe un camino de costo minimo desde u hasta v, lo cual equivale a retorna true si d[u][v] != (0.0 && Double.POSITIVE_INFINITY)
    
    PRECONDICION: ((v && u in 0..n-1) && this.hayCicloNegativo())
    
    POSTCONDICION: (existe == (d[u][v] != (0.0 && Double.POSITIVE_INFINITY)))
    
    TIEMPO: O(|V|^2)
    */
    fun existeUnCamino(u: Int, v: Int) : Boolean {
        var existe = false

        if (!(u in 0..n-1) && !(v in 0..n-1)) {
            throw RuntimeException ("Al menos uno de los vertices engresados no pertenece al grafo")
        }

        if (this.hayCicloNegativo()) {
            throw RuntimeException ("Existe un ciclo negativo, por tanto no hay un camino")
        }
        
        if (d[u][v] != 0.0 && d[u][v] != Double.POSITIVE_INFINITY) {
            existe = true
        }

        return existe
    }

    /*
    DESCRIPCION: Funcion que retorna una lista de arcos del camino de costo minimo segun 
    
    PRECONDICION: ((v && u in 0..n-1) && this.hayCicloNegativo() && this.existeUnCamino())
    
    POSTCONDICION: (ccm.size > 0)
    
    TIEMPO: O(|V|^2)
    */
    fun obtenerCaminoDeCostoMinimo(u: Int, v: Int) : Iterable<ArcoCosto> { 
        if (!(u in 0..n-1) || !(v in 0..n-1)) {
            throw RuntimeException("Uno de los vertices ingresados no pertenece al grafo")
        }

        if (this.hayCicloNegativo()) {
            throw RuntimeException ("Existe un ciclo negativo, por tanto no hay un camino")
        }

        var ccm = mutableListOf<ArcoCosto>()
        var camino = mutableListOf<ArcoCosto>()
        var stack = Stack<ArcoCosto>()
        var x = ArcoCosto(0,0, 0.0)

        if (this.existeUnCamino(u,v)) {
            var aux1 = u
            var aux2 = v

            while (aux2 != u && aux2 != -1) {
                var costo1 = p[aux1][aux2] as Int

                g.iterator().forEach {arco ->
                    if (costo1 == arco.fuente() && aux2 == arco.sumidero()) {
                        x = arco
                    }
                }
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
}
