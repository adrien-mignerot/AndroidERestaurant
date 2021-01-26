package fr.isen.mignerot.androiderestaurant

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.isen.mignerot.androiderestaurant.databinding.CategoryCellBinding

class CategoryListAdapter(private val categories: List<String>): RecyclerView.Adapter<CategoryListAdapter.CategoryHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryHolder {
        val itemBinding = CategoryCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CategoryListAdapter.CategoryHolder, position: Int) {
        holder.title.text = categories[position]
        /*holder.layout.setOnClickListener {
            categoriesClickListener.invoke(categories[position])
        }*/
    }

    override fun getItemCount(): Int = categories.size

    class CategoryHolder(binding: CategoryCellBinding) : RecyclerView.ViewHolder(binding.root) {
        val title = binding.dishName
        //val layout = binding.root
    }
}