package com.dextroxd.dogsgraphapi.mutators;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.dextroxd.dogsgraphapi.entity.Dog;
import com.dextroxd.dogsgraphapi.exception.BreedNotFoundException;
import com.dextroxd.dogsgraphapi.exception.DogNotFoundException;
import com.dextroxd.dogsgraphapi.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Mutation implements GraphQLMutationResolver {
    private DogRepository dogRepository;
    public Mutation(DogRepository dogRepository){
        this.dogRepository = dogRepository;
    }

    public boolean deleteDogBreed(String breed){
        boolean deleted = false;
        Iterable<Dog> dogs = dogRepository.findAll();
        for(Dog d:dogs){
            if(d.getBreed().equals(breed)){
                dogRepository.deleteById(d.getId());
                deleted = true;
            }
        }
        if(!deleted){
            throw new BreedNotFoundException("Breed not found",breed);
        }
        return deleted;
    }

    public Dog updateDogName(String newName,Long id){
        Optional<Dog> dog = dogRepository.findById(id);
        if(dog.isPresent()){
            Dog dogOriginal = dog.get();
            dogOriginal.setName(newName);
            dogRepository.save(dogOriginal);
            return dogOriginal;
        }
        else{
            throw new DogNotFoundException("Dog not found with id",id);
        }
    }
}
