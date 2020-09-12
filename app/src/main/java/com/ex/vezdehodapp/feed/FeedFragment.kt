package com.ex.vezdehodapp.feed

import android.content.Context
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.ex.vezdehodapp.R
import com.ex.vezdehodapp.TypeOfCollectionFragment
import com.ex.vezdehodapp.target_collection.CardTargetCollectionFragment
import com.ex.vezdehodapp.utils.showFragment
import kotlinx.android.synthetic.main.collection_post_item.view.*
import kotlinx.android.synthetic.main.custom_toolbar_create_post.view.*
import kotlinx.android.synthetic.main.fragment_create_post.*
import kotlinx.android.synthetic.main.fragment_feed.*
import java.util.*

class FeedFragment : Fragment() {

    lateinit var pref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pref = requireContext().getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE)

        val v = layoutInflater.inflate(R.layout.custom_toolbar, null)
        feed_list_toolbar.addView(v)

        v.toolbar_title.text = "Лента"
        v.toolbar_back_btn.setOnClickListener {
            fragmentManager!!.popBackStack()
        }

        create_collection_btn.setOnClickListener {
            showFragment(TypeOfCollectionFragment(), requireFragmentManager())
        }

        val viewCollection =
            LayoutInflater.from(context).inflate(R.layout.collection_post_item, null)

        val paramsForItem = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )

        viewCollection.layoutParams = paramsForItem

        container_for_collection.addView(viewCollection)

        val imageAsBytes: ByteArray = Base64.getDecoder().decode(pref.getString("image", "Иван Иванов")!!.toByteArray())
        viewCollection.image_for_collection.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.size))

        viewCollection.image_for_collection.clipToOutline = true

        viewCollection.title_for_collection.text = pref.getString("title", "Добряши помогают котикам")
        viewCollection.subtitle_for_collection.text = pref.getString("author", "Иван Иванов") + " · Закончится через 5 дней"
        viewCollection.author_name.text = pref.getString("author", "Иван Иванов")
        viewCollection.edit_text_for_description_for_post.text = pref.getString("comment_user", "Help pls")

        viewCollection.constraintLayout.setOnClickListener {
            showFragment(CardTargetCollectionFragment(), requireFragmentManager())
        }

    }
}