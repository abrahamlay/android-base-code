package com.atech.android.feature.test.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.atech.android.R
import com.atech.android.databinding.BottomSheetHomeSubmenuBinding
import com.atech.android.divider16
import com.atech.android.homeSubmenu
import com.atech.android.homeTitleSectionNoArrow
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetSubMenu(private val viewModel: TestHomeViewModel) : BottomSheetDialogFragment() {

    private lateinit var listener : OnDialogClickListener
    private lateinit var binding : BottomSheetHomeSubmenuBinding

    override fun getTheme(): Int {
        return R.style.Theme_Design_BottomSheetDialog
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = BottomSheetHomeSubmenuBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isCancelable = false
        binding.btnCloseBottomSheet.setOnClickListener {
            dialog?.cancel()
        }

        binding.recyclerView.withModels {

            divider16{
                id("divider1")
                spanSizeOverride { _, _, _ -> TestHomeFragment.GRID_1 }
            }

            homeTitleSectionNoArrow {
                id("title")
                title("Main")
                spanSizeOverride { _, _, _ -> 12 }
            }
            viewModel.subMenus.value?.let {
                it.forEach { model->
                    homeSubmenu {
                        id(model.image)
                        model(model)
                        spanSizeOverride { _, _, _ -> TestHomeFragment.GRID_4 }
                    }
                }
            }

            divider16{
                id("divider1")
                spanSizeOverride { _, _, _ -> TestHomeFragment.GRID_1 }
            }

            homeTitleSectionNoArrow {
                id("title")
                title("Main")
                spanSizeOverride { _, _, _ -> 12 }
            }
            viewModel.subMenus.value?.let {
                it.forEach { model->
                    homeSubmenu {
                        id(model.image)
                        model(model)
                        spanSizeOverride { _, _, _ -> TestHomeFragment.GRID_4 }
                    }
                }
            }

            divider16{
                id("divider1")
                spanSizeOverride { _, _, _ -> TestHomeFragment.GRID_1 }
            }
        }

    }

    fun showDialog(fragmentManager: FragmentManager, tag: String?, listener : OnDialogClickListener) {
        show(fragmentManager, tag)
        this.listener = listener
    }

    fun dissmissDialog(){
        dialog?.cancel()
    }

    interface OnDialogClickListener{
        fun onChange()
    }

}