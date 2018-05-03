package android1.myapplication1.utils

import java.math.BigInteger
import java.security.MessageDigest


fun getHash(str: String): String {
    val messageDigest = MessageDigest.getInstance("MD5").digest(str.toByteArray())
    val md5 = BigInteger(1, messageDigest).toString(16)
    return "0" * (32 - md5.length) + md5
  }

private operator fun String.times(i: Int) = (1..i).fold("") { acc, _ -> acc + this }

