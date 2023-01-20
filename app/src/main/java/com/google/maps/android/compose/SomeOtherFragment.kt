package com.google.maps.android.compose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

class SomeOtherFragment : Fragment() {

    private val navigationController: NavController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ComposeView {
        return ComposeView(requireContext()).apply {
            setContent {
                BackHandler {
                    navigationController.popBackStack()
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize(1f)
                        .background(Color.Gray)
                )
            }
        }
    }
}