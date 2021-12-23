package com.missingcontroller.parttimer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.missingcontroller.parttimer.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login, container, false
        )
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.viewModel = viewModel

        setupObservers()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        println("Token: ${CredentialManager.getToken()}")
        if (CredentialManager.getToken().isNotEmpty()) {
            val action = LoginFragmentDirections.navigationLoginToMainFragment()
            requireView().findNavController().navigate(action)
        }
    }

    private fun setupObservers() {
        viewModel.isValid.observe(this.viewLifecycleOwner, {
            binding.btLoginSubmit.setBackgroundColor(resources.getColor(R.color.logo_blue))
            binding.btLoginSubmit.isEnabled = it
        })

        viewModel.onSubmitClick.observe(this.viewLifecycleOwner, {
            viewModel.login()
        })

        viewModel.onCreateClick.observe(this.viewLifecycleOwner, {
            val action = LoginFragmentDirections.navigationLoginToCreateAccountFragment()
            requireView().findNavController().navigate(action)
        })

        viewModel.userDetails.observe(this.viewLifecycleOwner, {
            println("Data Ready: $it")
            val action = LoginFragmentDirections.navigationLoginToMainFragment()
            requireView().findNavController().navigate(action)
        })
    }
}