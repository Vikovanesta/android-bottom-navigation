package com.example.week9_bottomnavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.week9_bottomnavigation.databinding.FragmentTicketBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TicketFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TicketFragment : Fragment() {
    private var _binding: FragmentTicketBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding  = FragmentTicketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            var action = TicketFragmentDirections.actionTicketFragmentToOrderFragment(txtOrderedTicketType.text.toString())
            btnBuyTicket.setOnClickListener {
                findNavController().navigate(action)
            }

            findNavController().currentBackStackEntry?.savedStateHandle?.
                getLiveData<String>("ticketType")?.
                observe(viewLifecycleOwner) {
                    txtOrderedTicketType.text = it
                    txtOrderedTicket.visibility = View.VISIBLE
                    txtOrderedTicketType.visibility = View.VISIBLE
                    action = TicketFragmentDirections.actionTicketFragmentToOrderFragment(it)
                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TicketFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TicketFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}