//Ana Santos 17-10602
package ve.usb.libGrafo

/*
DESCRIPCIÓN: Implementación de la Clase Componentes Conexas apartir de una modificación
de DFS.
*/
public class ComponentesConexasDFS(val g: GrafoNoDirigido) {

  var busquedadCC = DFS(g)
  var n = g.obtenerNumeroDeVertices()

  /*
  DESCRIPCIÓN: Función que comprueba si dos vertices están en la misma componente, 
  esto se hace comparando el representante de cada uno guardado en la respectiva 
  posición del arreglo cc en Búsqueda En Profundidad
  PRECONDICIÓN: ((v in 0..n.1) && (u in 0..n-1))
  POSTCONDICIÓN:(estanMismaComponente() == (cc[u] == cc[v]))
  TIEMPO: O(|V|)
  */
  fun estanMismaComponente(v: Int, u: Int) : Boolean {
    if ((v !in 0..n-1) || (u !in 0..n-1)) {
      throw RuntimeException ("Al menos uno de estos vertices no pertenece al grafo")
    }

    var compDeV = busquedadCC.cc[v]
    var compDeU = busquedadCC.cc[u]

    if (compDeV == compDeU) {

      return true
    } else {

      return false
    }
  }

  /*
  DESCRIPCIÓN: Función que retorna el numero de componentes guardado en 
  contCC de busqueda en profundidad
  PRECONDICIÓN: (!componente.isEmpty())
  POSTCONDICIÓN: (contCC <= n)
  TIEMPO: 0(|V|)
  */
  fun nCC() : Int {
	  
    return busquedadCC.contCC
  }

  /*
  DESCRIPCIÓN: Funcion que retorna el numero de la componente a la que pertenece v
  PRECONDICIÓN: (v in 0..n-1)
  POSTCONDICIÓN: (compDeV <= n)
  TIEMPO: O(|V|)
  */
  fun obtenerComponente(v: Int) : Int {
    var compDeV = busquedadCC.cc[v]

    return compDeV
  }

  /*
  DESCRIPCIÓN: Funcion que retorna el numero de vertices que se encuentran en una 
  componente, contando si el elemento pertenece a la componente igual a compID
  PRECONDICIÓN: (0 < compID <= n)
  POSTCONDICIÓN: (contador <= n)
  TIEMPO: O(n)
  */
  fun numVerticesDeLaComponente(compID: Int) : Int {
    var contador = 0

    for (i in 0..n-1) {
      if ((busquedadCC.cc[i] == compID) && (compID != 0)) {
        contador += 1
      } else if (compID == 0) {
        println("Las componentes están identificadas a partir del 1")
      }
    }
    
    return contador
  }

}
