package com.mcshr.weather.forecast.ui.screens.select_city

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mcshr.weather.forecast.R
import com.mcshr.weather.forecast.databinding.FragmentSelectCityBinding
import com.mcshr.weather.forecast.ui.utils.setOnClickListenerWithDelay
import com.mcshr.weather.forecast.ui.utils.showMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectCityFragment : Fragment() {

    private var _binding: FragmentSelectCityBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("Fragment SelectCit binding is null")

    private val viewModel: SelectCityViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectCityBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.button.setOnClickListenerWithDelay {
            val cityName = binding.editTextSelectCity.text.toString().trim()
            if (cityName.isNotEmpty()) {
                viewModel.selectCity(cityName)
            } else {
                context?.showMessage(getString(R.string.error_empty_et_city))
            }
        }
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        viewModel.validationMessage.observe(viewLifecycleOwner){
            context?.showMessage(it)
        }
        viewModel.readyToClose.observe(viewLifecycleOwner){
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        super.onViewCreated(view, savedInstanceState)
    }
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }



    companion object {
        fun newInstance() = SelectCityFragment()
    }
}