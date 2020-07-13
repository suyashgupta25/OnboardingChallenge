package de.appsfactory.crawlerservice.error.exception;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

import static de.appsfactory.crawlerservice.error.exception.ErrorMessageConvert.toMap;

public class ComponentInitializationException extends RuntimeException {

    public ComponentInitializationException(Class clazz, String... searchParamsMap) {
        super(ComponentInitializationException.generateMessage(clazz.getSimpleName(), toMap(String.class, String.class, searchParamsMap)));
    }

    private static String generateMessage(String entity, Map<String, String> searchParams) {
        return StringUtils.capitalize(entity) +
                " initialization failed due to " +
                searchParams;
    }
}
