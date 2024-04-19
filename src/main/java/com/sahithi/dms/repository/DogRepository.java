package com.sahithi.dms.repository;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sahithi.dms.Models.Dog;;

public interface DogRepository extends CrudRepository<Dog,Integer>
{
	List<Dog> findByName(String Name);

}
