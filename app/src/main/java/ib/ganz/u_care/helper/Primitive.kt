package ib.ganz.simadminkarangtaruna.helper

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import androidx.core.text.HtmlCompat

// ------------------------ BOOLEAN ------------------------ //

inline infix fun Boolean?.then(block: () -> Unit): Boolean? {
    if (this == true) block()
    return this
}

inline fun Boolean?.then(b1: () -> Unit, b2: () -> Unit): Boolean? {
    if (this == true) b1()
    else b2()
    return this
}

inline infix fun Boolean?.otherwise(block: () -> Unit) {
    if (this == false) block()
}

fun <T> Boolean?.switch(f: () -> T, f1: () -> T): T {
    return if (this == true) f() else f1()
}

fun <T> Boolean?.switch(f: T, f1: T): T {
    return if (this == true) f else f1
}

fun Boolean?.toInt(): Int = if (this == true) 1 else 0

// ------------------------ NUMBER ------------------------ //


fun Int.moreThan(i: Int, f: (Int) -> Unit) {
    if (this > i) f(this)
}

fun Int.lessThan(i: Int, f: (Int) -> Unit) {
    if (this < i) f(this)
}

fun Int.equalTo(i: Int, f: (Int) -> Unit) {
    if (this == i) f(this)
}

fun Int.toView(c: Context, f: (View.() -> Unit)? = null): View {
    val v = LayoutInflater.from(c).inflate(this, null)
    f?.invoke(v)
    return v
}

// ------------------------ STRING ------------------------ //

fun String.parseColor() = Color.parseColor(this)

fun String.fromHtml(): Spanned {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) Html.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY)
    else Html.fromHtml(this)
}

// ------------------------ LIST ------------------------ //

fun <T> Iterable<T>.positionOf(t: T): Int {
    this.forEachIndexed { i, v ->
        if (v.toString() == t.toString()) {
            return i
        }
    }
    return -1
}

fun <T> MutableList<T>.replaceWith(ts: MutableList<T>?): MutableList<T> {
    clear()
    ts?.let { addAll(ts) }
    return this
}