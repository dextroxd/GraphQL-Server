package com.dextroxd.dogsgraphapi.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.dextroxd.dogsgraphapi.entity.Dog;
import com.dextroxd.dogsgraphapi.exception.DogNotFoundException;
import com.dextroxd.dogsgraphapi.repository.DogRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class QueryResolver implements GraphQLQueryResolver {
    DogRepository dogRepository;
    public QueryResolver(DogRepository dogRepository){
        this.dogRepository = dogRepository;
    }

    public List<Dog> findAllDogs(){
        return (List<Dog>) dogRepository.findAll();
    }

    public Dog findAllDogsById(Long id){
        Optional<Dog> optionalDog =  dogRepository.findById(id);
        if(optionalDog.isPresent()){
            return optionalDog.get();
        }
        else{
            throw new DogNotFoundException("DogNotFound",id);
        }
    }
}
