package com.sfeatherstone.sensynetechtest.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.sfeatherstone.sensynetechtest.R
import com.sfeatherstone.sensynetechtest.model.Hospital
import kotlinx.android.synthetic.main.item_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * An activity representing a single Item detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a [ItemListActivity].
 */
class MainActivity : AppCompatActivity() {

    private var twoPane: Boolean = false
    private val viewModel by viewModel<HospitalViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_list)

        if (item_detail_container != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }


        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.item_list_container, ItemListFragment())
                .commitNow()

            if (twoPane) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.item_detail_container, ItemDetailFragment())
                    .commitNow()
            } else {
                viewModel.hospital.observe(this, Observer<Hospital?> { item ->
                    item?.let {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.item_list_container, ItemDetailFragment())
                            .addToBackStack(item.OrganisationID)
                            .commit()
                    }
                })
            }

        }

        viewModel.loadData()
    }
}
