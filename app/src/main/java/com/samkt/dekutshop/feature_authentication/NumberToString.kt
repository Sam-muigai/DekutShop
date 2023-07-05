package com.samkt.dekutshop.feature_authentication


fun main() {
    try {
        val number = convertNumbersToString(275)
        println(number)
    } catch (e: Exception) {
        println(e.message)
    }
}

fun convertNumbersToString(num: Int): String {
    return when (num) {
        in 1..99 -> {
            convertZeroToHundred(num)
        }

        in 100..999 -> {
            convertHundredToThousand(num)
        }

        else -> {
            "Number is out of bound"
        }

    }
}


// 100 - 999
fun convertHundredToThousand(num: Int): String {
    val hundreds: Int = num / 100
    val modulus = num.mod(100)
    return if (hundreds == 1) {
        if (modulus == 0) {
            "ighana"
        } else {
            "ighana na ${convertZeroToHundred(modulus)}"
        }
    } else {
        if (modulus == 0) {
            "maghana ${convertDoubleNumber(hundreds)}"
        } else {
            "maghana ${convertDoubleNumber(hundreds)} na ${convertZeroToHundred(modulus)}"
        }
    }
}

//Converting number to text
// 0 to 99
fun convertZeroToHundred(num: Int): String {
    val tens: Int = num / 10
    val ones = num.mod(10)
    return if (tens == 0) {
        convertSingleNumber(num)
    } else if (tens == 1) {
        if (ones == 0) {
            "ikumi"
        } else "ikumi na ${convertSingleNumber(ones)}"
    } else {
        if (ones == 0) {
            "mirongo ${convertSingleNumber(tens)}"
        } else {
            "mirongo ${convertSingleNumber(tens)} na ${convertSingleNumber(ones)}"
        }
    }
}


//Converting number to text
//0 to 9
fun convertSingleNumber(num: Int): String {
    val numbers = listOf(
        "",
        "imweri",
        "iwi",
        "idadu",
        "inya",
        "isanu",
        "irandadu",
        "mfungade",
        "wunyanya",
        "ikenda"
    )
    return numbers[num]
}


//Converting For hundreds
fun convertDoubleNumber(num: Int): String {
    val numbers = listOf(
        "",
        "",
        "awi",
        "idadu",
        "ana",
        "masanu",
        "arandadu",
        "mfungade",
        "w'unyanya",
        "ikenda"
    )
    return numbers[num]
}




