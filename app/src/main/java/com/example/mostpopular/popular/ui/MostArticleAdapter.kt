package com.example.mostpopular.popular.ui

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mostpopular.databinding.RowPopularArticleBinding
import com.example.mostpopular.popular.data.model.Result
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.absoluteValue


class MostArticleAdapter(val requireContext: Context) :
    RecyclerView.Adapter<MostArticleAdapter.ViewHolder>() {
    private lateinit var binding: RowPopularArticleBinding
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        binding =
            RowPopularArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(differ.currentList[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount() = differ.currentList.size

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        fun setData(item: Result) {
            binding.apply {

                binding.titleNews.text = item.title
                val publishedDate = item.publishedDate?.split("-")
                val day = publishedDate?.get(2)

                binding.author.text =
                    day?.toInt()?.let { getDifferance(getCurrentDate(), it, item) }

                if (item.media?.size!! > 0) {
                    if (item.media?.get(0)?.mediaMetadata?.size!! > 0) {
                        Glide.with(requireContext)
                            .load(item.media?.get(0)?.mediaMetadata?.get(0)?.url)
                            .into(binding.imageView)
                    }
                }
            }
        }

        private fun getCurrentDate(): Int {
            val c: Date = Calendar.getInstance().getTime()
            println("Current time => $c")
            val df = SimpleDateFormat("dd", Locale.getDefault())
            val formattedDate: Int = df.format(c).toInt()
            return formattedDate
        }

        fun getDifferance(currentDate: Int, day: Int, item: Result): String {
            item.numberOfDay = (currentDate - day).absoluteValue
            Log.d("iiiiiiiiiii",item.numberOfDay.toString())
            return item.numberOfDay.toString() + "day"
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

}