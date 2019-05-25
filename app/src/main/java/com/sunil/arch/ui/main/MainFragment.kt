package com.sunil.arch.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sunil.arch.base.BaseFragment
import com.sunil.arch.base.BaseViewModel
import com.sunil.arch.databinding.FragmentMainBinding
import com.sunil.arch.viewModel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel


public class MainFragment : BaseFragment() {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner= viewLifecycleOwner
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configureRecyclerView()
    }

    private fun configureRecyclerView() {
        binding.fragmentHomeRv.adapter = MainAdapter(viewModel)
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }
}
