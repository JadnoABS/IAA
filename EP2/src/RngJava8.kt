import java.util.*

class RngJava8 : Random() {

    fun nextUInt32() : Int {
        return this.next(31);
    }

}

fun main() {
    val rng = RngJava8()
    rng.setSeed(0)
    for (i in 0..19) {
        println(rng.nextUInt32())
    }
    rng.setSeed(-0x1L)
    for (i in 0..19) {
        println(rng.nextUInt32())
    }
    rng.setSeed(-0x5f5f5f5f5f5f5f60L)
    for (i in 0..19) {
        println(rng.nextUInt32())
    }
}
