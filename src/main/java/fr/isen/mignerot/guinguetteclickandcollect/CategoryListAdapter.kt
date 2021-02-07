package fr.isen.mignerot.guinguetteclickandcollect

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.mignerot.guinguetteclickandcollect.databinding.CategoryCellBinding
import fr.isen.mignerot.guinguetteclickandcollect.model.Dish

class CategoryListAdapter(private val categories: List<Dish>, private val categoriesClickListener: (Dish) -> Unit): RecyclerView.Adapter<CategoryListAdapter.CategoryHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val itemBinding = CategoryCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CategoryListAdapter.CategoryHolder, position: Int) {
        holder.title.text = categories[position].title
        holder.price.text = categories[position].getFormattedPrice()

        val picasso = Picasso.get()
        if (categories[position].getFirstPicture() != null) {
            picasso
                .load(categories[position].getFirstPicture())
                .placeholder(R.drawable.logo)
                .into(holder.picture)
        } else {
            picasso
                .load(R.drawable.not_found)
                .into(holder.picture)
        }
        holder.layout.setOnClickListener {
            categoriesClickListener.invoke(categories[position])
        }
    }

    override fun getItemCount(): Int = categories.size

    class CategoryHolder(binding: CategoryCellBinding) : RecyclerView.ViewHolder(binding.root) {
        val title = binding.dishName
        val price = binding.dishPrice
        val picture = binding.dishPicture
        val layout = binding.root
    }
}