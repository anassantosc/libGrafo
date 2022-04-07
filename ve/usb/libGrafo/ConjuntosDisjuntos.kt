//Ana Santos 17-10602
package ve.usb.libGrafo
import java.util.LinkedList

/*
DESCRIPCIÓN: Implementación de la clase Conjuntos Disjuntos, donde encontramos 
las funciones base como make-set, union-set, find-set. 
*/
public class ConjuntosDisjuntos(val n: Int) {

    var componente : MutableList<LinkedList<Int>> = mutableListOf()
    var representante = Array(n){(0)}

    /*
    DESCRIPCIÓN: Esta es la implementación de make-set, donde se realiza unas lista
    de componentes y representantes.
    PRECONDICIÓN: (n > 1)
    POSTCONDICIÓN: (representantes[n-1] == n)
    TIEMPO: O(|V|)
    */
    init {
        var i = 0
        while (i < n)  {
            var j = LinkedList<Int>()
            j.add(i)
            representante[i] = i+1
            i += 1
            componente.add(j)
        }  
    }

    /*
    DESCRIPCIÓN: Función que implementa el algoritmo union-set, donde si los representantes
    son distintos, entonces se agrega una lista a la otra y se cambian los representantes. 
    Luego, renombramos a todos los representantes, según si la componente en la que se encuentra
    el vértice. Finalmente, si los representantes son iguales retorna true y en caso contrario
    retorna false.
    PRECONDICIÓN: (v != u)
    POSTCONDICIÓN: (unido == (representante[v] == representante[u]))
    TIEMPO: O(|V|^2)
    */
    fun union(v: Int, u: Int) : Boolean {
        if ((v !in 0..n-1) || (u !in 0..n-1)) {
            throw RuntimeException ("Al menos uno de los vertices no pertenece al grafo")
        }

        var a = representante[v]-1
        var b = representante[u]-1

        if (a != b) {
            componente[a].addAll(componente[b])
            componente.remove(componente[b])
            representante[u] = representante[v]
            for (i in 0..representante.size-1) {
                componente.forEachIndexed { index,j ->
                    if (j.contains(i)) {
                        representante[i] = index + 1
                    }
                }
            }
        }
        
        var unido : Boolean

        if (representante[v] == representante[u]) {
            unido = true
        } else {
            unido = false
        }

        return unido
    }

    /*
    DESCRIPCIÓN: Función que retorna representante de v, que se encuentra en la posicion v
    del arreglo representante.
    PRECONDICIÓN: (v in 0..n-1)
    POSTCONDICIÓN: (representante[v] <= n)
    TIEMPO: O(|V|)
    */
    fun encontrarConjunto(v: Int) : Int {
        if (v !in 0..n-1) {
            throw RuntimeException ("Este vertice no pertenece a ninguna componente")
        }

        return representante[v]
    }

    /*
    DESCRIPCIÓN: Función que retorna el numero de conjuntos disjuntos que hay, es decir, 
    retorna el número de listas dentro de la lista componente.
    PRECONDICIÓN: (!componente.isEmpty())
    POSTCONDICIÓN: (contador <= n)
    TIEMPO: O(|V|)
    */
    fun numConjuntosDisjuntos() : Int {
        var contador = 0

        for (i in componente) {
            contador += 1
        }

        return contador
    }
}
