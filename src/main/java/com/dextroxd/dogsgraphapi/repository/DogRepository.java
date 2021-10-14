package com.dextroxd.dogsgraphapi.repository;

import com.dextroxd.dogsgraphapi.entity.Dog;
import org.springframework.data.repository.CrudRepository;

public interface DogRepository extends CrudRepository<Dog,Long> {
}
