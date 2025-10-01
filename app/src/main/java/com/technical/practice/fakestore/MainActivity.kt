package com.technical.practice.fakestore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.technical.practice.fakestore.ui.FakeStoreApp
import com.technical.practice.fakestore.ui.theme.FakeStoreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FakeStoreTheme {
                FakeStoreApp()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainPreview() {
    FakeStoreApp()
}