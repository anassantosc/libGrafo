//Ana Santos 17-10602
package ve.usb.libGrafo

/*
DESCRIPCIÓN: Función que retorna el grafo inverso de un Grafo Dirigido. Agregando arco por arco, pero intercambiando fuente por sumidero.

PRECONDICIÓN: (g es un GrafoDirigido)

POSTCONDICIÓN: (i == g^(-1))

TIEMPO: O(|V||E|)
*/
fun digrafoInversoCosto(g: GrafoDirigidoCosto) : GrafoDirigidoCosto {
    
    var num = g.obtenerNumeroDeVertices()
    var i = GrafoDirigidoCosto(num)

    g.iterator().forEach {v ->
        var t = ArcoCosto(v.sumidero(),v.fuente(),v.costo())
        i.agregarArcoCosto(t)
    }

    return i
}
