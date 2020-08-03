package com.leadiro.starter.service.name;

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
        LastNameData lastNameData = new LastNameData();

        // TODO This is being loaded repeatedly. Refactor.
        lastNameLookUp = lastNameData.fetchData();

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

    /**
     * Process first name input.
     * @param name Input to process
     * @return processed input
     */
    public NameProcessDto processFirstName(final NameProcessDto name) {
        String processing;
        NameProcessDto result = name;
        List<String> firstNameLookUp;
        processing = result.getProcessing();
        Boolean firstNameFound = false;
        FirstNameData firstNameData = new FirstNameData();

        // TODO This is being loaded repeatedly. Refactor.
        firstNameLookUp = firstNameData.fetchData();

        // TODO Permutation of input is needed here,
        //  a last name may contain a combination of more than 1
        //  and process longest first.
        String[] split = processing.split(" ");
        for (String s : split) {
            if (firstNameLookUp.contains(s.toLowerCase())) {
                if (firstNameFound) {
                    // need to erase, input has multiple last names,
                    // so now we are not sure
                    result.getName().setFirstName(null);
                } else {
                    firstNameFound = true;
                    if (null == result.getName()) {
                        NameDto nameDto = new NameDto();
                        nameDto.setFirstName(s);
                        result.setName(nameDto);
                    } else {
                        result.getName().setFirstName(s);
                    }
                }
            }
        }
        result.setProcessing(result.getProcessing().replace(
                result.getName().getFirstName(), ""));
        return result;
    }

    // TODO other methods
    //  - clean non alpha numeric characters to help normalize input
    //  - remove consecutive dots
    //  - detect prefixes
    //  - in surname/lastname refer to each other
    //  - detect acronyms
    //  - check suffixes I II, Jr, Sr
    // TODO probably create also a abstract class for the flow itself
    // TODO consider also soundex

    public NameProcessDto processNoneNameCharacters(final NameProcessDto name) {
        NameProcessDto result = name;
        String processing = result.getProcessing();
        processing = processing.replaceAll("[^0-9a-zA-Z .']+", "").trim();
        result.setProcessing(processing);
        return result;
    }
}

