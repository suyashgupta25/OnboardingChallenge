package de.appsfactory.crawlerservice.error.exception;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

import static de.appsfactory.crawlerservice.error.exception.ErrorMessageConvert.toMap;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Class clazz, String... searchParamsMap) {
        super(EntityNotFoundException.generateMessage(clazz.getSimpleName(), toMap(String.class, String.class, searchParamsMap)));
    }

    private static String generateMessage(String entity, Map<String, String> searchParams) {
        return StringUtils.capitalize(entity) +
                " was not found for parameters " +
                searchParams;
    }
}
