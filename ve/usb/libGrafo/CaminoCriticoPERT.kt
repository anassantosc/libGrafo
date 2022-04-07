//Ana Santos 17-10602
package ve.usb.libGrafo

/*
DESCRIPCION: Implementación del Algoritmo de PERT usando Camino de Costo Mínimo DAG como base
*/
public class CaminoCriticoPERT(val g: GrafoDirigidoCosto, val s: Int) {
    
    var n = g.obtenerNumeroDeVertices()
    var dag = OrdenamientoTopologicoCosto(g).esDAG()
    var max = 0
    var ccc = 0.0
    var critico : Iterable<ArcoCosto> = mutableListOf<ArcoCosto>()

    /*
    DESCRIPCION: Implementacion para encontrar el camino crítico, negando el costo de los lados del grafo
    y posteriormente se le aplica el algoritmo CCM_DAG para encontrar el camino crítico y el costo del mismo.
    
    PRECONDICION: (esDAG == true)
    
    POSTCONDICION: ((para todo v en V: 0<=distancia[v]<=infinito)
    
    TIEMPO: O(V*E)
    */
    init {
        if (!dag) {
            throw RuntimeException("El grado tiene al menos un ciclo")
        }

        var gneg = GrafoDirigidoCosto(n)
        g.iterator().forEach  {arco ->
            var u = arco.fuente()
            var v = arco.sumidero()
            var w = -(arco.costo())
            var a = ArcoCosto(u,v,w)
            gneg.agregarArcoCosto(a)
        }
        var ccmdag = CCM_DAG(gneg,s)
        var s = Double.POSITIVE_INFINITY

        for (i in 0..n-1) {
            var camino = ccmdag.obtenerCaminoDeCostoMinimo(i) as List
            var peso = ccmdag.costo(i)
            if (s > peso) {
                s = peso
                max = i
                critico = camino
            }
        }

        ccc = -ccmdag.costo(max)
    }

    /*
    DESCRIPCION: Funcion que retorna el costo del camino crítico desde el vértice inicial s
    
    PRECONDICION: (max != s)
    
    POSTCONDICION: (ccmdag.costo(max) < ccc)
    
    TIEMPO: O(1)
    */
    fun costo() : Double { 

        return ccc
    }

    /*
    DESCRIPCION: Funcion que retorna una lista de arcos, la cual indica el camino crítico desde s, 
    usando como base el camino obtenido por CCM_DAG
    
    PRECONDICION: (max != s)
    
    POSTCONDICION: (critico.size >= 0)
    
    TIEMPO: O(E)
    */
    fun obtenerCaminoCritico() : Iterable<ArcoCosto> { 
        var aux = mutableListOf<ArcoCosto>()

        g.iterator().forEach {original ->
            critico.forEach {cc ->
                if (original.fuente() == cc.fuente() && original.sumidero() == cc.sumidero()) {
                    aux.add(original)
                }
            }
        }

        return aux
    }
}
