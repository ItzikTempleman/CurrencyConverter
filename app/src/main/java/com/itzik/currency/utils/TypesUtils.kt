package com.itzik.currency.utils

fun stringToPairGetIndex(input: String, returnIndex: Int, separator: String = ", "): String {
    if (input.isEmpty()) return input
    val parts = input
        .removePrefix("(")
        .removeSuffix(")")
        .split(separator)
        .map { it.trim() }

    if (parts.size != 2) {
        throw IllegalArgumentException("Input string does not represent a valid Pair")
    }

    return parts[returnIndex]
}