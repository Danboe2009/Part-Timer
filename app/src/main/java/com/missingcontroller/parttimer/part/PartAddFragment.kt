package com.missingcontroller.parttimer.part

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.missingcontroller.parttimer.R
import com.missingcontroller.parttimer.databinding.FragmentAddPartBinding

class PartAddFragment : Fragment() {

    private lateinit var binding : FragmentAddPartBinding
    private lateinit var viewModel: PartAddViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_add_part, container, false
        )
        viewModel = ViewModelProvider(this).get(PartAddViewModel::class.java)
        binding.viewModel = viewModel

        setupObserver()

        return binding.root
    }

    fun setupObserver(){
        viewModel.onSubmitClick.observe(this.viewLifecycleOwner, Observer {
            viewModel.addPart()
        })
    }
}