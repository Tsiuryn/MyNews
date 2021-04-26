package com.ts.alex.mynews.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.ts.alex.mynews.databinding.ItemNewsBinding
import com.ts.alex.mynews.domain.entity.news.Article

class ListNewsAdapter(
    private var itemList: List<Article>,
    private val clickListener: (news: Article) -> Unit
) :
    RecyclerView.Adapter<ListNewsAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemPersonBinding = ItemNewsBinding.inflate(layoutInflater, parent, false)
        return Holder(itemPersonBinding)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val news = itemList[position]
        holder.bind(news)
        holder.itemView.setOnClickListener {
            clickListener(news)
        }
    }

    class Holder(private val itemViewBinding: ItemNewsBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root) {

        fun bind(article: Article) {
            itemViewBinding.vTitle.text = article.title
            Glide
                .with(itemView)
                .load(article.urlToImage)
                .transform(RoundedCorners(32))
                .into(itemViewBinding.vImage)

        }
    }
}