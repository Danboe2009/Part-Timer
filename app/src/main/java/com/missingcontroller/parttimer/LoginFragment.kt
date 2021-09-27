package com.missingcontroller.parttimer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.missingcontroller.parttimer.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding  = DataBindingUtil.inflate(inflater,
            R.layout.fragment_login, container, false)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.viewModel = viewModel

        setupObservers()

        return binding.root
    }

    private fun setupObservers() {
        viewModel.isValid.observe(this.viewLifecycleOwner, {
            binding.btLoginSubmit.isEnabled = it
        })

        viewModel.onSubmitClick.observe(this.viewLifecycleOwner,{
            viewModel.login()
        })
    }
}