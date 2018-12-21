package com.protean.legislativetracker.zidane.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArgumentValidator {

    public static void validateArgument(boolean argumentIsIllegal, String errorMessage, Logger log) {
        if (argumentIsIllegal) {
            log.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
    }

}
