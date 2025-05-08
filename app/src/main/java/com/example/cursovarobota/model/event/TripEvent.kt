package com.example.cursovarobota.model.event

data class TripEvent(
    val trip : String
) : IEvent {
    override fun getEventType(): Int {
        return IEvent.TRIP_EVENT_TYPE
    }
}
