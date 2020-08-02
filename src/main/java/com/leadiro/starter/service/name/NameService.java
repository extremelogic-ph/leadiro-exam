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

@Service
@Slf4j
public class NameService {
    /**
     * Data needed.
     */
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
            NameProcessDto nameProcessDto = new NameProcessDto();
            nameProcessDto.setOriginalInput(name);
            nameProcessDto.setProcessing(name);

            nameDto = process(nameProcessDto);
            if (!(nameDto.getFirstName().isEmpty() && nameDto.getLastName().isEmpty())) {
                nameDtos.add(nameDto);
            }
        }
        return nameDtos;
    }

    private NameDto process(final NameProcessDto name) {
        NameProcessDto nameFlow = name;
        NameDto result = null;

        NameProcessing nameProcessingInstance = new NameProcessing();
            List<String> methods = fetchDataService.fetchMethodCalls();
        try {
            result = new NameDto();
            Method methodInstance;

            for (String method : methods) {
                methodInstance = NameProcessing.class.
                        getMethod(method, NameProcessDto.class);
                nameFlow
                        = (NameProcessDto) methodInstance.
                        invoke(nameProcessingInstance, nameFlow);
            }

            result.setLastName(nameFlow.getName().getLastName());
            result.setFirstName(nameFlow.getName().getFirstName());
        } catch (Exception e) {
        }

        return result;
    }
}
