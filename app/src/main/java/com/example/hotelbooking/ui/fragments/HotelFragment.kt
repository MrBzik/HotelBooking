package com.example.hotelbooking.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.RequestManager
import com.example.hotelbooking.R
import com.example.hotelbooking.adapters.MainDelegateAdapter
import com.example.hotelbooking.adapters.model.SimpleImageAdapterDelegate
import com.example.hotelbooking.adapters.model.SimpleImageItem
import com.example.hotelbooking.adapters.model.SimpleTextAdapterDelegate
import com.example.hotelbooking.adapters.model.SimpleTextItem
import com.example.hotelbooking.data.local.model.HotelInfoPresent
import com.example.hotelbooking.databinding.FragmentHotelBinding
import com.example.hotelbooking.extensions.observeFlow
import com.example.hotelbooking.ui.viewmodels.HotelViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HotelFragment : Fragment() {

    private lateinit var bind: FragmentHotelBinding

    @Inject
    lateinit var glide : RequestManager

    private val viewPagerAdapter by lazy {
        MainDelegateAdapter.Builder()
            .add(SimpleImageAdapterDelegate(glide))
            .build()
    }

    private val peculiaritiesAdapter by lazy {
        MainDelegateAdapter.Builder()
            .add(SimpleTextAdapterDelegate())
            .build()
    }


    private val hotelViewModel : HotelViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentHotelBinding.inflate(inflater)

        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeHotelInfoState()

        setupViewPager()

        setPeculiaritiesRV()

        setOnBtnNavigateToSuitsClickListener()

    }


    private fun observeHotelInfoState(){

        observeFlow(hotelViewModel.hotelInfoState){ hotelInfo ->

            onHotelInfoUpdated(hotelInfo)

        }
    }


    private fun onHotelInfoUpdated(hotelInfo: HotelInfoPresent){

        val rating = hotelInfo.rating.toString() + " " + hotelInfo.ratingName

        bind.apply {

            viewRating.tvRatingValue.text = rating

            tvHotelName.text = hotelInfo.name

            tvAddress.text = hotelInfo.address

            tvPrice.text = getString(R.string.text_price, hotelInfo.minimalPrice.toString())

            tvPriceCondition.text = hotelInfo.priceForIt

            tvDescription.text = hotelInfo.description

        }

        viewPagerAdapter.submitList(
            hotelInfo.imageUrls.map { SimpleImageItem(it) }
        )

        peculiaritiesAdapter.submitList(
            hotelInfo.peculiarities.map { SimpleTextItem(it) }
        )

    }


    private fun setupViewPager(){

        bind.viewPager.viewPager.adapter = viewPagerAdapter

        TabLayoutMediator(bind.viewPager.tabLayout, bind.viewPager.viewPager, true){ _, _ ->
        }.attach()

    }

    private fun setPeculiaritiesRV(){
        bind.rvPeculiarities.adapter = peculiaritiesAdapter
        bind.rvPeculiarities.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    }

    private fun setOnBtnNavigateToSuitsClickListener(){

        bind.btnNavigateToSuits.setOnClickListener {

            val action = HotelFragmentDirections.actionHotelFragmentToRoomsFragment(bind.tvHotelName.text.toString())

            findNavController().navigate(action)


        }

    }



}