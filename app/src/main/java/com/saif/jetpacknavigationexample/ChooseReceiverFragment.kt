package com.saif.jetpacknavigationexample

import android.Manifest
import android.app.PendingIntent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.saif.jetpacknavigationexample.databinding.FragmentChooseReceiverBinding

class ChooseReceiverFragment : Fragment() {

    private var _binding: FragmentChooseReceiverBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChooseReceiverBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNext.setOnClickListener {
            val receiverName = binding.etReceiverName.text.toString()

            val pendingIntent = findNavController()
                .createDeepLink()
                .setGraph(R.navigation.main_nav_graph)
                .setDestination(R.id.sendCashFragment)
                .setArguments(SendCashFragmentArgs(receiverName, "").toBundle())
                .createPendingIntent()

            showNotification(pendingIntent, receiverName)

            val action = ChooseReceiverFragmentDirections
                .actionChooseReceiverFragmentToSendCashFragment(receiverName, "")
            findNavController().navigate(action)

        }

        binding.btnCancel.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    private fun showNotification(pendingIntent: PendingIntent, receiverName: String) {
        val notification = NotificationCompat.Builder(requireContext(), CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notifications)
            .setContentTitle("Complete Transaction")
            .setContentText("Send money to $receiverName")
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        if (context?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.POST_NOTIFICATIONS
                )
            } == PackageManager.PERMISSION_GRANTED
        ) {
            NotificationManagerCompat.from(requireContext()).notify(1002, notification)
        }

    }
}