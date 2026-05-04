package com.umc.workbook

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.umc.workbook.ui.main.MainScreen
import com.umc.workbook.ui.theme.WorkbookTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WorkbookTheme {
                MainScreen()
            }
        }
    }
}
