package backskin.bankapi.validators;

public interface Validator<ObjectType, TagType> {
    boolean validateObject(ObjectType object, TagType tag);
    TagType extractTag(ObjectType objectType);
    String tagName();
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
