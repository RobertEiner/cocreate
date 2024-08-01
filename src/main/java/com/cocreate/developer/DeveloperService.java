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

    // Create a new developer
    public DeveloperDTO createDeveloper(Developer newDeveloper) {
        Developer dev = developerRepository.save(newDeveloper);
        return developerDTOMapper.mapToDTO(dev);
    }

    // Find a developer by it's ID
    public DeveloperDTO findDeveloperById(int id) {
        return developerRepository.findById(id)
                .map(developerDTOMapper::mapToDTO)        // We are only working with a single value and don't need to use streams
                .orElseThrow(() -> new ResourceNotFoundException("There exists no user with ID: " + id));
    }

    // Get all developers
    public List<DeveloperDTO> findAllDevelopers() {
        return developerRepository.findAll()
                .stream()                               // Here the list that is returned by findAll() is converted to a stream. We use stream API because we are working with a collection of elements
                .map(developerDTOMapper::mapToDTO)       // applies the mapToDTO method to each element
                .collect(Collectors.toList());          // the collect method gathers the elements of the stream into a List
    }

    // Update an existing developer
    public DeveloperDTO updateDeveloper(int id, Developer updateInfo) {
        Optional<Developer> optDev = developerRepository.findById(id);
        if(optDev.isPresent()) {
            Developer existingDev = optDev.get();
            // update dev
            if(updateInfo.getUserName() != null) existingDev.setUserName(updateInfo.getUserName());
            if(updateInfo.getEmailAddress() != null) existingDev.setEmailAddress(updateInfo.getEmailAddress());
            if(updateInfo.getPreferredLanguage() != null) existingDev.setPreferredLanguages(updateInfo.getPreferredLanguage());
            developerRepository.save(existingDev);
            return optDev.map(developerDTOMapper::mapToDTO).get();
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

