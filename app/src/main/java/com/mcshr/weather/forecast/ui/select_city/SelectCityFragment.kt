package com.mcshr.weather.forecast.ui.select_city

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mcshr.weather.forecast.databinding.FragmentSelectCityBinding

class SelectCityFragment : Fragment() {

    private var _binding: FragmentSelectCityBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("Fragment SelectCit binding is null")

    private val viewModel:SelectCityViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectCityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.button.setOnClickListener {
            val cityName = binding.editTextSelectCity.text.toString().trim()
            if (cityName.isNotEmpty()) {
                viewModel.selectCity(cityName)
            } else {
                showMessage("Enter city name")
            }
        }

        viewModel.validationMessage.observe(viewLifecycleOwner){
            showMessage(it)
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun showMessage(message:String){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance() = SelectCityFragment()
    }
}