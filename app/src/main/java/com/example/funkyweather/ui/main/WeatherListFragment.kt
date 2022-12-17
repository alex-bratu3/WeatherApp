package com.example.funkyweather.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.funkyweather.databinding.FragmentWeatherListBinding
import com.example.funkyweather.utils.hide
import com.example.funkyweather.utils.show
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A fragment representing a list of Items.
 */
class WeatherListFragment : Fragment() {

    private val viewModel: WeatherListViewModel by viewModel()

    private var _binding: FragmentWeatherListBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.model().observe(viewLifecycleOwner) {
            when (it) {
                is WeatherListViewModel.UiModel.ShowLoading -> binding.progressBar.show()
                is WeatherListViewModel.UiModel.HideLoading -> binding.progressBar.hide()
                is WeatherListViewModel.UiModel.Results ->
                    with(binding.list) {
                        layoutManager = LinearLayoutManager(context)
                        adapter = WeatherEntryRecyclerViewAdapter(it.responseList)
                    }

                is WeatherListViewModel.UiModel.Error -> Toast.makeText(
                    requireContext(),
                    it.errorMessage,
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        viewModel.onViewCreated()
    }

    companion object {
        @JvmStatic
        fun newInstance() = WeatherListFragment()
    }
}
