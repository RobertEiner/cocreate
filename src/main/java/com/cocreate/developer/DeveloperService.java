package com.cocreate.developer;

import com.cocreate.exceptions.ResourceNotFoundException;
import com.cocreate.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service // we annotate like this so that we can inject this class into other classes with the @Autowired keyword
public class DeveloperService {

    //@Autowired
    private final DeveloperRepository developerRepository;

    // we use controller injection, because field injection is advised against
    @Autowired // tells spring that "hey we need a UserRepository here, can you inject one for us?"
    public DeveloperService(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    public Developer createDeveloper(Developer newDeveloper) {
        return developerRepository.save(newDeveloper);
    }

    public Optional<Developer> findDeveloperById(int id) {
        if(developerRepository.findById(id).isEmpty()) {
            throw  new ResourceNotFoundException("There exists no user with ID: " + id);
        }
        return developerRepository.findById(id);
    }

    public List<Developer> findAllDevelopers() {
        return developerRepository.findAll();
    } // maybe sort in some order

    public void updateDeveloper(int id, Developer updateInfo) {
        Optional<Developer> developerToUpdate = findDeveloperById(id);
        if(developerToUpdate.isPresent()) {
            Developer existingDeveloper = developerToUpdate.get();
            existingDeveloper.setUserName(updateInfo.getUserName());
            existingDeveloper.setEmailAddress(updateInfo.getEmailAddress());
            existingDeveloper.setPreferredLanguages(updateInfo.getPreferredLanguage());
            developerRepository.save(existingDeveloper);
        } else {
            throw new ResourceNotFoundException("There exists no developer with that ID");
        }
    }

    public void deleteDeveloperById(int id) {
        if(developerRepository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("There exists no user with ID: " + id);
        }
        developerRepository.deleteById(id);
    }

}

