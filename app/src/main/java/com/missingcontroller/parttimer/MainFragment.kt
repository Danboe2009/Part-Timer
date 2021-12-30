package com.missingcontroller.parttimer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.missingcontroller.parttimer.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_main, container, false
        )
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.viewModel = viewModel

        binding.fab.root.setOnClickListener {
            val action = MainFragmentDirections.navigationMainFragmentToPartAddFragment()
            requireView().findNavController().navigate(action)
        }

        viewModel.getPartsList()

        println("Main Fragment Token: ${CredentialManager.getToken()}")

        setupObserver()

        return binding.root
    }

    fun setupObserver() {
        viewModel.partList.observe(this.viewLifecycleOwner, {
            binding.rvPartsList.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext())
                adapter = PartListAdapter(it.sortedBy { it.date }, 1)
            }
        })
    }
}