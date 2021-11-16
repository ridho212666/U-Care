package ib.ganz.bcmonitoring.helper

fun String.toRp(): String {
    val rev = StringBuilder(this).reverse().toString()
    val n = StringBuilder()

    for (i in rev.indices) {
        if (i != 0 && i % 3 == 0) n.append(".")
        n.append(rev.substring(i, i + 1))
    }

    return n.reverse().toString()
}

fun Int.toRp() = "$this".toRp()