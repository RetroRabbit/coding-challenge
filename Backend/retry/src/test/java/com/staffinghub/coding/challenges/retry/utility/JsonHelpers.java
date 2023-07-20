package com.staffinghub.coding.challenges.retry.utility;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonHelpers {

    /**
     * Converts a given object into a JSON string (serializes object)
     * @param obj {@link Object}
     * @return {@link String}
     */
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Converts a given JSON string into an object of the given class (de-serializes string)
     * @param jsonString {@link String} JSON string to de-serialize
     * @param var {@link Class<T>} The class of the object to convert the string into
     * @return {@link T} An object of the given type
     * @param <T>
     */
    public static <T> T asObject(final String jsonString, Class<T> var) {
        try {
            return new ObjectMapper().readValue(jsonString, var);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
