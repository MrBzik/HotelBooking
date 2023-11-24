package com.example.hotelbooking.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.hotelbooking.R
import com.example.hotelbooking.databinding.FragmentOrderSuccessBinding
import com.example.hotelbooking.utils.Logger

class OrderSuccessFragment : Fragment() {

    private var _bind : FragmentOrderSuccessBinding? = null
    private val bind
        get() = _bind!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bind = FragmentOrderSuccessBinding.inflate(layoutInflater)
        return bind.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHeader()

        setBtnSuperClickListener()

        setOrderHint()

    }

    private fun setOrderHint(){

        val orderNumber = (154..100500).random()

        bind.tvHintMain.text = getString(R.string.order_main_hint, orderNumber.toString())

    }

    private fun setBtnSuperClickListener(){

        bind.btnSuper.setOnClickListener {
            findNavController().popBackStack(R.id.hotelFragment, false)
//            findNavController().navigate(R.id.action_orderSuccessFragment_to_hotelFragment)

        }

    }


    private fun setHeader(){

        bind.viewHeader.tvHeaderTitle.text = getString(R.string.order_main_title)

        bind.viewHeader.flContainer.setOnClickListener {
            findNavController().popBackStack(R.id.bookingFragment, false)
        }
    }
}