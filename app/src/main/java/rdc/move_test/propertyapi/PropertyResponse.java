package rdc.move_test.propertyapi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import rdc.move_test.model.Property;

/*
Pojo for schema. Can be converted to data class in kotlin for cleaner code.
*/
public class PropertyResponse{

    @SerializedName("meta")
    private PropertyMeta propertyMeta;

    @SerializedName("properties")
    private List<Property> properties;

    public PropertyMeta getPropertyMeta() {
        return propertyMeta;
    }

    public void setPropertyMeta(PropertyMeta propertyMeta) {
        this.propertyMeta = propertyMeta;
    }

    public void setProperties(List<Property> properties){
        this.properties = properties;
    }

    public List<Property> getProperties(){
        return properties;
    }
}