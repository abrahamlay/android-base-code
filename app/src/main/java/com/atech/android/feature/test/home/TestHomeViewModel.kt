package com.atech.android.feature.test.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.atech.android.R
import com.atech.android.base.util.SingleEvents
import com.atech.android.base.viewmodel.BaseViewModel

class TestHomeViewModel : BaseViewModel(), HomeListener {

    private val _subMenus = MutableLiveData<List<ModelHomeSubMenu>>()
    val subMenus : LiveData<List<ModelHomeSubMenu>> = _subMenus

    private val _carousels = MutableLiveData<List<ModelHomeCarousel>>()
    val carousels : LiveData<List<ModelHomeCarousel>> = _carousels

    private val _subMenus2 = MutableLiveData<List<ModelHomeSubMenu2>>()
    val subMenus2 : LiveData<List<ModelHomeSubMenu2>> = _subMenus2

    private val _carouselBanner = MutableLiveData<List<ModelHomeCarouselBanner>>()
    val carouselBanner : LiveData<List<ModelHomeCarouselBanner>> = _carouselBanner

    private val _recomendations = MutableLiveData<List<ModelHomeRecomendation>>()
    val recomendations : LiveData<List<ModelHomeRecomendation>> = _recomendations

    private val _repairs = MutableLiveData<List<ModelHomeFixesAndRepair>>()
    val repairs : LiveData<List<ModelHomeFixesAndRepair>> = _repairs

    private val _showMoreMenu = MutableLiveData<SingleEvents<Boolean>>()
    val showMoreMenu: LiveData<SingleEvents<Boolean>> = _showMoreMenu

