package com.scs.dscatalog.resources;

import com.scs.dscatalog.dto.CategoryDTO;
import com.scs.dscatalog.entities.Category;
import com.scs.dscatalog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/categories") //usualmente a rota do recurso é colocada no plural
public class CategoryResource {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll() {
    List<CategoryDTO> list = categoryService.findAll();
    return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable Long id) {
        CategoryDTO dto = categoryService.findById(id);
        return ResponseEntity.ok().body(dto);
    }


    @PostMapping(value = "/save")
    public CategoryDTO save(@RequestBody Category category) {
        return categoryService.addNewCategory(category);
    }

}
