# QuickSort & Binary Insertion Sort

### Introduzione

-L’esercizio consisteva nell’implementazione di una libreria che offrisse due algoritmi di sorting: il binary insertion sort ed il quicksort. Nella presente relazione analizziamo i nostri risultati su un dataset di 20.000.000, i risultati parziali ed in alcuni casi i fallimenti .-

-Il binary insertion sort consiste in una modifica dell’insertion sort, in cui la ricerca della posizione corretta in cui inserire l’elemento corrente viene fatto attraverso una ricerca binaria, trasformando questo sotto problema in uno affrontato attraverso la famiglia di metodi Divide and Conquer.-

-Successivamente viene poi effettuata un’iterazione per spostare gli elementi alla destra dell’elemento inserito nelle posizioni successive.-

-Allo stesso modo il quicksort fa uso di una tecnica di Divide And Conquer con previa scelta di un elemento particolare detto pivot, che verrà utilizzato come perno per confrontare gli elementi e dividere il problema in sotto-problemi che essenzialmente si traduce in questo caso in dividere la struttura array in sub-array.-

-La scelta del pivot è fondamentale per garantire tempi ottimali nella risoluzione del problema, la nostra scelta si è ridotta essenzialmente a tre casi. In una sezione sottostante è possibile vedere la relativa discussione e risultati.-

**è importante far notare che le tempistiche di un algoritmo di sorting, al di là delle scelte tecniche effettuate son dipendenti dalla macchina attraverso cui vengono effettuati.**

### Complessità

Nei casi casi peggiori il quicksort effettua un ordinamento con un tempo quadratico, questo caso si presenta con l’array ordinato costringendo la ricerca su tutti gli elementi che vengono prima del pivot, spostato in ultima posizione come da prassi nel quicksort.

Un ordinamento in tempi quadratici non si dimostra adatto per dataset di grandi dimensioni ed altri metodi di sorting garantiscono un ordinamento in  O(n*log n)


### Scelta del pivot e variazioni del Quicksort

La nostra scelta del pivot si è ridotta a 4 possibilità, nella figura sotto mostriamo i tempi di esecuzione e a seguito motiviamo queste scelte.

|           | Median            | Last element   | Random          |
| ---       | ---               | ---            | ---             |
| Id        |  > 10 minuti      | > 10 minuti    | > 10 minuti     |
| String    |  > 10 minuti      | > 10 minuti    | > 10 minuti     |
| Int       |  19.96 secondi    | 26.42 secondi  | 20.55 secondi   |
| Float     |  19.98 secondi    | 24.66 secondi  | 19.91 secondi   |

- random viene scelto come pivot un elemento randomico, in questo modo si riduce la possilità di sviluppare casi O(n^2).
- Mo3 si prende come pivot il risultato dato dalla somma della prima posizione, l'ultima e la metà divise per 3.
- Last element & First element sono simili, non danno alcun risultato interessante nella scelta, hanno maggiore probabilità         
    di incorrere nel caso peggiore.

Qualunque sia la scelta del pivot l’ordinamento  di stringhe e id supera i tempi stabiliti dal professore (10 minuti) mentre per quanto riguarda i campi interi e floating point presentano una lieve differenza.

### Fallimento

- Caso stringhe e Caso id
- Il motivo è differente:
- 1) gli id sono già ordinati in ordine crescente, quindi con qualsiasi pivot il risultato non cambierà
- 2) per ordinare le stringhe occorre effettuare un controllo lettera per lettera fino a quando non trova una differenza e quindi 
        sa dove posizionare quella stringa.


### Tempo di computazione del Binary Insertion Sort
|Id         |  > 10 minuti
|String     |  > 10 minuti
|Int        |  > 10 minuti
|Float      |  > 10 minuti

- Il Binary Insertion Sort non è in grado di garantire un tempo computazionale accettabile per l'ordinamento di un dataset di 
    queste dimensioni, questo è dovuto dal gran numero di confronti che deve fare per ogni elemento che ha da posizionare.
- Questo algoritmo lavora bene in caso di un numero ristretto di elementi.
- Per ottimizzare questo algoritmo si potrebbe associare un merge sort, in questo modo si potrebbero creare dei sub-array di 
    dimesioni ridotte e poi ordinare ciascuno mediante Binary Insertion Sort. 


### Conclusione e paragone

Com’era prevedibile il QuickSort nel caso degli id, che sono già ordinati, ha una complessità temporale asintotica pari a O(n^2), mentre per il caso degli int e dei float ha tempi più accettabili.

Il cambio di pivot fa variare lievemente i risultati, ma non salva dai problemi generali riscontrati con id e stringhe.

Mettendo a confronto i due algoritmi si riscontra che, nonstante il problema con le stringhe e gli elementi ordinati, il quicksort è molto più veloce rispetto al binary insertion sort.