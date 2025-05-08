package com.example.cursovarobota.model.transport

data class Car(
    val nameOfCar: String
) : ITransport {
    override fun getTransportType() = ITransport.CAR_TYPE
    override fun getTransportTypeName() = "Car"
    override fun getName() = nameOfCar
}
