package ru.kpfu.itis.galiakhmetovniyaz.carwash.presentation.dialogFragment

import android.app.AlertDialog
import android.app.Dialog
import android.app.backup.BackupAgentHelper
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import ru.kpfu.itis.galiakhmetovniyaz.carwash.databinding.FragmentQrcodeDialogBinding
import ru.kpfu.itis.galiakhmetovniyaz.carwash.utils.QrCodeHelper

class QrCodeDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentQrcodeDialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentQrcodeDialogBinding.inflate(LayoutInflater.from(context))

        val userId = arguments?.getString(ARG_USER_ID)
        val carWashId = arguments?.getString(ARG_CAR_WASH_ID)
        val timeBooking = arguments?.getString(ARG_TIME_BOOKING)
        val carModel = arguments?.getString(ARG_CAR_MODEL)

        val qrCode = QrCodeHelper.makeQRBitmap(
            requireContext(),
            userId + carWashId + timeBooking + carModel,
            200
        )

        val dialogBuilder = AlertDialog.Builder(requireActivity())
        dialogBuilder.setView(binding.root)



        binding.run {
            imgQrcode.setImageBitmap(qrCode)
        }

        return dialogBuilder.create()

    }

    companion object {
        private const val ARG_USER_ID = "arg_user_id"
        private const val ARG_CAR_WASH_ID = "arg_car_wash_id"
        private const val ARG_TIME_BOOKING = "arg_time_booking"
        private const val ARG_CAR_MODEL = "arg_car_model"

        fun newInstance(
            userId: String,
            carWashId: String,
            timeBooking: String,
            carModel: String,
        ): QrCodeDialogFragment {
            val fragment = QrCodeDialogFragment()
            val args = Bundle()
            args.putString(ARG_USER_ID, userId)
            args.putString(ARG_CAR_WASH_ID, carWashId)
            args.putString(ARG_TIME_BOOKING, timeBooking)
            args.putString(ARG_CAR_MODEL, carModel)
            fragment.arguments = args
            return fragment
        }
    }

}