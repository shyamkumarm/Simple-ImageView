package com.exercise.images.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.exercise.images.R
import com.exercise.images.adapter.ViewAdapter.ViewHolder
import com.exercise.images.databinding.ItemLayoutBinding
import com.exercise.images.model.DataObject
import com.exercise.images.model.DataObject.DataObjectItem
import com.exercise.images.utils.ImageLoader


class ViewAdapter(
    val callback: (Int, DataObjectItem) -> Unit,
) : RecyclerView.Adapter<ViewHolder>() {

    private val TAG = "ViewAdapter"
    private var mAsyncListDiffer: AsyncListDiffer<DataObjectItem>? = null

    init {
        val diffUtilCallback: DiffUtil.ItemCallback<DataObjectItem> = object : DiffUtil.ItemCallback<DataObjectItem>() {
            override fun areItemsTheSame(newUser: DataObjectItem, oldUser: DataObjectItem): Boolean {
                return newUser.url == oldUser.url
            }

            override fun areContentsTheSame(newUser: DataObjectItem, oldUser: DataObjectItem): Boolean {
                return newUser == oldUser
            }
        }
        mAsyncListDiffer = AsyncListDiffer(this, diffUtilCallback)

    }







    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

      return  ViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mAsyncListDiffer?.currentList?.get(position)

        Log.d(TAG,item.toString())
        holder.mView.root.setOnClickListener {
            callback(
                holder.absoluteAdapterPosition,
                mAsyncListDiffer?.currentList!![holder.absoluteAdapterPosition]
            )
        }

        holder.mView.text.text = item?.title
        ImageLoader.loadImage(item?.url,holder.mView.imgView,
            R.mipmap.ic_launcher)


    }


    inner class ViewHolder(val mView: ItemLayoutBinding) : RecyclerView.ViewHolder(mView.root)

    override fun getItemCount(): Int  = mAsyncListDiffer?.currentList?.size ?: 0

    fun update(it: DataObject) {
        mAsyncListDiffer?.submitList(it)

    }


}



