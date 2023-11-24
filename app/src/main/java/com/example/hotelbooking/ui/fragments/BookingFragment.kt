package com.example.hotelbooking.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hotelbooking.R
import com.example.hotelbooking.adapters.MainDelegateAdapter
import com.example.hotelbooking.adapters.model.TouristItem
import com.example.hotelbooking.adapters.model.TouristsAdapterDelegate
import com.example.hotelbooking.data.local.model.BookingInfoPresent
import com.example.hotelbooking.databinding.FragmentBookingBinding
import com.example.hotelbooking.extensions.observeFlow
import com.example.hotelbooking.extensions.setValidator
import com.example.hotelbooking.ui.viewmodels.BookingViewModel
import com.example.hotelbooking.utils.BookingTourSum
import com.example.hotelbooking.utils.FieldsMasks
import com.redmadrobot.inputmask.MaskedTextChangedListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookingFragment : Fragment() {

    private var _bind : FragmentBookingBinding? = null
    private val bind
        get() = _bind!!

    private val bookingViewModel : BookingViewModel by viewModels()

    private val touristsAdapter : MainDelegateAdapter by lazy {

        MainDelegateAdapter.Builder()
            .add(TouristsAdapterDelegate(){ index ->
                touristsAdapter.notifyItemChanged(index)
            })
            .build()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bind = FragmentBookingBinding.inflate(layoutInflater)
        return bind.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeBookingInfoState()

        setHeader()

        setUpTouristsRecyclerView()

        setBtnMakePaymentClickListener()

        setupPhoneInputFormatting()

        observeTouristsList()

        setBtnAddTouristClickListener()

        setClientFieldsValidate()

    }

    private fun setClientFieldsValidate(){
        bind.viewClientInfo.etEmail.setValidator {
            bookingViewModel.validateEmail(it)
        }

        bind.viewClientInfo.etTelephoneNumber.setValidator {
            bookingViewModel.validatePhoneNumber(it)
        }
    }


    private fun setupPhoneInputFormatting(){

        MaskedTextChangedListener.Companion.installOn(
            bind.viewClientInfo.etTelephoneNumber,
            FieldsMasks.Mask.PHONE.mask
        )
    }

    private fun setBtnAddTouristClickListener(){

        bind.viewAddTourist.ivAddTourist.setOnClickListener {
            bookingViewModel.addNewTourist()
        }

    }


    private fun observeBookingInfoState(){
        observeFlow(bookingViewModel.bookingInfoState){ bookingInfo ->

            onBookingInfoStateUpdate(bookingInfo)

        }
    }

    private fun setBtnMakePaymentClickListener(){
        bind.btnMakePayment.setOnClickListener {
            findNavController().navigate(R.id.action_bookingFragment_to_orderSuccessFragment)
        }
    }


    private fun setHeader(){

        bind.viewHeader.tvHeaderTitle.text = getString(R.string.booking_main_title)

        bind.viewHeader.flContainer.setOnClickListener {
            findNavController().popBackStack()
        }
    }


    private fun setUpTouristsRecyclerView(){
        bind.rvTourist.adapter = touristsAdapter
        bind.rvTourist.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun observeTouristsList(){

        observeFlow(bookingViewModel.touristInfo){ list ->

            touristsAdapter.submitList(list.map { TouristItem(it) })
        }
    }


    private fun onBookingInfoStateUpdate(bookingInfo: BookingInfoPresent){


        bind.apply {

            viewHotelInfo.tvHotelName.text = bookingInfo.hotelName
            viewHotelInfo.tvHotelAddress.text = bookingInfo.hotelAddress
            val rating = bookingInfo.rating.toString() + " " + bookingInfo.ratingName
            viewHotelInfo.viewRating.tvRatingValue.text = rating

            viewBookingData.tvDepartureFrom.text = bookingInfo.departure
            viewBookingData.tvArrivalAt.text = bookingInfo.arrivalCountry
            viewBookingData.tvHotel.text = bookingInfo.hotelName
            val date = bookingInfo.tourDateStart + "–" + bookingInfo.tourDateStop
            viewBookingData.tvDates.text = date
            viewBookingData.tvNights.text = getString(R.string.nights_count, bookingInfo.numberOfNights.toString())
            viewBookingData.tvSuit.text = bookingInfo.room
            viewBookingData.tvNourishment.text = bookingInfo.nutrition

            viewPayment.tvPaymentTour.text = getString(R.string.text_price_short, bookingInfo.tourPrice.toString())
            viewPayment.tvPaymentFuelTax.text = getString(R.string.text_price_short, bookingInfo.fuelCharge.toString())
            viewPayment.tvPaymentServiceTax.text = getString(R.string.text_price_short, bookingInfo.serviceCharge.toString())
            val totalSum = BookingTourSum.getTotalBookingSum(bookingInfo).toString()

            viewPayment.tvPaymentSum.text = getString(R.string.text_price_short, totalSum)

            btnMakePayment.text = getString(R.string.btn_make_payment, totalSum)

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _bind = null
    }


}