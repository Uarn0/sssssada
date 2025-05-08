package com.example.cursovarobota.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.cursovarobota.R
import com.example.cursovarobota.databinding.ItemCarBinding
import com.example.cursovarobota.databinding.ItemMinibusBinding
import com.example.cursovarobota.databinding.ItemMotorcycleBinding
import com.example.cursovarobota.model.transport.Car
import com.example.cursovarobota.model.transport.ITransport
import com.example.cursovarobota.model.transport.Minibus
import com.example.cursovarobota.model.transport.Motorcycle

class MyAdapter : RecyclerView.Adapter<MyAdapter.BossOfTheHolder>() {

    private var transportList = mutableListOf<ITransport>()

    override fun getItemViewType(position: Int): Int {
        return transportList[position].getTransportType()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: MutableList<ITransport>){
        transportList = newList
        notifyDataSetChanged()
    }

    private fun removeItemAt(position: Int) {
        val removedItem = transportList[position]
        transportList.removeAt(position)
        notifyItemRemoved(position)
        onItemRemoved?.invoke(removedItem)
    }




    override fun onBindViewHolder(holder: BossOfTheHolder, position: Int) {
        val item = transportList[position]
        holder.bind(item)

        holder.itemView.findViewById<Button>(R.id.removeButton).setOnClickListener {
            removeItemAt(position)
        }
    }


    internal var onItemRemoved: ((ITransport) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BossOfTheHolder {
        return when (viewType){
            ITransport.CAR_TYPE -> CarViewHolder(
                ItemCarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            ITransport.MINIBUS_TYPE -> MinibusViewHolder(
                ItemMinibusBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            ITransport.MOTORCYCLE_TYPE -> MotorcycleViewHolder(
                ItemMotorcycleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            else -> throw IllegalStateException("Unexpected viewType $viewType")
        }
    }

    abstract class BossOfTheHolder(itemViewBinding: ViewBinding) : RecyclerView.ViewHolder(itemViewBinding.root){
        abstract fun bind(item: ITransport)
    }

    override fun getItemCount(): Int = transportList.size

    class CarViewHolder(private val itemViewBinding: ItemCarBinding) : BossOfTheHolder(itemViewBinding){
        override fun bind (item: ITransport){
            val car = item as Car
            itemViewBinding.transportName.text = car.nameOfCar
            itemViewBinding.transportType.text = car.getTransportTypeName()
        }
    }

    class MinibusViewHolder(private val itemViewBinding: ItemMinibusBinding) : BossOfTheHolder(itemViewBinding){
        override fun bind(item: ITransport) {
            val minibus = item as Minibus
            itemViewBinding.transportName.text = minibus.nameOfMinibus
            itemViewBinding.transportType.text = minibus.getTransportTypeName()
        }
    }

    class MotorcycleViewHolder(private val itemViewBinding: ItemMotorcycleBinding) : BossOfTheHolder(itemViewBinding){
        override fun bind(item: ITransport){
            val motorcycle = item as Motorcycle
            itemViewBinding.transportName.text = motorcycle.nameOfMoto
            itemViewBinding.transportType.text = motorcycle.getTransportTypeName()
        }
    }
}