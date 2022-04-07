//Ana Santos 17-10602
package ve.usb.libGrafo

/*
DESCRIPCIÓN: Implementación de un orden topológico de un DAG
*/
public class OrdenamientoTopologicoCosto(val g: GrafoDirigidoCosto) {
  
  var edge = ArcoCosto(0,0,0.0)

  /*
  DESCRIPCIÓN: Función verifica si el Grafo Dirigido es un DAG. En los arcos del grafo, verifica si existe un 
  backtree, en caso de haberlo existe un ciclo, por tanto no es un DAG.
  
  PRECONDICIÓN: (g es un Grafo Dirigido)
  
  POSTCONDICIÓN: (esUnGrafoAciclicoDirecto() retorna un valor booleano)
  
  TIEMPO: O(|E|)
  */  
  fun esDAG() : Boolean { 
    g.iterator().forEach {new ->
      var uno = new.cualquieraDeLosVertices()
      var dos = new.elOtroVertice(uno)
      var tuno = DFS(g).obtenerTiempos(uno)
      var tdos = DFS(g).obtenerTiempos(dos)
      
      if ((tdos.first <= tuno.first) && (tuno.first < tuno.second) && (tuno.second <= tdos.second)) {
        return false
      }
    }

    return true
  }

  /*
  DESCRIPCIÓN:Función que retorna el orden topológico del grafo DAG, utilizando la variable lista de BusquedaEnProfundidad
  
  PRECONDICIÓN: (g es un DAG)
  
  POSTCONDICIÓN: (lista.size > 0)
  
  TIEMPO: O(|V|)
  */
  fun obtenerOrdenTopologico() : Iterable<Int> { 
    var orden = DFS(g).lista 
     
    if (!this.esDAG()) {
      throw RuntimeException("El Digrafo no es un DAG")
    } 
    
    return orden    
  }

}
