# QuickSort & Binary Insertion Sort

### Introduzione

L’esercizio consisteva nell’implementazione di una libreria che offrisse due algoritmi di sorting: il binary insertion sort ed il quicksort. Nella presente relazione analizziamo i nostri risultati su un dataset di 20.000.000, i risultati parziali ed in alcuni casi i fallimenti .

Il binary insertion sort consiste in una modifica dell’insertion sort, in cui la ricerca della posizione corretta in cui inserire l’elemento corrente viene fatto attraverso una ricerca binaria, trasformando questo sotto problema in uno affrontato attraverso la famiglia di metodi Divide and Conquer.

Successivamente viene poi effettuata un’iterazione per spostare gli elementi alla destra dell’elemento inserito nelle posizioni successive.

Allo stesso modo il quicksort fa uso di una tecnica di Divide And Conquer con previa scelta di un elemento particolare detto pivot, che verrà utilizzato come perno per confrontare gli elementi e dividere il problema in sotto-problemi che essenzialmente si traduce in questo caso in dividere la struttura array in sub-array.

La scelta del pivot è fondamentale per garantire tempi ottimali nella risoluzione del problema, la nostra scelta si è ridotta essenzialmente a tre casi. In una sezione sottostante è possibile vedere la relativa discussione e risultati.

**è importante far notare che le tempistiche di un algoritmo di sorting, al di là delle scelte tecniche effettuate son dipendenti dalla macchina attraverso cui vengono effettuati.**

### Complessità

Nei casi casi peggiori il quicksort effettua un ordinamento quadratico, questo caso si presenta con l’array ordinato costringendo la ricerca su tutti gli elementi che vengono prima del pivot, spostato in ultima posizione come da prassi nel quicksort.

Un ordinamento in tempi quadratici non si dimostra adatto per dataset di grandi dimensioni ed altri metodi di sorting garantiscono un ordinamento in  O(n*log n)

### Scelta del pivot e variazioni del Quicksort

La nostra scelta del pivot si è ridotta a 4 possibilità, nella figura sotto mostriamo i tempi di esecuzione e a seguito motiviamo queste scelte.

|  | Median | First element | Last element | Random |
| --- | --- | --- | --- | --- |
| Id | > 10 minuti | > 10 minuti | > 10 minuti | >10 minuti |
| String | memory overflow | memory overflow | memory overflow | memory overflow |
| Float |  23.330 secondi | 24.826 secondi | 24.577 secondi | 25, 734 secondi |
| Int |  25.902 secondi | 25.775 secondi | 24.640 secondi | 26.249 secondi |
- random choice to minimize the worst case scenario O(n^2)
- median, essentially taking the first, last and mean, dividing by three and taking that as the pivot
- First element
- Last element

Qualunque sia la scelta del pivot l’ordinamento  di stringhe e id supera i tempi stabiliti dal professore (10 minuti) mentre per quanto riguarda i campi interi e floating point presentano una differenza di 0.78, 20.06 nel caso degli interi e 20.84 nel caso dei floating point per quanto riguarda la scelta dell’ultima posizione come pivot

### Fallimento

- Caso stringhe e Caso id

### Conclusione e paragone

Com’era prevedibile il QuickSort nel caso degli id, che sono già ordinati, ha una complessità temporale asintotica pari a O(n^2), mentre per il caso degli int e dei float ha tempi più accettabili.

Il caso delle stringhe è inaspettato in quanto il problema nasce a livello di complessità spaziale, creando un utilizzo di memoria eccessivo.

Il cambio di pivot fa variare lievemente i risultati, ma non salva dai problemi generali riscontrati con id e stringhe.

Ovviamente tutti i dati riportati sono machine-dependent.