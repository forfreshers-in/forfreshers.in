package in.forFresher.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.forFresher.entity.Position;
import in.forFresher.repository.PositionRepository;

@Service
public class PositionService {

	
    private final PositionRepository positionRepository;

    @Autowired
    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }
	
	public List<String> getAllPositionNames(){
		return positionRepository.findAll().stream().map(Position::getName).toList();
	}
	
	public Set<Position> getOrCreatePositions(List<String> positionNames) {
        Set<Position> positions = new HashSet<>();
        for (String name : positionNames) {
            Position position = positionRepository.findByName(name);
            if (position == null) {
                position = new Position();
                position.setName(name);
                position = positionRepository.save(position);
            }
//            position.setJobs(null);
            positions.add(position);
        }
        return positions;
    } 
}
