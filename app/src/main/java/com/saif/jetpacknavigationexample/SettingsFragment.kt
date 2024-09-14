package com.saif.jetpacknavigationexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.saif.jetpacknavigationexample.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private var _binding: FragmentSettingsBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etDefaultAmount.setText(SampleData.defaultAmount.value.toString())

        binding.btnSaveDefaultAmount.setOnClickListener {
            val defaultAmount = binding.etDefaultAmount.text.toString().toLong()
            SampleData.defaultAmount.value = defaultAmount
        }

        binding.btnAboutApp.setOnClickListener {
            val action = MainNavGraphDirections.actionGlobalAboutAppFragment()
            findNavController().navigate(action)
        }

    }

}