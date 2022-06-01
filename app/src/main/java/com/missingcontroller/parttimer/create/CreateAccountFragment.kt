package com.missingcontroller.parttimer.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.missingcontroller.parttimer.R
import com.missingcontroller.parttimer.databinding.FragmentCreateAccountBinding

class CreateAccountFragment : Fragment() {
    private lateinit var binding: FragmentCreateAccountBinding
    private lateinit var viewModel: CreateAccountViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_create_account, container, false
        )
        viewModel = ViewModelProvider(this).get(CreateAccountViewModel::class.java)
        binding.viewModel = viewModel

        setupObservers()

        return binding.root
    }

    private fun setupObservers() {
        viewModel.isValid.observe(this.viewLifecycleOwner, {
            binding.btCreateAccountCreate.setBackgroundColor(resources.getColor(R.color.logo_blue))
            binding.btCreateAccountCreate.isEnabled = it
        })

        viewModel.onCreateClicked.observe(this.viewLifecycleOwner, {
            viewModel.registration()
        })
    }
}