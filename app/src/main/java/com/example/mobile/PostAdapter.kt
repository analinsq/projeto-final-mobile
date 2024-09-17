import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile.Post
import com.example.mobile.databinding.ItemPostBinding


class PostAdapter(private val postList: List<Post>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    class PostViewHolder(val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = postList[position]
        holder.binding.tvNome.text = post.nome ?: "Nome indisponível"
        holder.binding.tvRaca.text = post.raca ?: "Raça não informada"
        holder.binding.tvCor.text = post.cor ?: "Cor não informada"
        holder.binding.tvDescricao.text = post.descricao ?: "Sem descrição"
    }

    override fun getItemCount(): Int {
        return postList.size
    }
}
