KC=	kotlinc
KFLAG=	-cp
GLIB=	ve/usb/libGrafo

all:	libGrafoKt.jar

libGrafoKt.jar: $(GLIB)/Lado.class\
		$(GLIB)/Arco.class\
		$(GLIB)/ArcoCosto.class\
		$(GLIB)/Arista.class\
		$(GLIB)/AristaCosto.class\
		$(GLIB)/Color.class\
		$(GLIB)/BFS.class\
		$(GLIB)/DFS.class\
		$(GLIB)/Grafo.class\
		$(GLIB)/GrafoDirigido.class\
		$(GLIB)/GrafoDirigidoCosto.class\
		$(GLIB)/GrafoNoDirigido.class\
		$(GLIB)/GrafoNoDirigidoCosto.class\
        $(GLIB)/OrdenamientoTopologico.class\
        $(GLIB)/OrdenamientoTopologicoCosto.class\
		$(GLIB)/UtilidadesKt.class\
		$(GLIB)/UtilidadesCostoKt.class\
		$(GLIB)/CFC.class\
		$(GLIB)/CFCcosto.class\
		$(GLIB)/CicloDigrafo.class\
		$(GLIB)/ConjuntosDisjuntos.class\
		$(GLIB)/ComponentesConexasCD.class\
		$(GLIB)/ComponentesConexasDFS.class\
		$(GLIB)/MSTKruskal.class\
		$(GLIB)/MSTPrim.class\
		$(GLIB)/CCM_BellmanFord.class\
		$(GLIB)/CCM_DAG.class\
		$(GLIB)/CCM_Dijkstra.class\
		$(GLIB)/CaminoCriticoPERT.class\
		$(GLIB)/Johnson.class\
		$(GLIB)/FloydWarshall.class\

	jar -cvf $@ $(GLIB)/*.class 

$(GLIB)/Lado.class: $(GLIB)/Lado.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/Lado.kt

$(GLIB)/Arco.class: $(GLIB)/Arco.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/Arco.kt

$(GLIB)/ArcoCosto.class: $(GLIB)/ArcoCosto.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/ArcoCosto.kt

$(GLIB)/Arista.class: $(GLIB)/Arista.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/Arista.kt

$(GLIB)/AristaCosto.class: $(GLIB)/AristaCosto.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/AristaCosto.kt

$(GLIB)/Grafo.class: $(GLIB)/Grafo.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/Grafo.kt

$(GLIB)/GrafoDirigido.class: $(GLIB)/GrafoDirigido.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/GrafoDirigido.kt

$(GLIB)/GrafoDirigidoCosto.class: $(GLIB)/GrafoDirigidoCosto.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/GrafoDirigidoCosto.kt

$(GLIB)/GrafoNoDirigido.class: $(GLIB)/GrafoNoDirigido.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/GrafoNoDirigido.kt

$(GLIB)/GrafoNoDirigidoCosto.class: $(GLIB)/GrafoNoDirigidoCosto.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/GrafoNoDirigidoCosto.kt

$(GLIB)/BFS.class: $(GLIB)/BFS.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/BFS.kt

$(GLIB)/DFS.class: $(GLIB)/DFS.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/DFS.kt	

$(GLIB)/Color.class: $(GLIB)/Color.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/Color.kt

$(GLIB)/OrdenamientoTopologico.class: $(GLIB)/OrdenamientoTopologico.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/OrdenamientoTopologico.kt

$(GLIB)/OrdenamientoTopologicoCosto.class: $(GLIB)/OrdenamientoTopologicoCosto.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/OrdenamientoTopologicoCosto.kt	

$(GLIB)/UtilidadesKt.class: $(GLIB)/Utilidades.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/Utilidades.kt

$(GLIB)/UtilidadesCostoKt.class: $(GLIB)/UtilidadesCosto.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/UtilidadesCosto.kt

$(GLIB)/CFC.class: $(GLIB)/CFC.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/CFC.kt

$(GLIB)/CFCcosto.class: $(GLIB)/CFCcosto.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/CFCcosto.kt	

$(GLIB)/CicloDigrafo.class: $(GLIB)/CicloDigrafo.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/CicloDigrafo.kt

$(GLIB)/ConjuntosDisjuntos.class: $(GLIB)/ConjuntosDisjuntos.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/ConjuntosDisjuntos.kt	

$(GLIB)/ComponentesConexasCD.class: $(GLIB)/ComponentesConexasCD.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/ComponentesConexasCD.kt	

$(GLIB)/ComponentesConexasDFS.class: $(GLIB)/ComponentesConexasDFS.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/ComponentesConexasDFS.kt		

$(GLIB)/MSTKruskal.class: $(GLIB)/MSTKruskal.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/MSTKruskal.kt	

$(GLIB)/MSTPrim.class: $(GLIB)/MSTPrim.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/MSTPrim.kt

$(GLIB)/CCM_BellmanFord.class: $(GLIB)/CCM_BellmanFord.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/CCM_BellmanFord.kt

$(GLIB)/CCM_DAG.class: $(GLIB)/CCM_DAG.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/CCM_DAG.kt

$(GLIB)/CCM_Dijkstra.class: $(GLIB)/CCM_Dijkstra.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/CCM_Dijkstra.kt

$(GLIB)/CaminoCriticoPERT.class: $(GLIB)/CaminoCriticoPERT.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/CaminoCriticoPERT.kt

$(GLIB)/Johnson.class: $(GLIB)/Johnson.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/Johnson.kt

$(GLIB)/FloydWarshall.class: $(GLIB)/FloydWarshall.kt
	$(KC) $(KFLAG) $(GLIB): $(GLIB)/FloydWarshall.kt		

clean:
	rm -rf libGrafoKt.jar META-INF $(GLIB)/*.class
