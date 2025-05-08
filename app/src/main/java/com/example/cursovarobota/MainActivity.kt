package com.example.cursovarobota

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cursovarobota.adapter.MyAdapter
import com.example.cursovarobota.databinding.ActivityMainBinding
import com.example.cursovarobota.fragment.AddTransportFragment
import com.example.cursovarobota.model.SharedViewModelTransport

class MainActivity : AppCompatActivity() {

    private val sharedViewModel: SharedViewModelTransport by viewModels()
    private var isFragmentVisible = false
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val customAdapter = MyAdapter()
        binding.recycleViewTransports.layoutManager = LinearLayoutManager(this)
        binding.recycleViewTransports.adapter = customAdapter

        customAdapter.onItemRemoved = { transport ->
            sharedViewModel.removeTransport(transport)
        }

        sharedViewModel.transportList.observe(this) { transportList ->
            customAdapter.updateList(transportList)
        }

        binding.fabAdd.setOnClickListener {
            if (!isFragmentVisible) {
                showFragment()
                binding.fabAdd.hide()
            }
        }
    }
    private fun showFragment() {
        binding.fragmentContainer.visibility = View.VISIBLE
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, AddTransportFragment())
            .addToBackStack(null)
            .commit()

        isFragmentVisible = true
    }


    fun onFragmentClosed() {
        binding.fabAdd.show()
        binding.fragmentContainer.visibility = View.GONE
        isFragmentVisible = false
    }

}
