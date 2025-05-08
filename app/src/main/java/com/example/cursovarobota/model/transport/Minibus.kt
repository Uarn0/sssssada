package com.example.cursovarobota.model.transport

data class Minibus(
    val nameOfMinibus: String
) : ITransport {
    override fun getTransportType() = ITransport.MINIBUS_TYPE
    override fun getTransportTypeName() = "Minibus"
    override fun getName() = nameOfMinibus
}
