package veterinary.Adapters.Pet;

/**
 *
 * @author Felipe Lopera
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import veterinary.Adapters.Pet.Entity.PetEntity;
import veterinary.Adapters.Pet.Repository.PetRepository;
import veterinary.domain.models.Person;
import veterinary.Adapters.Person.Entity.PersonEntity;
import veterinary.domain.models.Pet;
import veterinary.domain.ports.PetPort;

@Service
public class PetAdapter implements PetPort{
    
    @Autowired
	private PetRepository petRepository;
	
    @Override
    public boolean ExistByPetId(Pet pet) {
        return petRepository.existsById(pet.getPetId());
    }

     @Override
    public Pet findByPetId(Pet pet) {
        PetEntity petEntity = petRepository.findById(pet.getPetId());
        return petAdapter(petEntity);
    }

    @Override
    public void savePet(Pet pet) {
        PetEntity petEntity = petAdapter(pet);
        petRepository.save(petEntity);
        pet.setPetId(petEntity.getPetId());
    }
    
    private Pet petAdapter(PetEntity petEntity) {
        if (petEntity == null) {
            return null;
        }
        return new Pet(
            petEntity.getPetId(),
            petEntity.getName(),
            new Person(
            petEntity.getOwner().getId(),
            petEntity.getOwner().getName(),
            petEntity.getOwner().getAge(),
            petEntity.getOwner().getRole()
            ),  
                
            petEntity.getAge(),
            petEntity.getSpecie(),
            petEntity.getRace(), 
            petEntity.getCharacteristics(),
            petEntity.getWeight()    
        );
    }
    
    private PetEntity petAdapter(Pet pet) {
        if (pet == null) {
            return null;
        }
        PetEntity petEntity = new PetEntity();
        
        petEntity.setPetId(pet.getPetId());
        petEntity.setPetName(pet.getName());
        petEntity.setPetAge(pet.getAge());
        petEntity.setSpecie(pet.getSpice());
        petEntity.setRace(pet.getRace());
        petEntity.setCharacteristics(pet.getCharacteristics());
        petEntity.setWeight(pet.getWeight());
        
        PersonEntity ownerEntity = new PersonEntity();
        ownerEntity.setId(pet.getId().getId());
        ownerEntity.setName(pet.getId().getName());
        ownerEntity.setAge(pet.getId().getAge());
        ownerEntity.setRole(pet.getId().getRole());
        
        petEntity.setOwner(ownerEntity);
        return petEntity;
    }
}
