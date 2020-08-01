package com.leadiro.starter.service.name;

import com.leadiro.starter.service.name.dto.NameDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class NameService {
    /**
     * Process a list of names.
     * @param names List of names
     * @return Processed names;
     */
    public List<NameDto> processNames(final List<String> names) {
        List<NameDto> nameDtos = new ArrayList<>();
        for (String name : names) {
            // TODO call a method here that cleanses the name
            NameDto nameDto = new NameDto();
            nameDto.setFirstName("FN" + name);
            nameDto.setLastName("LN" + name);
            nameDtos.add(nameDto);
        }
        return nameDtos;
    }
}
