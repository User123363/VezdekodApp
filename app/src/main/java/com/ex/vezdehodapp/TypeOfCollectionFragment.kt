package com.ex.vezdehodapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.ex.vezdehodapp.target_collection.TargetCollectionFragment
import com.ex.vezdehodapp.utils.showFragment
import kotlinx.android.synthetic.main.custom_toolbar.view.*
import kotlinx.android.synthetic.main.fragment_donate_list.*
import kotlinx.android.synthetic.main.fragment_type_of_collection.*

class TypeOfCollectionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_type_of_collection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val v = layoutInflater.inflate(R.layout.custom_toolbar, null)
        type_of_collections_toolbar.addView(v)
        v.toolbar_title.text = "Тип сбора"
        v.toolbar_back_btn.setOnClickListener {
            fragmentManager!!.popBackStack()
        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                fragmentManager!!.popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)

        constraint_for_target_collection.setOnClickListener {
            showFragment(TargetCollectionFragment(), requireFragmentManager())
        }

        constraint_for_regular_collection.setOnClickListener {
            showFragment(TargetCollectionFragment(), requireFragmentManager())
        }
    }
}