package com.example.farmerpro.domain.fridge_use_case

import com.example.farmerpro.domain.repository.FridgeRepository

class GetFridges(

    private val repo: FridgeRepository
) {
    operator fun invoke() = repo.getFridgesFromFirestore()
}