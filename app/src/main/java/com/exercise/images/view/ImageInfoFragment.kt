package com.exercise.images.view


import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.exercise.images.R
import com.exercise.images.databinding.FragmentImageInfoDialogBinding
import com.exercise.images.model.DataObject
import com.exercise.images.utils.ApiConstants
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


/**
 *
 * A fragment that shows a list of items as a modal bottom sheet.
 *
 * You can show this modal bottom sheet from your activity like this:
 * <pre>
 *    ImageInfoFragment.newInstance(30).show(supportFragmentManager, "dialog")
 * </pre>
 */
class ImageInfoFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentImageInfoDialogBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentImageInfoDialogBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(requireContext(), R.style.MyTransparentBottomSheetDialogTheme)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val data = arguments?.getParcelable<DataObject.DataObjectItem>(ApiConstants.INTENT_DATA)
        binding.titleText.text = data?.title
        binding.descriptionText.text = data?.explanation
        binding.createdText.text = data?.date
        binding.copyRightText.text = data?.copyright

    }


    companion object {
        fun newInstance(itemCount: DataObject.DataObjectItem?): ImageInfoFragment =
            ImageInfoFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ApiConstants.INTENT_DATA, itemCount)
                }
            }

    }

    override fun onStart() {
        super.onStart()
        (view!!.parent as View).setBackgroundColor(Color.TRANSPARENT)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}