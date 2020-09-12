package com.ex.vezdehodapp.target_collection

import android.content.Context
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ex.vezdehodapp.R
import kotlinx.android.synthetic.main.collection_post_item.view.*
import kotlinx.android.synthetic.main.custom_toolbar_create_post.view.*
import kotlinx.android.synthetic.main.custom_toolbar_create_post.view.toolbar_back_btn
import kotlinx.android.synthetic.main.custom_toolbar_for_card_collection.view.*
import kotlinx.android.synthetic.main.fragment_card_target_collection.*
import kotlinx.android.synthetic.main.fragment_create_post.*
import java.util.*

class CardTargetCollectionFragment : Fragment() {

    lateinit var pref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card_target_collection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pref = requireContext().getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE)

        val v = layoutInflater.inflate(R.layout.custom_toolbar_for_card_collection, null)
        card_toolbar.addView(v)
        val imageAsBytes: ByteArray = Base64.getDecoder().decode(pref.getString("image", "Иван Иванов")!!.toByteArray())
        v.thumb.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.size))

        v.toolbar_back_btn.setOnClickListener {
            fragmentManager!!.popBackStack()
        }
    }
}