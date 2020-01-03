package rdc.move_test.propertyapi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import rdc.move_test.model.Property;

/*
Pojo for schema. Can be converted to data class in kotlin for cleaner code.
*/
public class PropertyMeta {


    @SerializedName("build")
    private String build;

    @SerializedName("schema")
    private String schema;


    @SerializedName("market")
    private boolean market;



    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }
    public boolean isMarket() {
        return market;
    }

    public void setMarket(boolean market) {
        this.market = market;
    }
}