package com.devsuperior.demo.services;

import java.util.List;
import java.util.Optional;

import com.devsuperior.demo.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.devsuperior.demo.dto.DepartmentDTO;
import com.devsuperior.demo.entities.Department;
import com.devsuperior.demo.repositories.DepartmentRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository repository;

	@Transactional(readOnly = true)
	public List<DepartmentDTO> findAll() {
		List<Department> list = repository.findAll(Sort.by("name"));
		return list.stream().map(x -> new DepartmentDTO(x)).toList();
	}

	@Transactional(readOnly = true)
	public DepartmentDTO findById(Long id){
		Optional<Department> obj = repository.findById(id);
		Department entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new DepartmentDTO(entity);
	}
}
