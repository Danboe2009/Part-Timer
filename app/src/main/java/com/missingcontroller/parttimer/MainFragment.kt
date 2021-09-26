package com.missingcontroller.parttimer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.missingcontroller.parttimer.databinding.FragmentMainBinding

class MainFragment : Fragment(){

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentMainBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_login, container, false)
        binding.viewModel = viewModel
        return binding.root
    }
}