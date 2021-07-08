package com.atech.android.feature.test.favorite

import androidx.fragment.app.viewModels
import com.atech.android.base.BaseFragment
import com.atech.android.databinding.FragmentTestFavoriteBinding
import com.atech.android.itemCity
import com.atech.android.itemDistrict
import com.atech.android.itemStreet
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped

@FragmentScoped
@AndroidEntryPoint
class TestFavoriteFragment : BaseFragment<FragmentTestFavoriteBinding, TestFavoriteViewModel>() {
    private val TAG = "TestMainFragment"
    override val viewModel: TestFavoriteViewModel by viewModels()
    override val binding: FragmentTestFavoriteBinding by lazy {
        FragmentTestFavoriteBinding.inflate(layoutInflater)
    }

    override fun onInitViews() {
        super.onInitViews()

        viewModel.init()

        binding.recyclerCity.withModels {
            viewModel.city.value?.let {
                it.forEachIndexed { index, model->
                    itemCity {
                        id(model.hashCode())
                        model(model)
                        index(index)
                        selected(index == viewModel.citySelected.value)
                        listener(viewModel)
                    }
                }
            }
        }

        binding.recyclerDistrict.withModels {
            viewModel.district.value?.let {
                it.forEachIndexed { index, model->
                    itemDistrict {
                        id(model.hashCode())
                        model(model)
                        index(index)
                        selected(index == viewModel.districtSelected.value)
                        listener(viewModel)
                    }
                }
            }
        }

        binding.recyclerStreet.withModels {
            viewModel.street.value?.let {
                it.forEachIndexed { index, model->
                    itemStreet {
                        id(model.hashCode())
                        model(model)
                        index(index)
                        listener(viewModel)
                    }
                }
            }
        }
    }

    override fun onInitObservers() {
        super.onInitObservers()

        viewModel.needUpdateView.observe(this, {
            it.getContentIfNotHandled()?.let {
                binding.recyclerCity.requestModelBuild()
                binding.recyclerDistrict.requestModelBuild()
                binding.recyclerStreet.requestModelBuild()
            }
        })
    }
}