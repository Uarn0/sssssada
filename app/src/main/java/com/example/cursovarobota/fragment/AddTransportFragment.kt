package com.example.cursovarobota.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.cursovarobota.MainActivity
import com.example.cursovarobota.R
import com.example.cursovarobota.databinding.FragmentAddTransportBinding
import com.example.cursovarobota.model.SharedViewModelTransport
import com.example.cursovarobota.model.transport.Car
import com.example.cursovarobota.model.transport.ITransport
import com.example.cursovarobota.model.transport.Minibus
import com.example.cursovarobota.model.transport.Motorcycle

class AddTransportFragment : Fragment() {

    private var _binding: FragmentAddTransportBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: SharedViewModelTransport by activityViewModels()
    private lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivity) {
            mainActivity = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddTransportBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.saveButton.setOnClickListener {
            val name = binding.nameOfTransport.text.toString()
            val selectedRadioButtonId = binding.transportTypeGroup.checkedRadioButtonId

            if (name.isBlank() || selectedRadioButtonId == -1) {
                Toast.makeText(requireContext(), "Будь ласка, введіть назву і виберіть тип транспорту", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val transport: ITransport = when (selectedRadioButtonId) {
                R.id.car -> Car(name)
                R.id.minibus -> Minibus(name)
                R.id.motorcycle -> Motorcycle(name)
                else -> throw IllegalArgumentException("Невідомий тип транспорту")
            }

            sharedViewModel.addTransport(transport)

            mainActivity.onFragmentClosed()
            requireActivity().supportFragmentManager.beginTransaction()
                .remove(this)
                .commit()
        }
    }

}