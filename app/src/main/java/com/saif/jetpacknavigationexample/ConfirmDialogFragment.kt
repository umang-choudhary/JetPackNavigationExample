package com.saif.jetpacknavigationexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.saif.jetpacknavigationexample.databinding.FragmentConfirmDialogBinding

class ConfirmDialogFragment : BottomSheetDialogFragment() {

    private val args: ConfirmDialogFragmentArgs by navArgs()

    private var _binding: FragmentConfirmDialogBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentConfirmDialogBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val receiverName = args.receiverName
        val amount = args.amount

        binding.tvMessage.text = "Do you want to send ₨$amount to $receiverName?"

        binding.btnYes.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "$amount has been sent to $receiverName",
                Toast.LENGTH_SHORT
            ).show()
            dismiss()
        }

        binding.btnNo.setOnClickListener {
            dismiss()
        }

    }

}