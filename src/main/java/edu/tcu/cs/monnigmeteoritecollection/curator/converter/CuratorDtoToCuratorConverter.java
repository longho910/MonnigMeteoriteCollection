package edu.tcu.cs.monnigmeteoritecollection.curator.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import edu.tcu.cs.monnigmeteoritecollection.curator.Curator;
import edu.tcu.cs.monnigmeteoritecollection.curator.dto.CuratorDto;

@Component
public class CuratorDtoToCuratorConverter implements Converter<CuratorDto, Curator> {

    @SuppressWarnings("null")
    @Override
    public Curator convert(CuratorDto source) {
        Curator Curator = new Curator();
        Curator.setUsername(source.username());
        Curator.setEnabled(source.enabled());
        Curator.setRoles(source.roles());
        return Curator;
    }

}