//package com.example.mobile
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//
//data class Pet(val imageResId: Int, val name: String, val breed: String, val age: String)
//
//class PetAdapter(private val petList: List<Pet>) : RecyclerView.Adapter<PetAdapter.PetViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pet, parent, false)
//        return PetViewHolder(view)
//    }
//
////    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
////        val pet = petList[position]
////        holder.petImageView.setImageResource(pet.imageResId)
////        holder.petNameTextView.text = pet.name
////        holder.petBreedTextView.text = pet.breed
////        holder.petAgeTextView.text = pet.age
////    }
//
//    override fun getItemCount(): Int {
//        return petList.size
//    }
//
////    class PetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
////        val petImageView: ImageView = itemView.findViewById(R.id.petImageView)
////        val petNameTextView: TextView = itemView.findViewById(R.id.petNameTextView)
////        val petBreedTextView: TextView = itemView.findViewById(R.id.petBreedTextView)
////        val petAgeTextView: TextView = itemView.findViewById(R.id.petAgeTextView)
////    }
//}