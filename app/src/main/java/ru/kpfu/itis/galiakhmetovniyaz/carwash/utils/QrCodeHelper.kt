package ru.kpfu.itis.galiakhmetovniyaz.carwash.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.util.Log
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import ru.kpfu.itis.galiakhmetovniyaz.carwash.R
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

object QrCodeHelper {

    fun makeQRBitmap(
        context: Context,
        qrCode: String?,
        size: Int,
        border: Int = 1,
        colorBackground: Int = context.getColor(R.color.QrCodeBackgroundColor)
        // saveFile: Boolean = false
    ): Bitmap? {
        if (qrCode.isNullOrEmpty()) return null
        val writer = QRCodeWriter()
        val bitMatrix = writer.encode(qrCode, BarcodeFormat.QR_CODE, size, size, createHintMap(border))
        val width = bitMatrix.width
        val height = bitMatrix.height
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        bitmap.setHasAlpha(true)
        for (x in 0 until width) {
            for (y in 0 until height) {
                bitmap.setPixel(
                    x,
                    y,
                    if (bitMatrix.get(x, y)) context.getColor(R.color.QrCodeColor) else colorBackground
                )
            }
        }
//        if (saveFile)
//            saveQrBitmap(context, bitmap)
        return bitmap
    }

    private fun createHintMap(border: Int): MutableMap<EncodeHintType, Any> {
        val hintMap: MutableMap<EncodeHintType, Any> = EnumMap(EncodeHintType::class.java)
        hintMap[EncodeHintType.CHARACTER_SET] = "UTF-8"
        hintMap[EncodeHintType.MARGIN] = border
        hintMap[EncodeHintType.ERROR_CORRECTION] = ErrorCorrectionLevel.L
        return hintMap
    }

    private fun saveQrBitmap(context: Context, bitmap: Bitmap) {
//        try {
//            val cachePath = File(context.cacheDir, Const.QrCode.QR_PATH)
//            cachePath.mkdirs()
//            val stream = FileOutputStream("$cachePath/${Const.QrCode.QR_CODE_SMALL}")
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
//            stream.close()
//        } catch (e: IOException) {
//            Log.e("QR Error", "msg")
//        }
    }

}