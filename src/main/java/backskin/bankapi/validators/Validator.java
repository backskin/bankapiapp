package backskin.bankapi.validators;

/**
 * The interface Validator.
 *
 * @param <ObjectType> the type parameter
 * @param <TagType>    the type parameter
 */
public interface Validator<ObjectType, TagType> {
    /**
     * Validate object boolean.
     *
     * @param object the object
     * @param tag    the tag
     * @return the boolean
     */
    boolean validateObject(ObjectType object, TagType tag);

    /**
     * Extract tag tag type.
     *
     * @param objectType the object type
     * @return the tag type
     */
    TagType extractTag(ObjectType objectType);

    /**
     * Tag name string.
     *
     * @return the string
     */
    String tagName();

    /**
     * Validation rule string.
     *
     * @param tag the tag
     * @return the string
     */
    default String validationRule(TagType tag){
        String tagValue;
        if (tag instanceof String) {
            tagValue = "'"+tag+"'";
        } else {
            tagValue = tag.toString();
        }
        return tagName() + " = " + tagValue;
    }
}
