//Ana Santos 17-10602
package ve.usb.libGrafo
import java.util.LinkedList
import java.util.Stack

/* 
DESCRIPCIÓN: Implementación del Algoritmo BFS, tomando como argumentos un grafo g y una lista enlazada
ot que nos indicará el order de búsqueda en caso .
*/
public class DFS(val g: Grafo, val ot: LinkedList<Int> = LinkedList<Int>()) {

    var n = g.obtenerNumeroDeVertices()
    var colores = Array(n){(Color.BLANCO)}
    var pr = Array<Int?>(n){(-1)}
    var succs : Array<MutableSet<Int>> = Array(n){mutableSetOf()}
    var ti = Array(n){(0)}
    var tf = Array(n){(0)}
    var tiempo: Int = 0
    var lista = LinkedList<Int>()
    var cc = Array(n){(0)}
    var contCC = 0

    /*
    DESCRIPCIÓN: Se realiza la implementación de DFS. Através de 5 arreglos, que almacenan los datos de cada vértice según su 
    índice, uno para color, otro para predecesores, otro para tiempo inicial, el ultimo para tiempo final y uno 
    para contar las componentes conexas, y una lista enlazada donde se guardan el orden topológico. Por tanto, todas las 
    asignaciones correspondientes al vértice en el que se busca, se harán a los arreglos
    
    PRECONDICIÓN: (ot.size <= 0)
    
    POSTCONDICIÓN: (para todo i : colores[i]==NEGRO )
    
    TIEMPO: O(|V|)
    */
    init {
        if (ot.size == 0) {
            for (v in 0..n-1) {
                if (colores[v]==Color.BLANCO) {
                    contCC += 1
                    dfsVisit(g,v)
                }
            }
        } else {
            for (v in ot) {
                if (colores[v]==Color.BLANCO) {
                    dfsVisit(g,v)
                }
            }
        }
    }

    /*
    DESCRIPCIÓN: Implementación recursiva de dfsVisit, donde cada vez que se visita un vértice se le marca, el tiempo
    inicial y final, se cambia de color, se guarda la componente a la que pertenece y se visitan sus adyacentes.
    
    PRECONDICIÓN: (u in 0..n-1)
    
    POSTCONDICIÓN: (color[u] == NEGRO)
    
    TIEMPO: O(|E|)
    */
    private fun dfsVisit(g: Grafo, u: Int) {
        tiempo = tiempo + 1
        cc[u] = contCC
        ti[u] = tiempo
        colores[u] = Color.GRIS
        g.adyacentes(u).forEach {w -> 
            if (colores[w.elOtroVertice(u)]==Color.BLANCO) {
                pr[w.elOtroVertice(u)] = u
                succs[u].add(w.elOtroVertice(u))
                dfsVisit(g,w.elOtroVertice(u)) 
            }
        }
        colores[u] = Color.NEGRO
        tiempo = tiempo + 1
        tf[u] = tiempo
        this.lista.addFirst(u)
    }

    /*
    DESCRIPCIÓN: Función que retorna el predecesor de un vértice v, que se encuentra guardado en la posición v del 
    arreglo pr
    
    PRECONDICIÓN: (v in 0..n-1)
    
    POSTCONDICIÓN: (predecesor as Int?)
    
    TIEMPO: O(|V|)
    */  
    fun obtenerPredecesor(v: Int) : Int? { 
        if (v !in 0..g.obtenerNumeroDeVertices()){
            throw RuntimeException("El vertice ingresado no pertenece al grafo")
        } 

        if (pr[v] == -1) {
            return null
        }

        return pr[v]
    }

    /*
    DESCRIPCIÓN: Función que retorna el par de tiempo inicial y tiempo final, ubicados en la posición v de los arreglos
    correspondientes
    
    PRECONDICIÓN: (v in 0..n-1)
    
    POSTCONDICIÓN: (par == Pair(ti[v],tf[v]))
    
    TIEMPO: O(|V|)
    */
    fun obtenerTiempos(v: Int) : Pair<Int, Int> { 
        var inicio = ti[v]
        var fin = tf[v]
        var par = Pair(inicio,fin)
        
        return par
    }

    /*
    DESCRIPCION: Funcion que retorna si existe un camino entre los vertice u y v.
    
    PRECONDICION: (v && u in 0..n-1)
    
    POSTCONDICION: (hay == (ti[u] < ti[v]) && (tf[v] < tf[u]))
    
    TIEMPO: O(|V^2|)
    */
    fun hayCamino(u: Int, v: Int) : Boolean {  
        if ((v !in 0..g.obtenerNumeroDeVertices()) || (u !in 0..g.obtenerNumeroDeVertices())){
            throw RuntimeException("El vertice ingresado no pertenece al grafo")
        }
        var hay = false
        if ((ti[u] < ti[v]) && (tf[v] < tf[u])) {
            hay = true
        }
        
        return hay
    }

    /*
    DESCRIPCION: Funcion que retorna una lista de vertices, lo cuales indican el camino de s hasta v en caso de que exista camino, para ello 
    se mueve por los predecesores, empezando por v hasta llegar a u, y guardando en la lista los vertices que sean (predecesor[v], v). En caso 
    contrario, devuelve una lista vacia.
    
    PRECONDICION: (v && u in 0..n-1)
    
    POSTCONDICION: (ccm.size > 0)
    
    TIEMPO: 0(|V^2|)
    */
    fun caminoDesdeHasta(u: Int, v: Int) : Iterable<Int>  { 
        if ((v !in 0..n-1) || (u !in 0..n-1)){
            throw RuntimeException("El vertice ingresado no pertenece al grafo")
        }

        var c = mutableListOf<Int?>()
        var camino = mutableListOf<Int>()
        var stack = Stack<Int>()
        var existe = this.hayCamino(u,v)

        if (existe) {
            var aux = v
            while (aux != u) {
                c.add(aux)
                aux = pr[aux] as Int
            }
        }
        c.add(u)
        stack.addAll(c)
        while (!stack.isEmpty()) {
            var aux = stack.pop() as Int
            camino.add(aux)
        }
        return camino
    }

    // Imprime por la salida estándar el depth-first forest
    fun depthFirstForest() { 
        var arbol = mutableSetOf<Int>()
        var bosque : MutableList<MutableSet<Int>> = mutableListOf()

        println("DEPTH FIRST FOREST")
        for (i in 1..contCC) {
            for (j in 0..n-1) {
                if (cc[j] == i) {
                    arbol.add(j)
                }
            }
            bosque.add(arbol)
            arbol = mutableSetOf<Int>()
        }
        println(bosque)
    }

}
