package com.atech.android.feature.test.home

import androidx.fragment.app.viewModels
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.carousel
import com.atech.android.*
import com.atech.android.base.BaseFragment
import com.atech.android.databinding.FragmentTestHomeBinding

class TestHomeFragment : BaseFragment<FragmentTestHomeBinding, TestHomeViewModel>() {
    private val TAG = "TestMainFragment"

    private lateinit var bottomSheetSubMenu: BottomSheetSubMenu

    override val viewModel: TestHomeViewModel by viewModels()
    override val binding: FragmentTestHomeBinding by lazy {
        FragmentTestHomeBinding.inflate(layoutInflater)
    }

    override fun onInitObservers() {
        super.onInitObservers()
        viewModel.showMoreMenu.observe(this,  {
            it.getContentIfNotHandled()?.let {
                bottomSheetSubMenu.showDialog(parentFragmentManager, "bottomNav", object :BottomSheetSubMenu.OnDialogClickListener{
                    override fun onChange() {
                        bottomSheetSubMenu.dissmissDialog()
                    }
                })
            }
        })
    }

    override fun onInitViews() {
        super.onInitViews()
        bottomSheetSubMenu = BottomSheetSubMenu(viewModel)

        viewModel.initData()

        Carousel.setDefaultGlobalSnapHelperFactory(null)
        binding.recyclerView.addItemDecoration(HomeSpacingDecoration())
        binding.recyclerView.withModels {
            homeHeader{
                id(1)
                spanSizeOverride { _, _, _ -> GRID_1 }
            }
            divider16{
                id("divider1")
                spanSizeOverride { _, _, _ -> GRID_1 }
            }
            viewModel.subMenus.value?.let {
                it.forEach { model->
                    homeSubmenu {
                        id(model.image)
                        model(model)
                        listener(viewModel)
                        spanSizeOverride { _, _, _ -> GRID_4 }
                    }
                }
            }
            divider16{
                id("divider2")
                spanSizeOverride { _, _, _ -> GRID_1 }
            }
            homeTitleSection {
                id("title section")
                title("Title Section")
                spanSizeOverride { _, _, _ -> GRID_1 }
            }

            viewModel.carousels.value?.let {
                val carousel = it.mapIndexed { index, modelHomeCarousel ->
                    HomeCarouselBindingModel_()
                        .id("carousel $index")
                        .model(modelHomeCarousel)
                }

                carouselWithIndicator {
                    id("carouselContent")
                    models(ModelHomeCarouselContainer(carousel))
                    spanSizeOverride { _, _, _ -> GRID_1 }
                }
            }

            divider16{
                id("divider3")
                spanSizeOverride { _, _, _ -> GRID_1 }
            }

            viewModel.subMenus2.value?.let {
                it.forEach { model->
                    homeSubmenu2 {
                        id(model.image)
                        model(model)
                        spanSizeOverride { _, _, _ -> GRID_3 }
                    }
                }
            }

            divider16{
                id("divider4")
                spanSizeOverride { _, _, _ -> GRID_1 }
            }

            viewModel.carouselBanner.value?.let {
                val carousel = it.mapIndexed { index, modelHomeCarousel ->
                    HomeCarouselBannerBindingModel_()
                        .id("carousel $index")
                        .model(modelHomeCarousel)
                }

                homeTitleSectionNoArrow {
                    id("title section")
                    title("Title Section")
                    spanSizeOverride { _, _, _ -> GRID_1 }
                }

                carousel {
                    id("promo")
                    padding(Carousel.Padding.dp(16, 4, 16, 16, 8))
                    models(carousel)
                    spanSizeOverride { _, _, _ -> GRID_1 }
                }

                lineDivider{
                    id("lineDivider1")
                    spanSizeOverride { _, _, _ -> GRID_1 }
                }

                divider16{
                    id("divider4")
                    spanSizeOverride { _, _, _ -> GRID_1 }
                }
            }

            viewModel.carouselBanner.value?.let {
                val carousel = it.mapIndexed { index, modelHomeCarousel ->
                    HomeCarouselBannerBindingModel_()
                        .id("carousel $index")
                        .model(modelHomeCarousel)
                }

                homeTitleSectionNoArrow {
                    id("title section")
                    title("Title Section")
                    spanSizeOverride { _, _, _ -> GRID_1 }
                }

                carousel {
                    id("promo")
                    padding(Carousel.Padding.dp(16, 4, 16, 16, 8))
                    models(carousel)
                    spanSizeOverride { _, _, _ -> GRID_1 }
                }

                lineDivider{
                    id("lineDivider1")
                    spanSizeOverride { _, _, _ -> GRID_1 }
                }
            }

            divider16{
                id("divider1")
                spanSizeOverride { _, _, _ -> GRID_1 }
            }

            homeTitleSectionNoArrow {
                id("title section")
                title("Title Section")
                spanSizeOverride { _, _, _ -> GRID_1 }
            }

            viewModel.recomendations.value?.let {
                it.forEach {modelData->
                    homeRecomendation {
                        id("recomendation")
                        model(modelData)
                        spanSizeOverride { _, _, _ -> GRID_1 }
                    }
                }
            }

            viewModel.repairs.value?.let {

                divider16 {
                    id("divider")
                }

                homeTitleSectionNoArrow {
                    id("title section")
                    title("Fixes And Repair")
                    spanSizeOverride { _, _, _ -> GRID_1 }
                }

                it.forEach { model->
                    homeFixAndRepair {
                        id("repairs")
                        model(model)
                        spanSizeOverride { _, _, _ -> GRID_3 }
                    }
                }
                lineDivider { id("line") }
            }

            for (i in 0 until 3){
                divider16{
                    id("divider1")
                    spanSizeOverride { _, _, _ -> GRID_1 }
                }
            }

        }
    }

    companion object{
        val GRID_1 = 12
        val GRID_2 = 6
        val GRID_3 = 4
        val GRID_4 = 3
    }
}