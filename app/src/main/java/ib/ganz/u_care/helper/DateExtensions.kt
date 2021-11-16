@file:kotlin.jvm.JvmName("XDate")
@file:kotlin.jvm.JvmMultifileClass

package ib.ganz.simadminkarangtaruna.helper

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import org.joda.time.*
import java.util.*

fun String.fromSql()        = DateQu.fromSql(this)
fun String.fromLaravel()    = try { DateQu.fromLaravel(this) } catch (e: Exception) { DateQu.fromSql(this) }
fun String.toCalendar()     = fromSql().toCalendar()
fun String.toDate()         = fromSql().toDate()
fun String.toIndoDate()     = fromSql().toIndoDate()
fun String.toIndoDateTime() = fromSql().toIndoDateTime()
fun String.toEngDateTime()  = fromSql().toEngDateTime()
fun String.toSqlDate()      = fromSql().toSqlDate()
fun String.toDateOnly()     = fromSql().toDateOnly()
fun String.toIndoDayName()  = fromSql().toIndoDayName()
fun String.toTime()         = fromSql().toTime()
fun String.toHour()         = fromSql().toHour()
fun String.toMinute()       = fromSql().toMinute()
fun String.dayInterval()    = fromSql().dayInterval()
fun String.dayCount()       = fromSql().dayCount()
fun String.notPassed()      = fromSql().notPassed()
fun String.isPassed()       = fromSql().isPassed()
fun String.asPast()         = fromSql().asPast()
fun String.asFuture()       = fromSql().asFuture()
fun String.asElapsed()      = fromSql().asElapsed()
fun String.toDayMonth()     = toCalendar().run { "${this.getDayOfMonth()} ${this.getMonthName()}" }
fun String.toMonthName()    = toCalendar().getMonthName()

fun now()                       = DateQu.now()
fun Date.dateQu()               = DateQu.fromDate(this)
fun Calendar.dateQu()           = DateQu.fromCalendar(this)
fun Calendar.getYear()          = get(Calendar.YEAR)
fun Calendar.getMonth()         = get(Calendar.MONTH)
fun Calendar.getMonthName()     = DateQu.indoMonths[get(Calendar.MONTH)]
fun Calendar.getDayOfMonth()    = get(Calendar.DAY_OF_MONTH)
fun Calendar.getDayOfWeek()     = get(Calendar.DAY_OF_WEEK)
fun Calendar.getHour()          = get(Calendar.HOUR_OF_DAY)
fun Calendar.getMinute()        = get(Calendar.MINUTE)
fun Calendar.getSecond()        = get(Calendar.SECOND)
fun Calendar.setYear(i: Int)    = set(Calendar.YEAR, i)
fun Calendar.setMonth(i: Int)   = set(Calendar.MONTH, i)
fun Calendar.setDayOfMonth(i: Int) = set(Calendar.DAY_OF_MONTH, i)

infix fun Calendar.setHour(i: Int): Calendar {
    set(Calendar.HOUR_OF_DAY, i)
    return this
}
infix fun Calendar.setMinute(i: Int): Calendar {
    set(Calendar.MINUTE, i)
    return this
}

class DateQu(private val cal: Calendar) {

