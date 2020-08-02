package com.leadiro.starter.service.name;

import com.leadiro.starter.service.FetchDataService;
import com.leadiro.starter.service.name.dto.NameDto;
import com.leadiro.starter.service.name.dto.NameProcessDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

// TODO create a dynamic configurable name processing flow
@Service
@Slf4j
public class NameService {
    @Autowired
    private FetchDataService fetchDataService;
    /**
     * Process a list of names.
     * @param names List of names
     * @return Processed names;
     */
    public List<NameDto> processNames(final List<String> names) {
        List<NameDto> nameDtos = new ArrayList<>();
        for (String name : names) {
            NameDto nameDto;
            // TODO call a method here that cleanses the name
            NameProcessDto nameProcessDto = new NameProcessDto();
            nameProcessDto.setProcessing(name);

            nameDto = process(nameProcessDto);
            nameDtos.add(nameDto);
        }
        return nameDtos;
    }

    private NameDto process(NameProcessDto name) {
        NameProcessDto nameFlow = name;
        NameDto result = null;


        NameProcessing nameProcessingInstance = new NameProcessing();
        try {
            result = new NameDto();
            Method methodInstance;

            methodInstance = NameProcessing.class.
                    getMethod("processNameSpaces", NameProcessDto.class);
            nameFlow
                    = (NameProcessDto) methodInstance.
                    invoke(nameProcessingInstance, nameFlow);

            methodInstance = NameProcessing.class.
                    getMethod("processLastName", NameProcessDto.class);
            nameFlow
                    = (NameProcessDto) methodInstance.
                    invoke(nameProcessingInstance, nameFlow);

            result.setLastName(nameFlow.getName().getLastName());
            result.setFirstName(nameFlow.getName().getFirstName());
        } catch (Exception e) {

        }

        return result;
    }

}
