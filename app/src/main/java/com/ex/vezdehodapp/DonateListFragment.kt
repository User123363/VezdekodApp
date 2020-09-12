package com.ex.vezdehodapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.ex.vezdehodapp.utils.showFragment
import kotlinx.android.synthetic.main.custom_toolbar.view.*
import kotlinx.android.synthetic.main.fragment_donate_list.*

class DonateListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_donate_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val v = layoutInflater.inflate(R.layout.custom_toolbar, null)
        donate_list_toolbar.addView(v)
        v.toolbar_title.text = "Пожертвования"
        v.toolbar_back_btn.visibility = View.GONE

        create_collection_btn.setOnClickListener {
            showFragment(TypeOfCollectionFragment(), requireFragmentManager())
        }
    }
}