package backskin.bankapi.dao;

public interface Validator<ObjectType, TagType> {
    boolean validateObject(ObjectType object, TagType tag);
    TagType extractTag(ObjectType objectType);
    String tagName();
    default String validationRule(TagType tag){
        String tagName;
        if (tag instanceof String) {
            tagName = "'"+tagName()+"'";
        } else {
            tagName = tagName();
        }
        return tagName + " = " + tag.toString();
    };
}
