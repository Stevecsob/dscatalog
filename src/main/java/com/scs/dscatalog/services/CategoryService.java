package com.scs.dscatalog.services;



import com.scs.dscatalog.dto.CategoryDTO;
import com.scs.dscatalog.entities.Category;
import com.scs.dscatalog.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true) //transação com banco de dados e readOlny nao trava o banco de dados para
    // operaçãos de apenas de leitura
    public List<CategoryDTO> findAll() {
        List<Category> list = categoryRepository.findAll();
        List<CategoryDTO> listDTO =  list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());

        return listDTO;
    }


//    public Optional<CategoryDTO> findById(Long id) {
//        Optional<Category> find = categoryRepository.findById(id);
//        Optional<CategoryDTO> findDTO = find.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
//        return findDTO;
//    }
}

