package com.sunil.arch.ui.moviecover

import android.os.Bundle
import android.transition.ChangeBounds
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.navArgs
import com.sunil.arch.R
import com.sunil.arch.base.BaseFragment
import com.sunil.arch.base.BaseViewModel
import com.sunil.arch.databinding.FragmentCoverBinding
import com.sunil.arch.viewModel.MovieCoverViewModel
import org.koin.android.viewmodel.ext.android.viewModel


open class MovieCoverFragment : BaseFragment() {

    private val viewModel: MovieCoverViewModel by viewModel()
    private val args: MovieCoverFragmentArgs by navArgs()
    private lateinit var binding: FragmentCoverBinding

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //configureTransition()
        binding = FragmentCoverBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = viewModel
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadWhenFragmentViewCreated(args.imageUrl)
    }

    private fun configureTransition() {
        val transition = ChangeBounds().apply { duration = 300 }
        sharedElementEnterTransition = transition
        sharedElementReturnTransition = transition
    }

}