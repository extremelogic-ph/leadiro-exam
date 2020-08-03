package com.leadiro.starter.service.name;

import com.leadiro.starter.service.FetchDataService;
import com.leadiro.starter.service.name.dto.NameDto;
import com.leadiro.starter.service.name.dto.NameProcessDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class NameService implements FetchDataService<String> {
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
            if (!(nameDto.getFirstName().isEmpty()
                    && nameDto.getLastName().isEmpty())) {
                nameDtos.add(nameDto);
            }
        }
        return nameDtos;
    }

    private NameDto process(final NameProcessDto name) {
        NameProcessDto nameFlow = name;
        NameDto result = null;

        NameProcessing nameProcessingInstance = new NameProcessing();
            List<String> methods = fetchData();

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

    /**
     * Retrieve method calls for cleansing flow.
     * @return List of method name to call
     */
    @Override
    public List<String> fetchData() {
        List<String> methodCall = new ArrayList<>();
        // TODO contents here are supposed to be in the database
        //  order of execution should also be considered
        methodCall.add("processNameSpaces");
        methodCall.add("processNoneNameCharacters");
        methodCall.add("processLastName");
        methodCall.add("processFirstName");
        return methodCall;
    }
}
