package com.example.cursovarobota.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cursovarobota.model.transport.ITransport

class SharedViewModelTransport : ViewModel() {
    private val _transportList = MutableLiveData<MutableList<ITransport>>()
    val transportList: LiveData<MutableList<ITransport>> = _transportList

    fun addTransport(transport: ITransport) {
        val current = _transportList.value?.toMutableList() ?: mutableListOf()
        current.add(transport)
        _transportList.value = current
        _transportList.value = _transportList.value
    }
    fun removeTransport(transport: ITransport) {
        _transportList.value = _transportList.value?.toMutableList()?.apply {
            remove(transport)
        }
    }
}