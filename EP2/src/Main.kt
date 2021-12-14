fun partition(arr: Array<Int>, inicio: Int, fim: Int): Int {

    val pivo = arr[fim];
    var i = inicio;

    for(j in inicio..fim-1){
        if(arr[j] <= pivo){
            i++;
            var aux = arr[j];
            arr[j] = arr[i-1];
            arr[i-1] = aux;
        }
    }
    arr[fim] = arr[i];
    arr[i] = pivo;

    return i;
}


fun quicksort(arr: Array<Int>, inicio: Int, fim: Int) {

    if(inicio < fim){
        val pivo = partition(arr, inicio, fim);
        quicksort(arr, inicio, pivo-1);
        quicksort(arr, pivo+1, fim);
    }
}


fun main() {
    val rng = RngJava8();

    rng.setSeed(0);
    var arr = Array<Int>(20, { i -> 0 });
    for(i in 0..19){
        arr[i] = rng.nextUInt32();
        print("${arr[i]} ");
    }
    println();

    quicksort(arr, 0, arr.size-1);
    for(i in arr){
        print("$i ");
    }
    println();

    rng.setSeed(-0x1L);
    for(i in 0..19){
        arr[i] = rng.nextUInt32();
        print("${arr[i]} ");
    }
    println();

    quicksort(arr, 0, arr.size-1);
    for(i in arr){
        print("$i ");
    }
    println();

    rng.setSeed(-0x5f5f5f5f5f5f5f60L);
    for(i in 0..19){
        arr[i] = rng.nextUInt32();
        print("${arr[i]} ");
    }
    println();

    quicksort(arr, 0, arr.size-1);
    for(i in arr){
        print("$i ");
    }
    println();
}