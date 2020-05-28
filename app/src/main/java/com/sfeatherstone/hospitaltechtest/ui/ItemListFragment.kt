package com.sfeatherstone.hospitaltechtest.ui

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.sfeatherstone.hospitaltechtest.R

import com.sfeatherstone.hospitaltechtest.model.ListHospital
import kotlinx.android.synthetic.main.item_list_content.view.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [ItemDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class ItemListFragment : Fragment() {

    private val viewModel by sharedViewModel<HospitalViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.item_list_fragment, container, false)

        setupRecyclerView(rootView.findViewById(R.id.item_list))
        return rootView
    }


    private fun setupRecyclerView(recyclerView: RecyclerView) {
        viewModel.hospitalList.observe(viewLifecycleOwner, Observer<List<ListHospital>> { itemList ->
            recyclerView.adapter =
                SimpleItemRecyclerViewAdapter(itemList, viewModel)
        })
    }

    class SimpleItemRecyclerViewAdapter(private val values: List<ListHospital>,
                                        private val viewModel: HospitalViewModel) :
        RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

        private val onClickListener: View.OnClickListener

        init {
            onClickListener = View.OnClickListener { v ->
                val item = v.tag as ListHospital
                viewModel.setHospital(item.id)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_content, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = values[position]
            holder.contentView.text = item.name

            with(holder.itemView) {
                tag = item
                setOnClickListener(onClickListener)
            }
        }

        override fun getItemCount() = values.size

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val contentView: TextView = view.content
        }
    }
}
