//Ana Santos 17-10602
package ve.usb.libGrafo

/* 
DESCRIPCIÓN: Implementación de la clase CicloDigrafo, la cual detecta si existe un ciclo en el grafo.
*/

public class CicloDigrafo(val g: GrafoDirigido) {

    var ciclo = mutableListOf<Int>()
    var orden = OrdenamientoTopologico(g)
    var profundidad = DFS(g)
    var arco = orden.edge

    /*
    DESCRIPCIÓN: Función que verifica la existencia de un ciclo, buscando si g es un DAG, si lo encuentra
    retorna false, en caso contrario retorna true.
    
    PRECONDICIÓN: (g es un GrafoDirigido)
    
    POSTCONDICIÓN: (ciclo == mutableListOf<Int>)
    
    TIEMPO: O(|E|)
    */
    fun existeUnCiclo() : Boolean {
        g.iterator().forEach {new ->
            var uno = new.cualquieraDeLosVertices()
            var dos = new.elOtroVertice(uno)
            var tuno = profundidad.obtenerTiempos(uno)
            var tdos = profundidad.obtenerTiempos(dos)

            if ((tdos.first <= tuno.first) && (tuno.first < tuno.second) && (tuno.second <= tdos.second)) { 
                arco = new
                return true
            }
        }
        return false
    }

    /*
    DESCRIPCIÓN: Función que realiza BFS para encontrar un ciclo según los predecesores, mientras 
    el predecesor de v sea distinto a u, agrega un vertice al ciclo.
   
    PRECONDICIÓN: (existeunciclo() == true)
    
    POSTCONDICIÓN: (ciclo.size > 0)
    
    TIEMPO: O(|V|)
    */
    fun cicloEncontrado() : Iterable<Int> {
        if (!this.existeUnCiclo()){
            throw RuntimeException ("No existen ciclos en el digrafo")
        }

        var v = arco.fuente()
        var u = arco.sumidero()
        
        ciclo.add(u)
        while(v != u) {
            ciclo.add(v)
            v = profundidad.obtenerPredecesor(v) as Int
        }
        ciclo.add(v)
        return this.ciclo
    }

} 