    companion object {
        fun fromSql(s: String)              = DateQu(getCalFromSql(s))
        fun fromLaravel(s: String)          = DateQu(getCalFromLaravel(s))
        fun fromIndoDate(s: String)         = DateQu(getCalFromIndoDate(s))
        fun fromDate(d: Date)               = DateQu(getCalFromDate(d))
        fun fromCalendar(c: Calendar)       = DateQu(c)
        fun fromIntArray(i: Array<Int>)     = DateQu(getCalFromIntArray(i))
        fun fromInts(y: Int, m: Int, d: Int)= DateQu(getCalFromIntArray(arrayOf(y, m, d)))
        @JvmStatic fun now()                           = DateQu(Calendar.getInstance())

        private fun getCalFromIntArray(i: Array<Int>) = Calendar.getInstance().apply { // y, m, d
            setYear(i[0])
            setMonth(i[1])
            setDayOfMonth(i[2])
        }
        private fun getCalFromDate(d: Date) = Calendar.getInstance().apply {
            setYear(d.year)
            setMonth(d.month)
            setDayOfMonth(d.day)
            setHour(d.hours)
            setMinute(d.minutes)
        }
        private fun getCalFromLaravel(s: String): Calendar {
            val arr = s.split("T")
            val time = arr[1].split(".")[0]
            val sql = arr[0] + " " + time
            return getCalFromSql(sql)
        }
        private fun getCalFromSql(s: String) = Calendar.getInstance().apply {
            val arr = s.split(" ")
            val dateStringArr = arr[0].split("-")

            (dateStringArr.size != 3 || dateStringArr[1].toIntOrNull() == null) then { throw IllegalStateException("Hey, this is not sql date format") }

            dateStringArr.run {
                setYear(this[0].toInt())
                setMonth(this[1].toInt() - 1)
                setDayOfMonth(this[2].toInt())
            }

            (arr.size == 2) then {
                arr[1].split(":").run {
                    setHour(this[0].toInt())
                    setMinute(this[1].toInt())
                }
            }
        }
        private fun getCalFromIndoDate(s: String) = Calendar.getInstance().apply {
            s.split(" - ").run {
                setDayOfMonth(this[0].toInt())
                setMonth(this[1].toInt())
                setYear(this[2].toInt())
            }
        }

        /**
         * Compare two calendar
         * @return 0 if it's the same day
         */
        fun dayInterval(c1: Calendar, c2: Calendar): Int {
            val tz1 = c1.timeZone
            val jodaTz1 = DateTimeZone.forID(tz1.id)
            val dateTime1 = DateTime(c1.timeInMillis, jodaTz1)
            val localDate1 = dateTime1.toLocalDate()

            val tz2 = c2.timeZone
            val jodaTz2 = DateTimeZone.forID(tz2.id)
            val dateTime2 = DateTime(c2.timeInMillis, jodaTz2)
            val localDate2 = dateTime2.toLocalDate()

            return Days.daysBetween(localDate1, localDate2).days
        }

        /**
         * Compare two calendar
         * @return 1 if it's the same day
         */
        fun dayCount(c1: Calendar, c2: Calendar) = dayInterval(c1, c2) + 1

        val indoMonths = arrayListOf("Jan", "Feb", "Mar", "Apr", "Mei", "Jun", "Jul", "Agu", "Sep", "Okt", "Nov", "Des")
        val indoMonths2 = arrayListOf("Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember")
        val engMonths = arrayListOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")
        val indoDays = arrayListOf("Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu")
        val engDays = arrayListOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
    }

    private val pastIntervals by lazy {
        object {
            val day     = dayInterval(toCalendar(), now().toCalendar())
            val hour    = now().toHour() - toHour()
            val minute  = now().toMinute() - toMinute()
            val second  = now().toSecond() - toSecond()
        }
    }
    private val futureIntervals by lazy {
        object {
            val day     = dayInterval(toCalendar(), now().toCalendar()) * -1
            val hour    = (now().toHour() - toHour()) * -1
            val minute  = (now().toMinute() - toMinute()) * -1
        }
    }
    private fun addLeading(i: Int) = if ((i.toString() + "").length == 1) "0$i" else "" + i

    fun toCalendar()    = cal
    fun toDate()        = Date(cal.getYear(), cal.getMonth(), cal.getDayOfMonth())
    fun toSimpleShowDate() = "${cal.getDayOfMonth()}/${cal.getMonth() + 1}/${cal.getYear()}"
    fun toIndoDate()    = "${cal.getDayOfMonth()} ${indoMonths[cal.getMonth()]} ${cal.getYear()}"
    fun toIndoDateTime()= "${cal.getDayOfMonth()} ${indoMonths[cal.getMonth()]} ${cal.getYear()} ${toTime()}"
    fun toEngDateTime() = "${toEngDayName()} ${cal.getDayOfMonth()} ${engMonths[cal.getMonth()]} ${cal.getYear()}, ${toTime()}"
    fun toSqlDate()     = "${addLeading(cal.getYear())}-${addLeading(cal.getMonth() + 1)}-${addLeading(cal.getDayOfMonth())}"
    fun toPeduliDate()  = "${addLeading(cal.getDayOfMonth())}/${addLeading(cal.getMonth() + 1)}/${addLeading(cal.getYear())}"
    fun toSqlDateTime() = "${addLeading(cal.getYear())}-${addLeading(cal.getMonth() + 1)}-${addLeading(cal.getDayOfMonth())} ${addLeading(cal.getHour())}:${addLeading(cal.getMinute())}"
    fun toDateOnly()    = cal.getDayOfMonth().toString()
    fun toIndoDayName() = indoDays[cal.getDayOfWeek()]
    fun toEngDayName()  = engDays[cal.getDayOfWeek()]
    fun toTime()        = "${addLeading(cal.getHour())}:${addLeading(cal.getMinute())}"
    fun toHour()        = cal.getHour()
    fun toMinute()      = cal.getMinute()
    fun toSecond()      = cal.getSecond()

