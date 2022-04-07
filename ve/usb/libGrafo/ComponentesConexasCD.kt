//Ana Santos 17-10602
package ve.usb.libGrafo

/*
DESCRIPCION: Implementacion de la clase Componentes Conexas como Conjuntos Disjuntos
usando la clase ConjuntosDisjuntos
*/
public class ComponentesConexasCD(val g: GrafoNoDirigido) {

    var n = g.obtenerNumeroDeVertices()
    var cd = ConjuntosDisjuntos(n)

    /*
    DESCRIPCIÓN: Implementación de las componentes conexas, tal que se busca la arista
    y si representante de la fuente y representante del sumidero son distintos entonces
    se unen estos vertices.
    PRECONDICIÓN: (aristas.sizes > 0)
    POSTCONDICIÓN: (cd.size > n)
    TIEMPO: O(numeroDeLados)
    */
    init {
        var aristas = g.iterator()

        aristas.forEach {i ->
            var x = i.cualquieraDeLosVertices()
            var y = i.elOtroVertice(x)

            if (cd.representante[x] != cd.representante[y]) {
                cd.union(x,y)
            }
        }
    }

    /*
    DESCRIPCIÓN: Función que retorna si dos vertices se encuentran en la misma componente
    PRECONDICIÓN: ((v in 0..n-1) && (u in 0..n-1))
    POSTCONDICIÓN: (mismaComponente() == (representante[u] == representante[v]))
    TIEMPO: O(1)
    */
    fun mismaComponente(v: Int, u: Int) : Boolean {
	    if (!(v in 0..n-1) || !(u in 0..n-1)) {
            throw RuntimeException("Al menos uno de los vertices introducidos no pertenece al grafo")
        }
        
        if (cd.representante[v] == cd.representante[u]) {
            
            return true
        } else {
            
            return false
        }
    }

    /*
    DESCRIPCIÓN: Funcion que retorna numero de componentes conexas usando una funcion
    de Conjuntos Disjuntos
    PRECONDICIÓN: (cd > 0)
    POSTCONDICIÓN: (n >= cd.size)
    TIEMPO: O(1)
    */
    fun nCC() : Int {

        return cd.numConjuntosDisjuntos()
    }

    /*
    DESCRIPCIÓN: Función que retorna representante de v, que se encuentra en la posicion v
    del arreglo representante.
    PRECONDICIÓN: (v in 0..n-1)
    POSTCONDICIÓN: (representante[v] <= n)
    TIEMPO: O(|V|)
    */
    fun obtenerComponente(v: Int) : Int {
	
        return cd.representante[v]
    }

    /*
    DESCRIPCIÓN: Funcion que retorna el numero de vertices que se encuentran en una 
    componente, contando si el elemento pertenece a la componente igual a compID
    PRECONDICIÓN: (0 < compID <= n)
    POSTCONDICIÓN: (contador <= n)
    TIEMPO: O(|V|)
    */
    fun numVerticesDeLaComponente(compID: Int) : Int {
        var tamaño = cd.representante.size
        var count = 0

        for (i in 0..tamaño-1) {
            if (cd.representante[i] == compID && compID != 0) {
                count += 1
            } else if (compID == 0) {
                println("Las componentes están identificadas a partir de la 1")
            }
        }

        return count
    }
}
