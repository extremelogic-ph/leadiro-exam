package com.leadiro.starter.service.name;

import com.leadiro.starter.service.FetchDataService;
import com.leadiro.starter.service.name.dto.NameDto;
import com.leadiro.starter.service.name.dto.NameProcessDto;

import java.util.List;

public class NameProcessing {

    /**
     * Default constructor.
     */
    public NameProcessing() {

    }

    /**
     * Data lookup.
     * TODO This data fetching should be redesigned to make this class reusable
     */
    private FetchDataService fetchDataService = new FetchDataService();

    /**
     * Process spaces input.
     * @param name Input to process
     * @return processed input
     */
    public NameProcessDto processNameSpaces(final NameProcessDto name) {
        String processing;
        NameProcessDto result = name;
        processing = name.getProcessing();

        // remove redundant spaces
        processing = processing.replaceAll("[ ]+", " ").trim();
        result.setProcessing(processing);

        return result;
    }

    /**
     * Process last name input.
     * @param name Input to process
     * @return processed input
     */
    public NameProcessDto processLastName(final NameProcessDto name) {
        String processing;
        NameProcessDto result = name;
        List<String> lastNameLookUp;
        processing = result.getProcessing();
        Boolean lastNameFound = false;

        // TODO This is being loaded repeatedly. Refactor.
        lastNameLookUp = fetchDataService.fetchLastNames();

        // TODO Permutation of input is needed here,
        //  a last name may contain a combination of more than 1
        //  and process longest first.
        String[] split = processing.split(" ");
        for (String s : split) {
            if (lastNameLookUp.contains(s.toLowerCase())) {
                if (lastNameFound) {
                    // need to erase, input has multiple last names,
                    // so now we are not sure
                    result.getName().setLastName(null);
                } else {
                    lastNameFound = true;
                    if (null == result.getName()) {
                        NameDto nameDto = new NameDto();
                        nameDto.setLastName(s);
                        result.setName(nameDto);
                    } else {
                        result.getName().setLastName(s);
                    }
                }
            }
        }
        result.setProcessing(result.getProcessing().replace(
                result.getName().getLastName(), ""));
        return result;
    }
}
