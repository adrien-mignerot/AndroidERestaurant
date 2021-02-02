package fr.isen.mignerot.androiderestaurant

import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.mignerot.androiderestaurant.databinding.BasketCellBinding
import fr.isen.mignerot.androiderestaurant.model.ItemBasket

private lateinit var binding: BasketCellBinding

class BasketListAdapter(private val data: MutableList<ItemBasket>, private val deleteItemListener: (ItemBasket) -> Unit) : RecyclerView.Adapter<BasketListAdapter.BasketHolder>() {
    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): BasketHolder {
        binding = BasketCellBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BasketHolder(binding)
    }

    override fun onBindViewHolder(holder: BasketHolder, position: Int) {
        val data : ItemBasket = data[position]
        holder.title.text = data.dish.title
        holder.tarif.text = data.dish.getFormattedPrice()
        holder.number.text = data.quantity.toString()
        val picture = data.dish.getFirstPicture()
        val picasso = Picasso.get()
        if (picture != null) {
            picasso
                    .load(picture)
                    .into(holder.image)
        } else {
            picasso
                    .load(R.drawable.not_found)
                    .into(holder.image)
        }
        holder.remove.setOnClickListener {
            deleteItem(position)
            deleteItemListener.invoke(data)
        }
    }

    private fun deleteItem(position: Int) {
        data.removeAt(position)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class BasketHolder(binding: BasketCellBinding): RecyclerView.ViewHolder(binding.root){
        val title = binding.dishName
        val tarif = binding.dishPrice
        val image = binding.dishPicture
        val number = binding.nbItemsBasket
        val remove = binding.removePicture
    }
}