package com.albuquerque.menuly.app.repository

import com.albuquerque.menuly.app.data.entity.CategoryEntity
import com.albuquerque.menuly.app.data.entity.FoodEntity

object FakeDataLocal {

    val cat1 = CategoryEntity(1, "BURGERS")
    val cat2 = CategoryEntity(2, "ALTERNATIVES")
    val cat3 = CategoryEntity(3, "SANDWICHES")
    val cat4 = CategoryEntity(4, "SALADS")

    val food1 = FoodEntity(1, "Caesar", "lorem ipsum", 12.0, null, cat4.categoryId)
    val food2 = FoodEntity(2, "Greek", "lorem ipsum", 12.0, null, cat4.categoryId)
    val food3 = FoodEntity(3, "Grilled Chicken", null, 12.0, null, cat3.categoryId)
    val food4 = FoodEntity(4, "Salmon Burger", "lorem ipsum", 12.0, null, cat2.categoryId)
    val food5 = FoodEntity(5, "The Classic", "lorem ipsum", 12.0, null, cat1.categoryId)

}

object FakeDataRemote {

    val responseMenu: String = """
        [
          {
            "id": 26576,
            "name": "BURGERS",
            "items": [
              {
                "id": 94298,
                "name": "The Classic",
                "price": 6.95,
                "image_url": "https://raw.githubusercontent.com/delivery-direto/sample-api/master/images/ilya-mashkov-_qxbJUr9RqI-unsplash.jpg"
              },
              {
                "id": 94301,
                "name": "The Spicy Pimento",
                "description": "onion rings + homemade pimento cheese + chipotle honey",
                "price": 11.95
              },
              {
                "id": 94345,
                "name": "The Saucey",
                "description": "portobello + grilled onions + swiss + new york burger sauce + lettuce + tomato",
                "price": 9.25
              },
              {
                "id": 94365,
                "name": "The Chicken N Cheeseburger",
                "description": "spicy chicken + cheeseburger + smoked bacon + chipotle honey + pickles",
                "price": 12.95,
                "image_url": "https://raw.githubusercontent.com/delivery-direto/sample-api/master/images/haseeb-jamil-J9lD6FS6_cs-unsplash.jpg"
              },
              {
                "id": 94357,
                "name": "The Bacon 1000",
                "description": "applewood smoked bacon + cheddar + 1000 island sauce + lettuce + tomato",
                "price": 9.25,
                "image_url": "https://raw.githubusercontent.com/delivery-direto/sample-api/master/images/jakub-kapusnak-4hgYULBzVEE-unsplash.jpg"
              },
              {
                "id": 94399,
                "name": "The BB-A",
                "description": "applewood smoked bacon + blue cheese + avocado + lettuce + tomato",
                "price": 9.95
              }
            ]
          },
          {
            "id": 26578,
            "name": "ALTERNATIVES",
            "items": [
              {
                "id": 12098,
                "name": "Turkey Burger",
                "description": "lettuce + tomato",
                "price": 10.25
              },
              {
                "id": 12198,
                "name": "Salmon Burger",
                "description": "sriracha aioli + pickled onions + cilantro + lettuce",
                "price": 12.5
              },
              {
                "id": 12187,
                "name": "Blackbean Burger - Vegan",
                "description": "avocado + carrots + lettuce + tomato + wholegrain bun",
                "price": 9.95,
                "image_url": "https://raw.githubusercontent.com/delivery-direto/sample-api/master/images/ruth-reyer-VA7WfV-OFsM-unsplash.jpg"
              }
            ]
          },
          {
            "id": 26578,
            "name": "SANDWICHES",
            "items": [
              {
                "id": 13132,
                "name": "Grilled Chicken",
                "price": 7.5
              },
              {
                "id": 13135,
                "name": "Cobb Chicken",
                "description": "applewood smoked bacon + blue cheese + avocado + lettuce + tomato",
                "price": 9.9
              },
              {
                "id": 12081,
                "name": "Spicy Crispy Chicken",
                "description": "buttermilk fried chicken + chipotle honey sauce + lettuce + tomato",
                "price": 11.25
              }
            ]
          },
          {
            "id": 26580,
            "name": "SALADS",
            "items": [
              {
                "id": 14146,
                "name": "Caesar",
                "description": "lettuce hearts + parmesan cheese + croutons",
                "price": 7.5,
                "image_url": "https://raw.githubusercontent.com/delivery-direto/sample-api/master/images/raphael-nogueira-63mHpYEyjCA-unsplash.jpg"
              },
              {
                "id": 14347,
                "name": "Greek",
                "description": "barrel feta + roasted peppers + olives",
                "price": 7.95
              },
              {
                "id": 14248,
                "name": "Grain Bowl",
                "description": "kale + baby greens + quinoa/farro + red peppers + avocado + dried cranberries + carrots",
                "price": 9.5
              },
              {
                "id": 14972,
                "name": "Cobb",
                "description": "applewood smoked bacon + blue cheese + avocado",
                "price": 9.5
              }
            ]
          },
          {
            "id": 26592,
            "name": "BEVERAGES",
            "items": [
              {
                "id": 16827,
                "name": "Coca Cola Freestyle",
                "price": 2.5
              },
              {
                "id": 16624,
                "name": "Spring Water",
                "price": 1.95
              },
              {
                "id": 16347,
                "name": "Wine",
                "price": 7
              }
            ]
          }
        ]
    """.trimIndent()

    val responseRestaurant: String = """
        {
          "id": 999,
          "name": "Super Taste Food",
          "delivery_fee": 5.99,
          "minimum_order_price": 50
        }
    """.trimIndent()

}