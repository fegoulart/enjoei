package com.leapi.enjoei.di.util

fun Float.toBrazilianCurrency(): String {

    val price: String = "%.2f".format(this)

    return "R$ ${price.replace(".", ",")}"
}