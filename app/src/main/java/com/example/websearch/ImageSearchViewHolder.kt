import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.websearch.R

class ImageSearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val imageView: ImageView = itemView.findViewById(R.id.imageView)
    private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)

    //setup databinding
    fun bind(searchResult: com.example.websearch.Value) {
        Glide.with(itemView.context).load(searchResult.image.url).into(imageView)
        nameTextView.text = searchResult.title
        Log.d("works", searchResult.image.url)
        //platformTextView.text = searchResult.locations.joinToString { it.display_name }

        itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(searchResult.url))
            itemView.context.startActivity(intent)
        }

    }
}