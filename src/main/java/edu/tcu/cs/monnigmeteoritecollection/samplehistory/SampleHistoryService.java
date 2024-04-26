package edu.tcu.cs.monnigmeteoritecollection.samplehistory;

import edu.tcu.cs.monnigmeteoritecollection.system.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SampleHistoryService {
    private final HistoryRepository historyRepository;

    public SampleHistoryService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public SampleHistory findById(Integer historyId) {
        return this.historyRepository.findById(historyId)
                .orElseThrow(() -> new ObjectNotFoundException("history", historyId));
    }
}
