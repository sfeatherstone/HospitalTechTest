package com.sfeatherstone.hospitaltechtest.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.sfeatherstone.hospitaltechtest.R
import com.sfeatherstone.hospitaltechtest.model.Hospital
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.item_detail.view.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [ItemListActivity]
 * in two-pane mode (on tablets) or a [ItemDetailActivity]
 * on handsets.
 */
class ItemDetailFragment : Fragment() {

    private val viewModel by sharedViewModel<HospitalViewModel>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.item_detail, container, false)

        viewModel.hospital.observe(viewLifecycleOwner, Observer<Hospital?> { item ->
            activity?.let {
                rootView.item_detail.text = "${item?.OrganisationName}\n${item?.toString()}"
                it.toolbar_layout?.title = item?.OrganisationName
            }
        })

        return rootView
    }

}