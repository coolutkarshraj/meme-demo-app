package com.io.utkarsh.ui.memeView

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.io.utkarsh.data.model.MemeApiResponseModel
import com.io.utkarsh.databinding.MemeItemLayoutBinding


class MemesAdapter(
    private val memeList: ArrayList<MemeApiResponseModel>
) : RecyclerView.Adapter<MemesAdapter.DataViewHolder>() {

    class DataViewHolder(private val binding: MemeItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(memeData: MemeApiResponseModel) {
            binding.textViewTitle.text = memeData.title
            Glide.with(binding.imageViewBanner.context)
                .load(memeData.url)
                .into(binding.imageViewBanner)
            itemView.setOnClickListener {
                val builder = CustomTabsIntent.Builder()
                val customTabsIntent = builder.build()
                customTabsIntent.launchUrl(it.context, Uri.parse(memeData.url))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            MemeItemLayoutBinding
                .inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = memeList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(memeList[position])
        holder.setIsRecyclable(false)
    }
    fun addData(list: List<MemeApiResponseModel>) {
        memeList.clear()
        memeList.addAll(list)
    }

}