    fun initData(){
        val temp = mutableListOf<ModelHomeSubMenu>()
        temp.add(ModelHomeSubMenu(R.drawable.img_home_submenu_1, "Market"))
        temp.add(ModelHomeSubMenu(R.drawable.img_home_submenu_2, "News"))
        temp.add(ModelHomeSubMenu(R.drawable.img_home_submenu_3, "Distric"))
        temp.add(ModelHomeSubMenu(R.drawable.img_home_submenu_4, "More"))
        _subMenus.value = temp

        val tempCarousel = mutableListOf<ModelHomeCarousel>()
        tempCarousel.add(ModelHomeCarousel(
            image1 = "https://img.freepik.com/free-photo/observation-urban-building-business-steel_1127-2397.jpg?size=626&ext=jpg",
            image2 = "https://img.freepik.com/free-photo/geometric-facades-residential-building_294094-27.jpg?size=626&ext=jpg",
            image3 = "https://jendela360.com/info/wp-content/uploads/2021/02/Green-building.jpg",
            title1 = "Building 1",
            title2 = "Building 2",
            title3 = "Building 3"

        ))
        tempCarousel.add(ModelHomeCarousel(
            image1 = "https://img.freepik.com/free-photo/observation-urban-building-business-steel_1127-2397.jpg?size=626&ext=jpg",
            image2 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRWdfrLxwAbBmauORFMDQQeBNvVSaGMEsZGqHrYtAfWrI8_je5gXhf9gZgC15-dJFsHBMc&usqp=CAU",
            image3 = "https://jendela360.com/info/wp-content/uploads/2021/02/Green-building.jpg",
            title1 = "Building 4",
            title2 = "Building 5",
            title3 = "Building 6"

        ))
        tempCarousel.add(ModelHomeCarousel(
            image1 = "https://img.freepik.com/free-photo/observation-urban-building-business-steel_1127-2397.jpg?size=626&ext=jpg",
            image2 = "https://img.freepik.com/free-photo/geometric-facades-residential-building_294094-27.jpg?size=626&ext=jpg",
            image3 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTLnVqgW3xiHwpwavP6EjKWZG1cSGvCfNLPYKviqEUwk6BRssfDaWA26bOqzxkkmQVrzRg&usqp=CAU",
            title1 = "Building 7",
            title2 = "Building 8",
            title3 = "Building 9"

        ))
        _carousels.value = tempCarousel

        val tempSubMenu2 = mutableListOf<ModelHomeSubMenu2>()
        tempSubMenu2.add(ModelHomeSubMenu2(R.drawable.img_home_submenu_5, "Sub 5"))
        tempSubMenu2.add(ModelHomeSubMenu2(R.drawable.img_home_submenu_6, "Sub 6"))
        tempSubMenu2.add(ModelHomeSubMenu2(R.drawable.img_home_submenu_7, "Sub 7"))
        tempSubMenu2.add(ModelHomeSubMenu2(R.drawable.img_home_submenu_8, "Sub 8"))
        tempSubMenu2.add(ModelHomeSubMenu2(R.drawable.img_home_submenu_9, "Sub 9"))
        tempSubMenu2.add(ModelHomeSubMenu2(R.drawable.img_home_submenu_10, "Sub 10"))
        _subMenus2.value = tempSubMenu2

        val tempCarouselBanner = mutableListOf<ModelHomeCarouselBanner>()
        tempCarouselBanner.add(ModelHomeCarouselBanner("https://pic1.zhimg.com/80/v2-e9ff1ab841a3db8254daf3657af4778c_1440w.jpg", "Sub 1"))
        tempCarouselBanner.add(ModelHomeCarouselBanner("https://pic3.zhimg.com/80/v2-1ff8a9b416f410819565583fbcaabf82_1440w.jpg", "Sub 2"))
        tempCarouselBanner.add(ModelHomeCarouselBanner("https://pic3.zhimg.com/80/v2-2410266f080627ae23758017fe17f3ba_1440w.jpg", "Sub 3"))
        tempCarouselBanner.add(ModelHomeCarouselBanner("https://pic2.zhimg.com/80/v2-0af6e25b57de6efff8081b7b7ff95f21_1440w.jpg", "Sub 4"))
        tempCarouselBanner.add(ModelHomeCarouselBanner("https://pic2.zhimg.com/80/v2-13a91b7ee59100cd01267f92558f7ea9_1440w.jpg", "Sub 5"))
        tempCarouselBanner.add(ModelHomeCarouselBanner("https://pic4.zhimg.com/80/v2-460e92c9f354e269730e9aa87317d07f_1440w.jpg", "Sub 6"))
        _carouselBanner.value = tempCarouselBanner

        val tempRecomendation = mutableListOf<ModelHomeRecomendation>()
        tempRecomendation.add(
            ModelHomeRecomendation(
            "https://static8.depositphotos.com/1192060/932/i/600/depositphotos_9320270-stock-photo-home-services.jpg",
            "Promo",
            "Lorem ipsum dolor sit amet",
            "https://thumbs.dreamstime.com/b/business-company-logo-27438249.jpg",
            "Your Company"
        ))
        tempRecomendation.add(ModelHomeRecomendation(
            "https://media.istockphoto.com/photos/couple-talking-about-home-decoration-picture-id1139355288?k=6&m=1139355288&s=612x612&w=0&h=0EoahYneNs3Pt4PECtJ_-iYQgJJ5FoBy17pyw5w-Gwk=",
            "Promo",
            "Aenean dictum condimentum",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQjPcCvhIcUpVMqNdX31gJU90TGEO2YZBxWPuvuiSMIwJaJ8LyHJqwL7wW32hX_2_H9kCU&usqp=CAU",
            "Your Company"
        ))
        tempRecomendation.add(ModelHomeRecomendation(
            "https://media.istockphoto.com/photos/an-older-couple-looking-at-blueprints-picture-id469764594",
            "Promo",
            "Cras ac hendrerit lectus",
            "https://s3.amazonaws.com/thumbnails.venngage.com/template/bdaf4e02-499c-4389-af74-51ff5a362585.png",
            "Talk Bussiness"
        ))
        tempRecomendation.add(ModelHomeRecomendation(
            "https://www.mckissock.com/wp-content/uploads/2020/04/GettyImages-1169751694-1024x683.jpg",
            "Promo",
            "Curabitur a odio id neque",
            "https://thumbs.dreamstime.com/b/business-logo-vector-unique-creative-logos-101529706.jpg",
            "Teamwork Creative"
        ))
        _recomendations.value = tempRecomendation

        val tempRepairs = mutableListOf<ModelHomeFixesAndRepair>()
        tempRepairs.add(ModelHomeFixesAndRepair("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQg5SvhUNIIMbS5hN1mCQg57B2nRZeiwFbIOsAUJqysGI9BSMYqmiULMDZEAaWjITO14WY&usqp=CAU","Waterproof"))
        tempRepairs.add(ModelHomeFixesAndRepair("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTodhClC5JTWKgJrlNc1kmnGaJgOycz-DakqngdIPRaaMaT03yoFUcAxu7zn3FLMiFCTyI&usqp=CAU","Plumbing"))
        tempRepairs.add(ModelHomeFixesAndRepair("https://cdn.dnaindia.com/sites/default/files/styles/full/public/2020/09/30/928320-unlock-5.jpg","Unlock"))
        tempRepairs.add(ModelHomeFixesAndRepair("https://www.sawyerglass.com/wp-content/uploads/2014/11/Custom-Glass-Windows-Glass-Storefronts-by-Commercial-Utah-Glass-Contractors-91.jpg","Window"))
        tempRepairs.add(ModelHomeFixesAndRepair("https://www.gardenoasis.co.uk/image/cache/catalog/product-images/Gates/Saxon_Double-600x600.jpg","Gate loading"))
        tempRepairs.add(ModelHomeFixesAndRepair("https://www.tampabay.com/resizer/dDUxw5vNksqX4oZPrqnNXAr_tiE=/1600x900/smart/cloudfront-us-east-1.images.arcpublishing.com/tbt/JR5TLTSFQGCFU33PBVDIFXTUHI.jpg","Chlorine"))
        tempRepairs.add(ModelHomeFixesAndRepair("https://cdns.klimg.com/merdeka.com/i/w/news/2020/12/07/1250832/540x270/cctv-atau-ip-cam-yang-sebaiknya-dipasang-untuk-mengamankan-rumah.jpg","Anti Theft"))
        tempRepairs.add(ModelHomeFixesAndRepair("https://www.europeanscientist.com/wp-content/uploads/thumbs/111-1-37oxj05rtlughvstf6b4zk.jpg","Electric"))
        tempRepairs.add(ModelHomeFixesAndRepair("https://cdn.pixabay.com/photo/2014/05/02/21/49/laptop-336373__480.jpg","Computer"))
        tempRepairs.add(ModelHomeFixesAndRepair("https://www.gannett-cdn.com/presto/2020/11/11/USAT/c2ea1a54-5be0-4258-8459-3a2b40fe3eec-appliance-hero.jpg?width=660&height=372&fit=crop&format=pjpg&auto=webp","Appliance"))
        tempRepairs.add(ModelHomeFixesAndRepair("https://dee2fx0lhl024.cloudfront.net/auctions/images_lots/BC7C75973014497C12DFC92A0DCFC675_nor02/1100059569_PREVIEW.JPG","Rack"))
        _repairs.value = tempRepairs
    }

    override fun onSubmenuClicked(subMenu: ModelHomeSubMenu) {
        if(subMenu.title == "More"){
            _showMoreMenu.value = SingleEvents(true)
        }
    }
}