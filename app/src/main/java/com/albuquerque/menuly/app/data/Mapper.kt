package com.albuquerque.menuly.app.data

import com.albuquerque.menuly.app.data.dto.CategoryDTO
import com.albuquerque.menuly.app.data.entity.CategoryEntity
import com.albuquerque.menuly.app.data.dto.FoodDTO
import com.albuquerque.menuly.app.data.entity.FoodEntity
import com.albuquerque.menuly.app.data.entity.MenuEntity
import com.albuquerque.menuly.app.data.ui.MenuUI


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

fun MenuEntity.toListMenuUI(): List<MenuUI> {
    return this.food
        .map {
            MenuUI(
                it.foodId,
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
                    this@toListMenuUI.category.categoryId,
                    this@toListMenuUI.category.name,
                    "",
                    0.0,
                    "",
                    true
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