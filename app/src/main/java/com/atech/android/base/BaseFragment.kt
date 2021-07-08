package com.atech.android.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.atech.android.base.viewmodel.BaseViewModel


/**
 * Created by Abraham Lay on 2020-06-09.
 */
@SuppressLint("Registered")
abstract class BaseFragment<VB : ViewBinding,VM : BaseViewModel> : Fragment() {
    abstract val viewModel: VM
    abstract val binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true ) {
                override fun handleOnBackPressed() {
                    onBackPress()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    protected open fun onBackPress(){
        if (!findNavController().popBackStack())
            requireActivity().finish()
        else
            findNavController().navigateUp()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(savedInstanceState == null){
            onInitViews()
            onInitObservers()
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        onInitViews()
        onInitObservers()
    }

    protected open fun onInitViews() = Unit

    protected open fun onInitObservers() = Unit

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPress()
                return false
            }
        }
        return super.onOptionsItemSelected(item)
    }


}