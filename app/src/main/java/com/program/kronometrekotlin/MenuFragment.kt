package com.program.kronometrekotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_menu.*

class MenuFragment : Fragment(R.layout.fragment_menu) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonKronometreGecis.setOnClickListener {
            val action = MenuFragmentDirections.actionMenuFragmentToKronometreFragment()
            findNavController().navigate(action)
        }
    }
}