    fun dayInterval() = dayInterval(cal, Calendar.getInstance())  //compare now to calendar of this, return 0 if it's the same day
    fun dayCount() = dayInterval(cal, Calendar.getInstance()) + 1 //compare now to calendar of this, return 1 if it's the same day

    fun notPassed() = !isPassed()
    fun isPassed() = now().toCalendar().after(toCalendar())

    fun asPast() = when {
        pastIntervals.day > 28  -> toIndoDate()
        pastIntervals.day > 7   -> "${pastIntervals.day / 7} ${mingguLalu()}"
        pastIntervals.day > 1   -> "${pastIntervals.day} ${hariLalu()}"
        pastIntervals.day == 1  -> kemaren()
        pastIntervals.day == 0  -> "${addLeading(toHour())}:${addLeading(toMinute())}"
        else -> toIndoDate()
    }
    fun asFuture() = when {
        futureIntervals.day > 28  -> toIndoDate()
        futureIntervals.day > 7   -> "${futureIntervals.day / 7} ${mingguLagi()}"
        futureIntervals.day > 1   -> "${futureIntervals.day} ${hariLagi()}"
        futureIntervals.day == 1  -> besok()
        futureIntervals.day == 0  -> when {
            futureIntervals.hour > 0      -> "${futureIntervals.hour} ${jamLagi()}"
            futureIntervals.minute > 0    -> "${futureIntervals.minute} ${menitLagi()}"
            else -> sekarang()
        }
        else -> toIndoDate()
    }
    fun asElapsed() = if (isPassed()) asPast() else asFuture()

    fun howOld(suffix: String = "Tahun"): String {
        val theY = toCalendar()
        val gapY = Years.yearsBetween(LocalDate(theY.getYear(), theY.getMonth(), theY.getDayOfMonth()), LocalDate())

        return "$gapY $suffix"
    }
}

private fun mingguLalu() = "minggu yang lalu"
private fun hariLalu() = "hari yang lalu"
private fun kemaren() = "Kemarin"
private fun jamLalu() = "jam yang lalu"
private fun menitLalu() = "menit yang lalu"
private fun detikLalu() = "detik yang lalu"
private fun sekarang() = "baru saja"
private fun menitLagi() = "menit lagi"
private fun jamLagi() = "jam lagi"
private fun besok() = "Besok"
private fun hariLagi() = "hari lagi"
private fun mingguLagi() = "mingggu lagi"

fun getDateTime(c: Context, tgl: DateQu.() -> Unit) {
    val now = DateQu.now().toCalendar()
    DatePickerDialog(c, { _, y, m, d ->
        run {
            now.run {
                setYear(y)
                setMonth(m)
                setDayOfMonth(d)
            }

            TimePickerDialog(c, { _, h, m ->
                run {
                    now setHour h setMinute m
                    tgl(now.dateQu())
                }
            }, now.getHour(), now.getMinute(), true).show()
        }
    }, now.getYear(), now.getMonth(), now.getDayOfMonth()).show()
}

fun getDate(c: Context, tgl: DateQu.() -> Unit) {
    val now = DateQu.now().toCalendar()
    DatePickerDialog(c, { _, y, m, d ->
        DateQu.fromInts(y, m, d).tgl()
    }, now.getYear(), now.getMonth(), now.getDayOfMonth()).show()
}

fun getTime(c: Context, tgl: DateQu.() -> Unit) {
    val now = DateQu.now().toCalendar()
    val time = TimePickerDialog(c, { _, h, m -> tgl(now.apply { this setHour h setMinute m }.dateQu()) },
            now.getHour(), now.getMinute(), true)
    time.show()
}