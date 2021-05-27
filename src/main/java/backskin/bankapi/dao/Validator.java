package backskin.bankapi.dao;

public interface Validator<ObjectType, TagType> {
    boolean validateObject(ObjectType object, TagType tag);
    TagType extractTag(ObjectType objectType);
    String tagName();
    default String validationSQLRule(TagType tag){
        return tagName() + " = " + tag.toString();
    };
}
