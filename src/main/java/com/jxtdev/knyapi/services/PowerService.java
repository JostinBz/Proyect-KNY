package com.jxtdev.knyapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jxtdev.knyapi.entities.Power;
import com.jxtdev.knyapi.repositories.PowerRepository;

@Service
public class PowerService {
    @Autowired
    private PowerRepository powerRepository;

    public List<Power> getAllCharacters(){
        return powerRepository.findAll();
    }

    public Optional<Power> getCharacter(Long id){
        return powerRepository.findById(id);
    }

    public Power addCharacter(Power power){
        return powerRepository.save(power);
    }

    public List<Power> addListCharacters(List<Power> power){
        return powerRepository.saveAll(power);
    }

    // Método para actualizar un personaje
    public Power updateCharacter(Long id, Power updatedPower) {
        return powerRepository.findById(id).map(power -> {
            power.setName(updatedPower.getName());
            power.setDescription(updatedPower.getDescription());
            return powerRepository.save(power);
        }).orElseThrow(() -> new RuntimeException("Character not found with id " + id));
    }

    public void deleteCharacterById(Long id){
        powerRepository.deleteById(id);
    }

    public void deleteCharacter(Power power){
        powerRepository.delete(power);;
    }

    public Power findById(Long id) {
        return powerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Power with id " + id + " not found"));
    }

    public List<Power> findAllByIds(List<Long> ids) {
        return powerRepository.findAllById(ids);
    }
}
