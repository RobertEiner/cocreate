package com.cocreate.developer;

import com.cocreate.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service // we annotate like this so that we can inject this class into other classes with the @Autowired keyword
public class DeveloperService {

    private final DeveloperRepository developerRepository;
    private final DeveloperDTOMapper developerDTOMapper;

    // we use controller injection, because field injection is advised against
    @Autowired // tells spring that "hey we need a UserRepository here, can you inject one for us?"
    public DeveloperService(DeveloperRepository developerRepository, DeveloperDTOMapper developerDTOMapper) {
        this.developerRepository = developerRepository;
        this.developerDTOMapper = developerDTOMapper;
    }

    public Developer createDeveloper(Developer newDeveloper) {
        return developerRepository.save(newDeveloper);
    }

    public DeveloperDTO findDeveloperById(int id) {
        return developerRepository.findById(id)
                .map(developerDTOMapper)        // We are only working with a single value and don't need to use streams
                .orElseThrow(() -> new ResourceNotFoundException("There exists no user with ID: " + id));
    }

    // Get all developers
    public List<DeveloperDTO> findAllDevelopers() {
        return developerRepository.findAll()
                .stream()                      // Here the list that is returned by findAll() is converted to a stream. We use stream API because we are working with a collection of elements
                .map(developerDTOMapper)       // applies the apply() method to each element of the stream
                .collect(Collectors.toList()); // the collect method gathers the elements of the stream into a List
    }

    // Update an existing developer
    public DeveloperDTO updateDeveloper(int id, Developer updateInfo) {
        Optional<Developer> optDev = developerRepository.findById(id);
        if(optDev.isPresent()) {
            Developer existingDev = optDev.get();
            // update dev
            existingDev.setUserName(updateInfo.getUserName());
            existingDev.setEmailAddress(updateInfo.getEmailAddress());
            existingDev.setPreferredLanguages(updateInfo.getPreferredLanguage());
            developerRepository.save(existingDev);
            return optDev.map(developerDTOMapper).get();
        } else {
            throw new ResourceNotFoundException("There is no developer with ID " + id);
        }
    }

    // Delete a specific developer
    public void deleteDeveloperById(int id) {
        if(developerRepository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("There exists no user with ID: " + id);
        }
        developerRepository.deleteById(id);
    }

}
