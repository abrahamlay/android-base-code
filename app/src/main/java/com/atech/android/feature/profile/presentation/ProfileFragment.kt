package com.atech.android.feature.profile.presentation

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.atech.android.databinding.FragmentProfileBinding
import com.atech.android.base.BaseFragment
import com.atech.android.base.viewmodel.BaseViewModel
import java.lang.RuntimeException

class ProfileFragment : BaseFragment<FragmentProfileBinding, BaseViewModel>() {
    override val viewModel: BaseViewModel by viewModels()
    override val binding: FragmentProfileBinding by lazy {
        FragmentProfileBinding.inflate(layoutInflater)
    }

    private val args: ProfileFragmentArgs by navArgs()

    override fun onInitViews() {
        super.onInitViews()
        findNavController().restoreState(args.toBundle())
        binding.tvTitlePage.text = args.title
        binding.root.setOnClickListener {
//            throw RuntimeException("Test Crash")
        }
        findNavController().saveState()?.putAll(args.toBundle())
    }


}