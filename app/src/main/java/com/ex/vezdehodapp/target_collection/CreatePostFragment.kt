package com.ex.vezdehodapp.target_collection

import android.content.Context
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ex.vezdehodapp.DonateListFragment
import com.ex.vezdehodapp.R
import com.ex.vezdehodapp.feed.FeedFragment
import com.ex.vezdehodapp.utils.showFragment
import kotlinx.android.synthetic.main.custom_toolbar_create_post.view.*
import kotlinx.android.synthetic.main.fragment_create_post.*
import java.util.*


class CreatePostFragment : Fragment() {

    lateinit var pref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pref = requireContext().getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE)

        val v = layoutInflater.inflate(R.layout.custom_toolbar_create_post, null)
        create_post_toolbar.addView(v)
        val firstName = pref.getString("author", "Оформление Оформление")!!.split(" ")
        v.toolbar_title.text = firstName[0]
        v.toolbar_back_btn.setOnClickListener {
            fragmentManager!!.popBackStack()
        }
        v.send_btn.setOnClickListener {
            pref.edit().putString("comment_user", edit_text_for_description_for_post.text.toString())
                .apply()
            showFragment(FeedFragment(), requireFragmentManager())
        }

        val imageAsBytes: ByteArray = Base64.getDecoder().decode(pref.getString("image", "Иван Иванов")!!.toByteArray())
        image_for_collection.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.size))

        image_for_collection.clipToOutline = true

        title_for_collection.text = pref.getString("title", "Добряши помогают котикам")
        subtitle_for_collection.text = pref.getString("author", "Иван Иванов")

    }
}