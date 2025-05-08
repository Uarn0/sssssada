package com.example.cursovarobota.model.event

data class RepairEvent(
    val repair : String
) : IEvent {
    override fun getEventType(): Int {
        return IEvent.REPAIR_EVENT_TYPE
    }
}
