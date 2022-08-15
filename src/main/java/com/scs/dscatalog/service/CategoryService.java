package com.scs.dscatalog.service;



import com.scs.dscatalog.entities.Category;
import com.scs.dscatalog.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true) //transação com banco de dados e readOlny nao trava o banco de dados para
    // operaçãos de apenas de leitura
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

}

