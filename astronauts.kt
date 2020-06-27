import java.io.*
import java.math.*
import java.security.*
import java.text.*
import java.util.*
import java.util.concurrent.*
import java.util.function.*
import java.util.regex.*
import java.util.stream.*
import kotlin.collections.*
import kotlin.comparisons.*
import kotlin.io.*
import kotlin.jvm.*
import kotlin.jvm.functions.*
import kotlin.jvm.internal.*
import kotlin.ranges.*
import kotlin.sequences.*
import kotlin.text.*

// Complete the journeyToMoon function below.
fun journeyToMoon(n: Int, astronaut: Array<Array<Int>>): Long {
    var m=n
    var id=mutableListOf<Int>()
    var sz=mutableListOf<Int>()
    var szroots=mutableListOf<Int>()
    var nastro=mutableListOf<Int>()
    for(i in 0..n){
        id.add(i)
        sz.add(1)
        szroots.add(0)
        nastro.add(0)
    }
    fun root(i:Int ):Int{
        var ii=i
        while (ii != id[ii]){
            id[ii] = id[id[ii]];
            ii=id[ii]
        }
        return ii
    }
    fun union(p:Int, q:Int){
        m--
        var i = root(p)
        var j = root(q)
        if (!(i==j)){
            if (sz[i] < sz[j]) {
                id[i] = j
                sz[j] += sz[i]
                szroots[j]=sz[j]
                szroots[i]=0
            }
            else {
                id[j] = i
                sz[i] += sz[j]
                szroots[i]=sz[i]
                szroots[j]=0
            }
        }
    }
    var ncon=0  //all stranouts with country pairs
    for (i in astronaut.indices){
        if(nastro[astronaut[i][0]]==0){
            ncon++
            nastro[astronaut[i][0]]=1
        }
        if(nastro[astronaut[i][1]]==0){
            ncon++
            nastro[astronaut[i][1]]=1
        }
        union(astronaut[i][0],astronaut[i][1])
    }
    var pwcp=0 //possible ways country with pairs
    var sumszr=0
    for (i in szroots.indices) {
        if (szroots[i] != 0) {
            sumszr += szroots[i]
            pwcp += (ncon - sumszr) * szroots[i]
        }
    }

    var cwop=n-ncon // country without pairs
    var pw=0L//possible ways
    if(cwop>1){
        cwop--
        for (i in 1..cwop){
            pw+=i
        }
    }else{
        pw=0
    }
    pw
    var a=(n-ncon)*(ncon)
    pw+=a
    pw+=pwcp




    return (pw)
}

fun main(args: Array<String>) {
    val scan = Scanner(System.`in`)

    val np = scan.nextLine().split(" ")

    val n = np[0].trim().toInt()

    val p = np[1].trim().toInt()

    val astronaut = Array<Array<Int>>(p, { Array<Int>(2, { 0 }) })

    for (i in 0 until p) {
        astronaut[i] = scan.nextLine().split(" ").map{ it.trim().toInt() }.toTypedArray()
    }

    val result = journeyToMoon(n, astronaut)
    print(result)
    /*println(" ")
    for (i in result.indices){
        print(result[i])
    }*/
}
