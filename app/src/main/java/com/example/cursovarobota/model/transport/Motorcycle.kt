package com.example.cursovarobota.model.transport

data class Motorcycle(
    val nameOfMoto: String
) : ITransport {
    override fun getTransportType() = ITransport.MOTORCYCLE_TYPE
    override fun getTransportTypeName() = "Motorcycle"
    override fun getName() = nameOfMoto
}
