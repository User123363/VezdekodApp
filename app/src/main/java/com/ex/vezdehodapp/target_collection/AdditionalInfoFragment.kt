package com.ex.vezdehodapp.target_collection

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.ex.vezdehodapp.R
import com.ex.vezdehodapp.utils.showFragment
import kotlinx.android.synthetic.main.custom_toolbar.view.*
import kotlinx.android.synthetic.main.fragment_additional_info.*
import kotlinx.android.synthetic.main.fragment_target_collection.*
import java.util.*


class AdditionalInfoFragment : Fragment() {

    private var picker: DatePickerDialog? = null
    lateinit var pref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_additional_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pref = requireContext().getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE)

        val v = layoutInflater.inflate(R.layout.custom_toolbar, null)
        additional_info_toolbar.addView(v)
        v.toolbar_title.text = "Дополнительно"
        v.toolbar_back_btn.setOnClickListener {
            fragmentManager!!.popBackStack()
        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                fragmentManager!!.popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)

        buildRadioBtn()

        buildCalendarView()

        saveInfo()

    }

    private fun saveInfo() {

        pref.edit().putString("author", edit_text_for_author.text.toString())
            .apply()

        var typeOfDate = ""
        typeOfDate = if(radioBtn1.isChecked)
            "1"
        else
            "2"

        pref.edit().putString("typeOfDate", typeOfDate)
            .apply()

        pref.edit().putString("date", edit_text_for_date.text.toString())
            .apply()


        save_additional_info_collection_btn.setOnClickListener {
            showFragment(CreatePostFragment(), requireFragmentManager())
        }
    }

    private fun buildRadioBtn() {
        radioBtn1.isChecked = true
        radioBtn1.buttonTintList =
            ContextCompat.getColorStateList(context!!, R.color.radio_btn_color)

        radioBtn1.setOnClickListener {
            when {
                radioBtn1.isChecked -> {
                    radioBtn1.buttonTintList =
                        ContextCompat.getColorStateList(context!!, R.color.radio_btn_color)
                    radioBtn2.buttonTintList = ContextCompat.getColorStateList(
                        context!!,
                        R.color.radio_btn_not_activate_color
                    )
                }
                radioBtn2.isChecked -> {
                    radioBtn1.buttonTintList = ContextCompat.getColorStateList(
                        context!!,
                        R.color.radio_btn_not_activate_color
                    )
                    radioBtn2.buttonTintList =
                        ContextCompat.getColorStateList(context!!, R.color.radio_btn_color)
                }
            }
        }

        radioBtn2.setOnClickListener {
            when {
                radioBtn1.isChecked -> {
                    radioBtn1.buttonTintList =
                        ContextCompat.getColorStateList(context!!, R.color.radio_btn_color)
                    radioBtn2.buttonTintList = ContextCompat.getColorStateList(
                        context!!,
                        R.color.radio_btn_not_activate_color
                    )
                }
                radioBtn2.isChecked -> {
                    radioBtn1.buttonTintList = ContextCompat.getColorStateList(
                        context!!,
                        R.color.radio_btn_not_activate_color
                    )
                    radioBtn2.buttonTintList =
                        ContextCompat.getColorStateList(context!!, R.color.radio_btn_color)
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun buildCalendarView() {

        edit_text_for_date.inputType = InputType.TYPE_NULL;

        edit_text_for_date.setOnClickListener {

            var cldr = Calendar.getInstance();
            var day = cldr.get(Calendar.DAY_OF_MONTH);
            var month = cldr.get(Calendar.MONTH);
            var year = cldr.get(Calendar.YEAR);

            picker = DatePickerDialog(
                requireContext(),
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                    edit_text_for_date.setText(
                        dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + year,
                        TextView.BufferType.EDITABLE
                    )

                }, year, month, day
            )
            picker!!.show()
        }

    }

}