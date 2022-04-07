//Ana Santos 17-10602
package ve.usb.libGrafo

/*
DESCRIPCIÓN: Función que retorna el grafo inverso de un Grafo Dirigido. Agregando arco por arco, pero intercambiando fuente por sumidero.

PRECONDICIÓN: (g es un GrafoDirigido)

POSTCONDICIÓN: (i == g^(-1))

TIEMPO: O(|V||E|)
*/
fun digrafoInverso(g: GrafoDirigido) : GrafoDirigido {
    
    var num = g.obtenerNumeroDeVertices()
    var i = GrafoDirigido(num)

    g.iterator().forEach {v ->
        var t = Arco(v.sumidero(),v.fuente())
        i.agregarArco(t)
    }

    return i
}
