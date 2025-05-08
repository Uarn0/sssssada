package com.example.cursovarobota.model.transport

interface ITransport {
    fun getTransportType():Int
    fun getTransportTypeName(): String
    fun getName(): String
    companion object{
        const val CAR_TYPE = 0
        const val MINIBUS_TYPE = 1
        const val MOTORCYCLE_TYPE = 2
    }
}