package one.digitalinnovation.personapi.service;

import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.exception.CpfAlreadyExistsException;
import one.digitalinnovation.personapi.exception.PersonNotFoundException;
import one.digitalinnovation.personapi.mapper.PersonMapper;
import one.digitalinnovation.personapi.repository.PersonRepository;
import one.digitalinnovation.personapi.utils.PersonUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static one.digitalinnovation.personapi.utils.PersonUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;
    
    
    // ====================== [ Create | Update - Person  ] ======================
    
    @Test
    void testGivenPersonDTOThenReturnSavedPerson() throws CpfAlreadyExistsException {
        PersonDTO personDTO = createFakeDTO();
        Person expectedSavedPerson = createFakeEntity();

        when(personRepository.save(any(Person.class))).thenReturn(expectedSavedPerson);

        Person person = personService.createPerson(personDTO);

        assertEquals(person, expectedSavedPerson);
        assertEquals(person.getCpf(), expectedSavedPerson.getCpf());
        
    }

    // ====================== [ Find Person By Id ] ======================
    
    @Test
    void testGivenAValidIdThenAPersonShouldbeReurned() throws PersonNotFoundException {
    	Person person = createFakeEntity();
    	
    	when(this.personRepository.findById(1L))
    		.thenReturn(Optional.of(person));
    	
    	PersonDTO foundPerson = this.personService.findById(1L);
    	
    	assertEquals(person.getId(), foundPerson.getId());
    	assertEquals(person.getCpf(), foundPerson.getCpf());
    	
    }
    
    @Test
    void testGivenAInvalidIdThenAPersonShouldbeReurned() throws PersonNotFoundException {
    	
    	when(this.personRepository.findById(1L))
    		.thenReturn(Optional.empty());

    	assertThrows(PersonNotFoundException.class, () -> this.personService.findById(1L));

    }
    
    // ====================== [ Delete Person ] ======================
    
    @Test
    void testWhenDeleteIsCalledWithValidIdThenAPersonShouldBeDeleted() throws PersonNotFoundException {
    	
    	Person person = createFakeEntity();
    	
    	when(this.personRepository.findById(1L))
    		.thenReturn(Optional.of(person));
    	
    	doNothing().when(this.personRepository).deleteById(1L);
    	
    	this.personService.delete(1L);
    	
    	verify(this.personRepository, times(1)).deleteById(1L);
    	
    }
    
    @Test
    void testWhenDeleteIsCalledWithInvalidIdThenAnExceptionShouldBeThrown() throws PersonNotFoundException {
    	
    	when(this.personRepository.findById(1L))
    		.thenReturn(Optional.empty());
    	
    	
    	assertThrows(PersonNotFoundException.class, () ->
    		this.personService.delete(1L)
    	);
    	
    }
    
    
}
