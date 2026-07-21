package com.ashutosh.inventory.repository;

import com.ashutosh.inventory.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByCategoryName(String categoryName);

    boolean existsByCategoryName(String categoryName);

}

/*Why Optional<Category>?

Suppose the category doesn't exist.

Instead of returning null, Java returns:

Optional.empty();

This encourages safer code.

Later we'll write:

Category category = categoryRepository
        .findByCategoryName(name)
        .orElseThrow(() ->
                new ResourceNotFoundException("Category not found"));

Much cleaner than checking for null.
 */