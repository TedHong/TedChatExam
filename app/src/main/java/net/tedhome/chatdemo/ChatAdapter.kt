package net.tedhome.chatdemo;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatAdapter(private val mDataAsset: Array<String>): RecyclerView.Adapter<ChatAdapter.ChatViewHolder>()
{
    class ChatViewHolder(val v: View) : RecyclerView.ViewHolder(v)
    {
        val chatText:TextView = v.findViewById(R.id.chat_text) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.chat_text_view, parent, false) as View

        return ChatViewHolder(v)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.chatText.text = mDataAsset[position]
    }

    override fun getItemCount() = mDataAsset.size
}