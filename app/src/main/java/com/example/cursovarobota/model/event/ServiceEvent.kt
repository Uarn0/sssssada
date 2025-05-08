package com.example.cursovarobota.model.event

data class ServiceEvent(
    val service: String
) : IEvent {
    override fun getEventType(): Int {
        return IEvent.SERVICE_EVENT_TYPE
    }
}
