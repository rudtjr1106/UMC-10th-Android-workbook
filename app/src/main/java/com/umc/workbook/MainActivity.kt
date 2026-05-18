package com.umc.workbook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.umc.workbook.ui.navigation.AppNavigation
import com.umc.workbook.ui.theme.WorkbookTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WorkbookTheme {
                AppNavigation()
            }
        }
    }
}
