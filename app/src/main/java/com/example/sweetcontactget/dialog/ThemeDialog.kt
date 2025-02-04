package com.example.sweetcontactget.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDelegate
import com.example.sweetcontactget.databinding.DialogMyPageThemeBinding

class ThemeDialog(context: Context) : Dialog(context) {
    private lateinit var binding : DialogMyPageThemeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogMyPageThemeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setCancelable(false)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialogResize(context,this@ThemeDialog, 0.7f, 0.15f)

        binding.btnThemeChangeCancel.setOnClickListener {
            changeTheme(AppCompatDelegate.MODE_NIGHT_NO)
            dismiss()
        }
        binding.btnThemeChangeSave.setOnClickListener {
            changeTheme(AppCompatDelegate.MODE_NIGHT_YES)
            dismiss()
        }
    }

    private fun changeTheme(mode: Int) {
        AppCompatDelegate.setDefaultNightMode(mode)
    }

    fun dialogResize(context: Context, dialog: Dialog, width: Float, height: Float){
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager

        if (Build.VERSION.SDK_INT < 30){
            val display = windowManager.defaultDisplay
            val size = Point()

            display.getSize(size)

            val window = dialog.window

            val x = (size.x * width).toInt()
            val y = (size.y * height).toInt()

            window?.setLayout(x, y)

        }else{
            val rect = windowManager.currentWindowMetrics.bounds

            val window = dialog.window
            val x = (rect.width() * width).toInt()
            val y = (rect.height() * height).toInt()

            window?.setLayout(x, y)
        }
    }
}