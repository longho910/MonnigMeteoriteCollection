package edu.tcu.cs.monnigmeteoritecollection.curator.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import edu.tcu.cs.monnigmeteoritecollection.curator.Curator;
import edu.tcu.cs.monnigmeteoritecollection.curator.dto.CuratorDto;

@Component
public class CuratorToCuratorDtoConverter implements Converter<Curator, CuratorDto> {

    @SuppressWarnings("null")
    @Override
    public CuratorDto convert(Curator source) {
        // We are not setting password in DTO.
        final CuratorDto curatorDto = new CuratorDto(source.getId(),
                source.getUsername(),
                source.isEnabled(),
                source.getRoles());
        return curatorDto;
    }

}