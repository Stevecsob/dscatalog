package com.scs.dscatalog.repositories;

import com.scs.dscatalog.dto.CategoryDTO;
import com.scs.dscatalog.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
//entidade e o tipo do id





}
