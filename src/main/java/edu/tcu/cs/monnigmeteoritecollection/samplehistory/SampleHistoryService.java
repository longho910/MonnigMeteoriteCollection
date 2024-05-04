package edu.tcu.cs.monnigmeteoritecollection.samplehistory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import edu.tcu.cs.monnigmeteoritecollection.system.exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class SampleHistoryService {
    
    private final SampleHistoryRepository sampleHistoryRepository;

    public SampleHistoryService(SampleHistoryRepository sampleHistoryRepository) {
        this.sampleHistoryRepository = sampleHistoryRepository;
    }


    // C
    public SampleHistory save(SampleHistory newSampleHistory) {
        return this.sampleHistoryRepository.save(newSampleHistory);
    }

    // R
    public SampleHistory findById(String historyId) {
        return this.sampleHistoryRepository.findById(Long.valueOf(historyId))
            .orElseThrow(() -> new ObjectNotFoundException("sample history", String.valueOf(historyId)));
    }

    // R - show all histories associated with a single meteorite
    public List<SampleHistory> findAllHistoryForMeteorite(Long meteoriteId) {
        List<SampleHistory> historyList = this.sampleHistoryRepository.findAll();
        List<SampleHistory> truncatedList = new ArrayList<>();
        for (SampleHistory elem : historyList) {
            if (elem.getMeteoriteId().equals(meteoriteId)) {
                truncatedList.add(elem);
            }
        }
        return truncatedList;
    }

    // U - update a history with new history
    public SampleHistory update(String historyId, SampleHistory update) {
        SampleHistory oldHistory = this.sampleHistoryRepository.findById(Long.valueOf(historyId))
            .orElseThrow(() -> new ObjectNotFoundException("sample_history", historyId));
        oldHistory.setDate(update.getDate());
        oldHistory.setCategory(update.getCategory());
        oldHistory.setNotes(update.getNotes());
        
        return this.sampleHistoryRepository.save(oldHistory);
    }

    // D - UC-10 
    public void deleteSampleHistory(String historyId) {
        this.sampleHistoryRepository.findById(Long.valueOf(historyId))
            .orElseThrow(() -> new ObjectNotFoundException("SampleHistory", historyId));
        this.sampleHistoryRepository.deleteById(Long.valueOf(historyId));
    }
}
