package com.sunil.arch.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.navArgs
import com.sunil.arch.R
import com.sunil.arch.base.BaseFragment
import com.sunil.arch.base.BaseViewModel
import com.sunil.arch.databinding.FragmentDetailBinding
import com.sunil.arch.viewModel.DetailViewModel
import org.koin.android.viewmodel.ext.android.viewModel

 class DetailFragment : BaseFragment() {

    private val viewModel: DetailViewModel by viewModel()
    private val args: DetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentDetailBinding

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.loadDataWhenActivityStarts(args.movie)
    }

    override fun getExtras(): FragmentNavigator.Extras {
        return FragmentNavigatorExtras(binding.imageView to getString(R.string.transition_avatar_dest))
    }

}