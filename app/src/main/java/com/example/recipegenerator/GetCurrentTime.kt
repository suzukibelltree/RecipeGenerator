package com.example.recipegenerator

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * 現在の時刻をフォーマットして返す関数
 */
fun getCurrentTime(): String {
    val dateFormat = SimpleDateFormat("yyyy/MM/dd/HH:mm", Locale.getDefault())
    return dateFormat.format(Date())
}