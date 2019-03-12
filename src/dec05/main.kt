package dec05

import java.io.File
import java.lang.Math.abs

val REACTING_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toList()

fun reactable(a: Char, b: Char) : Boolean {
    return (32 == abs(a.toByte() - b.toByte()))
}

fun react(chars: MutableList<Char>) : Boolean {
    for (i in 0..chars.count()) {
        if (i.inc() < chars.count() && reactable(chars[i], chars[i.inc()])) {
            chars.removeAt(i)
            chars.removeAt(i)
            return true
        }
    }
    return false
}

tailrec fun generateReactedList(chars: MutableList<Char>) : List<Char> {
    return when (react(chars)) {
        false -> chars.toList()
        true -> generateReactedList(chars)
    }
}

fun isEqualCaseInsensitive(a: Char, b: Char) : Boolean {
    return (a == b) || (a + 32 == b) || (a - 32 == b)
}

fun reactionLengthForLetter(chars: List<Char>, letter: Char) : Int {
    val filteredChars = chars.filter { !isEqualCaseInsensitive(letter, it)}
    return generateReactedList(filteredChars.toMutableList()).count()
}

fun minimumReactionLength(chars: List<Char>) : Int? {
    return REACTING_LETTERS
        .map { reactionLengthForLetter(chars, it) }
        .min()

}

fun main() {
    val input = File("dec05.txt").readText().toList()
    val minimumReactionLength = minimumReactionLength(input)
    println(minimumReactionLength)
}