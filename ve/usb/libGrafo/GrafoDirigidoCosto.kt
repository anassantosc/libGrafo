//Ana Santos 17-10602
package ve.usb.libGrafo
import java.io.File
import java.io.FileInputStream
import java.util.*

public class GrafoDirigidoCosto : Grafo {
    override var vertices: Int = 0
    var grafo = Array(vertices) {mutableListOf<ArcoCosto>()}
    override var lados: Int = 0
    override var grado: Int = 0

    // Se construye un grafo a partir del número de vértices
    constructor(numDeVertices: Int) {
        this.vertices = numDeVertices 
        this.grafo = Array(this.vertices){mutableListOf<ArcoCosto>()}
    }

    
    //Se construye un grafo a partir de un archivo.   
    constructor(nombreArchivo: String)  {
        var inputArchivo = FileInputStream(nombreArchivo)
        var scan = Scanner(inputArchivo, "UTF-8")
        this.vertices = scan.nextLine().toInt()
        this.lados = scan.nextLine().toInt()
        var linea : String
        this.grafo = Array(this.vertices){mutableListOf<ArcoCosto>()}

        //Nos metemos Linea a Linea para formar los lados
        while (scan.hasNext()) {
            linea = scan.nextLine()
            val sincomillas = linea.split(" ")
            val a = ArcoCosto(sincomillas[0].toInt(),sincomillas[1].toInt(),sincomillas[2].toDouble())
            var x = a.cualquieraDeLosVertices()
            var y = a.elOtroVertice(x)
            var cambio = true

            if (this.grafo[x].isEmpty()) {
                this.grafo[x].add(a)
                this.lados += 1
            } else {
                this.grafo[x].forEach { i ->
                    cambio = cambio && (i.sumidero() != y)
                }
                if (cambio) {
                    this.grafo[x].add(a)
                    this.lados +=1
                }
            } 
        } 
    }

    // Agrega un lado al digrafo. Si el lado no encuentra en el grafo se agrega y retorna True,
    // en caso contrario no se agraga al grafo y se retorna false.
    fun agregarArcoCosto(a: ArcoCosto) : Boolean {
        var x = a.fuente()
        var y = a.sumidero()
        var cambio = true

        if (x in 0..this.vertices-1) { 
            if (this.grafo[x].isEmpty()) {
                this.grafo[x].add(a)
                this.lados += 1
            } else {
                this.grafo[x].forEach { i ->
                    cambio = cambio && (i.sumidero() != y)
                }
                if (cambio) {
                    this.grafo[x].add(a)
                    this.lados +=1
                }
            }
        }
            
        return cambio
    }

    /*
    DESCRIPCIÓN: Función que retorna el grado de un vértice, el cual corresponde al tamaño de la lista grafo[v],
    grado exterior, sumado al grado interior el cual corresponde a la cantidad de aristas adyacentes a otros vértices
    de las cuales v es sumidero.
    
    PRECONDICIÓN: (v in 0..vertices-1)
    
    POSTCONDICIÓN: (grado >= 0)
    
    TIEMPO: O(|V|*|E|)
    */
    override fun grado(v: Int) : Int {
        if (v !in 0..this.vertices-1) {
            throw RuntimeException ("v no es un vertice del grafo")
        }
        var e = this.grafo[v].size
        var r = 0
            for (i in this.grafo) {
                i.forEach {new -> 
                    if (new.fin == v) {
                        r += 1
                    }
                }
            }
        this.grado = e+r
        return this.grado
    }

    /*
    DESCRIPCIÓN: Función que retorna el grado de un vértice, el cual corresponde al tamaño de la lista grafo[v],
    grado exterior.
    
    PRECONDICIÓN: (v in 0..vertices-1)
    
    POSTCONDICIÓN: (grado >= 0)
    
    TIEMPO: O(|E|)
    */
    fun gradoExterior(v: Int) : Int {
        if (v !in 0..this.vertices-1) {
            throw RuntimeException ("v no es un vertice del grafo")
        }

        var exterior = this.grafo[v].size
        
        return exterior
    }

    /*
    DESCRIPCIÓN: Función que retorna el grado interior, correspondiente la cantidad de aristas adyacentes 
    a otros vértices de las cuales v es sumidero.
    
    PRECONDICIÓN: (v in 0..vertices-1)
    
    POSTCONDICIÓN: (grado >= 0)
    
    TIEMPO: O(|E|)
    */
    fun gradoInterior(v: Int) : Int {
        if (v !in 0..this.vertices-1) {
            throw RuntimeException ("v no es un vertice del grafo")
        }

        var interior = 0
        for (i in this.grafo) {
            i.forEach {new -> 
                if (new.fin == v) {
                    interior += 1
                }
            }
        }        
        
        return interior
    }

    /*
    DESCRIPCIÓN: Función que retorna la variable lados, donde se reserva el número de lados del grafo.
    
    PRECONDICIÓN: (lados es un dato tipo entero)
    
    POSTCONDICIÓN: (obtenerNumeroDeLados() retorna un dato tipo entero)
    
    TIEMPO: O(1)
    */
    override fun obtenerNumeroDeLados(): Int {
        
        return this.lados
    }

    /*
    DESCRIPCIÓN: Función que retorna la variable vértices, donde se reserva el número de vértices del grafo.
    
    PRECONDICIÓN: (vertices es un dato tipo entero)
    
    POSTCONDICIÓN: (obtenerNumeroDeLados() retorna un dato tipo entero)
    
    TIEMPO: O(1)
    */
    override fun obtenerNumeroDeVertices() : Int {
        
        return this.vertices
    }


    /*
    DESCRIPCIÓN: Función que retorna una lista de adyacentes de v, que es la lista que se encuentra guardada
    en la posición v del arreglo grafo.
    
    PRECONDICIÓN: (v in 0..vertices-1)
    
    POSTCONDICIÓN: (adyacentes() es una lista de arcos)
    
    TIEMPO: O(1)
    */
    override fun adyacentes(v: Int) : Iterable<ArcoCosto> {
		
        return this.grafo[v]
    }

    /*
    DESCRIPCIÓN: Función que retorna una lista con todos los arcos, iterando sobre la variable 
    grafo que contiene todas las listas de adyacencias de todos los vértices.
    
    PRECONDICIÓN: (!grafo.isEmpty())
    
    POSTCONDICIÓN: (!arco.isEmpty())
    
    TIEMPO: O(|V|*|E|)
    */
    override operator fun iterator() : Iterator<ArcoCosto> {
        var arco = listOf<ArcoCosto>()

        for (i in this.grafo){
            arco+=(i)
        }
        
        return arco.iterator()
    }

    /*
    DESCRIPCIÓN: Función que retorna el grafo como String
    
    PRECONDICIÓN: (arcos == E && vertices > 0)
    
    POSTCONDICIÓN: (G = (V, E) as String)
    
    TIEMPO: O(|V|*|E|)
    */
    override fun toString() : String {
        var grafostring: String 
        var lista : MutableList<String> = mutableListOf()
        var v = listOf<Int>()

        for (i in 0..this.vertices-1) {
            v += (i)
            this.grafo[i].forEach { arco ->
                var str = arco.toString()
                lista.add(str)
            }
        }

        grafostring = lista.joinToString()
        val verticesstring = v.joinToString()

        return "El grafo dirigido con costo es (V = ["+verticesstring+"] , E = ["+grafostring+"])"
    }
}
