package datamodel;

import java.util.Map;

import constant.constants;

public class CharacterEntity extends baseEntity {

	public CharacterEntity() {
        super();
    }

    public CharacterEntity(String name, Map<String, String> additionalInfo, String description) {
        super(name, additionalInfo,description);
    }

    public CharacterEntity(String name, Map<String, String> additionalInfo, String description, String url) {
        super(name, additionalInfo, description);
        this.setRootURL(url);
    }

    @Override
    public String getType() {
        return constants.CHARACTER_ENTITY;
    }

	@Override
	public String toString() {
		return "CharacterEntity [id=" + id + ", name=" + name + ", description=" + description + ", additionalInfo="
				+ additionalInfo + ", relatedEntityIds=" + relatedEntityIds + ", rootURL=" + rootURL + "]";
	}

}
