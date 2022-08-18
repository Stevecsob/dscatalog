package com.scs.dscatalog.services;



import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scs.dscatalog.dto.CategoryDTO;
import com.scs.dscatalog.entities.Category;
import com.scs.dscatalog.repositories.CategoryRepository;
import com.scs.dscatalog.services.exceptions.DataBaseException;
import com.scs.dscatalog.services.exceptions.ResourceNotFoundException;


@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

//    @Transactional(readOnly = true) //transação com banco de dados e readOlny nao trava o banco de dados para
//    // operaçãos de apenas de leitura
//    public List<CategoryDTO> findAll() {
//        List<Category> list = categoryRepository.findAll();
//        List<CategoryDTO> listDTO =  list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
//
//        return listDTO;
//    }

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) {
        Optional<Category> obj = categoryRepository.findById(id);
        Category entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new CategoryDTO(entity);
    }

    @Transactional 
    public CategoryDTO addNewCategory(CategoryDTO dto) {
        Category entity = new Category();
        entity.setName(dto.getName());
        entity = categoryRepository.save(entity);
        return new CategoryDTO(entity);
    }
    
    @Transactional 
	public CategoryDTO update(Long id, CategoryDTO dto) {
    	try {
    	Category entity = categoryRepository.getReferenceById(id);
    	entity.setName(dto.getName());
    	entity = categoryRepository.save(entity);
    	return new CategoryDTO(entity);
    	}
    	catch(EntityNotFoundException e) {
    		throw new ResourceNotFoundException("Id not found " + id);
    	}
    }

	public void delete(Long id) {
		try {
		categoryRepository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new DataBaseException("Id not found " + id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataBaseException("Integrity violation");
		}
	}

  @Transactional(readOnly = true) 
  public Page<CategoryDTO> findAllPages(PageRequest pageRequest) {
      Page<Category> list = categoryRepository.findAll(pageRequest);
      Page<CategoryDTO> listDTO =  list.map(x -> new CategoryDTO(x));

      return listDTO;
  }
	


}

