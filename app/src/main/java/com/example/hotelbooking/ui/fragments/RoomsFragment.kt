package com.example.hotelbooking.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.RequestManager
import com.example.hotelbooking.R
import com.example.hotelbooking.adapters.MainDelegateAdapter
import com.example.hotelbooking.adapters.model.RoomItem
import com.example.hotelbooking.adapters.model.RoomsAdapterDelegate
import com.example.hotelbooking.databinding.FragmentSuitsBinding
import com.example.hotelbooking.extensions.observeFlow
import com.example.hotelbooking.ui.viewmodels.RoomsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RoomsFragment : Fragment() {

    private var _bind : FragmentSuitsBinding? = null
    private val bind
        get() = _bind!!

    val args : RoomsFragmentArgs by navArgs()

    @Inject
    lateinit var glide : RequestManager

    private val roomsViewModel : RoomsViewModel by viewModels()

    private val roomsRv by lazy {
        MainDelegateAdapter.Builder()
            .add(
                RoomsAdapterDelegate(glide){
                    findNavController().navigate(R.id.action_roomsFragment_to_bookingFragment)
                }
            )
            .build()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bind = FragmentSuitsBinding.inflate(layoutInflater)
        return bind.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHeader()

        observeRoomsList()

        setRoomsRecyclerView()

    }

    private fun setHeader(){

        bind.viewHeader.tvHeaderTitle.text = args.hotelName

        bind.viewHeader.flContainer.setOnClickListener {
            findNavController().popBackStack()
        }

    }


    private fun setRoomsRecyclerView(){
        bind.rvRooms.apply {
            adapter = roomsRv
            layoutManager = LinearLayoutManager(requireContext())
        }
    }


    private fun observeRoomsList(){
        observeFlow(roomsViewModel.listOfRooms){ rooms ->
            roomsRv.submitList(
                rooms.map {
                    RoomItem(it)
                }
            )
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _bind = null
    }

}