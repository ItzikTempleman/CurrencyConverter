package com.itzik.currency.utils

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

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


fun isFieldsEmpty(initial: String, target: String, amount: String? = null): Boolean =
    initial.isNotBlank() && target.isNotBlank() && amount?.isNotBlank() ?: true
