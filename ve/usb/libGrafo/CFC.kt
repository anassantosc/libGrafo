//Ana Santos 17-10602
package ve.usb.libGrafo

/*
DESCRIPCION: Implementación de la Clase CFC para hallar las componentes fuertemente conexas
de un grafo dirigido
*/
public class CFC(val g: GrafoDirigido) {

  var n = g.obtenerNumeroDeVertices() 
  var vOrdenados = DFS(g).lista
  var inverso = digrafoInverso(g)
  var busquedaI = DFS(inverso,vOrdenados)
  var cfc : MutableList<MutableSet<Int>> = mutableListOf()

  /*
  DESCRIPCIÓN: Funcion en cargada de obtener los ciclos CFC, primero se halla el digrafo inverso de g,
  después en arcos del inverso se veridica si existe un backtree, agregando ese vertice sumidero a la lista ciclo 
  y mientras v sea distinto a u, entonces se van agregando los predecesores del vertice sumidero, por último se va 
  agregando el ciclo a la lista de listas cfc.
    
  PRECONDICIÓN: (n > 0)
  
  POSTCONDICIÓN: (!cfc.isEmpty())
  
  TIEMPO: O(|V|+|E|)
  */
  init {
    inverso.iterator().forEach {i ->
      var uno = i.fuente()
      var dos = i.sumidero()
      var tuno = busquedaI.obtenerTiempos(uno)
      var tdos = busquedaI.obtenerTiempos(dos)
      var ciclo = mutableSetOf<Int>()

      if ((tdos.first <= tuno.first) && (tuno.first < tuno.second) && (tuno.second <= tdos.second)) { 
        ciclo.add(dos)
        while(uno != dos) {
          ciclo.add(uno)
          uno = busquedaI.obtenerPredecesor(uno) as Int
        }
        
        cfc.add(ciclo)
      } 
    }
  }

  /*
  DESCRIPCIÓN: Funcion que determina si dos vertices estan fuertemente conectados, revisando si 
  en la lista de ciclos un ciclo contiene a ambos vertices para poder retornar true.
    
  PRECONDICIÓN: ((v in 0..n-1) && (u in 0..n-1))
    
  POSTCONDICIÓN: (estanEnlaMismaCFC(v,u) == cfc.contains(u))
    
  TIEMPO: O(|V|+|E|)
  */
  fun estanEnLaMismaCFC(v: Int, u: Int) : Boolean {
	  if ((v !in 0..n-1) || (u !in 0..n-1)) {
      throw RuntimeException ("Al menos uno de estos vertices no pertenece al grafo")
    }
    
    cfc.forEach {i ->
      if (i.contains(v) && i.contains(u)) {

        return true
      } 
    }

    return false
  }

  /*
  DESCRIPCIÓN: Funcion que retorna el numero de ciclos del digrafo

  PRECONDICIÓN: (cfc.size >= 0)

  POSTCONDICIÓN: (contador >= 0)

  TIEMPO: O(|E|)
  */
  fun numeroDeCFC() : Int {
    var contador = 0

    for (i in cfc) {
      contador += 1
    }

    return contador
  }

  /*
  DESCRIPCIÓN: Funcion que retorna el identificador del ciclo al cual pertenece un vertice
  
  PRECONDICIÓN: (v in 0..n-1)
  
  POSTCONDICIÓN: (identificador > 0)
  
  TIEMPO: O(|E|)
  */
  fun obtenerIdentificadorCFC(v: Int) : Int {
	  var num = this.numeroDeCFC()
    var identificador = 0

    if (v !in 0..n-1){
      throw RuntimeException("El vertice ingresado no pertenece al grafo")
    }
    
    cfc.forEachIndexed {index,i ->
      if (i.contains(v) && (index >= 0) && (index < num)) {
        identificador = index

        return identificador
      }
    }
    
    return identificador
  }
    
    /*
    DESCRIPCIÓN: Funcion en cargada de obtener los ciclos CFC.
    
    PRECONDICIÓN: (n > 0)
    
    POSTCONDICIÓN: (!cfc.isEmpty())
    
    TIEMPO: O(|V|+|E|)
    */
  fun  obternerCFC() : Iterable<MutableSet<Int>> {

    return cfc
  }

  /*
  DESCRIPCIÓN: Funcion que retorna un grafo dirigido de las componentes, tal que se forma con los vertices 
  pertenecientes a los ciclos que están conectados a otros vertices distintos a los pertenecientes al
  ciclo.
  
  PRECONDICIÓN: (!obtenerCFC().isEmpty())
  
  POSTCONDICIÓN: (extra es un subconjunto de g)
  
  TIEMPO: O(|V|)
  */
  fun obtenerGrafoComponente() : GrafoNoDirigido {
	  var num = this.numeroDeCFC()
    var extra = GrafoNoDirigido(num)

    cfc.forEach {i ->
      i.forEach {j ->
        var v = this.obtenerIdentificadorCFC(j)
        g.adyacentes(j).forEach {k -> 
          if (!(i.contains(k.sumidero())) && (k.sumidero() < n)) {          
            var u = this.obtenerIdentificadorCFC(k.sumidero())
            extra.agregarArista(Arista(v,u))
          }
        }
      }
    }

    return extra
  }

}
