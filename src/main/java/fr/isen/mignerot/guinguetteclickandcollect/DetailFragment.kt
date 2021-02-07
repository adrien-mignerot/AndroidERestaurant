package fr.isen.mignerot.guinguetteclickandcollect

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import fr.isen.mignerot.guinguetteclickandcollect.databinding.FragmentDishPictureBinding

private lateinit var binding: FragmentDishPictureBinding

class DetailFragment: Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ) : View? {
        binding = FragmentDishPictureBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString("URL")?.let {
            Picasso.get().load(it).placeholder(R.drawable.picloading).into(binding.fragmentPicture)
        }
    }

    companion object {
        fun newInstance(picture: String): DetailFragment {
            return DetailFragment().apply { arguments = Bundle().apply { putString("URL", picture) } }
        }
    }
}
