

class Heap(val size: Int) {

    private val array = IntArray(size);
    private var heapSize = size;

    fun addToIndex(i: Int, elem: Int) {
        if(i < size) {
            this.array[i] = elem;
        } else {
            throw IllegalArgumentException("Index out of array bounds");
        }
    }

    fun elemInIndex(i: Int): Int {
        if(i < size) {
            return this.array[i];
        } else {
            throw IllegalArgumentException("Index out of array bounds");
        }
    }

    private fun leftChild(elem: Int): Int {
        return ((elem + 1) * 2) - 1;
    }

    private fun rightChild(elem: Int): Int {
        return (elem + 1) * 2;
    }

    private fun parent(elem: Int): Int {
        return (elem + 1) / 2;
    }

    private fun swap(i: Int, j: Int) {
        val aux = this.array[i];
        this.array[i] = this.array[j];
        this.array[j] = aux;
    }

    private fun maxHeapify(elem: Int) {
        val left = this.leftChild(elem);
        val right = this.rightChild(elem);
        var biggest = elem;

        if(left < this.heapSize && this.array[left] > this.array[elem]){
            biggest = left;
        }
        if(right < this.heapSize && this.array[right] > this.array[biggest]) {
            biggest = right;
        }
        if(biggest != elem){
            this.swap(elem, biggest)
            this.maxHeapify(biggest);
        }
    }

    private fun buildMaxHeap() {
        for(i in this.parent(this.heapSize - 1) downTo 0){
            this.maxHeapify(i);
        }
    }

    fun heapsort() {
        this.buildMaxHeap();
        for(i in this.array.size-1 downTo 1){
            this.swap(0, i);
            this.heapSize--;
            this.maxHeapify(0);
        }
    }

}

class TestRng() {
    val rng = RngJava8();

    val SEU_NUSP = 12608618;
    val CPU = "https://www.cpubenchmark.net/cpu.php?cpu=Intel+Core+i5-10300H+%40+2.50GHz&id=3646";
    val PASSMARK = 8759;
    val MEMORIA_RAM = "DDR4";
    val FREQUENCIA_DE_OPERACAO_DA_MEMORIA = 3200;
    val QUANTIDADE_DE_MEMORIA_RAM = 8;
    val QUANTIDADE_DE_PENTES_DE_MEMORIA = 2;
    val SO = "Linux arch 5.10.81-1-lts #1 SMP Sun, 21 Nov 2021 14:20:01 +0000 x86_64 GNU/Linux";
    val OBS = "Utilizando a JVM";
    val ALGORITMO = "HEAPSORT";

    val N = arrayOf( 1e6, 2e6, 5e6, 1e7, 2e7, 5e7, 1e8, 2e8, 5e8, 1e9, 2e9 );
    val sementes = arrayOf( 0, 314, 7676, 202, 457, 1000, 9070140, 2394587, 5827934, 934875, 42 );

    fun identifica() {
        this.rng.setSeed(this.SEU_NUSP.toLong());
        this.rng.nextUInt32();
        this.rng.nextUInt32();
        var seuHashCode: Int = this.rng.nextUInt32();

        println("${seuHashCode}\t ${this.CPU}\t ${this.PASSMARK}\t " +
                "${this.MEMORIA_RAM}\t ${this.FREQUENCIA_DE_OPERACAO_DA_MEMORIA}\t " +
                "${this.QUANTIDADE_DE_MEMORIA_RAM}\t ${this.QUANTIDADE_DE_PENTES_DE_MEMORIA}\t " +
                "${this.SO}\t ${this.ALGORITMO}\t ");
    }

    fun initTeste(iTeste: Int){
        val n = this.N[iTeste].toInt();
        this.rng.setSeed(this.sementes[iTeste].toLong());
        var arrayTeste = Heap(n);

        for(i in 0 until n){
            arrayTeste.addToIndex(i, this.rng.nextUInt32());
        }
        print("${arrayTeste.elemInIndex(n-1)}\t ");

        val begin = System.nanoTime();

        arrayTeste.heapsort();

        val end = System.nanoTime();

        print("${(end-begin)/1000}\t ");
    }

}

fun main() {
    val nTestesAExecutar = 11;
    val teste = TestRng();

    teste.identifica();
    for(i in 0 until nTestesAExecutar){
        teste.initTeste(i);
    }
    print(teste.OBS + "\n");
}