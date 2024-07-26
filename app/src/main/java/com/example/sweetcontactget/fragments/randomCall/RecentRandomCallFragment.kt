package com.example.sweetcontactget.fragments.randomCall

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sweetcontactget.R
import com.example.sweetcontactget.data.Contact
import com.example.sweetcontactget.data.ContactApplication
import com.example.sweetcontactget.data.DataObject
import com.example.sweetcontactget.databinding.FragmentRecentRancdomCallBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class RecentRandomCallFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentRecentRancdomCallBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecentRancdomCallBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        repeat(10) {

            DataObject.randomCallList.add(
                Contact.SweetiesID(
                    1,
                    Contact.SweetieInfo(
                        ContextCompat.getDrawable(requireContext(), R.drawable.img_sweetie_1),
                        name = "감우",
                        number = "010-2345-3444",
                        relationship = "친구",
                        memo = "멍청하다.",
                        heart = 0,
                        isMarked = false
                    )
                )

            )
        }


        val recentListViewAdapter = RecentRandomCallListViewAdapter()
        binding.rvRecentList.adapter = recentListViewAdapter
        binding.rvRecentList.layoutManager = LinearLayoutManager(this.context)
        recentListViewAdapter.submitList(DataObject.randomCallList)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RecentRandomCallFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}