package com.example.slam.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.slam.databinding.ItemSlamEntryBinding
import com.example.slam.model.SlamEntry

class SlamAdapter(
    private val slamList: MutableList<SlamEntry>,
    private val clickListener: (SlamEntry) -> Unit,
    private val deleteListener: (SlamEntry) -> Unit
) : RecyclerView.Adapter<SlamAdapter.SlamViewHolder>() {

    class SlamViewHolder(private val binding: ItemSlamEntryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(slam: SlamEntry, clickListener: (SlamEntry) -> Unit,    deleteListener: (SlamEntry) -> Unit
        ) {
            binding.entryNickname.text = slam.nickname
            binding.entryQuote.text = slam.favoriteQuote

            itemView.setOnClickListener {
                clickListener(slam)
            }
            binding.deleteButton.setOnClickListener {
                deleteListener(slam)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlamViewHolder {
        val binding = ItemSlamEntryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SlamViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SlamViewHolder, position: Int) {
        val slam = slamList[position]
        holder.bind(slam, clickListener, deleteListener)


    }

    override fun getItemCount() = slamList.size

  }
