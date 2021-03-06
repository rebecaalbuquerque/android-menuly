package com.albuquerque.data

import com.albuquerque.data.dto.CategoryDTO
import com.albuquerque.data.entity.CategoryEntity
import com.albuquerque.data.dto.FoodDTO
import com.albuquerque.data.dto.RestaurantDTO
import com.albuquerque.data.entity.FoodEntity
import com.albuquerque.data.entity.MenuEntity
import com.albuquerque.data.ui.MenuUI
import com.albuquerque.data.ui.RestaurantUI


fun CategoryDTO.toEntity() = CategoryEntity(this.id, this.name)

fun FoodDTO.toEntity(categoryId: Long): FoodEntity {
    return FoodEntity(
        this.id,
        this.name,
        this.description,
        this.price,
        this.imageUrl,
        categoryId
    )
}

fun FoodEntity.toMenuUI(): MenuUI {
    return MenuUI(
        id = this.foodId,
        name = this.name,
        description = this.description ?: "Descrição indisponível",
        price = this.price,
        imageUrl = this.imageUrl,
        isSelected = this.isSelected
    )
}

fun MenuEntity.toListMenuUI(): List<MenuUI> {
    return this.food
        .map {
            MenuUI(
                id = it.foodId,
                name = it.name,
                description = it.description ?: "Descrição indisponível",
                price = it.price,
                imageUrl = it.imageUrl,
                isSelected = it.isSelected
            )
        }
        .toMutableList().apply {
            this.add(
                0,
                MenuUI(
                    id = this@toListMenuUI.category.categoryId,
                    name = this@toListMenuUI.category.name,
                    description = "",
                    price = 0.0,
                    imageUrl = "",
                    isHeader = true
                )
            )
        }
}

fun CategoryDTO.toListMenuUI(): List<MenuUI> {
    return this.items
        .map {
            MenuUI(
                it.id,
                it.name,
                it.description ?: "Descrição indisponível",
                it.price,
                it.imageUrl
            )
        }
        .toMutableList().apply {
            this.add(
                0,
                MenuUI(
                    this@toListMenuUI.id,
                    this@toListMenuUI.name,
                    "",
                    0.0,
                    "",
                    true
                )
            )
        }
}

fun RestaurantDTO.toUI(): RestaurantUI {
    return RestaurantUI(
        this.id,
        this.name ?: "-",
        this.deliveryFee ?: 0.0,
        this.minimumOrderPrice ?: 0.0
    )
}