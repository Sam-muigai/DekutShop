package com.samkt.dekutshop.feature_authentication


fun main() {
    try {
        println(convertFromHundredToThousand("maghana awi na mirongo mfungade na isanu"))
    }catch (e:Exception){
        println(e.message)
    }
}


fun convertFromHundredToThousand(number: String):Int?{
    val numArray = number.split(" ")
    if (number.contains("ighana")){
        //Case for 100
        when (numArray.size) {
            1 -> {
                return 100
            }
            //110
            3 -> {
                val tens = numArray[2]
                val output = "1${convertStringToNumbers(number = tens)}"
                return output.toInt()
            }
            //120,130,140,150,160,170,180,190
            4 -> {
                val lastNo = "${numArray[2]} ${numArray[3]} "
                val output = "1${convertStringToNumbers(number = lastNo)}"
                return output.toInt()
            }
            else -> {
                val lastNo = "${numArray[2]} ${numArray[3]} ${numArray[4]} ${numArray[5]}"
                val output = "1${convertStringToNumbers(number = lastNo)}"
                return output.toInt()
            }
        }
    }else if (number.contains("maghana")){
        val hundreths = numArray[2]
        val tenths = numArray[4]
        val output = "${numArray[3]} ${numArray[4]} ${numArray[5]} ${numArray[6]}"
        val intOutput = "${convertToNumber(hundreths)}${convertStringToNumbers(output)}"
        return intOutput.toInt()
    }else{
        return convertStringToNumbers(number)
    }
    return null
}


//Convert from text to number
//0-99
fun convertStringToNumbers(number: String):Int{
    val input = number.trim()
    return if (input == "ikumi"){
        10
    }else if (number.contains("ikumi")){
        val lastNo = number.split(" ")[2]
        val numbers =  "1${convertToNumber(lastNo)}"
        numbers.toInt()
    } else if (number.contains("mirongo")){
        val numArray = number.split(" ")
        if (numArray.size == 4){
            //Check the value of tenth
            val tenthNo = numArray[1]
            val lastNo = numArray[3]
            val output = "${convertToNumber(tenthNo)}${convertToNumber(lastNo)}"
            output.toInt()
        }else{
            val tenthNo = numArray[1]
            val output = "${convertToNumber(tenthNo)}0"
            return output.toInt()
        }
    }else {
        convertToNumber(number)
    }
}


//Converting text to number
// 0-9
fun convertToNumber(number: String): Int{
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
    if (numbers.contains(number)){
        return numbers.indexOf(number)
    }else{
        throw NumberNotInLanguageException("Language not understood")
    }
}

class NumberNotInLanguageException(message: String) : Exception(message)

//Converting text to number
// 0-9
fun convertHundrethsToNumber(number: String): Int{
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
    if (numbers.contains(number)){
        return numbers.indexOf(number)
    }else{
        throw NumberNotInLanguageException("Language not understood")
    }
}




